package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

class ArtistLocationLookupTable(
        lookupFilePath: String = defaultLookupTablePath
) : AbstractFileLookupTable<String, ArtistLocationLookupTable.Entry>(lookupFilePath) {

    private fun <T> useEntries(block: (entries: Sequence<Entry>) -> T): T {
        return useLookupFileLines { lines ->
            val entries = lines
                    .map { line -> line.split(fieldDelimiter) }
                    .map { fields ->
                        Entry(
                                artistId = fields[0],
                                latitude = fields[1].toDouble(),
                                longitude = fields[2].toDouble(),
                                trackId = fields[3],
                                artistName = fields[4]
                        )
                    }
            block(entries)
        }
    }

    override val keys: Set<String> =
            useEntries { entries ->
                entries.map { it.artistId }.toSet()
            }

    override fun get(key: String): Entry? {
        return useEntries { entries ->
            entries.find { it.artistId == key }
        }
    }

    companion object {
        private const val fieldDelimiter = "<SEP>"

        const val subsetLookupTablePath = "data/additional-files/subset_artist_location.txt"
        private const val defaultLookupTablePath = subsetLookupTablePath
    }

    data class Entry(
            val artistId: String,
            val latitude: Double,
            val longitude: Double,
            val trackId: String,
            val artistName: String
    )
}