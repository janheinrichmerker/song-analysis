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

/**
 * Counts distinct elements of this collection.
 */
fun <ElementType> Iterable<ElementType>.countDistinct(): Map<ElementType, Int> {
    val buckets = mutableMapOf<ElementType, Int>()
    forEach { buckets.increment(it) }
    return buckets
}

/**
 * Counts distinct elements of this collection.
 */
fun <ElementType, SelectorType> Iterable<ElementType>.countDistinctBy(
        selector: (ElementType) -> SelectorType): Map<ElementType, Int> {
    val selectors = mapToMap { selector(it) to it }
    return selectors.keys
            .countDistinct()
            .mapKeys { (selector, _) ->
                selectors.getValue(selector)
            }
}

fun <ElementType : Comparable<ElementType>> Iterable<ElementType>.getDominantElement() =
        countDistinct().maxBy { it.value }!!.key

fun <ElementType : Comparable<ElementType>> Iterable<ElementType>.getDominantElementOrNull() =
        countDistinct().maxBy { it.value }?.key

fun <KeyType> MutableMap<KeyType, Int>.increment(key: KeyType, increment: Int = 1, default: Int = 0) {
    compute(key) { _, value ->
        (value ?: default) + increment
    }
}

fun <KeyType> MutableMap<KeyType, Long>.increment(key: KeyType, increment: Long = 1, default: Long = 0) {
    compute(key) { _, value ->
        (value ?: default) + increment
    }
}

fun <KeyType> MutableMap<KeyType, Float>.increment(key: KeyType, increment: Float = 1.0f, default: Float = 0.0f) {
    compute(key) { _, value ->
        (value ?: default) + increment
    }
}

fun <KeyType> MutableMap<KeyType, Double>.increment(key: KeyType, increment: Double = 1.0, default: Double = 0.0) {
    compute(key) { _, value ->
        (value ?: default) + increment
    }
}

fun <KeyType> MutableMap<KeyType, Int>.decrement(key: KeyType, decrement: Int = 1, default: Int = 0) =
        increment(key, -decrement, default)

fun <KeyType> MutableMap<KeyType, Long>.decrement(key: KeyType, decrement: Long = 1, default: Long = 0) =
        increment(key, -decrement, default)

fun <KeyType> MutableMap<KeyType, Float>.decrement(key: KeyType, decrement: Float = 1.0f, default: Float = 0.0f) =
        increment(key, -decrement, default)

fun <KeyType> MutableMap<KeyType, Double>.decrement(key: KeyType, decrement: Double = 1.0, default: Double = 0.0) =
        increment(key, -decrement, default)