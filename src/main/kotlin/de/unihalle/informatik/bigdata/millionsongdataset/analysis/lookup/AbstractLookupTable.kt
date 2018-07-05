/*
 * Based on Kotlin AbstractMap
 * Copyright 2010-2018 JetBrains s.r.o.
 */

package de.unihalle.informatik.bigdata.millionsongdataset.analysis.lookup

/**
 * Provides a skeletal implementation of the read-only [LookupTable] interface.
 *
 * The implementor is required to implement [keys] property, which should return a read-only set of lookup table keys,
 * and the [get] method for obtaining a value for a given key.
 *
 * @param K The type of lookup table keys. The table is invariant on its key type.
 * @param V The type of lookup table values. The table is covariant on its value type.
 */
abstract class AbstractLookupTable<K, V> protected constructor() : LookupTable<K, V> {

    override fun containsKey(key: K) = internalContainsKey(key)

    private fun internalContainsKey(key: Any?) = key in keys

    /**
     * Compares this lookup table with other instance with the ordered structural equality of their keys.
     *
     * @return true, if [other] instance is a [LookupTable] of the same size,
     * all keys of which are contained in the [keys] set of this table.
     */
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is LookupTable<*, *>) return false
        if (size != other.size) return false

        return other.keys.all { internalContainsKey(it) }
    }

    /**
     * Returns the hash code value for this lookup table.
     *
     * It is the same as the hashCode of [keys] set.
     */
    override fun hashCode(): Int = keys.hashCode()

    override fun isEmpty(): Boolean = size == 0
    override val size: Int get() = keys.size

    override fun toString(): String = keys.joinToString(", ", "{", "}") { "$it=?" }
}
