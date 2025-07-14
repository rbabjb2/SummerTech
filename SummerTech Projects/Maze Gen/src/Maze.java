import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

@SuppressWarnings("unused")
public class Maze extends JFrame implements Runnable {
    static JFrame frame = new JFrame("MAZE!!!!!!!!!!!!!!!!!!!!!!!!!");
    static JPanel panel = new JPanel();
    static JLabel label = new JLabel("Generic Text");
    static Random random = new Random();
    static int gridSize = 31;
    private static int maze[][] = new int[gridSize][gridSize];
    private Thread thread;
    private boolean isRunning;
    Color color = new Color(255, 255, 0);
    private BufferedImage image;
    public int[] pixels;
    public ArrayList<Texture> textures;
    public Camera camera;
    public Screen screen;

    public Maze() {
        image = new BufferedImage(700, 500, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        thread = new Thread(this);
        textures = new ArrayList<Texture>();
        textures.add(Texture.brick);
        screen = new Screen();
        frame.addKeyListener(camera);
        frame.setFocusable(true);
        frame.requestFocus();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                maze[j][i] = 1;
            }

        }
        maze[gridSize - 2][gridSize - 1] = 0;
        generateMaze(1, 1);
        camera = new Camera(1.5, 1.5, 1, 0, 0, -0.66, maze);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        panel.setBackground(color);
        frame.setVisible(true);
        panel.add(label);
        frame.add(panel);
        start();
    }

    public static void main(String[] args) {
        Maze Maze = new Maze();
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
        maze[row][col] = 0;
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
                    && (maze[newRow][newCol] == 1)) {
                maze[row + (newRow - row) / 2][col + (newCol - col) / 2] = 0;
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

    public void run() {
        double delta = 0;
        long startTime = System.nanoTime();
        final double frameRate = 1000000000.0 / 60.0;
        int red = 255;
        boolean redBig = true;
        while (isRunning) {
            long nanoTime = System.nanoTime();
            delta += ((nanoTime - startTime) / frameRate);
            startTime = nanoTime;
            while (delta >= 1) {
                delta--;
                color = new Color(red, 0, 255);
                panel.setBackground(color);

                if (red == 0) {
                    redBig = false;
                    red++;
                } else if (red == 255) {
                    redBig = true;
                    red--;
                }
                if (redBig == true) {
                    red--;
                } else {
                    red++;
                }
            }
        }
    }

    private synchronized void start() {
        isRunning = true;
        thread.start();
    }

    private synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
