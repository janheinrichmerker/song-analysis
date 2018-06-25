package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import hdf.`object`.h5.H5File
import org.apache.hadoop.io.WritableComparable
import java.io.DataInput
import java.io.DataOutput
import kotlin.properties.Delegates

class Hdf5Song() : Song, WritableComparable<Hdf5Song> {

    private var _artistFamiliarity: Double by Delegates.notNull()
    private var _artistHotttnesss: Double by Delegates.notNull()
    private var _artistId: String by Delegates.notNull()
    private var _artistMbId: String by Delegates.notNull()
    private var _artistPlaymeId: Int by Delegates.notNull()
    private var _artist7DigitalId: Int by Delegates.notNull()
    private var _artistLatitude: Double by Delegates.notNull()
    private var _artistLongitude: Double by Delegates.notNull()
    private var _artistLocation: String by Delegates.notNull()
    private var _artistName: String by Delegates.notNull()
    private var _release: String by Delegates.notNull()
    private var _release7DigitalId: Int by Delegates.notNull()
    private var _songId: String by Delegates.notNull()
    private var _songHotttnesss: Double by Delegates.notNull()
    private var _title: String by Delegates.notNull()
    private var _track7DigitalId: Int by Delegates.notNull()
    private var _similarArtists: Array<String> by Delegates.notNull()
    private var _artistTerms: Array<String> by Delegates.notNull()
    private var _artistTermsFrequency: DoubleArray by Delegates.notNull()
    private var _artistTermsWeight: DoubleArray by Delegates.notNull()
    private var _analysisSampleRate: Double by Delegates.notNull()
    private var _audioMd5: String by Delegates.notNull()
    private var _danceability: Double by Delegates.notNull()
    private var _duration: Double by Delegates.notNull()
    private var _fadeInEnd: Double by Delegates.notNull()
    private var _energy: Double by Delegates.notNull()
    private var _key: Int by Delegates.notNull()
    private var _keyConfidence: Double by Delegates.notNull()
    private var _loudness: Double by Delegates.notNull()
    private var _mode: Int by Delegates.notNull()
    private var _modeConfidence: Double by Delegates.notNull()
    private var _fadeOutStart: Double by Delegates.notNull()
    private var _tempo: Double by Delegates.notNull()
    private var _timeSignature: Int by Delegates.notNull()
    private var _timeSignatureConfidence: Double by Delegates.notNull()
    private var _trackId: String by Delegates.notNull()
    private var _segmentsStart: DoubleArray by Delegates.notNull()
    private var _segmentsConfidence: DoubleArray by Delegates.notNull()
    private var _segmentsPitches: DoubleArray by Delegates.notNull()
    private var _segmentsTimbre: DoubleArray by Delegates.notNull()
    private var _segmentsLoudnessMax: DoubleArray by Delegates.notNull()
    private var _segmentsLoudnessMaxTime: DoubleArray by Delegates.notNull()
    private var _segmentsLoudnessStart: DoubleArray by Delegates.notNull()
    private var _sectionsStart: DoubleArray by Delegates.notNull()
    private var _sectionsConfidence: DoubleArray by Delegates.notNull()
    private var _beatsStart: DoubleArray by Delegates.notNull()
    private var _beatsConfidence: DoubleArray by Delegates.notNull()
    private var _barsStart: DoubleArray by Delegates.notNull()
    private var _barsConfidence: DoubleArray by Delegates.notNull()
    private var _tatumsStart: DoubleArray by Delegates.notNull()
    private var _tatumsConfidence: DoubleArray by Delegates.notNull()
    private var _year: Int by Delegates.notNull()
    private var _artistMbTags: Array<String> by Delegates.notNull()
    private var _artistMbTagsCount: IntArray by Delegates.notNull()

    constructor(file: H5File, songIndex: Int = 0) : this() {
        _artistFamiliarity = file.getArtistFamiliarity(songIndex)
        _artistHotttnesss = file.getArtistHotttnesss(songIndex)
        _artistId = file.getArtistId(songIndex)
        _artistMbId = file.getArtistMbId(songIndex)
        _artistPlaymeId = file.getArtistPlaymeId(songIndex)
        _artist7DigitalId = file.getArtist7DigitalId(songIndex)
        _artistLatitude = file.getArtistLatitude(songIndex)
        _artistLongitude = file.getArtistLongitude(songIndex)
        _artistLocation = file.getArtistLocation(songIndex)
        _artistName = file.getArtistName(songIndex)
        _release = file.getRelease(songIndex)
        _release7DigitalId = file.getRelease7DigitalId(songIndex)
        _songId = file.getSongId(songIndex)
        _songHotttnesss = file.getSongHotttnesss(songIndex)
        _title = file.getTitle(songIndex)
        _track7DigitalId = file.getTrack7DigitalId(songIndex)
        _similarArtists = file.getSimilarArtists(songIndex)
        _artistTerms = file.getArtistTerms(songIndex)
        _artistTermsFrequency = file.getArtistTermsFrequency(songIndex) ?: DoubleArray(0)
        _artistTermsWeight = file.getArtistTermsWeight(songIndex) ?: DoubleArray(0)
        _analysisSampleRate = 0.0 //file.getAnalysisSampleRate(songIndex)
        _audioMd5 = file.getAudioMd5(songIndex)
        _danceability = file.getDanceability(songIndex)
        _duration = file.getDuration(songIndex)
        _fadeInEnd = file.getFadeInEnd(songIndex)
        _energy = file.getEnergy(songIndex)
        _key = file.getKey(songIndex)
        _keyConfidence = file.getKeyConfidence(songIndex)
        _loudness = file.getLoudness(songIndex)
        _mode = file.getMode(songIndex)
        _modeConfidence = file.getModeConfidence(songIndex)
        _fadeOutStart = file.getFadeOutStart(songIndex)
        _tempo = file.getTempo(songIndex)
        _timeSignature = file.getTimeSignature(songIndex)
        _timeSignatureConfidence = file.getTimeSignatureConfidence(songIndex)
        _trackId = file.getTrackId(songIndex)
        _segmentsStart = file.getSegmentsStart(songIndex) ?: DoubleArray(0)
        _segmentsConfidence = file.getSegmentsConfidence(songIndex) ?: DoubleArray(0)
        _segmentsPitches = file.getSegmentsPitches(songIndex) ?: DoubleArray(0)
        _segmentsTimbre = file.getSegmentsTimbre(songIndex) ?: DoubleArray(0)
        _segmentsLoudnessMax = file.getSegmentsLoudnessMax(songIndex) ?: DoubleArray(0)
        _segmentsLoudnessMaxTime = file.getSegmentsLoudnessMaxTime(songIndex) ?: DoubleArray(0)
        _segmentsLoudnessStart = file.getSegmentsLoudnessStart(songIndex) ?: DoubleArray(0)
        _sectionsStart = file.getSectionsStart(songIndex) ?: DoubleArray(0)
        _sectionsConfidence = file.getSectionsConfidence(songIndex) ?: DoubleArray(0)
        _beatsStart = file.getBeatsStart(songIndex) ?: DoubleArray(0)
        _beatsConfidence = file.getBeatsConfidence(songIndex) ?: DoubleArray(0)
        _barsStart = file.getBarsStart(songIndex) ?: DoubleArray(0)
        _barsConfidence = file.getBarsConfidence(songIndex) ?: DoubleArray(0)
        _tatumsStart = file.getTatumsStart(songIndex) ?: DoubleArray(0)
        _tatumsConfidence = file.getTatumsConfidence(songIndex) ?: DoubleArray(0)
        _year = file.getYear(songIndex)
        _artistMbTags = file.getArtistMbTags(songIndex)
        _artistMbTagsCount = file.getArtistMbTagsCount(songIndex)
    }

    override fun readFields(input: DataInput) {
        _artistFamiliarity = input.readDouble()
        _artistHotttnesss = input.readDouble()
        _artistId = input.readUTF()
        _artistMbId = input.readUTF()
        _artistPlaymeId = input.readInt()
        _artist7DigitalId = input.readInt()
        _artistLatitude = input.readDouble()
        _artistLongitude = input.readDouble()
        _artistLocation = input.readUTF()
        _artistName = input.readUTF()
        _release = input.readUTF()
        _release7DigitalId = input.readInt()
        _songId = input.readUTF()
        _songHotttnesss = input.readDouble()
        _title = input.readUTF()
        _track7DigitalId = input.readInt()
        _similarArtists = input.readUTFArray()
        _artistTerms = input.readUTFArray()
        _artistTermsFrequency = input.readDoubleArray()
        _artistTermsWeight = input.readDoubleArray()
        _analysisSampleRate = input.readDouble()
        _audioMd5 = input.readUTF()
        _danceability = input.readDouble()
        _duration = input.readDouble()
        _fadeInEnd = input.readDouble()
        _energy = input.readDouble()
        _key = input.readInt()
        _keyConfidence = input.readDouble()
        _loudness = input.readDouble()
        _mode = input.readInt()
        _modeConfidence = input.readDouble()
        _fadeOutStart = input.readDouble()
        _tempo = input.readDouble()
        _timeSignature = input.readInt()
        _timeSignatureConfidence = input.readDouble()
        _trackId = input.readUTF()
        _segmentsStart = input.readDoubleArray()
        _segmentsConfidence = input.readDoubleArray()
        _segmentsPitches = input.readDoubleArray()
        _segmentsTimbre = input.readDoubleArray()
        _segmentsLoudnessMax = input.readDoubleArray()
        _segmentsLoudnessMaxTime = input.readDoubleArray()
        _segmentsLoudnessStart = input.readDoubleArray()
        _sectionsStart = input.readDoubleArray()
        _sectionsConfidence = input.readDoubleArray()
        _beatsStart = input.readDoubleArray()
        _beatsConfidence = input.readDoubleArray()
        _barsStart = input.readDoubleArray()
        _barsConfidence = input.readDoubleArray()
        _tatumsStart = input.readDoubleArray()
        _tatumsConfidence = input.readDoubleArray()
        _year = input.readInt()
        _artistMbTags = input.readUTFArray()
        _artistMbTagsCount = input.readIntArray()
    }

    override fun compareTo(other: Hdf5Song) = artist.id.compareTo(other.artist.id, ignoreCase = true)

    override fun write(output: DataOutput) {
        output.writeDouble(_artistFamiliarity)
        output.writeDouble(_artistHotttnesss)
        output.writeUTF(_artistId)
        output.writeUTF(_artistMbId)
        output.writeInt(_artistPlaymeId)
        output.writeInt(_artist7DigitalId)
        output.writeDouble(_artistLatitude)
        output.writeDouble(_artistLongitude)
        output.writeUTF(_artistLocation)
        output.writeUTF(_artistName)
        output.writeUTF(_release)
        output.writeInt(_release7DigitalId)
        output.writeUTF(_songId)
        output.writeDouble(_songHotttnesss)
        output.writeUTF(_title)
        output.writeInt(_track7DigitalId)
        output.writeUTFArray(_similarArtists)
        output.writeUTFArray(_artistTerms)
        output.writeDoubleArray(_artistTermsFrequency)
        output.writeDoubleArray(_artistTermsWeight)
        output.writeDouble(_analysisSampleRate)
        output.writeUTF(_audioMd5)
        output.writeDouble(_danceability)
        output.writeDouble(_duration)
        output.writeDouble(_fadeInEnd)
        output.writeDouble(_energy)
        output.writeInt(_key)
        output.writeDouble(_keyConfidence)
        output.writeDouble(_loudness)
        output.writeInt(_mode)
        output.writeDouble(_modeConfidence)
        output.writeDouble(_fadeOutStart)
        output.writeDouble(_tempo)
        output.writeInt(_timeSignature)
        output.writeDouble(_timeSignatureConfidence)
        output.writeUTF(_trackId)
        output.writeDoubleArray(_segmentsStart)
        output.writeDoubleArray(_segmentsConfidence)
        output.writeDoubleArray(_segmentsPitches)
        output.writeDoubleArray(_segmentsTimbre)
        output.writeDoubleArray(_segmentsLoudnessMax)
        output.writeDoubleArray(_segmentsLoudnessMaxTime)
        output.writeDoubleArray(_segmentsLoudnessStart)
        output.writeDoubleArray(_sectionsStart)
        output.writeDoubleArray(_sectionsConfidence)
        output.writeDoubleArray(_beatsStart)
        output.writeDoubleArray(_beatsConfidence)
        output.writeDoubleArray(_barsStart)
        output.writeDoubleArray(_barsConfidence)
        output.writeDoubleArray(_tatumsStart)
        output.writeDoubleArray(_tatumsConfidence)
        output.writeInt(_year)
        output.writeUTFArray(_artistMbTags)
        output.writeIntArray(_artistMbTagsCount)
    }

    override val artist by lazy { Hdf5Artist() }
    override val release by lazy { Hdf5Release() }
    override val id by lazy { _songId }
    override val hotttnesss by lazy { _songHotttnesss }
    override val title by lazy { _title }
    override val analysisSampleRate by lazy { _analysisSampleRate }
    override val audioMd5 by lazy { _audioMd5 }
    override val danceability by lazy { _danceability }
    override val duration by lazy { _duration }
    override val fades by lazy { Hdf5Fades() }
    override val energy by lazy { _energy }
    override val key by lazy { Hdf5Key() }
    override val loudness by lazy { _loudness }
    override val mode by lazy { Hdf5Mode() }
    override val tempo by lazy { _tempo }
    override val timeSignature by lazy { Hdf5TimeSignature() }
    override val track by lazy { Hdf5Track() }
    override val segments by lazy { Array<Song.Segment>(_segmentsStart.size) { Hdf5Segment(it) } }
    override val sections by lazy { Array<Song.Section>(_sectionsStart.size) { Hdf5Section(it) } }
    override val beats by lazy { Array<Song.Beat>(_beatsStart.size) { Hdf5Beat(it) } }
    override val bars by lazy { Array<Song.Bar>(_barsStart.size) { Hdf5Bar(it) } }
    override val tatums by lazy { Array<Song.Tatum>(_tatumsStart.size) { Hdf5Tatum(it) } }
    override val year by lazy { _year }

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
        override val mbTags = Array<Song.Artist.MbTag>(_artistMbTags.size) { Hdf5MbTag(it) }
        override val terms = Array<Song.Artist.Terms>(_artistTerms.size) { Hdf5Terms(it) }
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
            override val frequency = _artistTermsFrequency[index]
            override val weight = _artistTermsWeight[index]

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
        override val start = _sectionsStart[index]
        override val confidence = _sectionsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Beat(index: Int) : Song.Beat {
        override val start = _beatsStart[index]
        override val confidence = _beatsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Bar(index: Int) : Song.Bar {
        override val start = _barsStart[index]
        override val confidence = _barsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Tatum(index: Int) : Song.Tatum {
        override val start = _tatumsStart[index]
        override val confidence = _tatumsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Segment(index: Int) : Song.Segment {
        override val start = _segmentsStart[index]
        override val confidence = _segmentsConfidence[index]
        override val pitch = _segmentsPitches[index]
        override val timbre = _segmentsTimbre[index]
        override val loudness = Hdf5Loudness(index)

        inner class Hdf5Loudness(index: Int) : Song.Segment.Loudness {
            override val max = _segmentsLoudnessMax[index]
            override val maxTime = _segmentsLoudnessMaxTime[index]
            override val start = _segmentsLoudnessStart[index]

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
Segments: ${"\n"}${segments.joinToString(separator = "\n", limit = 10).prependIndent()}
Sections: ${"\n"}${sections.joinToString(separator = "\n", limit = 10).prependIndent()}
Beats: ${"\n"}${beats.joinToString(separator = "\n", limit = 10).prependIndent()}
Bars: ${"\n"}${bars.joinToString(separator = "\n", limit = 10).prependIndent()}
Tatums: ${"\n"}${tatums.joinToString(separator = "\n", limit = 10).prependIndent()}
Year: $year
""".trim()
    }
}