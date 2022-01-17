package com.hiberus.customer.inditex.challenge;

import java.io.IOException;
import java.util.*;

class Graph {

    // We use Hashmap to store the edges in the graph
    // We have N-1 connections (N is the number of Vertex). We can consider that each source has only 1 destination or more than one
    // if we have isolated Vertex. I have decided to prepare the solution to have isolated nodes if proceed
    private static Map<Integer, List<NodeConnection>> completeGraph = new HashMap<>();

    private static Set<Triplet<Integer, Integer, Integer>> pathWithRedEdgeList = new HashSet<>();

    static final String RED_EDGE = "r";

    // This function adds a new vertex to the graph without the connections
    public void addVertex(Integer v)
    {
        completeGraph.put(v, new LinkedList<>());
    }

    // This function adds the edge between source to destination with the colour
    public void addEdge(Integer source,
                        Integer destination,
                        String colour)
    {

        if (!completeGraph.containsKey(source))
            addVertex(source);

        if (!completeGraph.containsKey(destination))
            addVertex(destination);

        completeGraph.get(source).add(new NodeConnection(destination, colour));

    }

    // Get the number of triplets
    public void getTripletCount()
    {
        TreeSet<Integer> nodeList;

        for (Integer origin : completeGraph.keySet()) {

            nodeList = new TreeSet<>();
            TreeSet<Integer> reachableNodes = reachableNodes(origin, nodeList);
            Iterator<Integer> reachableNodesIterator = reachableNodes.iterator();
            while (reachableNodesIterator.hasNext()) {
                findConnectedPaths(origin,reachableNodesIterator.next(), reachableNodes);
            }
        }
    }

    public static void findConnectedPaths(Integer origin, Integer middleNode, TreeSet<Integer> reachableNodes) {

        Iterator<Integer> iterator = reachableNodes.iterator();

        while (iterator.hasNext()){
            Integer third = iterator.next();
            if (( origin >= third)||(middleNode >= third)){
                //Already processed due the tuple {1, 2, 3} and {2, 1, 3} are the same for our purposes
                continue;
            }
            Triplet<Integer, Integer, Integer> validPath = Triplet.of(origin, middleNode, third);

            if(checkIfContainsRedEdge(validPath)){
                pathWithRedEdgeList.add(validPath);
            }
        }
    }

    private static boolean checkIfContainsRedEdge(Triplet<Integer, Integer, Integer> validPath) {
        boolean containsRedEdgeAtLeastOnce = checkRedPathBetweenNodes(validPath.first, validPath.second);

        if (!containsRedEdgeAtLeastOnce){
            System.out.println("No red edges found between node " + validPath.first + " and " + validPath.second);
            return false;
        }

        containsRedEdgeAtLeastOnce = checkRedPathBetweenNodes(validPath.second, validPath.third);
        if (!containsRedEdgeAtLeastOnce){
            System.out.println("No red edges found between node " + validPath.second + " and " + validPath.third);
            return false;
        }

        //As we know the validPath is an ordered Triplet, we can call in order, it means check the path between first-second and second-third
        //We have a connected N-1 graph, so if a-->b and b-->c contains red edges, a-->c must have the same red edges
        // So check red path between first and third has no sense with the requirement, but we can add here in needed

        return true;

    }

    private static boolean checkRedPathBetweenNodes(Integer source, Integer destination){

        List<NodeConnection> nodeConnectionsBetweenSourceAndDestination = completeGraph.get(source);

        if ((nodeConnectionsBetweenSourceAndDestination == null) || (nodeConnectionsBetweenSourceAndDestination.size() != 1)){
            System.out.println("Can not retrieve a valid path for the vertex : "+source);
            return false;
        }
        NodeConnection retrievedPath = nodeConnectionsBetweenSourceAndDestination.get(0);

        if (retrievedPath.colour.equals(RED_EDGE)){
            System.out.println("Red edge found between node :" +source + " and " + destination);
            return true;
        }

        if (!retrievedPath.destination.equals(destination)) {
            return checkRedPathBetweenNodes(retrievedPath.destination, destination);
        }

        return false;
    }

    public TreeSet<Integer> reachableNodes(Integer source, TreeSet<Integer> nodeList){

        if (completeGraph.containsKey(source)){
            List<NodeConnection> nodeConnections = completeGraph.get(source);
            for (int i=0; i<nodeConnections.size();i++){
                Integer destination = nodeConnections.get(i).destination;
                nodeList.add(destination);
                reachableNodes(destination, nodeList);
            }
        }
          return nodeList;
    }

    public int getNumberOfRedEdge(){
        System.out.println(pathWithRedEdgeList);
        return pathWithRedEdgeList.size();
    }
}

class NodeConnection{

    public Integer destination;
    public String colour;

    public NodeConnection(Integer destination, String colour) {
        this.destination = destination;
        this.colour = colour;
    }
}


public class Question10 {

    public static void main(String args[]) throws IOException {

       int nodes;
       Graph g = new Graph();

     System.out.println("Por favor especifique el número de vértices del árbol (1..9):");
       Scanner scan = new Scanner(System.in);
       String str_input = scan.nextLine();
       nodes = Integer.parseInt(str_input);

       for (int i=0;i<nodes -1;i++){
           System.out.println("Por favor especifique las conexiones y el color del camino de la forma [n1 n2 r|n]:");
           String inputParameters = scan.nextLine();
           String[] params = inputParameters.split(" ");
           //Validations?
          g.addEdge(Integer.parseInt(params[0]), Integer.parseInt(params[1]), params[2]);
       }


        // Gives the number of triplets in the graph.
        g.getTripletCount();
        System.out.println("Number of triplets : "+ g.getNumberOfRedEdge());
    }

}