#!/usr/bin/env bash

echo "Download The Million Song Subset."

if [ -f "archives/millionsongsubset_full.tar.gz" ]; then
    echo "Subset was already downloaded."
else
    echo "Downloading subset."
    wget "http://static.echonest.com/millionsongsubset_full.tar.gz" -O "archives/millionsongsubset_full.tar.gz"
fi

echo "Extracting additional files."
mkdir -p "temp"
tar -zxvf "archives/millionsongsubset_full.tar.gz" -C "temp/" "MillionSongSubset/AdditionalFiles"
rm -rf "additional-files"
mkdir "additional-files"
mv "temp/MillionSongSubset/AdditionalFiles/"* "additional-files/"
rm -r "temp"

echo "Extracting song files."
mkdir -p "temp"
tar -zxvf "archives/millionsongsubset_full.tar.gz" -C "temp/" "MillionSongSubset/data"
rm -rf "songs"
mkdir "songs"
mv "temp/MillionSongSubset/data/"* "songs/"
rm -r "temp"

