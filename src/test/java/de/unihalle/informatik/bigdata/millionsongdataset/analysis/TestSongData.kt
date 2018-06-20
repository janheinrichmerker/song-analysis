package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.Hdf5Song
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.openHdf5Readonly
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5.songsCount
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class TestSongData : Spek({

    val filename = javaClass.getResource("TRADUDB128F42A61F9.h5").path

    describe("a song file reader for file '$filename'") {

        openHdf5Readonly(filename) {

            it("should contain 1 song") {
                assertEquals(1, songsCount)
            }

            var nullableSong: Hdf5Song? = null
            it("should load the first song") {
                nullableSong = Hdf5Song(this@openHdf5Readonly, 0)
                assertNotNull(nullableSong)
            }

            val song = nullableSong!! // We know the song can't be null because of the previous test.

            it("should be printable") {
                val songString = song.toString()
                println(songString)

                assertNotNull(songString)
            }

            it("should have the title 'Squarebiz'") {
                assertEquals("Squarebiz", song.title)
            }

            it("should have the title 'Galactic'") {
                assertEquals("Galactic", song.artist.name)
            }

        }
    }
})
