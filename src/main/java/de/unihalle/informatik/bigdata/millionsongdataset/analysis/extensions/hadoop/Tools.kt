package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop

import org.apache.hadoop.util.Tool
import org.apache.hadoop.util.ToolRunner

fun Tool.runInCurrentContext(arguments: Array<String>) = ToolRunner.run(this, arguments)