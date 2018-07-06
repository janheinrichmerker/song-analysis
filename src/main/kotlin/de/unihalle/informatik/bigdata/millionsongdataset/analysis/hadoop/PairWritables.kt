package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop

import org.apache.hadoop.io.Text
import org.apache.hadoop.io.WritableComparable

class TextPairWritable : PairWritable<Text, Text> {
    // No argument constructor is needed for hadoop to read fields
    @Suppress("UNUSED")
    internal constructor() : super() {
        first = Text()
        second = Text()
    }

    constructor(first: Text, second: Text) : super(first, second)
}

fun <T : WritableComparable<in T>, R> PairWritable<T, T>.map(transform: (T) -> R) =
        Pair(transform(first), transform(second))

fun <A : WritableComparable<in A>, B : WritableComparable<in B>> PairWritable<A, B>.toPair() =
        Pair(first, second)

fun Pair<Text, Text>.toPairWritable() =
        TextPairWritable(first, second)