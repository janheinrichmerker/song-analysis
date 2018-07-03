package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions


/**
 * Applies the given [transform] function to each element of the original collection.
 */
inline fun <InputElementType, ReturnKeyType, ReturnValueType> Iterable<InputElementType>.mapToMap(
        transform: (InputElementType) -> Pair<ReturnKeyType, ReturnValueType>
): Map<ReturnKeyType, ReturnValueType> {
    val destination = mutableMapOf<ReturnKeyType, ReturnValueType>()
    for (item in this) {
        val (key, value) = transform(item)
        destination[key] = value
    }
    return destination
}