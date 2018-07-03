package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.configuration
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.runInCurrentContext
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.SelectableTool
import org.apache.hadoop.conf.Configuration
import kotlin.system.exitProcess

object Analyse {

    @JvmStatic
    fun main(arguments: Array<String>) {

        val tool = SelectableTool {
            when (it) {
                "draw-artist-values-location-heatmap",
                "draw-artist-hotttness-location-heatmap",
                "draw-artist-danceability-location-heatmap" -> DrawArtistValuesLocationHeatmap
                "analyse-artist-hotttness" -> AnalyseArtistHotttness
                "analyse-artist-danceability" -> AnalyseArtistDanceability
                else -> AnalyseArtistHotttness
            }
        }
        tool.configuration = Configuration()

        val status = tool.runInCurrentContext(arguments)
        exitProcess(status)
    }
}