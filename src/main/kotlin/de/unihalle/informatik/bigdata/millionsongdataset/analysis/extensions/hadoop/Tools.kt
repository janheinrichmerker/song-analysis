package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop

import de.unihalle.informatik.bigdata.songs.extensions.containingJar
import org.apache.hadoop.util.Tool
import org.apache.hadoop.util.ToolRunner

fun Tool.runInCurrentContext(arguments: Array<String>) = ToolRunner.run(this, arguments)

@Suppress("UNUSED")
inline val <reified T : Tool> T.containingJar: String?
    get() = T::class.containingJar