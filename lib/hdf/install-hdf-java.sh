#!/usr/bin/env bash

echo "Start installation of HDF Java."
if [ -f "HDFJava-3.3.2-Linux.sh" ]; then
    echo "HDF Java was already downloaded."
else
    echo "Downloading HDF Java."
    wget "http://support.hdfgroup.org/ftp/HDF5/releases/HDF-JAVA/hdfjni-3.3.2/bin/HDFJava-3.3.2_Stat-centos7_64.tar.gz"
    echo "Unpacking HDF Java."
    tar -zxvf "HDFJava-3.3.2_Stat-centos7_64.tar.gz" "hdf/HDFJava-3.3.2-Linux.sh"
    mv "hdf/HDFJava-3.3.2-Linux.sh" "HDFJava-3.3.2-Linux.sh"
fi

echo "Running HDF Java install script."
./HDFJava-3.3.2-Linux.sh

echo "Moving HDF Java library files."
if [ -d "HDF_Group/HDFJava/3.3.2/" ]; then
    mv "HDF_Group/HDFJava/3.3.2/" "hdf-java"
    rm -r "HDF_Group/"
elif [ -d "HDFJava-3.3.2-Linux/HDF_Group/HDFJava/3.3.2/" ]; then
    mv "HDFJava-3.3.2-Linux/HDF_Group/HDFJava/3.3.2/" "hdf-java"
    rm -r "HDFJava-3.3.2-Linux/"
else
    exit 1
fi

