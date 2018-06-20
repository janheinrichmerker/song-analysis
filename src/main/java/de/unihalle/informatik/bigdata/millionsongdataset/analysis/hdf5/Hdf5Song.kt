package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.generateArray
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import hdf.`object`.h5.H5File

class Hdf5Song(private val file: H5File, private val songIndex: Int = 0) : Song {

    private val _artistFamiliarity: Double = file.getArtistFamiliarity(songIndex)
    private val _artistHotttnesss: Double = file.getArtistHotttnesss(songIndex)
    private val _artistId: String = file.getArtistId(songIndex)
    private val _artistMbId: String = file.getArtistMbId(songIndex)
    private val _artistPlaymeId: Int = file.getArtistPlaymeId(songIndex)
    private val _artist7DigitalId: Int = file.getArtist7DigitalId(songIndex)
    private val _artistLatitude: Double = file.getArtistLatitude(songIndex)
    private val _artistLongitude: Double = file.getArtistLongitude(songIndex)
    private val _artistLocation: String = file.getArtistLocation(songIndex)
    private val _artistName: String = file.getArtistName(songIndex)
    private val _release: String = file.getRelease(songIndex)
    private val _release7DigitalId: Int = file.getRelease7DigitalId(songIndex)
    private val _songId: String = file.getSongId(songIndex)
    private val _songHotttnesss: Double = file.getSongHotttnesss(songIndex)
    private val _title: String = file.getTitle(songIndex)
    private val _track7DigitalId: Int = file.getTrack7DigitalId(songIndex)
    private val _similarArtists: Array<String> = file.getSimilarArtists(songIndex)
    private val _artistTerms: Array<String> = file.getArtistTerms(songIndex)
    private val _artistTermsFrequency: DoubleArray? = file.getArtistTermsFrequency(songIndex)
    private val _artistTermsWeight: DoubleArray? = file.getArtistTermsWeight(songIndex)
    private val _analysisSampleRate: Double = 0.0 //file.getAnalysisSampleRate(songIndex)
    private val _audioMd5: String = file.getAudioMd5(songIndex)
    private val _danceability: Double = file.getDanceability(songIndex)
    private val _duration: Double = file.getDuration(songIndex)
    private val _fadeInEnd: Double = file.getFadeInEnd(songIndex)
    private val _energy: Double = file.getEnergy(songIndex)
    private val _key: Int = file.getKey(songIndex)
    private val _keyConfidence: Double = file.getKeyConfidence(songIndex)
    private val _loudness: Double = file.getLoudness(songIndex)
    private val _mode: Int = file.getMode(songIndex)
    private val _modeConfidence: Double = file.getModeConfidence(songIndex)
    private val _fadeOutStart: Double = file.getFadeOutStart(songIndex)
    private val _tempo: Double = file.getTempo(songIndex)
    private val _timeSignature: Int = file.getTimeSignature(songIndex)
    private val _timeSignatureConfidence: Double = file.getTimeSignatureConfidence(songIndex)
    private val _trackId: String = file.getTrackId(songIndex)
    private val _segmentsStart: DoubleArray? = file.getSegmentsStart(songIndex)
    private val _segmentsConfidence: DoubleArray? = file.getSegmentsConfidence(songIndex)
    private val _segmentsPitches: DoubleArray? = file.getSegmentsPitches(songIndex)
    private val _segmentsTimbre: DoubleArray? = file.getSegmentsTimbre(songIndex)
    private val _segmentsLoudnessMax: DoubleArray? = file.getSegmentsLoudnessMax(songIndex)
    private val _segmentsLoudnessMaxTime: DoubleArray? = file.getSegmentsLoudnessMaxTime(songIndex)
    private val _segmentsLoudnessStart: DoubleArray? = file.getSegmentsLoudnessStart(songIndex)
    private val _sectionsStart: DoubleArray? = file.getSectionsStart(songIndex)
    private val _sectionsConfidence: DoubleArray? = file.getSectionsConfidence(songIndex)
    private val _beatsStart: DoubleArray? = file.getBeatsStart(songIndex)
    private val _beatsConfidence: DoubleArray? = file.getBeatsConfidence(songIndex)
    private val _barsStart: DoubleArray? = file.getBarsStart(songIndex)
    private val _barsConfidence: DoubleArray? = file.getBarsConfidence(songIndex)
    private val _tatumsStart: DoubleArray? = file.getTatumsStart(songIndex)
    private val _tatumsConfidence: DoubleArray? = file.getTatumsConfidence(songIndex)
    private val _year: Int = file.getYear(songIndex)
    private val _artistMbTags: Array<String> = file.getArtistMbTags(songIndex)
    private val _artistMbTagsCount: IntArray = file.getArtistMbTagsCount(songIndex)

    override val artist = Hdf5Artist()
    override val release = Hdf5Release()
    override val id = _songId
    override val hotttnesss = _songHotttnesss
    override val title = _title
    override val analysisSampleRate = _analysisSampleRate
    override val audioMd5 = _audioMd5
    override val danceability = _danceability
    override val duration = _duration
    override val fades = Hdf5Fades()
    override val energy = _energy
    override val key = Hdf5Key()
    override val loudness = _loudness
    override val mode = Hdf5Mode()
    override val tempo = _tempo
    override val timeSignature = Hdf5TimeSignature()
    override val track = Hdf5Track()
    override val segments = if (_segmentsStart == null) null
    else generateArray(_segmentsStart.size) { index ->
        Hdf5Segment(index) as Song.Segment
    }
    override val sections = if (_sectionsStart == null) null
    else generateArray(_sectionsStart.size) { index ->
        Hdf5Section(index) as Song.Section
    }
    override val beats = if (_beatsStart == null) null
    else generateArray(_beatsStart.size) { index ->
        Hdf5Beat(index) as Song.Beat
    }
    override val bars = if (_barsStart == null) null
    else generateArray(_barsStart.size) { index ->
        Hdf5Bar(index) as Song.Bar
    }
    override val tatums = if (_tatumsStart == null) null
    else generateArray(_tatumsStart.size) { index ->
        Hdf5Tatum(index) as Song.Tatum
    }
    override val year = _year

    inner class Hdf5Fades : Song.Fades {
        override val fadeInEnd = _fadeInEnd
        override val fadeOutStart = _fadeOutStart

        override fun toString(): String {
            return """
Fade-in end: $fadeInEnd
Fade-out start: $fadeOutStart
""".trim()
        }
    }

    inner class Hdf5Key : Song.Key {
        override val key = _key
        override val confidence = _keyConfidence

        override fun toString(): String {
            return """
Key: $key
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Mode : Song.Mode {
        override val mode = _mode
        override val confidence = _modeConfidence

        override fun toString(): String {
            return """
Mode: $mode
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5TimeSignature : Song.TimeSignature {
        override val timeSignature = _timeSignature
        override val confidence = _timeSignatureConfidence

        override fun toString(): String {
            return """
Time signature: $timeSignature
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Track : Song.Track {
        override val id = _trackId
        override val sevenDigitalId = _track7DigitalId

        override fun toString(): String {
            return """
ID: $id
7digital ID: $sevenDigitalId
""".trim()
        }
    }

    inner class Hdf5Release : Song.Release {
        override val release = _release
        override val sevenDigitalId = _release7DigitalId

        override fun toString(): String {
            return """
Release: $release
7digital ID: $sevenDigitalId
""".trim()
        }
    }

    inner class Hdf5Artist : Song.Artist {
        override val name = _artistName
        override val id = _artistId
        override val mbId = _artistMbId
        override val playmeId = _artistPlaymeId
        override val sevenDigitalId = _artist7DigitalId
        override val familiarity = _artistFamiliarity
        override val hotttnesss = _artistHotttnesss
        override val location = Hdf5Location()
        override val mbTags = generateArray(_artistMbTags.size) { index ->
            Hdf5MbTag(index) as Song.Artist.MbTag
        }
        override val terms = generateArray(_artistTerms.size) { index ->
            Hdf5Terms(index) as Song.Artist.Terms
        }
        override val similar = _similarArtists

        inner class Hdf5MbTag(index: Int) : Song.Artist.MbTag {
            override val tag = _artistMbTags[index]
            override val count = _artistMbTagsCount[index]

            override fun toString(): String {
                return """
Tag: $tag
Count: $count
""".trim()
            }
        }

        inner class Hdf5Terms(index: Int) : Song.Artist.Terms {
            override val terms = _artistTerms[index]
            override val frequency = _artistTermsFrequency!![index]
            override val weight = _artistTermsWeight!![index]

            override fun toString(): String {
                return """
Terms: $terms
Frequency: $frequency
Weight: $weight
""".trim()
            }
        }

        inner class Hdf5Location : Song.Artist.Location {
            override val location = _artistLocation
            override val latitude = _artistLatitude
            override val longitude = _artistLongitude

            override fun toString(): String {
                return """
Location: $location
Latitude: $latitude
Longitude: $longitude
""".trim()
            }
        }

        override fun toString(): String {
            return """
Name: $name
ID: $id
MusicBrainz ID: $mbId
Playme ID: $playmeId
7digital ID: $sevenDigitalId
Familiarity: $familiarity
Hotttnesss: $hotttnesss
Location: ${"\n"}${location.toString().prependIndent()}
MusicBrainz tags: ${"\n"}${mbTags.joinToString(separator = "\n", limit = 10).prependIndent()}
The Echo Nest tags: ${"\n"}${terms.joinToString(separator = "\n", limit = 10).prependIndent()}
Similar artists: ${"\n"}${similar.joinToString(separator = "\n", limit = 10).prependIndent()}
""".trim()
        }
    }

    inner class Hdf5Section(index: Int) : Song.Section {
        override val start = _sectionsStart!![index]
        override val confidence = _sectionsConfidence!![index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Beat(index: Int) : Song.Beat {
        override val start = _beatsStart!![index]
        override val confidence = _beatsConfidence!![index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Bar(index: Int) : Song.Bar {
        override val start = _barsStart!![index]
        override val confidence = _barsConfidence!![index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Tatum(index: Int) : Song.Tatum {
        override val start = _tatumsStart!![index]
        override val confidence = _tatumsConfidence!![index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Segment(index: Int) : Song.Segment {
        override val start = _segmentsStart!![index]
        override val confidence = _segmentsConfidence!![index]
        override val pitch = _segmentsPitches!![index]
        override val timbre = _segmentsTimbre!![index]
        override val loudness = Hdf5Loudness(index)

        inner class Hdf5Loudness(index: Int) : Song.Segment.Loudness {
            override val max = _segmentsLoudnessMax!![index]
            override val maxTime = _segmentsLoudnessMaxTime!![index]
            override val start = _segmentsLoudnessStart!![index]

            override fun toString(): String {
                return """
Max: $max
Max time: $maxTime
Start: $start
""".trim()
            }
        }

        override fun toString(): String {
            return """
Artist: $start
Confidence: $confidence
Pitch: $pitch
Timbre: $timbre
Loudness: ${"\n"}${loudness.toString().prependIndent()}
""".trim()
        }
    }

    override fun toString(): String {
        return """
Artist: ${"\n"}${artist.toString().prependIndent()}
Release: ${"\n"}${release.toString().prependIndent()}
ID: $id
Hotttnesss: $hotttnesss
Title: $title
Analysis sample rate: $analysisSampleRate
Audio MD5 hash: $audioMd5
Danceability: $danceability
Track: ${"\n"}${track.toString().prependIndent()}
Duration: $duration
Fades: ${"\n"}${fades.toString().prependIndent()}
Energy: $energy
Key: ${"\n"}${key.toString().prependIndent()}
Loudness: $loudness
Mode: ${"\n"}${mode.toString().prependIndent()}
Tempo: $tempo
Time signature: ${"\n"}${timeSignature.toString().prependIndent()}
Segments: ${"\n"}${segments?.joinToString(separator = "\n", limit = 10)?.prependIndent()}
Sections: ${"\n"}${sections?.joinToString(separator = "\n", limit = 10)?.prependIndent()}
Beats: ${"\n"}${beats?.joinToString(separator = "\n", limit = 10)?.prependIndent()}
Bars: ${"\n"}${bars?.joinToString(separator = "\n", limit = 10)?.prependIndent()}
Tatums: ${"\n"}${tatums?.joinToString(separator = "\n", limit = 10)?.prependIndent()}
Year: $year
""".trim()
    }
}