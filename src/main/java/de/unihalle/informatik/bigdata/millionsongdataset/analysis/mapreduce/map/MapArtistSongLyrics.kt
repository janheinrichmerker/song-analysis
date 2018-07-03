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
        val lyrics = lyricsLookupTable[song.track.id]
        val genre = genreLookupTable[song.track.id]
        if (lyrics == null || genre == null) {
            return
        }
        lyrics.lyrics
                .forEach { (word, count) ->
                    println("Mapping (${genre.majorGenre}, $word) -> $count.")
                    context.write(TextPairWritable(Text(genre.majorGenre), Text(word)), IntWritable(count))
                }
    }
}