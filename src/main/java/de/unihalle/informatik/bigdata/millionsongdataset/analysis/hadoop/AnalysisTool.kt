package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop

import org.apache.hadoop.conf.Configurable
import org.apache.hadoop.conf.Configured
import org.apache.hadoop.util.Tool
import java.io.File

abstract class AnalysisTool : Tool, Configurable by Configured() {

    private lateinit var _inputPathName: String
    val inputPathName: String
        get() = _inputPathName
    private lateinit var _outputPathName: String
    val outputPathName: String
        get() = _outputPathName

    val outputTabSeparated: List<List<String>>
        get() {
            return File(outputPathName)
                    .listFiles { _, name -> name.startsWith("part-r-") }
                    .orEmpty()
                    .flatMap { file ->
                        file.readLines().map { it.split("\t") }
                    }
        }

    override fun run(arguments: Array<out String>?): Int {
        if (arguments == null) {
            throw IllegalArgumentException("Missing program arguments.")
        }
        if (arguments.size < 2 ||
                arguments.getOrNull(0).isNullOrEmpty() ||
                arguments.getOrNull(1).isNullOrEmpty()) {
            throw IllegalArgumentException("Missing analysis input and/or output directory arguments.")
        }

        _inputPathName = arguments[0]
        _outputPathName = arguments[1]
        println("Analysing '$inputPathName'. (Outputs will be saved to '$outputPathName'.)")

        val success = analyse(arguments.filterIndexed { index, _ -> index >= 2 }.toTypedArray())
        return if (success) 0 else 1
    }

    abstract fun analyse(arguments: Array<String>): Boolean
}