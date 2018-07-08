package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.GenreLookupTable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.LyricsLookupTable
import java.io.File
import java.util.*

object SizeTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val trackIds = File("data/songs")
                .getLeafFiles()
                .map { it.nameWithoutExtension }
        println("Tracks:")
        println(trackIds.size)
        println()

        val lyricsLookupTable = LyricsLookupTable(LyricsLookupTable.trainLookupTablePath)
        val lyricsTrackIds = lyricsLookupTable.keys
        println("Known lyrics:")
        println(lyricsTrackIds.size)
        println("Tracks with known lyrics:")
        val knownLyricsTrackIds = trackIds.filter { it in lyricsTrackIds }
        println(knownLyricsTrackIds.size)
        println(knownLyricsTrackIds.joinToString(limit = 10))
        println("Random track with known lyrics:")
        val randomKnownLyricsTrackId = knownLyricsTrackIds.random()
        println(randomKnownLyricsTrackId)
        println(lyricsLookupTable[randomKnownLyricsTrackId])
        println()

        val genreLookupTable = GenreLookupTable()
        val genreTrackIds = genreLookupTable.keys
        println("Known genres:")
        println(genreTrackIds.size)
        println("Tracks with known genre:")
        val knownGenreTrackIds = trackIds.filter { it in genreTrackIds }
        println(knownGenreTrackIds.size)
        println(knownGenreTrackIds.joinToString(limit = 10))
        println("Random track with known genre:")
        val randomKnownGenreTrackId = knownGenreTrackIds.random()
        println(randomKnownGenreTrackId)
        println(genreLookupTable[randomKnownGenreTrackId])
        println()

        val genreLyricsTrackIds = lyricsTrackIds.filter { it in genreTrackIds }
        println("Known genres+lyrics:")
        println(genreLyricsTrackIds.size)
        println("Tracks with known genre+lyrics:")
        val knownGenreLyricsTrackIds = trackIds.filter { it in genreLyricsTrackIds }
        println(knownGenreLyricsTrackIds.size)
        println(knownGenreLyricsTrackIds.joinToString(limit = 10))
        println("Random track with known genre+lyrics:")
        val randomKnownGenreLyricsTrackId = knownGenreTrackIds.random()
        println(randomKnownGenreLyricsTrackId)
        println(genreLookupTable[randomKnownGenreLyricsTrackId])
        println(lyricsLookupTable[randomKnownGenreLyricsTrackId])
        println()
    }

    private val random = Random()
    private fun <T> List<T>.random(): T {
        return get(random.nextInt(size))
    }

    private fun File.getLeafFiles(): List<File> {
        return if (isDirectory) {
            listFiles().flatMap { it.getLeafFiles() }
        } else {
            listOf(this)
        }
    }
}