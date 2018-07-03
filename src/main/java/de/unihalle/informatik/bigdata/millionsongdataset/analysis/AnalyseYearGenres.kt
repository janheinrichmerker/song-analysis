package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.IoTool
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map.MapYearGenre
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5.Hdf5SongFileInputFormat
import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import java.io.File

object AnalyseYearGenres : IoTool() {

    override fun onRun(arguments: Array<String>): Boolean {
        println("Analysing '$inputFileName'. (Outputs will be saved to '$outputFileName'.)")

        return jobOf(configuration) {
            jar = AnalyseGenreWordCount::class.containingJar
            inputPathName = inputFileName
            inputDirRecursively = true
            inputFormat = Hdf5SongFileInputFormat::class
            mapper = MapYearGenre::class
            outputKey = IntWritable::class
            outputValue = Text::class
            outputFormat = TextOutputFormat::class
            outputPathName = outputFileName

            addCacheFile(File(hdf5LibraryPath).toURI())
        }.await(verbose = true)
    }
}