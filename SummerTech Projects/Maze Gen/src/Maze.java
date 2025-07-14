import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Maze extends JFrame implements KeyListener {
    static JFrame frame = new JFrame("MAZE!!!!!!!!!!!!!!!!!!!!!!!!!");
    static JPanel panel = new JPanel();
    static JLabel label = new JLabel("Generic Text");
    static Random random = new Random();
    static int gridSize = 31;
    private static char maze[][] = new char[gridSize][gridSize];

    public Maze() {
        super();
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocus();
    }

    public static void main(String[] args) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                maze[j][i] = 'X';
            }

        }
        maze[1][0] = ' ';
        maze[gridSize - 2][gridSize - 1] = ' ';
        generateMaze(1, 1);
        printScreen();
        new Maze();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(label);
    }

    private static void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static void generateMaze(int row, int col) {
        maze[row][col] = ' ';
        int[] directions = { 0, 1, 2, 3 };
        shuffleArray(directions);
        for (int direction : directions) {
            int newRow = row;
            int newCol = col;
            if (direction == 0) {
                newRow -= 2;
            }
            if (direction == 1) {
                newRow += 2;
            }
            if (direction == 2) {
                newCol -= 2;
            }
            if (direction == 3) {
                newCol += 2;
            }
            if (newRow > 0 && newRow < gridSize - 1 && newCol > 0 && newCol < gridSize - 1
                    && (maze[newRow][newCol] == 'X')) {
                maze[row + (newRow - row) / 2][col + (newCol - col) / 2] = ' ';
                generateMaze(newRow, newCol);
            }
        }
    }

    public static void printScreen() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                System.out.print(maze[y][x]);
            }
            System.out.println();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
            break;
        }
    }

    @Override 
}
