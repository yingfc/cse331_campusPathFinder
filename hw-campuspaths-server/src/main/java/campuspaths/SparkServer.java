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

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.Map;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        CampusMap map = new CampusMap();

        // listBuilding endpoint
        Spark.get("/listBuilding", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                Map<String, String> res = map.buildingNames();
                if (res == null) {
                    Spark.halt(400, "Error listing buildings");
                }
                Gson gson = new Gson();
                return gson.toJson(res);
            }
        });

        // findPath endpoint
        Spark.get("findPath", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String sourceNode = request.queryParams("start");
                String destNode = request.queryParams("end");
                if (sourceNode == null || destNode == null) {
                    Spark.halt(400, "Must have start and end node");
                }
                Path<Point> path = map.findShortestPath(sourceNode, destNode);
                if (path == null) {
                    Spark.halt(400, "Must provide valid building abbreviation");
                }
                Gson gson = new Gson();
                return gson.toJson(path);
            }
        });
    }

}
