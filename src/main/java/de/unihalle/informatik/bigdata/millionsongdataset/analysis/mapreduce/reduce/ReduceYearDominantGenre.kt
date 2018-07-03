package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.getDominantElementOrNull
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class ReduceYearDominantGenre : Reducer<IntWritable, Text, IntWritable, Text>() {
    public override fun reduce(
            key: IntWritable,
            values: Iterable<Text>,
            context: Context) {
        val genres = values.map { it.toString() }
        val dominantGenre = genres.getDominantElementOrNull() ?: return
        context.write(key, Text(dominantGenre))
    }
}