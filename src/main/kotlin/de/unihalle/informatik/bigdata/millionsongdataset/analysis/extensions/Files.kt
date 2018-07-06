package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions

import java.io.File

fun File.createFileAndDirectoryIfNotExists(): File {
    parentFile.mkdirs()
    if (!exists()) {
        createNewFile()
    }
    return this
}