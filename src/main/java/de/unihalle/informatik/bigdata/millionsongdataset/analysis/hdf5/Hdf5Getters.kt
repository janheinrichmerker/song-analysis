/**
 * File containing utility methods to open a HDF5 song file and access its content.
 */
package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5

import hdf.`object`.h5.H5File
import hdf.hdf5lib.exceptions.HDF5Exception

/**
 * Opens an existing HDF5 file with read only access.
 * Closes the HDF5 file after [block] is executed.
 */
fun <ReturnType> openHdf5Readonly(filename: String, block: H5File.() -> ReturnType): ReturnType {
    val file = hdf5_getters.hdf5_open_readonly(filename)

    var exception: Throwable? = null
    try {
        return file.block()
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when (exception) {
            null -> file.close()
            else -> try {
                file.close()
            } catch (hdF5Exception: HDF5Exception) {
                println("Could not close HDF5 file?")
                hdF5Exception.printStackTrace()
            } catch (ignore: Throwable) {
            }
        }
    }
}

val H5File.songsCount: Int
    get() = hdf5_getters.get_num_songs(this)

fun H5File.getArtistFamiliarity(songIndex: Int = 0): Double {
    return hdf5_getters.get_artist_familiarity(this, songIndex)
}

fun H5File.getArtistHotttnesss(songIndex: Int = 0): Double {
    return hdf5_getters.get_artist_hotttnesss(this, songIndex)
}

fun H5File.getArtistId(songIndex: Int = 0): String {
    return hdf5_getters.get_artist_id(this, songIndex)
}

fun H5File.getArtistMbId(songIndex: Int = 0): String {
    return hdf5_getters.get_artist_mbid(this, songIndex)
}

fun H5File.getArtistPlaymeId(songIndex: Int = 0): Int {
    return hdf5_getters.get_artist_playmeid(this, songIndex)
}

fun H5File.getArtist7DigitalId(songIndex: Int = 0): Int {
    return hdf5_getters.get_artist_7digitalid(this, songIndex)
}

fun H5File.getArtistLatitude(songIndex: Int = 0): Double {
    return hdf5_getters.get_artist_latitude(this, songIndex)
}

fun H5File.getArtistLongitude(songIndex: Int = 0): Double {
    return hdf5_getters.get_artist_longitude(this, songIndex)
}

fun H5File.getArtistLocation(songIndex: Int = 0): String {
    return hdf5_getters.get_artist_location(this, songIndex)
}

fun H5File.getArtistName(songIndex: Int = 0): String {
    return hdf5_getters.get_artist_name(this, songIndex)
}

fun H5File.getRelease(songIndex: Int = 0): String {
    return hdf5_getters.get_release(this, songIndex)
}

fun H5File.getRelease7DigitalId(songIndex: Int = 0): Int {
    return hdf5_getters.get_release_7digitalid(this, songIndex)
}

fun H5File.getSongId(songIndex: Int = 0): String {
    return hdf5_getters.get_song_id(this, songIndex)
}

fun H5File.getSongHotttnesss(songIndex: Int = 0): Double {
    return hdf5_getters.get_song_hotttnesss(this, songIndex)
}

fun H5File.getTitle(songIndex: Int = 0): String {
    return hdf5_getters.get_title(this, songIndex)
}

fun H5File.getTrack7DigitalId(songIndex: Int = 0): Int {
    return hdf5_getters.get_track_7digitalid(this, songIndex)
}

fun H5File.getSimilarArtists(songIndex: Int = 0): Array<String> {
    return hdf5_getters.get_similar_artists(this, songIndex)
}

fun H5File.getArtistTerms(songIndex: Int = 0): Array<String> {
    return hdf5_getters.get_artist_terms(this, songIndex)
}

fun H5File.getArtistTermsFrequency(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_artist_terms_freq(this, songIndex)
}

fun H5File.getArtistTermsWeight(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_artist_terms_weight(this, songIndex)
}

fun H5File.getAnalysisSampleRate(songIndex: Int = 0): Double {
    return hdf5_getters.get_analysis_sample_rate(this, songIndex)
}

fun H5File.getAudioMd5(songIndex: Int = 0): String {
    return hdf5_getters.get_audio_md5(this, songIndex)
}

fun H5File.getDanceability(songIndex: Int = 0): Double {
    return hdf5_getters.get_danceability(this, songIndex)
}

fun H5File.getDuration(songIndex: Int = 0): Double {
    return hdf5_getters.get_duration(this, songIndex)
}

fun H5File.getFadeInEnd(songIndex: Int = 0): Double {
    return hdf5_getters.get_end_of_fade_in(this, songIndex)
}

fun H5File.getEnergy(songIndex: Int = 0): Double {
    return hdf5_getters.get_energy(this, songIndex)
}

fun H5File.getKey(songIndex: Int = 0): Int {
    return hdf5_getters.get_key(this, songIndex)
}

fun H5File.getKeyConfidence(songIndex: Int = 0): Double {
    return hdf5_getters.get_key_confidence(this, songIndex)
}

fun H5File.getLoudness(songIndex: Int = 0): Double {
    return hdf5_getters.get_loudness(this, songIndex)
}

fun H5File.getMode(songIndex: Int = 0): Int {
    return hdf5_getters.get_mode(this, songIndex)
}

fun H5File.getModeConfidence(songIndex: Int = 0): Double {
    return hdf5_getters.get_mode_confidence(this, songIndex)
}

fun H5File.getFadeOutStart(songIndex: Int = 0): Double {
    return hdf5_getters.get_start_of_fade_out(this, songIndex)
}

fun H5File.getTempo(songIndex: Int = 0): Double {
    return hdf5_getters.get_tempo(this, songIndex)
}

fun H5File.getTimeSignature(songIndex: Int = 0): Int {
    return hdf5_getters.get_time_signature(this, songIndex)
}

fun H5File.getTimeSignatureConfidence(songIndex: Int = 0): Double {
    return hdf5_getters.get_time_signature_confidence(this, songIndex)
}

fun H5File.getTrackId(songIndex: Int = 0): String {
    return hdf5_getters.get_track_id(this, songIndex)
}

fun H5File.getSegmentsStart(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_segments_start(this, songIndex)
}

fun H5File.getSegmentsConfidence(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_segments_confidence(this, songIndex)
}

fun H5File.getSegmentsPitches(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_segments_pitches(this, songIndex)
}

fun H5File.getSegmentsTimbre(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_segments_timbre(this, songIndex)
}

fun H5File.getSegmentsLoudnessMax(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_segments_loudness_max(this, songIndex)
}

fun H5File.getSegmentsLoudnessMaxTime(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_segments_loudness_max_time(this, songIndex)
}

fun H5File.getSegmentsLoudnessStart(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_segments_loudness_start(this, songIndex)
}

fun H5File.getSectionsStart(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_sections_start(this, songIndex)
}

fun H5File.getSectionsConfidence(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_sections_confidence(this, songIndex)
}

fun H5File.getBeatsStart(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_beats_start(this, songIndex)
}

fun H5File.getBeatsConfidence(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_beats_confidence(this, songIndex)
}

fun H5File.getBarsStart(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_bars_start(this, songIndex)
}

fun H5File.getBarsConfidence(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_bars_confidence(this, songIndex)
}

fun H5File.getTatumsStart(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_tatums_start(this, songIndex)
}

fun H5File.getTatumsConfidence(songIndex: Int = 0): DoubleArray? {
    return hdf5_getters.get_tatums_confidence(this, songIndex)
}

fun H5File.getYear(songIndex: Int = 0): Int {
    return hdf5_getters.get_year(this, songIndex)
}

fun H5File.getArtistMbTags(songIndex: Int = 0): Array<String> {
    return hdf5_getters.get_artist_mbtags(this, songIndex)
}

fun H5File.getArtistMbTagsCount(songIndex: Int = 0): IntArray {
    return hdf5_getters.get_artist_mbtags_count(this, songIndex)
}

val H5File.artistFamiliarity: Double
    get() = hdf5_getters.get_artist_familiarity(this)

val H5File.artistHotttnesss: Double
    get() = hdf5_getters.get_artist_hotttnesss(this)

val H5File.artistId: String
    get() = hdf5_getters.get_artist_id(this)

val H5File.artistMbId: String
    get() = hdf5_getters.get_artist_mbid(this)

val H5File.artistPlaymeId: Int
    get() = hdf5_getters.get_artist_playmeid(this)

val H5File.artist7DigitalId: Int
    get() = hdf5_getters.get_artist_7digitalid(this)

val H5File.artistLatitude: Double
    get() = hdf5_getters.get_artist_latitude(this)

val H5File.artistLongitude: Double
    get() = hdf5_getters.get_artist_longitude(this)

val H5File.artistLocation: String
    get() = hdf5_getters.get_artist_location(this)

val H5File.artistName: String
    get() = hdf5_getters.get_artist_name(this)

val H5File.release: String
    get() = hdf5_getters.get_release(this)

val H5File.release7DigitalId: Int
    get() = hdf5_getters.get_release_7digitalid(this)

val H5File.songId: String
    get() = hdf5_getters.get_song_id(this)

val H5File.songHotttnesss: Double
    get() = hdf5_getters.get_song_hotttnesss(this)

val H5File.title: String
    get() = hdf5_getters.get_title(this)

val H5File.track7DigitalId: Int
    get() = hdf5_getters.get_track_7digitalid(this)

val H5File.similarArtists: Array<String>
    get() = hdf5_getters.get_similar_artists(this)

val H5File.artistTerms: Array<String>
    get() = hdf5_getters.get_artist_terms(this)

val H5File.artistTermsFrequency: DoubleArray?
    get() = hdf5_getters.get_artist_terms_freq(this)

val H5File.artistTermsWeight: DoubleArray?
    get() = hdf5_getters.get_artist_terms_weight(this)

val H5File.analysisSampleRate: Double
    get() = hdf5_getters.get_analysis_sample_rate(this)

val H5File.audioMd5: String
    get() = hdf5_getters.get_audio_md5(this)

val H5File.danceability: Double
    get() = hdf5_getters.get_danceability(this)

val H5File.duration: Double
    get() = hdf5_getters.get_duration(this)

val H5File.fadeInEnd: Double
    get() = hdf5_getters.get_end_of_fade_in(this)

val H5File.energy: Double
    get() = hdf5_getters.get_energy(this)

val H5File.key: Int
    get() = hdf5_getters.get_key(this)

val H5File.keyConfidence: Double
    get() = hdf5_getters.get_key_confidence(this)

val H5File.loudness: Double
    get() = hdf5_getters.get_loudness(this)

val H5File.mode: Int
    get() = hdf5_getters.get_mode(this)

val H5File.modeConfidence: Double
    get() = hdf5_getters.get_mode_confidence(this)

val H5File.fadeOutStart: Double
    get() = hdf5_getters.get_start_of_fade_out(this)

val H5File.tempo: Double
    get() = hdf5_getters.get_tempo(this)

val H5File.timeSignature: Int
    get() = hdf5_getters.get_time_signature(this)

val H5File.timeSignatureConfidence: Double
    get() = hdf5_getters.get_time_signature_confidence(this)

val H5File.trackId: String
    get() = hdf5_getters.get_track_id(this)

val H5File.segmentsStart: DoubleArray?
    get() = hdf5_getters.get_segments_start(this)

val H5File.segmentsConfidence: DoubleArray?
    get() = hdf5_getters.get_segments_confidence(this)

val H5File.segmentsPitches: DoubleArray?
    get() = hdf5_getters.get_segments_pitches(this)

val H5File.segmentsTimbre: DoubleArray?
    get() = hdf5_getters.get_segments_timbre(this)

val H5File.segmentsLoudnessMax: DoubleArray?
    get() = hdf5_getters.get_segments_loudness_max(this)

val H5File.segmentsLoudnessMaxTime: DoubleArray?
    get() = hdf5_getters.get_segments_loudness_max_time(this)

val H5File.segmentsLoudnessStart: DoubleArray?
    get() = hdf5_getters.get_segments_loudness_start(this)

val H5File.sectionsStart: DoubleArray?
    get() = hdf5_getters.get_sections_start(this)

val H5File.sectionsConfidence: DoubleArray?
    get() = hdf5_getters.get_sections_confidence(this)

val H5File.beatsStart: DoubleArray?
    get() = hdf5_getters.get_beats_start(this)

val H5File.beatsConfidence: DoubleArray?
    get() = hdf5_getters.get_beats_confidence(this)

val H5File.barsStart: DoubleArray?
    get() = hdf5_getters.get_bars_start(this)

val H5File.barsConfidence: DoubleArray?
    get() = hdf5_getters.get_bars_confidence(this)

val H5File.tatumsStart: DoubleArray?
    get() = hdf5_getters.get_tatums_start(this)

val H5File.tatumsConfidence: DoubleArray?
    get() = hdf5_getters.get_tatums_confidence(this)

val H5File.year: Int
    get() = hdf5_getters.get_year(this)

val H5File.artistMbTags: Array<String>
    get() = hdf5_getters.get_artist_mbtags(this)

val H5File.artistMbTagsCount: IntArray
    get() = hdf5_getters.get_artist_mbtags_count(this)
