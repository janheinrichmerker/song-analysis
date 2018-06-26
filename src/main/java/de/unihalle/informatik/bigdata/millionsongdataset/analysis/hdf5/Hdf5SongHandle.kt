/*
 * Thierry Bertin-Mahieux (2010) Columbia University
 * tb2332@columbia.edu
 * This code contains a set of getters functions to access the fields
 * from an HDF5 song file (regular file with one song or summary file
 * with many songs) in Java.
 * The goal is to reproduce the Python getters behaviour.
 * Our aim is only to show how to use the HDF5 files with Java, the
 * code is not optimized at all!
 * NOTE ON 2D ARRAYS: pitch and timbre are supposed to be #segs x 12
 * They are stored in 1D array by concatenating rows, e.g. elem 20
 * should be row 1 elem 8.
 * To get element of row r and column c from an array a, call a[r*12+c]
 * To get a faster code, you should load metadata/songs and analysis/songs
 * only once, see the Matlab code in /MatlabSrc for inspiration.
 * This is part of the Million Song Dataset project from
 * LabROSA (Columbia University) and The Echo Nest.
 * Copyright 2010, Thierry Bertin-Mahieux
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Edits: Copyright 2018 Jan Heinrich Reimer
 */

package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hdf5.dataOrNull
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.WritableSongHandle
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.SongHandle
import hdf.`object`.h5.H5CompoundDS
import hdf.`object`.h5.H5File
import hdf.`object`.h5.H5ScalarDS
import java.util.*

internal class Hdf5SongHandle internal constructor(
        private val file: H5File,
        private val songIndex: Int = 0)
    : SongHandle by WritableSongHandle() {

    override var artistFamiliarity: Double =
            getDouble(
                    groupName = "/metadata/songs",
                    memberName = "artist_familiarity"
            )

    override var artistHotttnesss: Double =
            getDouble(
                    groupName = "/metadata/songs",
                    memberName = "artist_hotttnesss"
            )


    override var artistId: String =
            getString(
                    groupName = "/metadata/songs",
                    memberName = "artist_id"
            )


    override var artistMbId: String =
            getString(
                    groupName = "/metadata/songs",
                    memberName = "artist_mbid"
            )


    override var artistPlaymeId: Int =
            getInt(
                    groupName = "/metadata/songs",
                    memberName = "artist_playmeid"
            )


    override var artist7DigitalId: Int =
            getInt(
                    groupName = "/metadata/songs",
                    memberName = "artist_7digitalid"
            )


    override var artistLatitude: Double =
            getDouble(
                    groupName = "/metadata/songs",
                    memberName = "artist_latitude"
            )


    override var artistLongitude: Double =
            getDouble(
                    groupName = "/metadata/songs",
                    memberName = "artist_longitude"
            )


    override var artistLocation: String =
            getString(
                    groupName = "/metadata/songs",
                    memberName = "artist_location"
            )


    override var artistName: String =
            getString(
                    groupName = "/metadata/songs",
                    memberName = "artist_name"
            )


    override var release: String =
            getString(
                    groupName = "/metadata/songs",
                    memberName = "release"
            )


    override var release7DigitalId: Int =
            getInt(
                    groupName = "/metadata/songs",
                    memberName = "release_7digitalid"
            )


    override var songId: String =
            getString(
                    groupName = "/metadata/songs",
                    memberName = "song_id"
            )


    override var songHotttnesss: Double =
            getDouble(
                    groupName = "/metadata/songs",
                    memberName = "song_hotttnesss"
            )


    override var title: String =
            getString(
                    groupName = "/metadata/songs",
                    memberName = "title"
            )


    override var track7DigitalId: Int =
            getInt(
                    groupName = "/metadata/songs",
                    memberName = "track_7digitalid"
            )


    override var similarArtists: Array<String> =
            getStringArray(
                    groupName = "metadata",
                    arrayName = "similar_artists"
            )


    override var artistTerms: Array<String> =
            getStringArray(
                    groupName = "metadata",
                    arrayName = "artist_terms"
            )


    override var artistTermsFrequency: DoubleArray =
            getDoubleArray(
                    groupName = "metadata",
                    arrayName = "artist_terms_freq",
                    memberName = "idx_artist_terms"
            )


    override var artistTermsWeight: DoubleArray =
            getDoubleArray(
                    groupName = "metadata",
                    arrayName = "artist_terms_weight",
                    memberName = "idx_artist_terms"
            )


    override var analysisSampleRate: Int =
            getInt(
                    groupName = "/analysis/songs",
                    memberName = "analysis_sample_rate"
            )


    override var audioMd5: String =
            getString(
                    groupName = "/analysis/songs",
                    memberName = "audio_md5"
            )


    override var danceability: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "danceability"
            )


    override var duration: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "duration"
            )


    override var fadeInEnd: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "end_of_fade_in"
            )


    override var energy: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "energy"
            )


    override var key: Int =
            getInt(
                    groupName = "/analysis/songs",
                    memberName = "key"
            )


    override var keyConfidence: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "key_confidence"
            )


    override var loudness: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "loudness"
            )


    override var mode: Int =
            getInt(
                    groupName = "/analysis/songs",
                    memberName = "mode"
            )


    override var modeConfidence: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "mode_confidence"
            )


    override var fadeOutStart: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "start_of_fade_out"
            )


    override var tempo: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "tempo"
            )


    override var timeSignature: Int =
            getInt(
                    groupName = "/analysis/songs",
                    memberName = "time_signature"
            )


    override var timeSignatureConfidence: Double =
            getDouble(
                    groupName = "/analysis/songs",
                    memberName = "time_signature_confidence"
            )


    override var trackId: String =
            getString(
                    groupName = "/analysis/songs",
                    memberName = "track_id"
            )


    override var segmentsStart: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "segments_start"
            )


    override var segmentsConfidence: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "segments_confidence"
            )


    override var segmentsPitches: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "segments_pitches",
                    dimensionsCount = 2
            )


    override var segmentsTimbre: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "segments_timbre",
                    dimensionsCount = 2
            )


    override var segmentsLoudnessMax: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "segments_loudness_max"
            )


    override var segmentsLoudnessMaxTime: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "segments_loudness_max_time"
            )


    override var segmentsLoudnessStart: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "segments_loudness_start"
            )


    override var sectionsStart: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "sections_start"
            )


    override var sectionsConfidence: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "sections_confidence"
            )


    override var beatsStart: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "beats_start"
            )


    override var beatsConfidence: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "beats_confidence"
            )


    override var barsStart: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "bars_start"
            )


    override var barsConfidence: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "bars_confidence"
            )


    override var tatumsStart: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "tatums_start"
            )


    override var tatumsConfidence: DoubleArray =
            getDoubleArray(
                    groupName = "analysis",
                    arrayName = "tatums_confidence"
            )


    override var year: Int =
            getInt(
                    groupName = "/musicbrainz/songs",
                    memberName = "year"
            )


    override var artistMbTags: Array<String> =
            getStringArray(
                    groupName = "musicbrainz",
                    arrayName = "artist_mbtags"
            )


    override var artistMbTagsCount: IntArray =
            getIntArray(
                    groupName = "musicbrainz",
                    arrayName = "artist_mbtags_count",
                    memberName = "idx_artist_mbtags"
            )

    private fun getInt(groupName: String, memberName: String): Int {
        val analysis = file[groupName] as H5CompoundDS
        analysis.run {
            init()

            val memberIndex = memberNames.indexOf(memberName)
            if (memberIndex < 0) throw IllegalArgumentException("Member '$memberName' not found in group '$groupName'")

            val dataVector = data as Vector<*>
            val col = dataVector[memberIndex] as IntArray
            return col[songIndex]
        }
    }

    private fun getDouble(groupName: String, memberName: String): Double {
        val analysis = file[groupName] as H5CompoundDS
        analysis.run {
            init()

            val memberIndex = memberNames.indexOf(memberName)
            if (memberIndex < 0) throw IllegalArgumentException("Member '$memberName' not found in group '$groupName'")

            val dataVector = data as Vector<*>
            val column = dataVector[memberIndex] as DoubleArray
            return column[songIndex]
        }
    }

    private fun getString(groupName: String, memberName: String): String {
        val analysis = file[groupName] as H5CompoundDS
        analysis.run {
            init()

            val memberIndex = memberNames.indexOf(memberName)
            if (memberIndex < 0) throw IllegalArgumentException("Member '$memberName' not found in group '$groupName'")

            val dataVector = data as Vector<*>
            @Suppress("UNCHECKED_CAST")
            val column = dataVector[memberIndex] as Array<String>
            return column[songIndex]
        }
    }

    private fun getDoubleArray(
            groupName: String,
            arrayName: String,
            memberName: String = "idx_$arrayName",
            dimensionsCount: Int = 1): DoubleArray {
        val analysis = file["$groupName/songs"] as H5CompoundDS
        val array = file["/$groupName/$arrayName"] as H5ScalarDS
        analysis.run {
            init()

            val memberIndex = memberNames.indexOf(memberName)
            if (memberIndex < 0) throw IllegalArgumentException("Member '$memberName' not found in group '$groupName'")

            val dataVector = data as Vector<*>
            val column = dataVector[memberIndex] as IntArray

            array.run {
                init()

                val data: DoubleArray = array.dataOrNull() ?: return DoubleArray(0)

                when (dimensionsCount) {
                    1 -> {
                        val startIndex = column[songIndex]
                        val endIndex =
                                if (songIndex + 1 < column.size) {
                                    column[songIndex + 1]
                                } else {
                                    data.size
                                }
                        return data.copyOfRange(startIndex, endIndex - 1)
                    }
                    2 -> {
                        // Multiply indices by 12.
                        val startIndex = column[songIndex] * 12
                        val endIndex =
                                if (songIndex + 1 < column.size) {
                                    column[songIndex + 1] * 12
                                } else {
                                    data.size
                                }
                        return data.copyOfRange(startIndex, endIndex - 1)
                    }
                    else -> throw IllegalArgumentException("Can't parse more than 2 dimensions.")
                }
            }
        }
    }

    private fun getIntArray(
            groupName: String,
            arrayName: String,
            memberName: String = "idx_$arrayName"): IntArray {
        val analysis = file["$groupName/songs"] as H5CompoundDS
        val array = file["/$groupName/$arrayName"] as H5ScalarDS
        analysis.run {
            init()

            val memberIndex = memberNames.indexOf(memberName)
            if (memberIndex < 0) throw IllegalArgumentException("Member '$memberName' not found in group '$groupName'")

            val dataVector = data as Vector<*>
            val column = dataVector[memberIndex] as IntArray

            array.run {
                init()

                val data: IntArray = array.dataOrNull() ?: return IntArray(0)

                val startIndex = column[songIndex]
                val endIndex =
                        if (songIndex + 1 < column.size) {
                            column[songIndex + 1]
                        } else {
                            data.size
                        }
                return data.copyOfRange(startIndex, endIndex - 1)
            }
        }
    }

    private fun getStringArray(
            groupName: String,
            arrayName: String,
            memberName: String = "idx_$arrayName"): Array<String> {
        val analysis = file["$groupName/songs"] as H5CompoundDS
        val array = file["/$groupName/$arrayName"] as H5ScalarDS
        analysis.run {
            init()

            val memberIndex = memberNames.indexOf(memberName)
            if (memberIndex < 0) throw IllegalArgumentException("Member '$memberName' not found in group '$groupName'")

            val dataVector = data as Vector<*>
            val column = dataVector[memberIndex] as IntArray

            array.run {
                init()

                val data: Array<String> = array.dataOrNull() ?: return emptyArray()

                val startIndex = column[songIndex]
                val endIndex =
                        if (songIndex + 1 < column.size) {
                            column[songIndex + 1]
                        } else {
                            data.size
                        }
                return data.copyOfRange(startIndex, endIndex - 1)
            }
        }
    }
}