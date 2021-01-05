Data Structures & Algorithms - UOW 203 Lab 7: 
Grade Given: 100%

Assignment objective:
For this exercise, you are to find the optimal order for multiplying a sequence of matrices.

As usual, your program will prompt for the name of an input file and the read and process the data
contained in this file.

The file contains the following data.
N, the number of matrices to be multiplied together
N pairs of integers which are the row and column dimensions of each matrix.
E.g. The following input
3
3 4
4 2
2 5

Defines a problem in which we are to multiply three matrices, say M[0], M[1] and M[2], where:
M[0] has 3 rows and 4 columns;
M[1] has 4 rows and 2 columns;
M[2] has 2 rows and 5 columns.

Output for the program is the value of best(0,N), the minimum number of multiplications required
to compute the matrix R = M[0]xM[1]x…xM[N‐1].
You may leave your solution as a memoized, recursive formulation if you have problems formulating
the looped iterative scheme.