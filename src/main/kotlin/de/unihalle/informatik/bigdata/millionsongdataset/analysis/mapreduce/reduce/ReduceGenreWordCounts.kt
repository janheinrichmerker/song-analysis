package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.TextPairWritable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.map
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class ReduceGenreWordCounts : Reducer<TextPairWritable, IntWritable, Text, IntWritable>() {

    override fun reduce(
            key: TextPairWritable,
            values: Iterable<IntWritable>,
            context: Context) {
        var sum = 0
        for (value in values) {
            sum += value.get()
        }

        val (genre, word) = key.map { it.toString() }

        context.write(Text("$genre\t$word"), IntWritable(sum))
    }
}