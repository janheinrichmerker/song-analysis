package de.unihalle.informatik.bigdata.millionsongdataset.analysis.debug

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5.openHdf5File
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5.songs

object PrintSongData {

    @JvmStatic
    fun main(args: Array<String>) {
        val filename = javaClass.getResource("data/songs/A/D/U/TRADUDB128F42A61F9.h5").path
        println("File: $filename")

        openHdf5File(filename) {
            val songs = songs
            println("Number of songs: ${songs.size}")
            if (songs.size > 1) {
                println("We'll just display song information for song #0.")
            }

            val song = songs.first()
            println(song)
        }
    }
}