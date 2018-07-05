package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop

import org.apache.hadoop.io.Text

class TextPairWritable : PairWritable<Text, Text> {
    // No argument constructor is needed for hadoop to read fields
    @Suppress("UNUSED")
    internal constructor() : super() {
        first = Text()
        second = Text()
    }

    constructor(first: Text, second: Text) : super(first, second)
}