package main.java.edu.gwu.maze.solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MazeSolverApplication {

    private static final CellComparator CELL_COMPARATOR = new CellComparator();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("maze.txt");

        int[][] maze = new int[81][81];
        Scanner input = new Scanner(file);
        for (int i = 0; input.hasNextLine(); i++) {
            Scanner colReader = new Scanner(input.nextLine());
            for (int j = 0; colReader.hasNextInt(); j++) {
                maze[i][j] = colReader.nextInt();
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Start Points: ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        System.out.print("End Points: ");
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();

        Instant start = Instant.now();

        aStar(maze, startX, startY, endX, endY);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: " + timeElapsed.toMillis() + " ms");
    }

    private static void aStar(int[][] maze, int startX, int startY, int endX, int endY) {
        if (maze[startX][startY] == 1 || maze[endX][endY] == 1) {
            System.out.println("NO");
            return;
        }

        Map<Integer, Map<Integer, Integer>> gCost = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> fCost = new HashMap<>();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                Map<Integer, Integer> cellCost;
                if (Objects.isNull(gCost.get(i))) {
                    cellCost = new HashMap<>();
                } else {
                    cellCost = gCost.get(i);
                }
                cellCost.put(j, Integer.MAX_VALUE);
                gCost.put(i, cellCost);
                fCost.put(i, cellCost);
            }
        }
        Map<Integer, Integer> startCellGCost = gCost.get(startX);
        startCellGCost.put(startY, 0);
        Map<Integer, Integer> startCellFCost = fCost.get(startX);
        int startHeuristicCost = Cell.calcHeuristicCost(startX, startY, endX, endY);
        startCellFCost.put(startY, startHeuristicCost);
        gCost.put(startX, startCellGCost);
        fCost.put(startX, startCellFCost);

        PriorityQueue<Cell> cellPriorityQueue = new PriorityQueue<>(CELL_COMPARATOR);
        cellPriorityQueue.add(new Cell(startX, startY, startHeuristicCost, startHeuristicCost));

        while (!cellPriorityQueue.isEmpty()) {
            Cell currentCell = cellPriorityQueue.poll();
            if (currentCell.getX() == endX && currentCell.getY() == endY) {
                System.out.println("YES");
                return;
            }

            int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // UP, DOWN, LEFT, RIGHT
            for (int[] dir : directions) {
                if (currentCell.getX() + dir[0] < maze.length &&
                        currentCell.getX() + dir[0] >= 0 &&
                        currentCell.getY() + dir[1] < maze[currentCell.getX()].length &&
                        currentCell.getY() + dir[1] >= 0 &&
                        maze[currentCell.getX() + dir[0]][currentCell.getY() + dir[1]] == 0) {
                    Cell childCell =
                            new Cell(currentCell.getX() + dir[0], currentCell.getY() + dir[1]);

                    int tempGCost = gCost.get(currentCell.getX()).get(currentCell.getY()) + 1;
                    int tempHCost =
                            Cell.calcHeuristicCost(childCell.getX(), childCell.getY(), endX, endY);
                    int tempFCost = tempGCost + tempHCost;

                    if (tempFCost < fCost.get(childCell.getX()).get(childCell.getY())) {
                        gCost.get(childCell.getX()).put(childCell.getY(), tempGCost);
                        fCost.get(childCell.getX()).put(childCell.getY(), tempFCost);
                        childCell.setFinalCost(tempFCost);
                        childCell.setHeuristicCost(tempHCost);
                        cellPriorityQueue.add(childCell);
                    }
                }
            }
        }
        System.out.println("NO");
    }

}
