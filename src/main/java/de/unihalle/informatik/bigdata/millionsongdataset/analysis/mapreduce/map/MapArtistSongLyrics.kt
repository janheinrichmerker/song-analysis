package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.TextPairWritable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.GenreLookupTable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.LyricsLookupTable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MapArtistSongLyrics : Mapper<Text, Song, TextPairWritable, IntWritable>() {
    lateinit var lyricsLookupTable: LyricsLookupTable
    lateinit var genreLookupTable: GenreLookupTable

    override fun setup(context: Context?) {
        lyricsLookupTable = LyricsLookupTable()
        genreLookupTable = GenreLookupTable()
    }

    override fun map(
            key: Text,
            song: Song,
            context: Context) {
        if (song.track.id !in lyricsLookupTable || song.track.id !in genreLookupTable) {
            println("No lyrics could be found for track ${song.track.id}.")
            return
        }
        val lyricsEntry = lyricsLookupTable[song.track.id]!!
        val lyrics = lyricsEntry.lyrics
        val genreEntry = genreLookupTable[song.track.id]!!
        val majorGenre = genreEntry.majorGenre
        val minorGenre = genreEntry.minorGenre
        lyrics.forEach { (word, count) ->
            println("Mapping ($majorGenre, $word) -> $count.")
            context.write(TextPairWritable(Text(majorGenre), Text(word)), IntWritable(count))

            if (minorGenre != null) {
                println("Mapping ($minorGenre, $word) -> $count.")
                context.write(TextPairWritable(Text(minorGenre), Text(word)), IntWritable(count))
            }
        }
    }
}