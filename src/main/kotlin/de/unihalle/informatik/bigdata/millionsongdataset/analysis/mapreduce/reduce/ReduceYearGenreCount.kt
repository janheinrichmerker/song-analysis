package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.countDistinct
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class ReduceYearGenreCount : Reducer<IntWritable, Text, Text, IntWritable>() {
    public override fun reduce(
            year: IntWritable,
            genres: Iterable<Text>,
            context: Context) {

        genres.map { it.toString() }
                .countDistinct()
                .forEach { genre, count ->
                    context.write(Text("$year\t$genre"), IntWritable(count))
                }
    }
}