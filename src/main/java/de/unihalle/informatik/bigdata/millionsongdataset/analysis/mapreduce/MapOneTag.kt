package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MapOneTag : Mapper<Text, Song, Text, IntWritable>() {
    public override fun map(
            key: Text,
            song: Song,
            context: Mapper<Text, Song, Text, IntWritable>.Context) {
        song.artist.mbTags.forEach {
            context.write(Text(it.tag), IntWritable(it.count))
        }
    }
}