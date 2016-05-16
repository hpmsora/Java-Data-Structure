import java.lang.String;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class AdjGraph implements Graph {
    private boolean digraph;
    private int totalNode;
    private Vector<String> nodeList;
    private int totalEdge;
    private Vector<LinkedList<Integer>>  adjList; //Adjacency list
    private Vector<Boolean> visited;
    private Vector<Integer> nodeEnum; //list of nodes pre visit
    private static int[][] edges = new int[0][3];
  
    public AdjGraph() {
	init();
    }
    public AdjGraph(boolean ifdigraph) {
	init();
	digraph =ifdigraph;
    }
    public void init() {
	nodeList = new Vector<String>(); 
	adjList = new Vector<LinkedList<Integer>>(); 
	visited = new Vector<Boolean>();
	nodeEnum = new Vector<Integer>();
	totalNode = totalEdge = 0;
	digraph = false;
	int num_max_edges = factorial(nodeList.size());
    }
  
    public int factorial(int x) {
	int sum = 1;
	for(int i = 1; i <= x; i++)
	    sum *= i;
	return sum;
    }
  
    //set vertices
    public void setVertex(String[] nodes) {
	for(int i = 0; i < nodes.length; i ++) {
	    nodeList.add(nodes[i]);
	    adjList.add(new LinkedList<Integer>());
	    visited.add(false);
	    totalNode ++;
	}
    }
    //add a vertex
    public void addVertex(String label) {
	nodeList.add(label);
	visited.add(false);
	adjList.add(new LinkedList<Integer>());
	totalNode ++;
    }
    public int getNode(String node) {
	for(int i = 0; i < nodeList.size(); i ++) {
	    if(nodeList.elementAt(i).equals(node)) return i;
	}
	return -1;
    }
    //return the number of vertices
    public int length() {
	return nodeList.size();
    }
    //add edge from v1 to v2
    public void setEdge(int v1, int v2, int weight) {
	LinkedList<Integer> tmp = adjList.elementAt(v1);
	if(adjList.elementAt(v1).contains(v2) == false) {
	    tmp.add(v2);
	    adjList.set(v1,  tmp);
	    totalEdge ++;
	}
    }
    public void setEdge(String v1, String v2, int weight) {
	if((getNode(v1) != -1) && (getNode(v2) != -1)) {
	    //add edge from v1 to v2
	    setEdge(getNode(v1), getNode(v2), weight);
	    //for digraph, add edge from v2 to v1 as well
	    if(digraph == false) {
		setEdge(getNode(v2), getNode(v1), weight);
	    }
	}
    }
  
    public int getDistance(int v1, int v2) {
	int num_edge = totalEdge / 2 + 1;
	for(int i = 0; i < num_edge; i++) {
	    int[] element_edge = edges[i];
	    int x = element_edge[0];
	    int y = element_edge[1];
	    if(((v1 == x) && (v2 == y)) || ((v1 == y) && (v2 == x)))
		return element_edge[2];
	}
	return 0;
    
    }
  
    //it is important to keep track if a vertex is visited or not (for traversal)
    public void setVisited(int v) {
	visited.set(v, true);
	nodeEnum.add(v);
    }
    public boolean ifVisited(int v) {
	return visited.get(v);
    }
    public void clearWalk() {
	//clean up before traverse
	nodeEnum.clear();
	for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
    }
    public void walk(String method) {
	clearWalk();
	//traverse the graph 
	for(int i = 0; i < nodeList.size(); i ++) {
	    if(ifVisited(i) == false) {
		if(method.equals("BFS")) BFS(i); //i as the start node
		else if(method.equals("DFS")) DFS(i); //i as the start node
		else {
		    System.out.println("unrecognized traversal order: " + method);
		    System.exit(0);
		}
	    }
	}
	System.out.println(method + ":");
	displayEnum();
    }
    public void DFS(int v) {
	setVisited(v);
	LinkedList<Integer> neighbors = adjList.elementAt(v);
	for(int i = 0; i < neighbors.size(); i ++) {
	    int v1 = neighbors.get(i);
	    if(ifVisited(v1) == false) DFS(v1); 
	}
    }
    public void BFS(int s) {
	ArrayList<Integer> toVisit = new ArrayList<Integer>();
	toVisit.add(s);
	while(toVisit.size() > 0) {
	    int v = toVisit.remove(0); //first-in, first-visit
	    setVisited(v);
	    LinkedList<Integer> neighbors = adjList.elementAt(v);
	    for(int i = 0; i < neighbors.size(); i ++) {
		int v1 = neighbors.get(i);
		if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
		    toVisit.add(v1);
		}
	    }
	}
    }
    public void display() {
	System.out.println("total nodes: " + totalNode);
	System.out.println("total edges: " + totalEdge);
    }
    public void displayEnum() {
	for(int i = 0; i < nodeEnum.size(); i ++) {
	    System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
	}
	System.out.println();
    }
  
    public int[][] myFloyd() {
    
	int [][] dist = new int[nodeList.size()][nodeList.size()];
	int idx = 0;
	int infi = 9999;
    
	for(int i = 0; i < nodeList.size(); i++) {
	    for(int j = 0; j < nodeList.size(); j++) {
		if (i == j)
		    dist[i][j] = 0;
		else
		    dist[i][j] = infi;
	    }
	}
	/*
	for(int i = 0; i < nodeList.size(); i++) {
	    for(int j = 0; j < nodeList.size(); j++) {
		System.out.println(i + " " + j + ": " + getDistance(i, j));
		dist[i][j] = getDistance(i, j);
	    }
	    }*/
	for(int i = 0; i < nodeList.size(); i++) {
	    LinkedList<Integer> adj = adjList.get(i);
	    for(int j : adj) {
		dist[i][j] = getDistance(i, j);
	    }
	}
    
	for(int i = 0; i < nodeList.size(); i++) {
	    for(int j = 0; j < nodeList.size(); j++) {
		for(int k = 0; k < nodeList.size(); k++) {
		    if (dist[i][j] > (dist[i][k] + dist[k][j]))
			dist[i][j] = dist[i][k] + dist[j][k];
		}
	    }
	}
    
	return dist;
    }
  
    public int max_dist(int[][] x) {
	int num_max = 0;
	for(int i = 0; i < nodeList.size(); i++) {
	    for(int j = 0; j < nodeList.size(); j++) {
		if(x[i][j] < 9999)
		    if(num_max < x[i][j])
			num_max = x[i][j];
	    }
	}
	return num_max;
    }
  
    public static String readFile(String fileName)
    {
	String result = "";
	try {
	    String line = null;
	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
      
	    while((line = bufferedReader.readLine()) != null) {
		result += line + "\n";
	    }   
	    bufferedReader.close();         
	}
	catch(FileNotFoundException ex) {
	    System.out.println("Unable to open file '" + fileName + "'");                
	}
	catch(IOException ex) {
	    System.out.println("Error reading file '" + fileName + "'");
    }
    return result;
  }
  
  public static void main(String[] args) {
    String file;
    Scanner scan = new Scanner(System.in);
    System.out.println("Input file name(Include *.txt) Default: sample.txt");
    String file_name = scan.nextLine();
    
    String context_origin = readFile(file_name);
    String context = context_origin;
    int context_length = context.length();
    
    String sub_str = "";
    
    String[] context_line = context.split("\n");
    String[] first_line = context_line[0].split(" ");
    int num_farm = Integer.parseInt(first_line[0]);
    int num_roads = Integer.parseInt(first_line[1]);
    String[] farms = new String[num_farm];
    
    for(int i = 0; i < num_farm; i++) {
      farms[i] = Integer.toString(i);
    }
    
    AdjGraph input = new AdjGraph();
    input.setVertex(farms);
    edges = new int[num_roads][3];
    
    for(int i = 1; i <= num_roads; i++) {
      String lining = context_line[i];
      String[] element = lining.split(" ");
      
      int edge_weight = Integer.parseInt(element[2]);
      input.setEdge(element[0], element[1], edge_weight);

      edges[i-1][0] = Integer.parseInt(element[0]);
      edges[i-1][1] = Integer.parseInt(element[1]);
      edges[i-1][2] = Integer.parseInt(element[2]);
    }    

    int[][] table = input.myFloyd();

    for(int i = 0; i < 6; i++) {
	for(int j = 0; j < 6; j++) {
	    System.out.print(table[i][j] + "\t");
	}
	System.out.print("\n");
    }

    Scanner scanX = new Scanner(System.in);
    System.out.println("Start Vertex: ");
    int st = scanX.nextInt();
    System.out.println("End Vertex: ");
    int ed = scanX.nextInt();
    System.out.println(table[st-1][ed-1]);

    System.out.println("Output: ");
    int max = input.max_dist(table);
    System.out.println(max);
  }
}
