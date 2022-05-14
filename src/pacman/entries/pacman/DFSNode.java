package pacman.entries.pacman;

import pacman.game.Constants;
import pacman.game.internal.Node;

import java.util.EnumMap;

public class DFSNode {
    private int index;
    private EnumMap<Constants.MOVE, int[]> neighbours;
    private boolean visited;

    public DFSNode(int index, EnumMap<Constants.MOVE, int[]> neighbours) {
        this.index = index;
        this.neighbours = neighbours;
        visited = false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public EnumMap<Constants.MOVE, int[]> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(EnumMap<Constants.MOVE, int[]> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
