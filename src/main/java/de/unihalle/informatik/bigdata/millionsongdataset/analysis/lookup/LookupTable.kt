package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

import java.io.File

abstract class LookupTable<KeyType, EntryTape : LookupTable.Entry<KeyType, EntryTape>>(
        lookupFilePath: String
) : AbstractMap<KeyType, EntryTape>() {

    protected val lookupFile: File = File(lookupFilePath)

    final override val entries: Set<EntryTape> by lazy { parseEntries() }

    protected abstract fun parseEntries(): Set<EntryTape>

    abstract class Entry<KeyType, EntryType : Entry<KeyType, EntryType>> : Map.Entry<KeyType, EntryType> {
        @Suppress("UNCHECKED_CAST")
        final override val value: EntryType by lazy { this as EntryType }
    }

}