package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.InputSplit
import org.apache.hadoop.mapreduce.JobContext
import org.apache.hadoop.mapreduce.TaskAttemptContext
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat

class Hdf5SongFileInputFormat : FileInputFormat<Text, Song>() {
    override fun isSplitable(context: JobContext?, filename: Path?) = false

    override fun createRecordReader(split: InputSplit, context: TaskAttemptContext) = Hdf5SongFileRecordReader()
}