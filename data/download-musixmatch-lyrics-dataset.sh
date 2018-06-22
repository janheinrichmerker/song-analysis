#!/usr/bin/env bash

echo "Download the musiXmatch lyrics train dataset."

if [ -f "archives/mxm_dataset_test.txt.zip" ]; then
    echo "Test dataset was already downloaded."
else
    echo "Downloading test dataset."
    wget "https://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_test.txt.zip" -O "archives/mxm_dataset_test.txt.zip"
fi
echo "Extracting test dataset."
unzip -o "archives/mxm_dataset_test.txt.zip" -d "lyrics"

if [ -f "archives/mxm_dataset_train.txt.zip" ]; then
    echo "Train dataset was already downloaded."
else
    echo "Downloading train dataset."
    wget "https://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_train.txt.zip" -O "archives/mxm_dataset_train.txt.zip"
fi
echo "Extracting train dataset."
unzip -o "archives/mxm_dataset_train.txt.zip" -d "lyrics"

