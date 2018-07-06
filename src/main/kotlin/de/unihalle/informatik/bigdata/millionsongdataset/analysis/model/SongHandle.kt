package de.unihalle.informatik.bigdata.millionsongdataset.analysis.model

import org.apache.hadoop.io.Writable

/**
 * Handle to store all song data in a one-level structure.
 *
 * Note: To access song data one should use the more convenient [Song] class.
 */
interface SongHandle : Writable {
    var artistFamiliarity: Double
    var artistHotttnesss: Double
    var artistId: String
    var artistMbId: String
    var artistPlaymeId: Int
    var artist7DigitalId: Int
    var artistLatitude: Double
    var artistLongitude: Double
    var artistLocation: String
    var artistName: String
    var release: String
    var release7DigitalId: Int
    var songId: String
    var songHotttnesss: Double
    var title: String
    var track7DigitalId: Int
    var similarArtists: Array<String>
    var artistTerms: Array<String>
    var artistTermsFrequency: DoubleArray
    var artistTermsWeight: DoubleArray
    var analysisSampleRate: Int
    var audioMd5: String
    var danceability: Double
    var duration: Double
    var fadeInEnd: Double
    var energy: Double
    var key: Int
    var keyConfidence: Double
    var loudness: Double
    var mode: Int
    var modeConfidence: Double
    var fadeOutStart: Double
    var tempo: Double
    var timeSignature: Int
    var timeSignatureConfidence: Double
    var trackId: String
    var segmentsStart: DoubleArray
    var segmentsConfidence: DoubleArray
    var segmentsPitches: DoubleArray
    var segmentsTimbre: DoubleArray
    var segmentsLoudnessMax: DoubleArray
    var segmentsLoudnessMaxTime: DoubleArray
    var segmentsLoudnessStart: DoubleArray
    var sectionsStart: DoubleArray
    var sectionsConfidence: DoubleArray
    var beatsStart: DoubleArray
    var beatsConfidence: DoubleArray
    var barsStart: DoubleArray
    var barsConfidence: DoubleArray
    var tatumsStart: DoubleArray
    var tatumsConfidence: DoubleArray
    var year: Int
    var artistMbTags: Array<String>
    var artistMbTagsCount: IntArray
}