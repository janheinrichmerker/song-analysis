package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

class GenreLookupTable(
        lookupFilePath: String = defaultLookupTablePath
) : LookupTable<String, GenreLookupTable.Entry>(lookupFilePath) {

    override fun parseEntries(): Set<Entry> {
        return lookupFile
                .readLines()
                .dropWhile { line -> line.startsWith(commentLinePrefix) }
                .mapTo(mutableSetOf()) { line ->
                    val fields = line.split(fieldDelimiter)
                    Entry(
                            trackId = fields[0],
                            majorGenre = fields[1],
                            minorGenre = fields.getOrNull(2)
                    )
                }
    }

    companion object {
        const val commentLinePrefix = '#'
        const val fieldDelimiter = '\t'

        private const val cd1LookupTablePath = "data/genres/msd_tagtraum_cd1.cls"
        private const val cd2LookupTablePath = "data/genres/msd_tagtraum_cd2.cls"
        private const val cd2cLookupTablePath = "data/genres/msd_tagtraum_cd2c.cls"
        const val defaultLookupTablePath = cd1LookupTablePath
    }

    data class Entry(
            val trackId: String,
            val majorGenre: String,
            val minorGenre: String?
    ) : LookupTable.Entry<String, Entry>() {
        override val key = trackId
    }
}