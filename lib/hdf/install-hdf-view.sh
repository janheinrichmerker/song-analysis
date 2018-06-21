#!/usr/bin/env bash

echo "Start installation of HDF View."
if [ -f "HDFView-2.14.0-Linux.sh" ]; then
    echo "HDF View was already downloaded."
else
    echo "Downloading HDF View."
    wget "http://support.hdfgroup.org/ftp/HDF5/hdf-java/current/bin/HDFView-2.14-centos7_64.tar.gz"
    echo "Unpacking HDF View."
    tar -zxvf "HDFView-2.14-centos7_64.tar.gz" "HDFView-2.14.0-Linux.sh"
fi

echo "Running HDF View install script."
./HDFView-2.14.0-Linux.sh

echo "Moving HDF View library files."
if [ -d "HDFView/2.14.0/" ]; then
    mv "HDFView/2.14.0/" "hdf-view"
    rm -r "HDFView/"
elif [ -d "HDFView-2.14.0-Linux/HDFView/2.14.0/" ]; then
    mv "HDFView-2.14.0-Linux/HDFView/2.14.0/" "hdf-view"
    rm -r "HDFView-2.14.0-Linux/"
else
    exit 1
fi

