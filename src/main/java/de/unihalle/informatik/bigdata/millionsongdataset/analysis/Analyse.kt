package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map.MapArtistHotttness
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5.Hdf5SongFileInputFormat
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce.ReduceDoubleMean
import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.conf.Configurable
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.conf.Configured
import org.apache.hadoop.io.DoubleWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import org.apache.hadoop.util.Tool
import org.apache.hadoop.util.ToolRunner
import java.io.File


class Analyse : Configurable by Configured(), Tool {

    override fun run(args: Array<String>?): Int {
        val configuration = conf

        if (args == null) {
            throw IllegalArgumentException("Missing program arguments.")
        }
        println(args.joinToString())

        val inputPath = args[0]
        val outputPath = args[1]

        val hdf5LibraryPath = configuration["hdf.hdf5lib.H5.hdf5lib"]
        System.setProperty("hdf.hdf5lib.H5.hdf5lib", hdf5LibraryPath)


        println("Analysing '$inputPath'. (Outputs will be saved to '$outputPath'.)")
        var success = true

        success = success && jobOf(configuration) {
            jar = Analyse::class.containingJar
            inputPathName = inputPath
            inputDirRecursively = true
            inputFormatKClass = Hdf5SongFileInputFormat::class
            mapperKClass = MapArtistHotttness::class
            reducerKClass = ReduceDoubleMean::class
            outputKeyKClass = Text::class
            outputValueKClass = DoubleWritable::class
            outputFormatKClass = TextOutputFormat::class
            outputPathName = outputPath

            addCacheFile(File(hdf5LibraryPath).toURI())
        }.await(verbose = true)

//        success = success && jobOf(configuration) {
//            jar = Analyse::class.containingJar
//            inputPathName = inputPath
//            inputDirRecursively = true
//            inputFormatKClass = Hdf5SongFileInputFormat::class
//            mapperKClass = MapOneTag::class
//            reducerKClass = ReduceWordCount::class
//            outputKeyKClass = Text::class
//            outputValueKClass = IntWritable::class
//            outputFormatKClass = TextOutputFormat::class
//            outputPathName = outputPath
//
//            addCacheFile(File(hdf5LibraryPath).toURI())
//        }.await(verbose = true)

        return if (success) 0 else 1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val configuration = Configuration()
            val tool = Analyse()
            val status = ToolRunner.run(configuration, tool, args)
            System.exit(status)
        }
    }
}