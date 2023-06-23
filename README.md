# Java Coding Test

Please complete the following questions. For 1 and 2, please use Java. For 3, you can use any language you prefer.
Compilable/runnable code is
required, runnable code with tests that I can easily run to verify your submission is always preferred. The choice of
the tool to compile, test and run the
code is yours.
Please publish to Github and email me the link or create a .tar.gz file.

## 1. [Imagine we have an interface "GNode" that looks like this](./gnode):

   ```java
   public interface GNode {
    public String getName();

    public List<GNode> getChildren();
}
   ```

    - Observe that this GNode can be thought of as defining a graph.
    - In implementing the functions below, you can assume that any graph defined by a GNode is acyclic (no cycles).
    - Assume that when a GNode has no children, getChildren() returns a list of size 0, and *not* null.
    - You can also assume that all children returned by getChildren() are not null.

Implement a function with the following signature:

   ```java
   public List<GNode> walkGraph(GNode node);
   ```

which should return a List containing every GNode in the graph. Each node should appear in the List exactly once (i.e.
no duplicates).

## 2. [Implement a function with the following signature](./gnode):

```java
public List<List<GNode>>paths(GNode node);
```

which should return a List of Lists, representing all possible paths through the graph starting at 'node'. The List
returned can be thought of as a List of
paths, where each path is represented as an List of GNodes.

Example:
Assume the following graph:

```
   A
     B
       E
       F
     C
       G
       H
       I
     D
       J
```

paths(A) = ( (A B E) (A B F) (A C G) (A C H) (A C I) (A D J) )

## 3. [Write an HTTP server with REST API](./rest-api)

Youre creating a tool that helps people organise documents into different groups called tags.
We can represent each document by a [URI](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier). Each document can
have multiple tags. Tags are represented as text strings -
you can enforce reasonable constraints (i.e. limit length, prevent white-spaces, etc.)Tags form a hierarchy, so each tag
can have sub-tags. For
example "animals" tag might have a sub-
tags like "mammals" and "birds" which can have sub-tags of their own. In other words the "is a sub-tag of" relation
is transitive.

Implement an HTTP server that will expose the following API endpoint: `GET /taggedContent?tag=<tag>`

This returns a JSON array of documents (represented by URIs) associated with the given tag (and its sub-tags).

The API is read-only so the "database" of documents and tags can be completely static. You can either hard-code it or
preferably read it from a file (or
classpath resource in Java).

## 4. [Write a quick and dirty program](./quick_dirty)

(Shell, Python, Perl, Java, Lisp, C++, APL, or whatever) to produce a count of all the different "words" in a text
file. Use any definition of word that makes
logical sense or makes your job easy. The output might look like this:

```
17 a
14 the
9 of
9 in
8 com
7 you
7 that
7 energy
6 to
...
```
For this input file, the word "a" occured 17 times, "the" 14 times, etc.