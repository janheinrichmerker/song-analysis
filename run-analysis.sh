#!/bin/sh

# Build JAR using Gradle.
./gradlew shadowJar

# Specify the path to the analysis task JAR.
TASK_JAR=build/libs/analysis-0.1.0-all.jar
# Specify input data path.
INPUT_DATA=data/input
# Specify output data path.
OUTPUT_DATA=data/output

# Clear output directory.
if [ -d "$OUTPUT_DATA" ]; then
    rm -r ${OUTPUT_DATA}
fi


# Run the data analysis task.
/usr/local/hadoop/bin/hadoop -Djava.library.path "hdf-view/lib" jar ${TASK_JAR} ${INPUT_DATA} ${OUTPUT_DATA}
