package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.Hdf5Song
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.openHdf5Readonly
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.songsCount
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
        openHdf5Readonly(filename) {

            assertEquals("Hasn't loaded exactly 1 song.", 1, songsCount)

            val nullableSong = Hdf5Song(this@openHdf5Readonly, 0)
            assertNotNull("Couldn't load the first song.", nullableSong)

            val songString = nullableSong.toString()
            println(songString)

            assertNotNull("Couldn't print song.", songString)

            assertEquals("Doesn't have the title 'Squarebiz'.", "Squarebiz", nullableSong.title)

            assertEquals("Doesn't have the title 'Galactic'.", "Galactic", nullableSong.artist.name)
        }
    }
}
