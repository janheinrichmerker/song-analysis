package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

import java.io.File

abstract class AbstractFileLookupTable<KeyType, ValueType>(
        lookupFilePath: String
) : AbstractLookupTable<KeyType, ValueType>() {

    protected val lookupFile: File = File(lookupFilePath)

    protected fun <T> useLookupFileLines(block: (entries: Sequence<String>) -> T) = lookupFile.useLines(block = block)

}