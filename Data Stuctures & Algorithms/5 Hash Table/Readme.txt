Data Structures & Algorithms - UOW 203 Lab 5: 
Grade Given: 100%

Assignment objective:
For this exercise, you are to implement a simple hash table.

The file contains a sequence of integer values. Read them and construct a hash table using chaining.
For this exercise, you may use dynamic data for chains greater than one in length, but the majority
of the dictionary should be an array of hash nodes.
Read each, integer in turn and calculate its hash value using mod 100 as the hashing function.
Thus, is if the key is k, the hash value h(k) = k mod 100.

When you have finished calculate:
1. The number of empty entries in the hash table.
2. The length of the longest chain.

Your program should output these two values – these should be the only outputs.