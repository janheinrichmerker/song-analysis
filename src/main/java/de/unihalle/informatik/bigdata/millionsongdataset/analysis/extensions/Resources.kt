package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions

import java.io.File
import java.io.InputStream
import java.net.URL

fun Any.loadResource(name: String): URL {
    return javaClass.getResource(name)
}

fun Any.loadResourceFile(name: String): File {
    return File(javaClass.getResource(name).toURI())
}

fun Any.loadResourceStream(name: String): InputStream {
    return javaClass.getResourceAsStream(name)
}
