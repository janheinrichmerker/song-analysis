package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

import java.io.File

class ArtistLocationLookup(private val lookupFile: File) : AbstractMap<String, ArtistLocationLookup.Entry>() {

    constructor(lookupFilePath: String = defaultLookupTablePath) : this(File(lookupFilePath))

    override val entries: Set<Entry> = parseEntries()

    private fun parseEntries(): Set<Entry> {
        return lookupFile.readLines()
                .mapNotNull { line ->
                    line.split("<SEP>")
                            .takeIf { columns -> columns.size == 5 }
                            ?.let { columns ->
                                Entry(
                                        artistId = columns[0],
                                        latitude = columns[1].toDouble(),
                                        longitude = columns[2].toDouble(),
                                        trackId = columns[3],
                                        artistName = columns[4]
                                )
                            }
                }
                .toSet()
    }


    companion object {
        const val defaultLookupTablePath = "data/additional-files/subset_artist_location.txt"
    }

    data class Entry(
            val artistId: String,
            val latitude: Double,
            val longitude: Double,
            val trackId: String,
            val artistName: String
    ) : Map.Entry<String, Entry> {
        override val key: String = artistId
        override val value: Entry = this
    }
}