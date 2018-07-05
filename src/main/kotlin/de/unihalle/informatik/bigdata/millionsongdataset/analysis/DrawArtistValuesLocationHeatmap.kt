package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.createFileAndDirectoryIfNotExists
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.loadResourceFile
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.Heatmap
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.draw
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.ArtistLocationLookupTable
import java.io.File

object DrawArtistValuesLocationHeatmap {

    @JvmStatic
    fun main(arguments: Array<String>) {
        if (arguments.size < 2 ||
                arguments[0].isEmpty() ||
                arguments[1].isEmpty()) {
            throw IllegalArgumentException("Missing analysis input and/or output directory arguments.")
        }

        val inputFile = File(arguments[0])
        val outputFile = File(arguments[1])


        val artistValues =
                inputFile.readLines()
                        .map { line -> line.split(",", "\t").map { it.trim() } }
                        .dropWhile { columns -> columns[1].toDoubleOrNull() == null }
                        .filter { columns -> columns.size == 2 }
                        .map { columns -> columns[0] to columns[1].toDouble() }
                        .toMap()

        val artistLocationLookup = ArtistLocationLookupTable()

        val heatmapEntries = artistValues
                .mapNotNullTo(mutableSetOf()) { (artistId, value) ->
                    artistLocationLookup[artistId]
                            ?.let { entry ->
                                Heatmap.Entry(entry.longitude + 180, -entry.latitude + 90, value)
                            }
                }
        val heatmap = Heatmap(360, 180, heatmapEntries)
        println("Found " + heatmap.entries.size + " artists with known location.")

        heatmap.draw(
                loadResourceFile("world-map-equirectangular-projection-3000.png"),
                outputFile.createFileAndDirectoryIfNotExists(),
                1 / 100.0)
    }
}