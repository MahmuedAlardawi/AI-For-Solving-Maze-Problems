/* CPCS-331_CS1_AE
AI Group Project: Solving Maze Problems

Students:-
Mahmued Alardawi - 2135209
Ahmad Aljedaani - 2136071
Abdullah Emad Almashharawi - 2136141
Abdullah Abed Alharbi- 2135999

*/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class Solving_Maze_Problems_AE {

    static int[][] maze = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0},
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    static boolean isGoalReached = false;

    public static void main(String[] args) {
        Maze();

        // start points:
        int x = 1;
        int y = 17;

        System.out.println("Maze problem");
        System.out.println("Note: Green is the start and red is the end");
        System.out.println("------------\n");

        System.out.println("Solve using: -");
        System.out.println("1) DFS");
        System.out.println("2) BFS");
        System.out.print("Pick a number: ");
        int input = Input(new Scanner(System.in)).nextInt();

        if (input == 1) {DFS(x, y);}
        else {Queue<MazePosition> q = new ArrayDeque<>() {};BFS(x, y, q);}
        Maze();
    }

    // Scanner
    public static Scanner Input(Scanner input) {return new Scanner(System.in);}

    // Maze drawing
    public static void Maze() {
        JFrame frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new GridLayout(maze.length, maze[0].length));

        for (int[] ints : maze) {
            for (int anInt : ints) {
                JPanel panel = new JPanel();
                if (anInt == 0) {panel.setBackground(Color.BLACK);}
                else if (anInt == 3) {panel.setBackground(Color.GREEN);}
                else if (anInt == 9) {panel.setBackground(Color.red);}
                else {panel.setBackground(Color.WHITE);}
                frame.add(panel);
            }
        }
        frame.setVisible(true);
    }

    // making maze (x,y) to add them in queue/stack
    static class MazePosition {
        int x, y;

        public MazePosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public MazePosition(){}

        public int getX() {return x;}
        public void setX(int x) {this.x = x;}
        public int getY() {return y;}
        public void setY(int y) {this.y = y;}

        @Override
        public String toString() {
            return "MazeP{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    // DFS
    public static void DFS(int x, int y) {
        if (maze[x][y] == 9){
            isGoalReached = true;
            return ;
        }
        maze[x][y] = 3;

        if ((maze[x-1][y] == 1 || maze[x-1][y] == 9)&& !isGoalReached){
            DFS(x-1, y);
            if(isGoalReached){return;}
        }
        if ((maze[x][y+1] == 1 || maze[x][y+1] == 9)&& !isGoalReached){
            DFS(x, y + 1);
            if(isGoalReached){return;}
        }
        if ((maze[x+1][y] == 1 || maze[x+1][y] == 9)&& !isGoalReached){
            DFS(x+1, y);
            if(isGoalReached){return;}
        }
        if ((maze[x][y-1] == 1 || maze[x][y-1] == 9)&& !isGoalReached){
            DFS(x, y-1);
        }
    }

    // BFS using queue
    public static void BFS(int x, int y, Queue<MazePosition> q) {

        if (maze[x][y] == 9){isGoalReached = true;return;}
        if ((maze[x-1][y] == 1 || maze[x-1][y] == 9) && !isGoalReached){q.add(new MazePosition(x-1, y));}
        if ((maze[x][y+1] == 1 || maze[x][y+1] == 9) && !isGoalReached){q.add(new MazePosition(x, y+1));}
        if ((maze[x+1][y] == 1 || maze[x+1][y] == 9) && !isGoalReached){q.add(new MazePosition(x+1, y));}
        if ((maze[x][y-1] == 1 || maze[x][y-1] == 9) && !isGoalReached){q.add(new MazePosition(x, y-1));}

        MazePosition index = q.poll();
        x = Objects.requireNonNull(index).getX();
        y = index.getY();
        if (maze[x][y] != 9) {maze[x][y] = 3;}
        BFS(x, y, q);
    }
}