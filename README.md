# reverse-database

Creates a reversed database for tandem MS searches.

## Usage

Takes a fasta formatted protein database and returns a fasta formatted
protein database with reversed sequences included.

Run using the jar:

    $ java -jar reverse-database-0.1.0-standalone.jar [args]

Or the bash script in the bin directory:

    $ reverse-database.sh [args]

## Options

```
  -i, --input PATH   Path to file containing full paths of mzML or mgf files to be searched.
  -o, --output PATH  Path to file containing X! Tandem parameters.
  -h, --help         Print help message.
```

## License

Copyright © 2016 Jason Mulvenna

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
