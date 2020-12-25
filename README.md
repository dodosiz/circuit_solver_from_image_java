# Circuit Solver from Image (Java Edition)

This is a project that comes as a result from the [MATLAB Version](https://github.com/dodosiz/circuit-solver-from-image).

Motivation is to write the same logic with open source technologies, in particular open source Java Libraries
like [Java ML](http://java-ml.sourceforge.net/).

In the first stage a ML classification algorithm has been implemented. There are 3 kinds of training data:

* capacitors
* resistors
* inductors

The algorithms takes those binary images and writes the pixel coordinates in a file. Then a KNN classifier
is trained with it.

In order to test it, we draw either a resistor, capacitor or inductor in /resources/circuit_components/input.png
and run the main class. The result of the classification is being written in the command line.