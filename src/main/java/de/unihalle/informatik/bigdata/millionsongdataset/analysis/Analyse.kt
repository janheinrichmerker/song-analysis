package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.loadResourceFile
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.Heatmap
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.draw
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.ArtistLocationLookup
import java.io.File

object Analyse {

    @JvmStatic
    fun main(args: Array<String>) {

        /*
        val configuration = Configuration()
        val tool = AnalyseArtistHotttness()
        val status = ToolRunner.run(configuration, tool, args)

        val output = tool.output.filter { (_, value) -> value > 0.0 }
        */


        val output =
                File("results/artist-average-song-hotttness.csv")
                        .readLines()
                        .filterIndexed { index, _ -> index > 0 }
                        .map { line -> line.split(", ") }
                        .filter { columns -> columns.size == 2 }
                        .map { columns -> columns[0] to columns[1].toDouble() }
                        .toMap()







        println("Mapped ${output.entries.size} to their average song hotttness.")

        val artistLocationLookup = ArtistLocationLookup()

        val heatmapEntries = output
                .mapNotNull { (artistId, hotttness) ->
                    artistLocationLookup[artistId]
                            ?.let {
                                Heatmap.Entry(it.longitude + 180, -it.latitude + 90, hotttness)
                            }
                }
                .toSet()
        val heatmap = Heatmap(360, 180, heatmapEntries)
        println("Found " + heatmap.entries.size + " artists with known location.")

        heatmap.draw(
                loadResourceFile("world-map-equirectangular-projection-1000.png"),
                File(/*tool.outputPathName*/ "out/analytics", "map.png")
                        .also {
                            it.parentFile.mkdirs()
                            if (!it.exists()) it.createNewFile()
                        },
                1 / 20.0)

        // System.exit(status)
    }
}