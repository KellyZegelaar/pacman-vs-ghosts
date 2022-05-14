package pacman.game.internal;

import pacman.game.Constants;
import pacman.game.Game;

import java.util.*;

public class BreadthFirstSearch {

    private N[] graph;

    public void createGraph(Node[] nodes)
    {
        graph=new N[nodes.length];

        //create graph
        for(int i=0;i<nodes.length;i++)
            graph[i]=new N(nodes[i].nodeIndex);

        //add neighbours
        for(int i=0;i<nodes.length;i++)
        {
            EnumMap<Constants.MOVE,Integer> neighbours=nodes[i].neighbourhood;
            Constants.MOVE[] moves= Constants.MOVE.values();

            for(int j=0;j<moves.length;j++)
                if(neighbours.containsKey(moves[j]))
                    graph[i].adjacent.add(new E(graph[neighbours.get(moves[j])],moves[j],1));
        }
    }

    public synchronized int[] computeBFSPath(int startIndex, int targetIndex, Constants.MOVE lastMoveMade) {
        System.out.println("RoOt: " + startIndex + "TaRgEt: " + targetIndex);
        N startNode = graph[startIndex];
        N targetNode = graph[targetIndex];

        if (startIndex == targetIndex) {

            return new int[]{startIndex};
        }

        N target = bfs(startNode, targetNode, lastMoveMade);
        System.out.println("------------------------------------------");
        System.out.println("FOUND TARGET: " + target.toString());
        System.out.println("------------------------------------------");
        return extractPath(target);
    }

    private synchronized int[] extractPath(N target)
    {
        ArrayList<Integer> route = new ArrayList<Integer>();
        N current = target;
        route.add(current.index);

        while (current.parent != null)
        {
            int parentIndex = current.parent.index;
            route.add(parentIndex);
            current = current.parent;
        }

        Collections.reverse(route);

        int[] routeArray=new int[route.size()];

        for(int i=0;i<routeArray.length;i++)
            routeArray[i]=route.get(i);
        return routeArray;
    }


//    private synchronized int[] bfs(N startnode, N targetNode, Game game) {
//        Queue<N> queue = new LinkedList();
//        queue.add(startnode);
//
//        List<Boolean> visited = new ArrayList<>();
//        visited.add(startnode.index, true);
//        int[] prev = new int[game.getCurrentMaze().graph.length];
//
//        while (!queue.isEmpty()) {
//            N node = queue.poll();
//            ArrayList<E> neighbours = startnode.adjacent;
//
//            for (E next : neighbours) {
//                if (!visited.get(next.node.index)) {
//                    queue.add(next.node);
//                    visited.add(next.node.index, true);
//                    prev[next.node.index] = node.index;
//                }
//            }
//        }
//        return prev;
//    }

    private synchronized N bfs(N root, N targetNode, Constants.MOVE lastMoveMade) {
        if (root.isEqual(targetNode)) {
            return root;
        } else {
            Queue<N> queue = new LinkedList<>();
            for (E edge : root.adjacent) {
                queue.add(edge.node);
            }

            N child = null;

            while (!queue.isEmpty()) {
                child = queue.poll();
                System.out.println("BFS Index: " + child.index);
                System.out.println("BFS Target: " + targetNode.index);
            }

                if (child.isEqual(root)) {
                    return child;
                }
                for (E edge : root.adjacent) {
                    queue.add(edge.node);
                }
            }
        return null;
        }
}
