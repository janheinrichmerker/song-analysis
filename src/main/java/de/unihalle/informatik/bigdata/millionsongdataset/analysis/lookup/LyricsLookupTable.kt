package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.mapToMap
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Lyrics

class LyricsLookupTable(
        lookupFilePath: String = defaultLookupTablePath
) : LookupTable<String, LyricsLookupTable.Entry>(lookupFilePath) {

    override fun parseEntries(): Set<Entry> {
        val lines = lookupFile.readLines()
        val linesUntilWordList = lines
                .dropWhile { line ->
                    line.startsWith(commentLinePrefix) || !line.startsWith(wordListLinePrefix)
                }
        val words = linesUntilWordList
                .take(1)
                .single()
                .removePrefix(wordListLinePrefix.toString())
                .split(wordListDelimiter)
        return linesUntilWordList
                .dropWhile { line ->
                    line.startsWith(commentLinePrefix) || line.startsWith(wordListLinePrefix)
                }
                .mapTo(mutableSetOf()) { line ->
                    val columns = line
                            .split(countListDelimiter)
                    Entry(
                            trackId = columns[0],
                            musixmatchTrackId = columns[1],
                            lyrics = columns
                                    .drop(2)
                                    .mapToMap { column ->
                                        val wordCount = column.split(countListElementDelimiter)
                                        val word = words[wordCount[0].toInt() - 1]
                                        val count = wordCount[1].toInt()
                                        word to count
                                    }
                    )
                }
    }

    companion object {
        const val commentLinePrefix = '#'
        const val wordListLinePrefix = '%'
        const val wordListDelimiter = ','
        const val countListDelimiter = ','
        const val countListElementDelimiter = ':'

        private const val testLookupTablePath = "data/lyrics/mxm_dataset_test.txt"
        private const val trainLookupTablePath = "data/lyrics/mxm_dataset_train.txt"
        private const val defaultLookupTablePath = trainLookupTablePath
    }

    data class Entry(
            val trackId: String,
            val musixmatchTrackId: String,
            val lyrics: Lyrics
    ) : LookupTable.Entry<String, Entry>() {
        override val key = trackId
    }
}