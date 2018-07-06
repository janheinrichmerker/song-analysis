package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.wholefile

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.BytesWritable
import org.apache.hadoop.io.IOUtils
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapreduce.InputSplit
import org.apache.hadoop.mapreduce.RecordReader
import org.apache.hadoop.mapreduce.TaskAttemptContext
import org.apache.hadoop.mapreduce.lib.input.FileSplit
import java.io.IOException

class WholeFileRecordReader : RecordReader<NullWritable, BytesWritable>() {

    private lateinit var fileSplit: FileSplit
    private lateinit var configuration: Configuration
    private var processed = false

    private val key = NullWritable.get()
    private val value = BytesWritable()

    override fun initialize(inputSplit: InputSplit, taskAttemptContext: TaskAttemptContext) {
        this.fileSplit = inputSplit as FileSplit
        this.configuration = taskAttemptContext.configuration
    }

    @Throws(IOException::class)
    override fun nextKeyValue(): Boolean {
        if (!processed) {
            val contents = ByteArray(fileSplit.length.toInt())

            val file = fileSplit.path
            val fileSystem = file.getFileSystem(configuration)


            fileSystem.open(file).use {
                IOUtils.readFully(it, contents, 0, contents.size)
                value.set(contents, 0, contents.size)
            }
            processed = true
            return true
        }
        return false
    }

    override fun getCurrentKey(): NullWritable = key

    override fun getCurrentValue() = value

    override fun getProgress() = if (processed) 1.0f else 0.0f

    override fun close() = Unit // Nothing to close.
}