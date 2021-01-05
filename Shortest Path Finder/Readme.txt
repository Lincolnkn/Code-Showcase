Data Structures & Algorithms - UOW 203 Assignment 1: 
Grade Given: 80%

Assignment objective:
This assignment involves extension to the single source-single destination shortest
path problem.

Your program should:
1. Read the name of a text file from the console. (Not the command line)
2. Read an undirected graph from the file.
3. Find the shortest path between the start and goal vertices specified in the file.
4. Print out the vertices on the path, in order from start to goal.
5. Print out the length of this path.
6. Find the second shortest path between the start and goal vertices specified in the file.
7. Print out the vertices on the path, in order from start to goal.
8. Print out the length of this path.

The data files are constructed as follows:
	- 	Two integers: nVertices and nEdges, the number of vertices and edges in the graph.
	- 	nVertices triples consisting of the label and the x‐ and y‐coordinates of each
		vertex. (An int followed by two doubles)
	- 	nEdges triples consisting of the labels of the start and end vertices of each
		edge, along with its weight. Note: the weight associated with an edge will be
		greater than or equal to the Euclidean distance between its start and end
		vertices as determined by their coordinates. (Two ints followed by a double)
	- 	Two labels, the indicating the start and goal vertices for which the paths are
		required. (Two ints)