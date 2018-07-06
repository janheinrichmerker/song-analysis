package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.wholefile

import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.BytesWritable
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapreduce.InputSplit
import org.apache.hadoop.mapreduce.JobContext
import org.apache.hadoop.mapreduce.TaskAttemptContext
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat

class WholeFileInputFormat : FileInputFormat<NullWritable, BytesWritable>() {
    override fun isSplitable(context: JobContext?, filename: Path?) = false

    override fun createRecordReader(split: InputSplit, context: TaskAttemptContext) = WholeFileRecordReader()
}