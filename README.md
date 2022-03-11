# AutoWesnoth

**Note**: This is a Java learning and practice playground project of myself.
By no means it has good good design and coding practices, nor suitable for any kind of production usage.

This is going to be "auto-chess" type of game based on [Battle For Wesnoth](https://www.wesnoth.org/) assets.
It is far from being playable and probably will never become.

The goal of this project is to learn and practise Java and have some fun while doing that.
For this reason docs are going to keep my own findings rather than documentation for building/extending the projects.

## Usage

As of now the only supported operation is building the package and running the tests:
```
./gradlew build
```
No executable is created nor any interface to use etc.

## Decision log

0. Apparently Java is not the best language to build games as it is rather strict. It should be great for writing reliable code for other software but for games it might be quite limiting. This if fine as the goal is actually learning Java, not producing great product in the end.

1. Build parser for [Wesnoth WML files](https://wiki.wesnoth.org/SyntaxWML) first so that I don't have to compose units myself, and practise string processing.
This is very counter-productive move for any real world scenario of game building but the goal of this project is not productive game development.

1.1. ANTLR for parsing Wesnoth seems to be too complex and heavyweight.
There is a [parser for WML files](https://github.com/some1one/wesnoth-wml-parser) which compiles and integrates to Java just fine but it crashes on some stock files so I chose the path to doing simple parsing myself.

1.2. Iteration over lines from WML file using [Java Stream API](https://dev.java/learn/the-stream-api/) doesn't seem to be viable: Stream API is apparently quite different to iterators in Python and Ruby - Java streams can be parallelized and otherwise modified and this requires quite different coding practices (seem to be overweight for a simple file parsing). In Ruby/Python iterators is mostly a different syntax for loops while Java streams are different.

1.3. Java has iterating for loop which worked quite fine. I had to create an object for storing the WML tags and attributes according to [spec](https://wiki.wesnoth.org/SyntaxWML). No semantic processing is done, just an AST is built.

1.4. Creating unit object from the AST - in progress.
