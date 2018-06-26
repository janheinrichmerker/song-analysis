package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.io.DoubleWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MapArtistHotttness : Mapper<Text, Song, Text, DoubleWritable>() {
    override fun map(
            key: Text,
            song: Song,
            context: Context) {
        if (!song.hotttnesss.isNaN()) {
            println("Mapping ${song.artist.id} -> ${song.hotttnesss}.")
            context.write(Text(song.artist.id), DoubleWritable(song.hotttnesss))
        }
    }
}