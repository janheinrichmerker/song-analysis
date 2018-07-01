package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.ArtistLocationLookup
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.util.ToolRunner

object Analyse {

    @JvmStatic
    fun main(args: Array<String>) {

        val configuration = Configuration()
        val tool = AnalyseArtistDanceability()
        val status = ToolRunner.run(configuration, tool, args)

        val output = tool.output
        println(output.entries.size)
        println(output.entries.joinToString())

        val artistLocationLookup = ArtistLocationLookup()
        println(artistLocationLookup.entries.size)
        println(artistLocationLookup.entries.joinToString())

        System.exit(status)
    }
}