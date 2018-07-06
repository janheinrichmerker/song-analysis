package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.io.DoubleWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MapArtistFamiliarity : Mapper<Text, Song, Text, DoubleWritable>() {
    override fun map(
            key: Text,
            song: Song,
            context: Context) {
        val id = song.artist.id
        val familiarity = song.artist.familiarity.takeIf { it > 0.0 } ?: return

        println("Mapping $id -> $familiarity.")
        context.write(Text(id), DoubleWritable(familiarity))
    }
}