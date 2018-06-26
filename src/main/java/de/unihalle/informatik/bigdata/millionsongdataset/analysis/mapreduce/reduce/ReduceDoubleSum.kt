package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce

import org.apache.hadoop.io.DoubleWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class ReduceDoubleSum : Reducer<Text, DoubleWritable, Text, DoubleWritable>() {
    public override fun reduce(
            key: Text,
            values: Iterable<DoubleWritable>,
            context: Context) {
        var sum = 0.0
        for (value in values) {
            sum += value.get()
        }
        context.write(key, DoubleWritable(sum))
    }
}