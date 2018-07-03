# Data Analytics Project: The Million Song Dataset

Analysing The Million Song Dataset.

## Installation

1. Download and install _HDF Java_ and _HDFView+Object_:  
```bash
./lib/hdf/install-hdf.sh
```
1. Download and install _Hadoop_:  
```bash
./lib/hadoop/install-hadoop.sh
```
1. Download the data sets:  
```bash
./data/download.sh
```

## Configuration

### Log level

If you want Hadoop to only print warnings to the console,
add the following line to `lib/hadoop/hadoop-3.1.0/etc/hadoop/hadoop-env.sh`:
```bash
HADOOP_ROOT_LOGGER=WARN,DRFA
```

## Questions
  
*TODO:* List specific questions.
