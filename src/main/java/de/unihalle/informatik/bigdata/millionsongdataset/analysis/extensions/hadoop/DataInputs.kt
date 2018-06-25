package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop

import java.io.DataInput
import java.io.IOException

@Throws(IOException::class)
fun DataInput.readUTFArray(): Array<String> {
    return Array(readInt()) {
        readUTF()
    }
}

@Throws(IOException::class)
fun DataInput.readIntArray(): IntArray {
    return IntArray(readInt()) {
        readInt()
    }
}

@Throws(IOException::class)
fun DataInput.readDoubleArray(): DoubleArray {
    return DoubleArray(readInt()) {
        readDouble()
    }
}
