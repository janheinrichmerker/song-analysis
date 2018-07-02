package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop

import org.apache.hadoop.io.Text

class TextPairWritable
    : PairWritable<Text, Text> {

    constructor() : super()
    constructor(first: Text, second: Text) : super(first, second)

    override fun createFields() {
        first = Text()
        second = Text()
    }

    override fun compareTo(other: PairWritable<Text, Text>?): Int {
        return first.compareTo(other?.first)
                .takeIf { it != 0 }
                ?: second.compareTo(other?.second)
    }
}