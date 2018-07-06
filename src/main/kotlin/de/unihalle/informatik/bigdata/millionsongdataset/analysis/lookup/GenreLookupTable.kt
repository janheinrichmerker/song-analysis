package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

class GenreLookupTable(
        lookupFilePath: String = defaultLookupTablePath
) : AbstractFileLookupTable<String, GenreLookupTable.Entry>(lookupFilePath) {

    private fun <T> useEntries(block: (entries: Sequence<Entry>) -> T): T {
        return useLookupFileLines { lines ->
            val entries = lines
                    .dropWhile { line -> line.startsWith(commentLinePrefix) }
                    .map { line -> line.split(fieldDelimiter) }
                    .map { fields ->
                        Entry(
                                trackId = fields[0],
                                majorGenre = fields[1],
                                minorGenre = fields.getOrNull(2)
                        )
                    }
            block(entries)
        }
    }

    override val keys: Set<String> =
            useEntries { entries ->
                entries.map { it.trackId }.toSet()
            }

    override fun get(key: String): Entry? {
        return useEntries { entries ->
            entries.find { it.trackId == key }
        }
    }

    companion object {
        private const val commentLinePrefix = '#'
        private const val fieldDelimiter = '\t'

        const val cd1LookupTablePath = "data/genres/msd_tagtraum_cd1.cls"
        const val cd2LookupTablePath = "data/genres/msd_tagtraum_cd2.cls"
        const val cd2cLookupTablePath = "data/genres/msd_tagtraum_cd2c.cls"
        private const val defaultLookupTablePath = cd1LookupTablePath
    }

    data class Entry(
            val trackId: String,
            val majorGenre: String,
            val minorGenre: String? = null
    )
}