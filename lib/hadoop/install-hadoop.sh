#!/usr/bin/env bash

echo "Install Hadoop executables."

if type -p java; then
    echo "Found Java executable in PATH."
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
    echo "Found Java executable in JAVA_HOME."     
else
    echo "No Java executable found. Aborting installation."
    exit 1
fi

if [ -f "hadoop-3.1.0.tar.gz" ]; then
    echo "Hadoop was already downloaded."
else
    echo "Downloading Hadoop."
    wget "http://www-eu.apache.org/dist/hadoop/common/hadoop-3.1.0/hadoop-3.1.0.tar.gz"
fi

echo "Extracting Hadoop."
rm -rf "hadoop-3.1.0"
tar -xzvf "hadoop-3.1.0.tar.gz"

