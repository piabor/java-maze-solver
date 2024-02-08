package main.java.edu.gwu.maze.solver;

public class Cell {

    private final int x;
    private final int y;
    private int finalCost;
    private int heuristicCost;

    public Cell(int x, int y, int finalCost, int heuristicCost) {
        this.x = x;
        this.y = y;
        this.finalCost = finalCost;
        this.heuristicCost = heuristicCost;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(int finalCost) {
        this.finalCost = finalCost;
    }

    public int calcHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public static int calcHeuristicCost(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

}
