package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop

import org.apache.hadoop.fs.Path
import java.io.File

fun Path.toFile(): File {
    return File(toUri())
}

val Path.absolutePath: String
    get() = toUri().rawPath.orEmpty()
