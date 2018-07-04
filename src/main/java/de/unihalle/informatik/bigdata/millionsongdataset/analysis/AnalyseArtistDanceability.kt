package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.IoTool
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map.MapArtistDanceability
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5.Hdf5SongFileInputFormat
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce.ReduceDoubleMean
import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.io.DoubleWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat

object AnalyseArtistDanceability : IoTool() {

    override fun onRun(arguments: Array<String>): Boolean {
        println("Analysing '$inputFileName'. (Outputs will be saved to '$outputFileName'.)")

        return jobOf(configuration) {
            jar = AnalyseArtistDanceability::class.containingJar
            inputPathName = inputFileName
            inputDirRecursively = true
            inputFormatKClass = Hdf5SongFileInputFormat::class
            mapperKClass = MapArtistDanceability::class
            reducerKClass = ReduceDoubleMean::class
            outputKeyKClass = Text::class
            outputValueKClass = DoubleWritable::class
            outputFormatKClass = TextOutputFormat::class
            outputPathName = outputFileName
            cacheFilePaths = listOf(hdf5LibraryPath)
        }.await(verbose = true)
    }
}