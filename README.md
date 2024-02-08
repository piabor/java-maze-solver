# Maze Solver Application

This Java application is designed to solve mazes using the A* algorithm. The program takes a maze as input, represented by a 2D array where 0 denotes a clear path, and 1 represents walls. Users can specify the starting and ending points within the maze, and the A* algorithm efficiently finds the shortest path between these points.
## Requirements

**Java (JDK):** https://www.oracle.com/java/technologies/downloads/


## Run Locally

Clone the project

```bash
  git clone https://github.com/piabor/java-maze-solver.git
```

Go to the project directory

```bash
  cd ./java-maze-solver
```

Generate runnable .class files from .java files

```bash
  javac -sourcepath ./src/ -d ./out/ ./src/main/java/edu/gwu/maze/solver/MazeSolverApplication.java
```

Run the program:

```bash
  java -cp ./out/ main/java/edu/gwu/maze/solver/MazeSolverApplication
```