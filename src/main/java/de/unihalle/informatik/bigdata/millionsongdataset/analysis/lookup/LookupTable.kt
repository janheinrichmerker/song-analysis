/*
 * Based on Kotlin Map
 * Copyright 2010-2018 JetBrains s.r.o.
 */

package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

/**
 * A collection that holds pairs of objects (keys and values) and supports efficiently retrieving
 * the value corresponding to each key. Lookup table keys are unique; the table holds only one value for each key.
 * Methods in this interface support only read-only access to the lookup table.
 * In comparison to [Map], [LookupTable] does not support searching by value but only by key.
 * @param K The type of lookup table keys. The table is invariant on its key type, as it
 *          can accept key as a parameter (of [containsKey] for example) and return it in [keys] set.
 * @param V The type of lookup table values. The table is covariant on its value type.
 */
interface LookupTable<K, V> {
    // Query Operations
    /**
     * Returns the number of key/value pairs in the map.
     */
    val size: Int

    /**
     * Returns `true` if the map is empty (contains no elements), `false` otherwise.
     */
    fun isEmpty(): Boolean

    /**
     * Returns `true` if the map contains the specified [key].
     */
    fun containsKey(key: K): Boolean

    /**
     * Returns `true` if the map contains the specified [key].
     */
    operator fun contains(key: K): Boolean = containsKey(key)

    /**
     * Returns the value corresponding to the given [key], or `null` if such a key is not present in the map.
     */
    operator fun get(key: K): V?

    /**
     * Returns the value corresponding to the given [key], or [defaultValue] if such a key is not present in the map.
     */
    fun getOrDefault(key: K, defaultValue: V): V = get(key) ?: defaultValue

    // Views
    /**
     * Returns a read-only [Set] of all keys in this map.
     */
    val keys: Set<K>
}