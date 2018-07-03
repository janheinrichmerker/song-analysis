# Datenanalyse des Million Song Dataset

## Einleitung

> Wie viele Daten braucht man, damit wir es 'Big Data' nennen können?  
> Eine Million sollten doch reichen, oder?

Die Aufgabe ist, auf einem möglichst nicht unter 10.000 Datenpunkten großen Datensatz 
eine oder nach belieben mehrere Problemstellungen zu lösen.

### Motivation / Wieso gerade Musik?

Von News-Headlines über Video-Streaming Metrics bis hin zu neusten Informationen zur menschlichen DNS. 
Eine umfangreiche Sammlung von Titel, Interpret, Herausgabejahr, usw. mit direkter Anbindung 
an weitere Datensätze, die z.B. das Analysieren von Songtexten ermöglichen, 
war außerhalb unseres Radars.
Der entscheidene Vorschlag kam von Til, welcher leider nicht mehr Teil der Gruppe ist: 
"Million Song Dataset." "Wie haben die denn genau eine Million Songs zusammen gekriegt?!"

Was diese Metadaten-Zusammenstellung der populärsten Musik um 2011 so attraktiv macht:
Jeder von uns kann sich mit dem Thema anfreunden und sah keine besondere Schwierigkeit, 
sich interessante Fragen zu stellen.
Außerdem: Jeder aus dem Team verfolgt ein musikalisches Hobby. 
Der eine lädt selbst gemixte Musik auf SoundCloud hoch, 
der andere bringt sich ein Instrument bei. 
Dadurch erweckt sich auch persönliches Interesse, 
zu sehen, z.B. welchen Einfluss der Lieblingskünstler auf die moderne Musikindustrie genommen hat.

**TODO**: Was speziell interessiert uns an den Daten?

## Struktur der Daten

**TODO**

### Zusätzliche Daten 

#### _MusiXmatch_ Lyrics

**TODO**

#### _tagtraum_ Genre Tags

**TODO**

## Fragestellungen

**TODO**

## Technische Umsetzung

**TODO**

### Herausforderungen

Bei der Analyse von Song-Dateien des "Million Song Dataset" 
liegt die Schwierigkeit nicht nur in der
großen Datenmenge von etwa 300 GB - ~3 GB in dem untersuchten Subset -, 
sondern auch in dem verwendeten Datenformat.

Die Dateien des "Million Song Dataset" liegen in Dateien des HDF5-Formates vor.
Diese benötigen eine eigene Bibliothek, um sie in Java einlesen zu können.
Die Einbindung von nativen Bibliotheken wiederum in Hadoop ist schwierig, da im Normalfall
die Datanodes auf physikalisch anderen Geräten liegen.
Hier musste also eine Lösung über den geteilten Cache des Hadoop File Systems gefunden werden.

**TODO**

## Ergebnisse

**TODO**

## Ausblick
 
### Analyse des kompletten Million Song Dataset

**TODO**: Was ändern mehr Daten?
