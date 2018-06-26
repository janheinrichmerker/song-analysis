package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5.openHdf5File
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5.songs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

object TestSongData {

    @JvmStatic
    fun main(arguments: Array<String>) {
        testSongFileReader()
    }

    @Test
    fun testSongFileReader() {
        val file = javaClass.getResource("TRADUDB128F42A61F9.h5")
        assertNotNull("Couldn't load resource file.", file)

        val filename = file.path
        openHdf5File(filename) {

            val songs = songs
            assertEquals("Hasn't loaded exactly 1 song.", 1, songs.size)

            val song = songs.first()

            val songString = song.toString()
            assertNotNull("Couldn't print song.", songString)
            println(songString)

            assertEquals("Doesn't have the title 'Squarebiz'.", "Squarebiz", song.title)
            assertEquals("Doesn't have the title 'Galactic'.", "Galactic", song.artist.name)
        }
    }
}
