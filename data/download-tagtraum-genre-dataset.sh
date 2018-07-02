#!/usr/bin/env bash

echo "Download the tagtraum genre annotations dataset."


if [ -f "archives/msd_tagtraum_cd1.cls.zip" ]; then
    echo "CD1 dataset was already downloaded."
else
    echo "Downloading test dataset."
    wget "http://www.tagtraum.com/genres/msd_tagtraum_cd1.cls.zip" -O "archives/msd_tagtraum_cd1.cls.zip"
fi
echo "Extracting CD1 dataset."
unzip -o "archives/msd_tagtraum_cd1.cls.zip" -d "genres"


if [ -f "archives/msd_tagtraum_cd2.cls.zip" ]; then
    echo "CD2 dataset was already downloaded."
else
    echo "Downloading test dataset."
    wget "http://www.tagtraum.com/genres/msd_tagtraum_cd2.cls.zip" -O "archives/msd_tagtraum_cd2.cls.zip"
fi
echo "Extracting CD2 dataset."
unzip -o "archives/msd_tagtraum_cd2.cls.zip" -d "genres"


if [ -f "archives/msd_tagtraum_cd2c.cls.zip" ]; then
    echo "CD2C dataset was already downloaded."
else
    echo "Downloading test dataset."
    wget "http://www.tagtraum.com/genres/msd_tagtraum_cd2c.cls.zip" -O "archives/msd_tagtraum_cd2c.cls.zip"
fi
echo "Extracting CD2C dataset."
unzip -o "archives/msd_tagtraum_cd2c.cls.zip" -d "genres"
