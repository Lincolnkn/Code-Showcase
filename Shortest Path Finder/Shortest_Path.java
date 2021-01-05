/*
 *  Lincoln knowles
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

class Node {
    int iLabel;
    double xCor;
    double yCor;

    public Node(int label, double xCo, double yCo) {
        this.iLabel = label;
        this.xCor = xCo;
        this.yCor = yCo;
    }
}

public class Shortest_Path {

    static Node[] candidate;        // Nodes not yet chosen
    static int previousNode;
    static double[][] ddEdgeWeights;// 2d Matrix : [end][start]
    static int[] parent;
    static double[] distance;
    static int nVertices;           // Num vertices
    static int nEdges;              // Num edges
    static int start;               // Start label
    static int end;                 // Goal label


    public static void main(String[] args) {
        //      Read Data
        //-------------------------
        Scanner sc = new Scanner(System.in);
        System.err.print("Enter file name: ");
        String fileName = sc.next();
        try {
            sc = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            System.err.println("File failed to open: " + ex.toString());
            System.exit(0);
        }

        nVertices = sc.nextInt() + 1; // +1 accounts for arrays starting at 1. Instead of 0
        nEdges = sc.nextInt();
        candidate = new Node[nVertices];
        ddEdgeWeights = new double[nVertices][nVertices];

        for (int i = 1; i < nVertices; i++) candidate[i] = new Node(sc.nextInt(), sc.nextDouble(), sc.nextDouble());

        for (int i = 1; i < nVertices; i++) {
            for (int j = 1; j < nVertices; j++) {
                if (i == j) ddEdgeWeights[i][j] = 0;
                else ddEdgeWeights[i][j] = Double.MAX_VALUE;
            }
        }

        for (int i = 0; i < nEdges; i++) {
            int startV = sc.nextInt();
            int endV = sc.nextInt();
            double weight = sc.nextDouble();
            // Takes care of duplicates
            // Sets the matrix symmetrical as the edges are undirected
            if (ddEdgeWeights[endV][startV] > weight) {
                ddEdgeWeights[startV][endV] = weight;
                ddEdgeWeights[endV][startV] = weight;
            }
        }
        start = sc.nextInt();
        end = sc.nextInt();
        //-------------------------

        System.out.println("Start : " + start);
        System.out.println("End   : " + end);
        System.out.println();
        Dijkstra(ddEdgeWeights);
        printPath();

        //Resets labels
        for (int i = 1; i < nVertices; i++) candidate[i].iLabel = i;

        System.out.println("\nSecond Shortest Path");
        Dijkstra(ddEdgeWeights);
        printPath();

    }

    static double[] Dijkstra(double[][] gArray) {
        previousNode = start;
        // Returns array[2..n]
        distance = new double[nVertices];  // Index = Label
        parent = new int[nVertices];
        for (int i = 1; i < nVertices; i++) {
            distance[i] = gArray[start][i];
            parent[i] = start;
        }
        candidate[start].iLabel = Integer.MAX_VALUE;

        int reachableNodes = nVertices - 1;
        do {
            int v = HeuristicFindMinIndex(distance);
            previousNode = v;
            candidate[v].iLabel = Integer.MAX_VALUE;            // Replaces c[v] with max value. (Removes from array)
            reachableNodes--;
            for (int i = 1; i < nVertices; i++) {
                if (candidate[i].iLabel != Integer.MAX_VALUE) { // Only updates nodes still in candidate array
                    if (distance[i] > distance[v] + gArray[v][i]) {
                        distance[i] = distance[v] + gArray[v][i];
                        parent[i] = v;
                    }
                }
            }
            if (v == end) break; // Stop when vertex g joins the selected set
        } while (reachableNodes > 0);

        return distance;
    }

    // Heuristic Function
    static int HeuristicFindMinIndex(double[] distance) {
        double heurDistance;
        double minTemp = Double.MAX_VALUE;
        double minCost = Double.MAX_VALUE;
        int index = 0;

        for (int i = 1; i < nVertices; i++) {
            /*  Heuristic distance found by :   d(p,q) =  √(p1-q1)^2 + (p2-q2)^2
             *        - Where p = goal position   &   q = v
             *    Source : Wikipedia. Two Dimensions
             *   https://en.wikipedia.org/wiki/Euclidean_distance#Two_dimensions
             * */
            heurDistance = Math.sqrt(Math.pow(candidate[end].xCor - candidate[i].xCor, 2) +
                    Math.pow(candidate[end].yCor - candidate[i].yCor, 2));
            // Heuristic distance (estimate) must not exceed the actual distance between the two points. Use actual distance
            if (heurDistance <= ddEdgeWeights[end][i] && ddEdgeWeights[previousNode][i] != Double.MAX_VALUE) {
                minTemp = heurDistance + ddEdgeWeights[start][i];
            } else minTemp = distance[i];

            if (candidate[i].iLabel != Integer.MAX_VALUE && minTemp < minCost) {
                minCost = minTemp;
                index = i;
            }
        }
        return index;
    }

    //Print Results and update edges
    static void printPath() {
        int[] path = new int[nVertices];
        int pathCount = 0;
        double pathCost = 0;
        int previousNode = end;
        int k = end;

        while (k != start) {
            path[pathCount++] = k;
            k = parent[k];
            pathCost += ddEdgeWeights[k][previousNode];
            //Removes edges for second shortest run
            ddEdgeWeights[k][previousNode] = Double.MAX_VALUE;
            previousNode = k;
        }
        path[pathCount] = start;

        for (int i = pathCount; i > 0; i--) System.out.print(path[i] + " > ");
        System.out.println(end);
        System.out.println("Path Length : " + pathCost);
    }
}