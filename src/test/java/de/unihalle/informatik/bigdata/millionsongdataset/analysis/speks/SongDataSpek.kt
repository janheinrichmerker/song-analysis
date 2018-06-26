package de.unihalle.informatik.bigdata.millionsongdataset.analysis.speks

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5.openHdf5File
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5.songs
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

object SongDataSpek : Spek({

    describe("a song file reader") {

        val file = javaClass.getResource("TRADUDB128F42A61F9.h5")
        it("should load a resource file") {
            assertNotNull(file)
        }

        val filename = file.path
        describe(" for file '$filename'") {


            openHdf5File(filename) {
                val songs = songs

                it("should contain 1 song") {
                    assertEquals(1, songs.size)
                }

                val song = songs.first()
                it("should be printable") {
                    val songString = song.toString()
                    assertNotNull(songString)
                    println(songString)
                }

                it("should have the title 'Squarebiz'") {
                    assertEquals("Squarebiz", song.title)
                }

                it("should have the title 'Galactic'") {
                    assertEquals("Galactic", song.artist.name)
                }

            }
        }
    }
})
