package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.createFileAndDirectoryIfNotExists
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.loadResourceFile
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.IoTool
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.Heatmap
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.draw
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.ArtistLocationLookupTable

object DrawArtistValuesLocationHeatmap : IoTool() {

    override fun onRun(arguments: Array<String>): Boolean {
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
                loadResourceFile("world-map-equirectangular-projection-1000.png"),
                outputFile.createFileAndDirectoryIfNotExists(),
                1 / 20.0)

        return true
    }
}