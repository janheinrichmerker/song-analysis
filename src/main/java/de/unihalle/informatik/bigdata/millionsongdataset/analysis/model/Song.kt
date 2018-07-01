package de.unihalle.informatik.bigdata.millionsongdataset.analysis.model

import org.apache.hadoop.io.WritableComparable

interface Song : WritableComparable<Song> {

    val artist: Artist
    val release: Release
    val id: String
    val hotttnesss: Double
    val title: String
    /**
     * Sample rate of the audio used for the song analysis.
     */
    val analysisSampleRate: Int
    val audioMd5: String
    val danceability: Double
    val duration: Double
    val fades: Fades
    val energy: Double
    val key: Key
    val loudness: Double
    val mode: Mode
    val tempo: Double
    val timeSignature: TimeSignature
    val track: Track
    val segments: Array<Segment>?
    val sections: Array<Section>?
    val beats: Array<Beat>?
    val bars: Array<Bar>?
    val tatums: Array<Tatum>?
    val year: Int

    interface Fades {
        val fadeInEnd: Double
        val fadeOutStart: Double
    }

    interface Key {
        val key: Int
        val confidence: Double
    }

    interface Mode {
        val mode: Int
        val confidence: Double
    }


    interface TimeSignature {
        val timeSignature: Int
        val confidence: Double
    }

    interface Track {
        val id: String
        val sevenDigitalId: Int
    }

    interface Release {
        val release: String
        val sevenDigitalId: Int
    }

    interface Artist {
        val name: String
        val id: String
        val mbId: String
        val playmeId: Int?
        val sevenDigitalId: Int?
        val familiarity: Double
        val hotttnesss: Double
        val location: Location
        val mbTags: Array<MbTag>
        val terms: Array<Terms>
        val similar: Array<String>

        interface MbTag {
            val tag: String
            val count: Int
        }

        interface Terms {
            val terms: String
            val frequency: Double
            val weight: Double
        }

        interface Location {
            val label: String
            val latitude: Double
            val longitude: Double
        }
    }

    interface Section {
        val start: Double
        val confidence: Double
    }

    interface Beat {
        val start: Double
        val confidence: Double
    }

    interface Bar {
        val start: Double
        val confidence: Double
    }

    interface Tatum {
        val start: Double
        val confidence: Double
    }

    interface Segment {
        val start: Double
        val confidence: Double
        val pitch: Double
        val timbre: Double
        val loudness: Loudness

        interface Loudness {
            val max: Double
            val maxTime: Double
            val start: Double
        }
    }
}