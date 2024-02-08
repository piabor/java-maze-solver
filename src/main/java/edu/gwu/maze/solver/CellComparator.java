package main.java.edu.gwu.maze.solver;

import java.util.Comparator;

public class CellComparator implements Comparator<Cell> {

    @Override
    public int compare(Cell c1, Cell c2) {
        if (c1.getFinalCost() < c2.getFinalCost()) return -1;
        else if (c1.getFinalCost() > c2.getFinalCost()) return 1;
        else if (c1.calcHeuristicCost() < c2.calcHeuristicCost()) return -1;
        else if (c1.calcHeuristicCost() > c2.calcHeuristicCost()) return 1;
        return 0;
    }

}
