package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5

import hdf.`object`.h5.H5CompoundDS
import hdf.`object`.h5.H5File

class Hdf5Songs internal constructor(private val file: H5File) : AbstractList<Hdf5Song>() {
    override val size: Int by lazy {
        try {
            val metadata = file.get("/metadata/songs") as H5CompoundDS
            metadata.run {
                init()
                height
            }
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }

    override operator fun get(index: Int): Hdf5Song {
        return Hdf5Song(file, index)
    }
}