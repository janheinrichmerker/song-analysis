package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5

import hdf.`object`.h5.H5ScalarDS
import hdf.hdf5lib.exceptions.HDF5Exception

fun <DataType> H5ScalarDS.dataOrNull(): DataType? {
    return try {
        @Suppress("UNCHECKED_CAST")
        data as DataType
    } catch (e: HDF5Exception) {
        val message = e.message.orEmpty()
        when {
            "empty" in message -> logIfNeeded("Selected HDF5 (sub)set is empty.")
            else -> {
                println("Couldn't read HDF5 (sub)set.")
                e.printStackTrace()
            }
        }
        null
    }
}

private fun logIfNeeded(message: String) {
    // println(message)
}