package main.java.edu.gwu.ai.assignment.maze;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeApplication {

    public static void main(String[] args) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("maze.txt");

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        assert is != null;
        Scanner input = new Scanner(is);
        while (input.hasNextLine()) {
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList<Integer> col = new ArrayList<>();
            while (colReader.hasNextInt()) {
                col.add(colReader.nextInt());
            }
            matrix.add(col);
        }
        System.out.printf(matrix.toString());
    }

}
