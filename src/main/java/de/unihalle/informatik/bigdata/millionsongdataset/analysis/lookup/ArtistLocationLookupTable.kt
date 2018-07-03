package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

class ArtistLocationLookupTable(
        lookupFilePath: String = defaultLookupTablePath
) : LookupTable<String, ArtistLocationLookupTable.Entry>(lookupFilePath) {

    override fun parseEntries(): Set<Entry> {
        return lookupFile
                .readLines()
                .mapTo(mutableSetOf()) { line ->
                    val field = line.split(fieldDelimiter)
                    Entry(
                            artistId = field[0],
                            latitude = field[1].toDouble(),
                            longitude = field[2].toDouble(),
                            trackId = field[3],
                            artistName = field[4]
                    )
                }
    }

    companion object {
        const val fieldDelimiter = "<SEP>"

        const val defaultLookupTablePath = "data/additional-files/subset_artist_location.txt"
    }

    data class Entry(
            val artistId: String,
            val latitude: Double,
            val longitude: Double,
            val trackId: String,
            val artistName: String
    ) : LookupTable.Entry<String, Entry>() {
        override val key = artistId
    }
}