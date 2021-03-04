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

package pathfinder;

import graph.DirectedGraph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements the ModelAPI interface, enabling the controller to return data
 * from the model and request the model to do some computation.
 */
public class CampusMap implements ModelAPI {

    // Rep invariant:
    // short2Full != null && buildingMap != null && g != null
    // && every key and value of short2Full are not null
    // && every key and value of buildingMap are not null
    // && every key and value of g are not null

    // Abstract Function:
    // AF(this) = a CampusMap such that
    //      this.short2Full = a map of buildings' short names to full names
    //      this.buildingMap = a map of buildings' short names to locations
    //      this.g = a directed graph of campus paths

    private Map<String, String> short2Full;
    private Map<String, Point> buildingMap;
    private DirectedGraph<Point, Double> g;

    /**
     * The constructor that initializes the map information.
     */
    public CampusMap() {
        short2Full = new HashMap<>();
        buildingMap = new HashMap<>();
        g = new DirectedGraph<>();
        String buildingFileName = "campus_buildings.tsv";
        String pathFileName = "campus_paths.tsv";

        List<CampusBuilding> campusBuildings = CampusPathsParser.parseCampusBuildings(buildingFileName);
        for (CampusBuilding building : campusBuildings) {
            short2Full.put(building.getShortName(), building.getLongName());
            buildingMap.put(building.getShortName(), new Point(building.getX(), building.getY()));
        }

        List<CampusPath> campusPaths = CampusPathsParser.parseCampusPaths(pathFileName);
        for (CampusPath path : campusPaths) {
            Point startNode = new Point(path.getX1(), path.getY1());
            Point endNode = new Point(path.getX2(), path.getY2());
            g.addNode(startNode);
            g.addNode(endNode);
            g.addEdge(startNode, endNode, path.getDistance());
        }
    }

    @Override
    public boolean shortNameExists(String shortName) {
        return short2Full.containsKey(shortName);
    }

    @Override
    public String longNameForShort(String shortName) {
        if (!shortNameExists(shortName)) {
            throw new IllegalArgumentException();
        }
        return short2Full.get(shortName);
    }

    @Override
    public Map<String, String> buildingNames() {
        return short2Full;
    }

    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        if (shortNameExists(startShortName) && shortNameExists(endShortName)) {
            Point startPoint = buildingMap.get(startShortName);
            Point endPoint = buildingMap.get(endShortName);
            Path<Point> res = new Path<>(startPoint);
            List<DirectedGraph.LabeledEdge<Point, Double>> dijkstra = MarvelPathsWeighted.Dijkstra(g, startPoint, endPoint);
            if (dijkstra != null) {
                for (DirectedGraph.LabeledEdge<Point, Double> le : dijkstra) {
                    if (!startPoint.equals(le.getDest())) {
                        res = res.extend(le.getDest(), le.getEdgeLabel());
                    }
                }
            }
            return res;
        } else {
            return null;
        }
    }
}
