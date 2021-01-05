Data Structures & Algorithms - UOW 203 Lab 6: 
Grade Given: 100%

Assignment objective:
For this exercise, you are to implement the Karp‐Rabin string search algorithm.

As usual, your program will prompt for the name of an input file and the read and process the data
contained in this file.
The file contains two sequences of characters.
The first is the target sequence, T, the second is the search sequence S.
Read both sequences into character arrays and find all occurrences of sequence S in sequence T
using the Karp‐Rabin algorithm.
For each matched sequence, print the location of the first matched character in T.
For example, if the test data looked like this:

ACGTACGTACCAGTA
AC

The output should be:
0 4 8

Notes:
1. The sequence T is no longer than 5000 characters
2. The sequence S is no longer than 10 characters
3. The alphabet used in both sequences consists of the letters A, C, G and T, the DNA base
sequences.
4. Choose an appropriate modulus, m, for the hash function.
5. Try to make your hash computation as efficient as possible.