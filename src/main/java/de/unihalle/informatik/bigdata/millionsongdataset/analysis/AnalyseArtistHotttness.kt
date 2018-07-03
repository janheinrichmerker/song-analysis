package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.IoTool
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map.MapArtistSongHotttness
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5.Hdf5SongFileInputFormat
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce.ReduceDoubleMean
import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.io.DoubleWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import java.io.File

object AnalyseArtistHotttness : IoTool() {

    override fun onRun(arguments: Array<String>): Boolean {
        println("Analysing '$inputFileName'. (Outputs will be saved to '$outputFileName'.)")

        return jobOf(configuration) {
            jar = AnalyseArtistHotttness::class.containingJar
            inputPathName = this@AnalyseArtistHotttness.inputFileName
            inputDirRecursively = true
            inputFormatKClass = Hdf5SongFileInputFormat::class
            mapperKClass = MapArtistSongHotttness::class
            reducerKClass = ReduceDoubleMean::class
            outputKeyKClass = Text::class
            outputValueKClass = DoubleWritable::class
            outputFormatKClass = TextOutputFormat::class
            outputPathName = this@AnalyseArtistHotttness.outputFileName

            addCacheFile(File(hdf5LibraryPath).toURI())
        }.await(verbose = true)
    }
}