package de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap

class Heatmap(
        val width: Int,
        val height: Int,
        override val entries: Set<Entry>
) : AbstractMap<Pair<Double, Double>, Double>() {

    fun scale(newWidth: Int, newHeight: Int): Heatmap {
        val scaleX = newWidth.toDouble() / width
        println("scaleX: $scaleX")
        val scaleY = newHeight.toDouble() / height
        println("scaleY: $scaleY")
        return Heatmap(
                newWidth,
                newHeight,
                entries.map {
                    Entry(it.x * scaleX, it.y * scaleY, it.value)
                }.toSet())
    }

    data class Entry(
            val x: Double,
            val y: Double,
            override val value: Double
    ) : Map.Entry<Pair<Double, Double>, Double> {
        override val key: Pair<Double, Double> = x to y
    }
}