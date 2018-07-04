# Analysing the Million Song Dataset

## Abstract

New York is the hotttest town for music artists.

## Introduction

> How much data do you need to be able to call it _"Big Data"_?  
> – Turns out a million is just the right amount!

Part of the "Big Data Analytics" lecture is to analyse a dataset, not much smaller than 10 000 data points, with respect to one or more  problems.

### Motivation

#### Why Music?

From news headlines to video streaming metrics to the newest information about the human DNS: Big datasets are everywhere around us.

A comprehensive collection of titles, artists, publishing year etc. - with direct connections to further datasets such as lyrics or genres - seems to be the optimal subject of investigation.
What makes this collection of song metadata from 2011 so attractive: Everyone can easily identify with the relevance of the data. Music accompanies us in everyday life, may it be learning an instrument or mixing music. 
Therefore comes a natural personal interest, to learn [...].

**TODO**: What is our specific interest in the choosen data?

## Data

"The _Million Song Dataset_ is a freely-available collection of audio features and metadata for a million contemporary popular music tracks."[^millionsong-home]

[^millionsong-home]: https://labrosa.ee.columbia.edu/millionsong/

### Structure

The dataset contains 1 000 000 track files. Each one is stored in the HDF5 format[^hdf5-home] and consists of the following fields:[^millionsong-field-list ]

|Field name|Type|Description|
|:---|:---|
|Analysis sample rate|`Float`|Sample rate of the audio used|
|Artist 7digitalid|`Int`|ID from 7digital.com or -1|
|Artist familiarity|`Float`|Algorithmic estimation|
|Artist hotttnesss|`Float`|Algorithmic estimation|
|Artist id|`String`|Echo Nest ID|
|Artist latitude|`Float`|Latitude|
|Artist location|`String`|Location name|
|Artist longitude|`Float`|Longitude|
|Artist mbid|`String`|ID from musicbrainz.org|
|Artist mbtags|`Array<String>`|Tags from musicbrainz.org|
|Artist mbtags count|`Array<Int>`|Tag counts for musicbrainz tags|
|Artist name|`String`|Artist name|
|Artist playmeid|`Int`|ID from playme.com, or -1|
|Artist terms|`Array<String>`|Echo Nest tags|
|Artist terms freq|`Array<Float>`|Echo Nest tags freqs|
|Artist terms weight|`Array<Float>`|Echo Nest tags weight|
|Audio md5|`String`|Audio hash code|
|Bars confidence|`Array<Float>`|Confidence measure|
|Bars start|`Array<Float>`|Beginning of bars, usually on a beat|
|Beats confidence|`Array<Float>`|Confidence measure|
|Beats start|`Array<Float>`|Result of beat tracking|
|Danceability|`Float`|Algorithmic estimation|
|Duration|`Float`|In seconds|
|End of fade in|`Float`|Seconds at the beginning of the song|
|Energy|`Float`|Energy from listener point of view|
|Key|`Int`|Key the song is in|
|Key confidence|`Float`|Confidence measure|
|Loudness|`Float`|Overall loudness in dB|
|Mode|`Int`|Major or minor|
|Mode confidence|`Float`|Confidence measure|
|Release|`String`|Album name|
|Release 7digitalid|`Int`|ID from 7digital.com or -1|
|Sections confidence|`Array<Float>`|Confidence measure|
|Sections start|`Array<Float>`|Largest grouping in a song, e.g. verse|
|Segments confidence|`Array<Float>`|Confidence measure|
|Segments loudness max|`Array<Float>`|Max dB value|
|Segments loudness max time|`Array<Float>`|Time of max dB value, i.e. end of attack|
|Segments loudness max start|`Array<Float>`|dB value at onset|
|Segments pitches|`2dArray<Float>`|Chroma feature, one value per note|
|Segments start|`Array<Float>`|Musical events, ~ note onsets|
|Segments timbre|`2dArray<Float>`|Texture features (MFCC+PCA-like)|
|Similar artists|`Array<String>`|Echo Nest artist IDs (sim. algo. unpublished)|
|Song hotttnesss|`Float`|Algorithmic estimation|
|Song id|`String`|Echo Nest song ID|
|Start of fade out|`Float`|Time in sec|
|Tatums confidence|`Array<Float>`|Confidence measure|
|Tatums start|`Array<Float>`|Smallest rythmic element|
|Tempo|`Float`|Estimated tempo in BPM|
|Time signature|`Int`|Estimate of number of beats per bar, e.g. 4|
|Time signature confidence|`Float`|Confidence measure|
|Title|`String`|Song title|
|Track id|`String`|Echo Nest track ID|
|Track 7digitalid|`Int`|ID from 7digital.com or -1|
|Year|`Int`|Song release year from MusicBrainz or 0|

[^millionsong-field-list]: https://labrosa.ee.columbia.edu/millionsong/pages/field-list
[^hdf5-home]: https://support.hdfgroup.org/HDF5/

### Additional datasets

The huge advantage of the _Million Song Dataset_ is that it provides easy access to additional third party data sets.
Many papers and projects already exist, that build uppon the _Million Song Dataset_ and provide additional data.

#### _MusiXmatch_ lyrics dataset

**TODO**

#### _tagtraum_ genre annotation dataset

**TODO**

## Questions

**TODO**

## Technical implementation

**TODO**: Java, Hadoop, ...

### Challanges

During analysing track files from the _Million Song Dataset_ challanges arise not only from the huge data size of roughly 300 GB - ~3 GB for the studied subset -, 
but also from the used HDF5 data format[^hdf5-home].
A custom Hadoop `InputFormat` and a serializable `Song` class had to be written to support parsing and storing the HDF5 song files in a format usable in Hadoop.
Additionally parsing HDF5 files in Java requires the use of a native library, though including native libraries in Hadoop is difficult, because normally each Hadoop data node is running on a separate physical machine, thus one needs to configure the native library on each node.
Writing custom wrappers around the Hadoop analyse tool and using Hadoop file system's shared cache it was possible to bypass this problem.

Running Hadoop on a single-node cluster of course is not using Hadoop's full potential. Though, as there was no multi-node cluster available at the time of writing, this project was limited to a single-node cluster.
For each analysed input file Hadoop creates a mapper. Although a mapper can process multiple splits (regions) of a file, the custom input format made for HDF5 songs doesn't support splits - neither does the underlying HDF5 library. Consequently Hadoop would have to create 1 000 000 mappers (10 000 for the subset) which of course overflows the RAM of most consumer hardware.
Therefore only parts of the subset, the `B` sub-folder, were analysed.

## Results

**TODO**

## Outlook
 
### Analysing the whole dataset

**TODO**: Whats impacts has analysing more data?

## Bibliography

1. Thierry Bertin-Mahieux, Daniel P.W. Ellis, Brian Whitman, and Paul Lamere. 
The Million Song Dataset. In Proceedings of the 12th International Society
for Music Information Retrieval Conference (ISMIR 2011), 2011.
2. Hendrik Schreiber. Improving genre annotations for the million song dataset. In Proceedings of the 16th International Society for Music Information Retrieval Conference (ISMIR), pages 241-247, Málaga, Spain, Oct. 2015.

