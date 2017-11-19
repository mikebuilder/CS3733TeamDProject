package edu.wpi.cs3733.programname.pathfind;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.PathStrategies.HandicappedPath;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.PathStrategies.StandardPath;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PathfindingController {


    /**
     * Takes in the starting and ending locations, and calls AStar to find the path between them
     *
     * @param startNode - the starting location for the path
     * @param endNode   - the end node, aka the destination, for the path
     * @return - result is the list of edges efficiently connecting startNode to endNode
     */
    public List<NodeData> initializePathfind(LinkedList<NodeData> allNodes, LinkedList<Edge> allEdges, String startNode, String endNode, LinkedList<Edge.Restriction> restrictions) {
        // ManageController manager = new ManageController();
        // List<Edge> allEdges = manager.getAllEdgeData();
        //something about getEdges()
        LinkedList<Edge> currentList = allEdges;
        if(restrictions.contains(Edge.Restriction.GENERAL))
        {
            StandardPath standardPath = new StandardPath();
            currentList = standardPath.getEdges(currentList);
        }
        if(restrictions.contains(Edge.Restriction.HANDICAPPED))
        {
            HandicappedPath handicappedPath = new HandicappedPath();
            currentList = handicappedPath.getEdges(currentList);
        }
        if(restrictions.contains(Edge.Restriction.ASTAR))
        {
            AStar newAstar = new AStar(allNodes, currentList, startNode, endNode);
            return newAstar.getFinalList();
        }
        if(restrictions.contains(Edge.Restriction.DEPTH))
        {
        return null;
        }
        if(restrictions.contains(Edge.Restriction.BREADTH))
        {
        return null;
        }
        else return null;



    }
}