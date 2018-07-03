package de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.map

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup.GenreLookupTable
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MapYearGenre : Mapper<Text, Song, IntWritable, Text>() {
    lateinit var genreLookupTable: GenreLookupTable

    override fun setup(context: Context?) {
        genreLookupTable = GenreLookupTable()
    }

    override fun map(
            key: Text,
            song: Song,
            context: Context) {
        val year = song.year.takeUnless { it <= 0 }
        if (year == null) {
            println("Skipping song as it has no year associated with it.")
            return
        }
        val genre = genreLookupTable[song.track.id]?.majorGenre ?: return
        println("Mapping $year -> $genre.")
        context.write(IntWritable(year), Text(genre))
    }
}