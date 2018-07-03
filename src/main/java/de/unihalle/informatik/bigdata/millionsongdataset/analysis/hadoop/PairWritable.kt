package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop

import org.apache.hadoop.io.WritableComparable
import java.io.DataInput
import java.io.DataOutput

/**
 * Represents a generic pair of two values.
 *
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * Pair exhibits value semantics, i.e. two pairs are equal if both components are equal.
 *
 * @param A type of the first value.
 * @param B type of the second value.
 * @property first First value.
 * @property second Second value.
 * @constructor Creates a new instance of Pair.
 */
abstract class PairWritable<A : WritableComparable<in A>, B : WritableComparable<in B>>()
    : WritableComparable<PairWritable<A, B>> {

    constructor(first: A, second: B) : this() {
        this.first = first
        this.second = second
    }

    lateinit var first: A
    lateinit var second: B

    abstract fun createFields()

    final override fun readFields(input: DataInput) {
        createFields()
        first.readFields(input)
        second.readFields(input)
    }

    override fun write(output: DataOutput) {
        first.write(output)
        second.write(output)
    }

    /**
     * Returns string representation of the [PairWritable] including its [first] and [second] values.
     */
    override fun toString(): String = "($first, $second)"
}