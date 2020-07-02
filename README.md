# Conway's Game of Life

## Description

An implementation of the Game of Life in Java.

Instead of optimizing strictly for performance, it is written with
extensibility and clarity in mind.

## TODO
- Move timer and JFrame from main

- Use sparse bit vectors to detect cycles then auto-reset?
	Multiple copies of "Board" is too memory expensive

- Move options into GUI: initial population rate, speed, colour, 
	pause simulation, initialize with known patterns (flyers, etc),
	board size, cell size

- Implement mouse interaction to set individual cells

- Indicate cell age with colour?

- Cells could handle their colour passed in when instantiated?

- Utilize multiple threads?
