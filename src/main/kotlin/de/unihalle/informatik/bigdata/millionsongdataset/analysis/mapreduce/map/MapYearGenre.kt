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
        val year = song.year.takeUnless { it <= 0 } ?: return
        val (_, genre, minorGenre) = genreLookupTable[song.track.id] ?: return
        println("Mapping $year -> $genre.")
        context.write(IntWritable(year), Text(genre))
        if (minorGenre != null) {
            println("Mapping $year -> $minorGenre (minor).")
            context.write(IntWritable(year), Text(minorGenre))
        }
    }
}