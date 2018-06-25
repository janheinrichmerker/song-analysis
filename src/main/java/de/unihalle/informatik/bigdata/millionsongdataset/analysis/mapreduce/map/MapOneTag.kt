package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MapOneTag : Mapper<Text, Song, Text, IntWritable>() {
    override fun map(
            key: Text,
            song: Song,
            context: Context) {
        song.artist.mbTags.forEach {
            println("Mapping ${it.tag} -> ${it.count}.")
            context.write(Text(it.tag), IntWritable(it.count))
        }
    }
}