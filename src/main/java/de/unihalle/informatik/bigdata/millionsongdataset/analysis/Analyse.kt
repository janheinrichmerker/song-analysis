package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map.MapOneTag
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5.Hdf5SongFileInputFormat
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce.ReduceWordCount
import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import java.net.URI

object Analyse {

    @JvmStatic
    fun main(args: Array<String>) {

        val inputPath = args[0]
        val outputPath = args[1]
        val hdf5LibraryPath = args[2]

        println("Analysing '$inputPath'. (Outputs will be saved to '$outputPath'.)")

        val job = jobOf {
            jar = Analyse::class.containingJar
            inputPathName = inputPath
            inputDirRecursively = true
            inputFormatKClass = Hdf5SongFileInputFormat::class
            mapperKClass = MapOneTag::class
            reducerKClass = ReduceWordCount::class
            outputKeyKClass = Text::class
            outputValueKClass = IntWritable::class
            outputFormatKClass = TextOutputFormat::class
            outputPathName = outputPath

            System.setProperty("hdf.hdf5lib.H5.hdf5lib", hdf5LibraryPath)
            configuration["hdf.hdf5lib.H5.hdf5lib"] = hdf5LibraryPath

            addCacheFile(URI(hdf5LibraryPath))
        }
        println(job.inputFormatClass)


        job.await(verbose = true)


    }
}