package de.unihalle.informatik.bigdata.millionsongdataset.analysis.debug

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.Hdf5Song
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.openHdf5Readonly
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.songsCount

object PrintSongData {

    @JvmStatic
    fun main(args: Array<String>) {
        val filename = javaClass.getResource("data/songs/A/D/U/TRADUDB128F42A61F9.h5").path
        println("File: $filename")

        openHdf5Readonly(filename) {
            println("Number of songs: $songsCount")
            if (songsCount > 1) {
                println("We'll just display song information for song #0.")
            }

            val song = Hdf5Song(this, 0)
            println(song)
        }
    }
}