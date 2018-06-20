package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.Hdf5Song
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.songId
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.songsCount
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import hdf.`object`.h5.H5File
import hdf.hdf5lib.exceptions.HDF5Exception
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.InputSplit
import org.apache.hadoop.mapreduce.JobContext
import org.apache.hadoop.mapreduce.RecordReader
import org.apache.hadoop.mapreduce.TaskAttemptContext
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.input.FileSplit

class SongInputFormat : FileInputFormat<Text, Song>() {

    override fun createRecordReader(
            split: InputSplit,
            context: TaskAttemptContext) = SongRecordReader()

    override fun isSplitable(context: JobContext?, file: Path?) = false

    class SongRecordReader : RecordReader<Text, Song>() {
        private lateinit var file: H5File
        private var index = 0
        private var size: Int = 0

        override fun getCurrentValue(): Song {
            return Hdf5Song(file, index)
        }

        override fun nextKeyValue(): Boolean {
            return if (index < size - 1) {
                index++
                true
            } else {
                false
            }
        }

        override fun getCurrentKey() = Text(file.songId)

        override fun close() {
            try {
                file.close()
            } catch (hdF5Exception: HDF5Exception) {
                println("Could not close HDF5 file?")
                hdF5Exception.printStackTrace()
            } catch (ignore: Throwable) {
            }
        }

        override fun initialize(genericSplit: InputSplit, context: TaskAttemptContext) {
            val split = genericSplit as FileSplit
            val filePath = split.path
            file = H5File(filePath.name)
            size = file.songsCount
        }

        override fun getProgress(): Float {
            return index / size.toFloat()
        }

    }
}
