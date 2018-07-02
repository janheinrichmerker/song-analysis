/*
 * Catalano Math Library
 * The Catalano Framework
 *
 * Copyright © Diego Catalano, 2012-2016
 * diego.catalano at live.com
 *
 * Copyright © Andrew Kirillov, 2007-2008
 * andrew.kirillov at gmail.com
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package de.unihalle.informatik.bigdata.millionsongdataset.analysis.heatmap.util

/**
 * Gaussian function.
 * The class is used to calculate 1D and 2D Gaussian functions for specified Sigma (s) value:
 *
 * 1-D: f(x) = exp( x * x / ( -2 * s * s ) ) / ( s * sqrt( 2 * PI ) )
 * 2-D: f(x, y) = exp( x * x + y * y / ( -2 * s * s ) ) / ( s * s * 2 * PI )
 *
 * @author Diego Catalano
 */
class Gaussian(
        /**
         * Sigma value.
         * Default value is set to 1. Minimum allowed value is 0.00000001.
         */
        sigma: Double = 1.0
) {

    /**
     * Sigma value.
     * Default value is set to 1. Minimum allowed value is 0.00000001.
     */
    val sigma = Math.max(0.00000001, sigma)
    private val sqrSigma = this.sigma * this.sigma

    /**
     * Evaluate the 1-D Gaussian function for a given value.
     * @param x value.
     * @return Function's value at point x.
     */
    private operator fun get(x: Double) = Math.exp(x * x / (-2 * sqrSigma)) / (Math.sqrt(2 * Math.PI) * this.sigma)

    /**
     * Evaluate the 1-D Gaussian function for a given value.
     * @param x value.
     * @return Function's value at point x.
     */
    operator fun get(x: Number) = this[x.toDouble()]

    /**
     * Evaluate the 2-D Gaussian function for a given value.
     * @param x value.
     * @param y value.
     * @return Function's value at point (x,y).
     */
    private operator fun get(x: Double, y: Double) = Math.exp(-(x * x + y * y) / (2 * sqrSigma))

    /**
     * Evaluate the 2-D Gaussian function for a given value.
     * @param x value.
     * @param y value.
     * @return Function's value at point (x,y).
     */
    operator fun get(x: Number, y: Number) = this[x.toDouble(), y.toDouble()]

    /**
     * Generate a 1-D Gaussian kernel.
     * @param size Kernel size (should be odd).
     * @return 1-D Gaussian kernel of the specified size.
     */
    fun generateKernel1D(size: Int): Map<Int, Double> {
        if (size < 1) {
            throw IllegalArgumentException("Size can't be zero or negative, was $size.")
        }
        if (size % 2 == 0) {
            throw IllegalArgumentException("Size must be odd, was $size.")
        }

        val r = size / 2
        val kernel = mutableMapOf<Int, Double>()

        // Compute kernel.
        for (x in -r..r) {
            kernel[x] = this[x]
        }

        return kernel
    }

    /**
     * Generate a 2-D Gaussian kernel.
     * @param size Kernel size (should be odd).
     * @return 2-D Gaussian kernel of specified size.
     */
    fun generateKernel2D(size: Int): Map<Pair<Int, Int>, Double> {
        if (size < 1) {
            throw IllegalArgumentException("Size can't be zero or negative, was $size.")
        }
        if (size % 2 == 0) {
            throw IllegalArgumentException("Size must be odd, was $size.")
        }

        val r = size / 2
        val kernel = mutableMapOf<Pair<Int, Int>, Double>()

        // Compute kernel.
        for (y in -r..r) {
            for (x in -r..r) {
                val value = this[x, y]
                kernel[x to y] = value
            }
        }

        val sum = kernel.values.sum()
        return kernel
                .mapValues { (_, value) ->
                    value / sum
                }
    }
}