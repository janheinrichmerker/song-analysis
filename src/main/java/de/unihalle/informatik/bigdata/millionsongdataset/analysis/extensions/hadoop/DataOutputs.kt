package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop

import java.io.DataOutput
import java.io.IOException


@Throws(IOException::class)
fun DataOutput.writeUTFArray(array: Array<String>) {
    writeInt(array.size)
    array.forEach {
        writeUTF(it)
    }
}

@Throws(IOException::class)
fun DataOutput.writeIntArray(array: IntArray) {
    writeInt(array.size)
    array.forEach {
        writeInt(it)
    }
}

@Throws(IOException::class)
fun DataOutput.writeDoubleArray(array: DoubleArray) {
    writeInt(array.size)
    array.forEach {
        writeDouble(it)
    }
}
