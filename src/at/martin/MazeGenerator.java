package at.martin;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by martin on 09.08.16.
 */
public class MazeGenerator {
    private Cell[][] grid;
    private Stack<Cell> stack;
    private Cell currentCell;
    private int visited;
    private int totalCells;

    public MazeGenerator(int x , int y){
            grid = new Cell[x][y];
            totalCells  = x*y;
            for (int r = 0; r < x;r++){
                for (int c = 0; c < y;c++){
                    grid[r][c] = new Cell();
                    grid[r][c].positionX = r;
                    grid[r][c].positionY = c;
                }
            }
            currentCell = grid[0][0];
            currentCell.visited = true;
            visited++;
            stack = new Stack<>();
            stack.push(currentCell);

            generateMaze();


    }

    private void generateMaze() {
        while (visited < totalCells){

            System.out.println("push to stack");

            ArrayList<Cell> neigh = new ArrayList<>();
            Cell top = null;
            Cell right = null;
            Cell bottom = null;
            Cell left = null;

            //Top-Neighbour
            if (currentCell.positionX-1 >= 0 && grid[currentCell.positionX-1][currentCell.positionY].visited == false){
                top = grid[currentCell.positionX-1][currentCell.positionY];
                neigh.add(top);

            }  if (currentCell.positionX+1 < grid.length && grid[currentCell.positionX+1][currentCell.positionY].visited == false){
                bottom = grid[currentCell.positionX+1][currentCell.positionY];
                neigh.add(bottom);
            } if (currentCell.positionY-1 >= 0 && grid[currentCell.positionX][currentCell.positionY-1].visited == false){
                left = grid[currentCell.positionX][currentCell.positionY-1];
                neigh.add(left);
            } if (currentCell.positionY+1 < grid[0].length && grid[currentCell.positionX][currentCell.positionY+1].visited == false){
                right = grid[currentCell.positionX][currentCell.positionY+1];
                neigh.add(right);
            }

            if (neigh.size() >0){
                int rand = (int) Math.floor(Math.random()*neigh.size());
                Cell next = neigh.get(rand);

                if (top != null && next.equals(top)){
                    currentCell.topWall = false;
                    next.bottomWall=false;
                } else if (right != null && next.equals(right)){
                    currentCell.rightWall = false;
                    next.leftWall = false;
                } else if (bottom != null && next.equals(bottom)){
                    currentCell.bottomWall = false;
                    next.topWall=false;
                } else if (left != null && next.equals(left)){
                    currentCell.leftWall = false;
                    next.rightWall=false;
                }

                visited++;

                currentCell = next;
                currentCell.visited = true;
                stack.push(currentCell);

            } else {
               currentCell = stack.pop();

            }



        }
    }
    private void printMaze(){
        for (int r = 0; r < grid.length;r++){
            for (int c = 0; c < grid[0].length;c++){
                System.out.print(grid[r][c]);
            }
        }
    }
    public char[][] mazeToArray(){
        char[][] maze = new char[grid.length+2][grid[0].length+2];
        for (int r = 0; r < grid.length;r++){
            for (int c = 0; c < grid[0].length;c++){
                Cell current = grid[r][c];
                if (r-1 >= 0) {
                    maze[r-1][c] = grid[r][c].topWall ? '#' : ' ';
                } if (r+1 < maze.length){
                    maze[r+1][c] = grid[r][c].bottomWall ? '#':' ';
                } if (c-1 >= 0){
                    maze[r][c-1] = grid[r][c].leftWall ? '#' : ' ';
                } if (c+1 < maze[0].length){
                    maze[r][c+1] = grid[r][c].rightWall ? '#': ' ';
                }
            }
        }
        return maze;
    }

    private class Cell {
        private boolean topWall = true;
        private boolean rightWall = true;
        private boolean bottomWall = true;
        private boolean leftWall = true;
        private boolean visited = false;
        private int positionX;
        private int positionY;


        public Cell(){

        }

        @Override
        public String toString(){
            String s = "";
            s+= topWall ? '#' : ' ';
            s+= "\n";
            s+= leftWall ? '#':' ';
            s+= ' ';
            s+= rightWall ? '#' : ' ';
            s+= "\n";
            s+= " ";
            s+= bottomWall ? '#': ' ';
            return s;
        }
    }
}
