package de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.increment
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.util.ArgbEvaluator
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.util.Gaussian
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Creates a [BufferedImage], fills it with blank pixels.
 * Then proceeds to fill it with this [Heatmap].
 * If a coordinate does not exist in the heatmap, it stays blank.
 *
 * @receiver The heatmap data.
 * @param inputFile The background image file.
 * @param outputFile The file to save the heatmap to.
 * @return `false` if no appropriate writer is found, `true` otherwise.
 */
fun Heatmap.draw(
        inputFile: File,
        outputFile: File,
        smoothness: Double = 1 / 30.0): Boolean {
    val inputImage = ImageIO.read(inputFile)
    val heatmapImage = BufferedImage(inputImage.width, inputImage.height, BufferedImage.TYPE_INT_ARGB)
    heatmapImage.drawHeatmap(this, smoothness)

    val outputImage = BufferedImage(inputImage.width, inputImage.height, BufferedImage.TYPE_INT_ARGB)
    val graphics = outputImage.graphics!!
    graphics.drawImage(inputImage, 0, 0, null)
    graphics.drawImage(heatmapImage, 0, 0, null)

    return ImageIO.write(outputImage, outputFile.extension, outputFile)
}

/**
 * Draws the given [heatmap] on top of this [BufferedImage].
 * If a coordinate does not exist in the heatmap, the according pixel stays the same.
 *
 * @receiver The [BufferedImage] on which to draw the heatmap.
 * @param heatmap The heatmap data.
 * @param smoothness The smoothness/blurriness of the heatmap data.
 */
fun BufferedImage.drawHeatmap(
        heatmap: Heatmap, smoothness: Double = 1 / 30.0) {

    // Scale the heatmap to fit in this image.
    val scaledHeatmap = heatmap.scale(width, height)

    // Generate a 2D Gaussian kernel to smooth-out "hot spots".
    val averageSize = (heatmap.width + heatmap.height) / 2.0
    val averageScale = (width / heatmap.width.toDouble() + height / heatmap.height.toDouble()) / 2.0

    val gaussian = Gaussian(averageSize / averageScale * smoothness)
    val kernelSizeFactor = 10.0
    val kernelSize = (gaussian.sigma * kernelSizeFactor)
    val kernel = gaussian.generateKernel2D(kernelSize.nearestOdd)

    // Round all heatmap entry coordinates to their nearest integer position.
    val buckets = mutableMapOf<Pair<Int, Int>, Double>()
    scaledHeatmap.entries.forEach { entry ->
        val x = entry.x.roundToInt()
        val y = entry.y.roundToInt()
        kernel.forEach { (xOffset, yOffset), scale ->
            buckets.increment(x + xOffset to y + yOffset, entry.value * scale)
        }
    }

    // Determine the "brightest" and "darkest" values.
    val min = buckets.values.min() ?: 0.0
    val max = buckets.values.max() ?: 1.0
    val amplitude = max - min

    buckets.forEach { (coordinates, value) ->
        val (x, y) = coordinates

        // Map each value to its scaled value from 0 to 1.
        val scaledValue = (value - min) / amplitude
        val warmth = calculateWarmth(scaledValue)

        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            setRGB(x, y, warmth)
        }
    }
}

private val Double.nearestOdd: Int
    get() = 2 * (this / 2).toInt() + 1

/**
 * Calculates the "warmth" RGB color of a pixel.
 *
 * @param intensity A value between 0 and 1.
 * @return An [Int] color with the calculated warmth.
 */
private fun calculateWarmth(intensity: Double, intensityPower: Double = 1.0): Int {
    val startColor: Int = 0x000000ff
    val endColor: Int = 0xffff3300.toInt()
    val scaledIntensity = intensity.pow(intensityPower)

    return ArgbEvaluator.evaluate(scaledIntensity, startColor, endColor)
}
