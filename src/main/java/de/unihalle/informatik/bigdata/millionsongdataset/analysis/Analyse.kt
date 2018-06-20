package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.MapOneWord
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.ReduceWordCount
import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat

object Analyse {

    @JvmStatic
    fun main(args: Array<String>) {

        val job = jobOf {
            jar = Analyse::class.containingJar
            outputKeyKClass = Text::class
            outputValueKClass = IntWritable::class
            mapperKClass = MapOneWord::class
            reducerKClass = ReduceWordCount::class
            inputFormatKClass = TextInputFormat::class
            outputFormatKClass = TextOutputFormat::class
            inputPathName = args[0]
            outputPathName = args[1]
        }
        job.await(verbose = true)


    }
}