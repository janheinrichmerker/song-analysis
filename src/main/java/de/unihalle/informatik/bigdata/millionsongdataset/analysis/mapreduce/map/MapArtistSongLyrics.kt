package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.TextPairWritable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.LyricsLookup
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MapArtistSongLyrics : Mapper<Text, Song, TextPairWritable, IntWritable>() {
    lateinit var lyricsLookup: LyricsLookup

    override fun setup(context: Context?) {
        lyricsLookup = LyricsLookup(LyricsLookup.Dataset.TRAIN)
    }

    override fun map(
            key: Text,
            song: Song,
            context: Context) {
        val lyrics = lyricsLookup[song.track.id]
        lyrics?.lyrics?.forEach { (word, count) ->
            println("Mapping (${song.artist.id}, $word) -> $count.")
            context.write(TextPairWritable(Text(song.artist.id), Text(word)), IntWritable(count))
        }
    }
}