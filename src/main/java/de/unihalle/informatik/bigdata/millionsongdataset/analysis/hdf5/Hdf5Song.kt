package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hdf5

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop.WritableSongHandle
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.SongHandle
import hdf.`object`.h5.H5File
import java.io.DataInput
import java.io.DataOutput

class Hdf5Song internal constructor(private val handle: SongHandle) : Song {

    internal constructor(file: H5File, songIndex: Int = 0) : this(Hdf5SongHandle(file, songIndex))
    @Suppress("UNUSED") // The empty constructor is needed so that Hadoop can create songs per reflection.
    internal constructor() : this(WritableSongHandle())

    override fun readFields(input: DataInput) {
        handle.readFields(input)
    }

    override fun compareTo(other: Song) = id.compareTo(other.id, ignoreCase = true)

    override fun write(output: DataOutput) {
        handle.write(output)
    }

    override val artist by lazy { Hdf5Artist() }
    override val release by lazy { Hdf5Release() }
    override val id by lazy { handle.songId }
    override val hotttnesss by lazy { handle.songHotttnesss }
    override val title by lazy { handle.title }
    override val analysisSampleRate by lazy { handle.analysisSampleRate }
    override val audioMd5 by lazy { handle.audioMd5 }
    override val danceability by lazy { handle.danceability }
    override val duration by lazy { handle.duration }
    override val fades by lazy { Hdf5Fades() }
    override val energy by lazy { handle.energy }
    override val key by lazy { Hdf5Key() }
    override val loudness by lazy { handle.loudness }
    override val mode by lazy { Hdf5Mode() }
    override val tempo by lazy { handle.tempo }
    override val timeSignature by lazy { Hdf5TimeSignature() }
    override val track by lazy { Hdf5Track() }
    override val segments by lazy { Array<Song.Segment>(handle.segmentsStart.size) { Hdf5Segment(it) } }
    override val sections by lazy { Array<Song.Section>(handle.sectionsStart.size) { Hdf5Section(it) } }
    override val beats by lazy { Array<Song.Beat>(handle.beatsStart.size) { Hdf5Beat(it) } }
    override val bars by lazy { Array<Song.Bar>(handle.barsStart.size) { Hdf5Bar(it) } }
    override val tatums by lazy { Array<Song.Tatum>(handle.tatumsStart.size) { Hdf5Tatum(it) } }
    override val year by lazy { handle.year }

    inner class Hdf5Fades : Song.Fades {
        override val fadeInEnd = handle.fadeInEnd
        override val fadeOutStart = handle.fadeOutStart

        override fun toString(): String {
            return """
Fade-in end: $fadeInEnd
Fade-out start: $fadeOutStart
""".trim()
        }
    }

    inner class Hdf5Key : Song.Key {
        override val key = handle.key
        override val confidence = handle.keyConfidence

        override fun toString(): String {
            return """
Key: $key
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Mode : Song.Mode {
        override val mode = handle.mode
        override val confidence = handle.modeConfidence

        override fun toString(): String {
            return """
Mode: $mode
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5TimeSignature : Song.TimeSignature {
        override val timeSignature = handle.timeSignature
        override val confidence = handle.timeSignatureConfidence

        override fun toString(): String {
            return """
Time signature: $timeSignature
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Track : Song.Track {
        override val id = handle.trackId
        override val sevenDigitalId = handle.track7DigitalId

        override fun toString(): String {
            return """
ID: $id
7digital ID: $sevenDigitalId
""".trim()
        }
    }

    inner class Hdf5Release : Song.Release {
        override val release = handle.release
        override val sevenDigitalId = handle.release7DigitalId

        override fun toString(): String {
            return """
Release: $release
7digital ID: $sevenDigitalId
""".trim()
        }
    }

    inner class Hdf5Artist : Song.Artist {
        override val name = handle.artistName
        override val id = handle.artistId
        override val mbId = handle.artistMbId
        override val playmeId = handle.artistPlaymeId
        override val sevenDigitalId = handle.artist7DigitalId
        override val familiarity = handle.artistFamiliarity
        override val hotttnesss = handle.artistHotttnesss
        override val location = Hdf5Location()
        override val mbTags = Array<Song.Artist.MbTag>(handle.artistMbTags.size) { Hdf5MbTag(it) }
        override val terms = Array<Song.Artist.Terms>(handle.artistTerms.size) { Hdf5Terms(it) }
        override val similar = handle.similarArtists

        inner class Hdf5MbTag(index: Int) : Song.Artist.MbTag {
            override val tag = handle.artistMbTags[index]
            override val count = handle.artistMbTagsCount[index]

            override fun toString(): String {
                return """
Tag: $tag
Count: $count
""".trim()
            }
        }

        inner class Hdf5Terms(index: Int) : Song.Artist.Terms {
            override val terms = handle.artistTerms[index]
            override val frequency = handle.artistTermsFrequency[index]
            override val weight = handle.artistTermsWeight[index]

            override fun toString(): String {
                return """
Terms: $terms
Frequency: $frequency
Weight: $weight
""".trim()
            }
        }

        inner class Hdf5Location : Song.Artist.Location {
            override val location = handle.artistLocation
            override val latitude = handle.artistLatitude
            override val longitude = handle.artistLongitude

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
        override val start = handle.sectionsStart[index]
        override val confidence = handle.sectionsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Beat(index: Int) : Song.Beat {
        override val start = handle.beatsStart[index]
        override val confidence = handle.beatsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Bar(index: Int) : Song.Bar {
        override val start = handle.barsStart[index]
        override val confidence = handle.barsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Tatum(index: Int) : Song.Tatum {
        override val start = handle.tatumsStart[index]
        override val confidence = handle.tatumsConfidence[index]

        override fun toString(): String {
            return """
Start: $start
Confidence: $confidence
""".trim()
        }
    }

    inner class Hdf5Segment(index: Int) : Song.Segment {
        override val start = handle.segmentsStart[index]
        override val confidence = handle.segmentsConfidence[index]
        override val pitch = handle.segmentsPitches[index]
        override val timbre = handle.segmentsTimbre[index]
        override val loudness = Hdf5Loudness(index)

        inner class Hdf5Loudness(index: Int) : Song.Segment.Loudness {
            override val max = handle.segmentsLoudnessMax[index]
            override val maxTime = handle.segmentsLoudnessMaxTime[index]
            override val start = handle.segmentsLoudnessStart[index]

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