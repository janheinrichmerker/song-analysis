package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.mapToMap
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Lyrics

class LyricsLookupTable(
        lookupFilePath: String = defaultLookupTablePath
) : AbstractFileLookupTable<String, LyricsLookupTable.Entry>(lookupFilePath) {

    private fun <T> useEntries(block: (entries: Sequence<Entry>) -> T): T {
        return useLookupFileLines { lines ->
            val entries = lines
                    .dropWhile { line -> line.startsWith(commentLinePrefix) || line.startsWith(wordListLinePrefix) }
                    .map { line -> line.split(countListDelimiter) }
                    .map { fields ->
                        Entry(
                                trackId = fields[0],
                                musixmatchTrackId = fields[1].toInt(),
                                lyrics = fields
                                        .drop(2)
                                        .mapToMap { column ->
                                            val wordCount = column.split(countListElementDelimiter)
                                            val word = words[wordCount[0].toInt() - 1]
                                            val count = wordCount[1].toInt()
                                            word to count
                                        }
                        )
                    }
            block(entries)
        }
    }

    private val words: List<String> =
            useLookupFileLines { lines ->
                lines.dropWhile { line -> !line.startsWith(wordListLinePrefix) }
                        .first()
                        .removePrefix(wordListLinePrefix.toString())
                        .split(wordListDelimiter)
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
        private const val wordListLinePrefix = '%'
        private const val wordListDelimiter = ','
        private const val countListDelimiter = ','
        private const val countListElementDelimiter = ':'

        const val testLookupTablePath = "data/lyrics/mxm_dataset_test.txt"
        const val trainLookupTablePath = "data/lyrics/mxm_dataset_train.txt"
        private const val defaultLookupTablePath = trainLookupTablePath
    }

    data class Entry(
            val trackId: String,
            val musixmatchTrackId: Int,
            val lyrics: Lyrics
    )
}