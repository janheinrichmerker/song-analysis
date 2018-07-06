/**
 * File containing utility methods to open a HDF5 song file and access its content.
 */
package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.Hdf5Song
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.Hdf5Songs
import hdf.`object`.h5.H5File
import hdf.hdf5lib.exceptions.HDF5Exception

typealias Hdf5File = H5File

/**
 * Opens an HDF5 file.
 */
fun openHdf5File(filename: String, mode: Int = Hdf5File.READ): Hdf5File {
    return Hdf5File(filename, mode)
}

/**
 * Opens an HDF5 file.
 * Closes the HDF5 file after [block] is executed.
 */
fun <ReturnType> openHdf5File(filename: String, mode: Int = Hdf5File.READ, block: Hdf5File.() -> ReturnType): ReturnType {
    val file = openHdf5File(filename, mode)

    var exception: Throwable? = null
    try {
        return file.block()
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when (exception) {
            null -> file.close()
            else -> file.closeSafely()
        }
    }
}

/**
 * Closes the HDF5 file.
 */
fun Hdf5File.closeSafely() {
    try {
        close()
    } catch (ex: HDF5Exception) {
        println("Could not close HDF5 file.")
        ex.printStackTrace()
    } catch (ignore: Throwable) {
    }
}

val Hdf5File.songs: List<Hdf5Song>
    get() = Hdf5Songs(this)
