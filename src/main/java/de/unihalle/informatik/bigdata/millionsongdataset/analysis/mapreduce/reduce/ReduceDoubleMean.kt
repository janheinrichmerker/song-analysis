package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reduce

import org.apache.hadoop.io.DoubleWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer

class ReduceDoubleMean : Reducer<Text, DoubleWritable, Text, DoubleWritable>() {
    public override fun reduce(
            key: Text,
            values: Iterable<DoubleWritable>,
            context: Context) {
        var mean = 0.0
        for ((index, value) in values.withIndex()) {
            mean += (value.get() - mean) / (index + 1)
            println("Element ${index + 1} ($key): ${value.get()} -> Mean: $mean")
        }
        context.write(key, DoubleWritable(mean))
    }
}