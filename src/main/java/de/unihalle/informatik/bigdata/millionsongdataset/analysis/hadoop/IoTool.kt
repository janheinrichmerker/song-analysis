package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.configuration
import org.apache.hadoop.conf.Configurable
import org.apache.hadoop.conf.Configured
import org.apache.hadoop.util.Tool
import java.io.File

abstract class IoTool : Tool, Configurable by Configured() {

    private lateinit var _inputFileName: String
    val inputFileName: String
        get() = _inputFileName
    val inputFile: File
        get() = File(inputFileName)

    private lateinit var _outputFileName: String
    val outputFileName: String
        get() = _outputFileName
    val outputFile: File
        get() = File(outputFileName)

    val hdf5LibraryPath: String by lazy { configuration["hdf.hdf5lib.H5.hdf5lib"] }

    final override fun run(arguments: Array<out String>?): Int {
        if (arguments == null) {
            throw IllegalArgumentException("Missing program arguments.")
        }
        if (arguments.size < 2 ||
                arguments[0].isEmpty() ||
                arguments[1].isEmpty()) {
            throw IllegalArgumentException("Missing analysis input and/or output directory arguments.")
        }

        // Save the HDF5 library path to the tool configuration
        System.setProperty("hdf.hdf5lib.H5.hdf5lib", hdf5LibraryPath)


        _inputFileName = arguments[0]
        _outputFileName = arguments[1]

        val success = onRun(arguments.drop(2).toTypedArray())
        return if (success) 0 else 1
    }

    abstract fun onRun(arguments: Array<String>): Boolean
}