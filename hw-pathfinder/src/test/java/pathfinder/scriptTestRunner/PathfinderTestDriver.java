/*
 * Copyright (C) 2021 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder.scriptTestRunner;

import graph.DirectedGraph;
import pathfinder.MarvelPathsWeighted;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

    // *********************************
    // ***  Interactive Test Driver  ***
    // *********************************

    public static void main(String[] args) {
        try {
            if (args.length > 1) {
                printUsage();
                return;
            }

            PathfinderTestDriver td;

            if (args.length == 0) {
                td = new PathfinderTestDriver(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
                System.out.println("Running in interactive mode.");
                System.out.println("Type a line in the script testing language to see the output.");
            } else {
                String fileName = args[0];
                File tests = new File(fileName);

                System.out.println("Reading from the provided file.");
                System.out.println("Writing the output from running those tests to standard out.");
                if (tests.exists() || tests.canRead()) {
                    td = new PathfinderTestDriver(new FileReader(tests), new OutputStreamWriter(System.out));
                } else {
                    System.err.println("Cannot read from " + tests.toString());
                    printUsage();
                    return;
                }
            }

            td.runTests();

        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        }
    }

    private static void printUsage() {
        System.err.println("Usage:");
        System.err.println("  Run the gradle 'build' task");
        System.err.println("  Open a terminal at hw-pathfinder/build/classes/java/test");
        System.err.println("  To read from a file: java pathfinder.scriptTestRunner.PathfinderTestDriver <name of input script>");
        System.err.println("  To read from standard in (interactive): java pathfinder.scriptTestRunner.PathfinderTestDriver");
    }

    // ***************************
    // ***  JUnit Test Driver  ***
    // ***************************

    /**
     * String -> Graph: maps the names of graphs to the actual graph
     **/
    private final Map<String, DirectedGraph<String, Double>> graphs = new HashMap<>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new PathfinderTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @throws IOException if the input or output sources encounter an IOException
     * @spec.effects Executes the commands read from the input and writes results to the output
     **/
    // Leave this method public
    public void runTests() throws IOException {
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            if ((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if (st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<String>();
                    while (st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch (command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            output.println("Exception: " + e.toString());
        }
    }

    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        graphs.put(graphName, new DirectedGraph<>());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        DirectedGraph<String, Double> g = graphs.get(graphName);
        g.addNode(nodeName);
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        double edgeLabel = Double.parseDouble(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel) {
        DirectedGraph<String, Double> g = graphs.get(graphName);
        g.addNode(parentName);
        g.addNode(childName);
        g.addEdge(parentName, childName, edgeLabel);
        output.println(String.format("added edge %.3f", edgeLabel) + " from " + parentName + " to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        DirectedGraph<String, Double> g = graphs.get(graphName);
        String res = graphName + " contains:";

        List<String> sorted = new ArrayList<>(g.getAllNodes());
        Collections.sort(sorted);
        for (String node: sorted) {
            res += " " + node;
        }
        output.println(res);
    }

    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        DirectedGraph<String, Double> g = graphs.get(graphName);
        String res = "the children of " + parentName + " in " + graphName + " are:";
        Set<DirectedGraph.LabeledEdge<String, Double>> set = new HashSet<>(g.getEdges(parentName));
        List<DirectedGraph.LabeledEdge<String, Double>> list = new ArrayList<>(set);
        list.sort((o1, o2) -> {
            if (!(o1.getDest().equals(o2.getDest()))) {
                return o1.getDest().compareTo(o2.getDest());
            }
            if (!(o1.getEdgeLabel().equals(o2.getEdgeLabel()))) {
                return o1.getEdgeLabel().compareTo(o2.getEdgeLabel());
            }
            return 0;
        });

        for (DirectedGraph.LabeledEdge<String, Double> le: list) {
            res += " " + le.getDest() + String.format("(%.3f", le.getEdgeLabel()) + ")";
        }
        output.println(res);
    }

    private void findPath(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new CommandException("Bad arguments to findPath: " + arguments);
        }

        String graphName = arguments.get(0);
        String sourceNode = arguments.get(1).replace('_', ' ');
        String destNode = arguments.get(2).replace('_', ' ');
        findPath(graphName, sourceNode, destNode);
    }

    private void findPath(String graphName, String sourceNode, String destNode) {
        DirectedGraph<String, Double> g = graphs.get(graphName);

        if (!g.containsNode(sourceNode) && !g.containsNode(destNode)) {
            output.println("unknown character " + sourceNode);
            output.println("unknown character " + destNode);
        } else if (!g.containsNode(sourceNode)) {
            output.println("unknown character " + sourceNode);
        } else if (!g.containsNode(destNode)) {
            output.println("unknown character " + destNode);
        } else {
            String s = sourceNode;
            String res = "path from " + sourceNode + " to " + destNode + ":";
            List<DirectedGraph.LabeledEdge<String, Double>> path = MarvelPathsWeighted.Dijkstra(g, sourceNode, destNode);

            if (path == null) {
                res += "\n" + "no path found";
            } else {
                double cost = 0;
                path.remove(0);
                for (DirectedGraph.LabeledEdge<String, Double> le : path) {
                    res += "\n" + s + " to " + le.getDest() + " with weight " + String.format("%.3f", le.getEdgeLabel());
                    cost += le.getEdgeLabel();
                    s = le.getDest();
                }
                res += "\n" + "total cost: " + String.format("%.3f", cost);
            }
            output.println(res);
        }
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
