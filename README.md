# Word transformer

## What does it do?

Word transformer is an amazing Java program that looks for a word (of group of words) in a text file, and replace this word with something else. The "something else" is up to the user, as he has different options (encrypt the word with a Caesar code, switch all the letters to uppercase or lowercase, take all the letters and rearrange them in alphabetical order, etc.)

## Installation

The program can be compiled from source. First, clone the repo.

```
$ git clone git@github.com:yanniSkawronski/dai-lab01-cli.git
```

Then move into the main folder and compile the package. This requires Java to be installed on the computer.

```
$ cd dai-lab01-cli
$ ./mvnw dependency:go-offline clean compile package
```

Our extraordinary program is now ready to go in `target/`.

```
$ cd target
$ java -jar dai-lab01-cli-1.0-SNAPSHOT.jar -V
```

This should display the version.

## Usage

Using the program looks like this:

```
$ java -jar dai-lab01-cli-1.0-SNAPSHOT.jar -p=<pattern> <infile> <outfile> [COMMAND]
```

`<pattern>` is the sequence of character the program will look for.

`<infile>` is the file read by the program, in which he will search the aforementioned pattern.

`<outfile>` is the file in which the program will write the content of the `<infile>`, with a certain transformation applied to the pattern.

`[COMMAND]` specifies the transformation applied to the pattern.

Several options are available:

```
  -e, --regex               match regex pattern instead, max 1 match group
  -h, --help                Show help message and exit.
  -r, --repeat              transform all occurrences, instead of just the first one
  -V, --version             Print version information and exit.

```

## Exemples

## Authors

- ![Yanni Skawronski](@yanniSkawronski)
- ![Tadeusz Kondracki](@GlysVenture)
- ![Jules Rossier](@julesrossier)
