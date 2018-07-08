package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.IoTool
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.GenreLookupTable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map.MapYearGenre
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5.Hdf5SongFileInputFormat
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce.ReduceYearGenreCount
import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat

object AnalyseYearGenres : IoTool() {

    override fun onRun(arguments: Array<String>): Boolean {
        println("Analysing '$inputFileName'. (Outputs will be saved to '$outputFileName'.)")

        return jobOf(configuration) {
            jar = AnalyseGenreWordCount::class.containingJar
            inputPathName = inputFileName
            inputDirRecursively = true
            inputFormat = Hdf5SongFileInputFormat::class
            mapper = MapYearGenre::class
            mapOutputKey = IntWritable::class
            mapOutputValue = Text::class
            reducer = ReduceYearGenreCount::class
            outputKey = Text::class
            outputValue = IntWritable::class
            outputFormat = TextOutputFormat::class
            outputPathName = outputFileName
            cacheFilePaths = listOf(hdf5LibraryPath, GenreLookupTable.cd1LookupTablePath)
        }.await(verbose = true)
    }
}