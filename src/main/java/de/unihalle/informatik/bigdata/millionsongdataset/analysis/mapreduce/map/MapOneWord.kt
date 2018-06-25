package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map

import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper
import java.util.*

class MapOneWord : Mapper<LongWritable, Text, Text, IntWritable>() {
    private val word = Text()

    public override fun map(
            key: LongWritable,
            value: Text,
            context: Context) {
        val line = value.toString()
        val tokenizer = StringTokenizer(line)
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken())
            context.write(word, one)
        }
    }

    companion object {
        private val one = IntWritable(1)
    }
}