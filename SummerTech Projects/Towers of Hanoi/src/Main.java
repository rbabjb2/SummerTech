import java.util.ArrayList;

public class Main {
    ArrayList<Integer> left = new ArrayList<Integer>();
    ArrayList<Integer> right = new ArrayList<Integer>();
    ArrayList<Integer> center = new ArrayList<Integer>();
    int towerHeight = 7;

    public void solve(ArrayList<Integer> goal, ArrayList<Integer> start, ArrayList<Integer> other, int moveNum, int height) {
        if (moveNum == 0) {
            move(goal, start);
            solve(other, start, goal, moveNum + 1, height);
        } else if (moveNum == height) {
            System.out.println(left);
            System.out.println(center);
            System.out.println(right);
        } else {
            move(goal, start);
            solve(goal, other, start, 0, moveNum);
            solve(other, start, goal, moveNum + 1,towerHeight);
        }
    }

    public Main() {
        for (int i = 0; i < towerHeight; i++) {
            left.add(i);
        }
        solve(right, left, center, 0, towerHeight);
        /*
         * moveTop2(center, left, right);
         * 
         * move(right, left);
         * 
         * moveTop2(right, center, left);
         * // Green
         * 
         * move(center, left);
         * 
         * moveTop2(left, right, center);
         * 
         * move(center, right);
         * 
         * moveTop2(center, left, right);
         * // Light Blue
         * 
         * move(right, left);
         * // Bring in next piece
         * 
         * // Dark Blue
         * 
         * moveTop2(right, center, left);
         * 
         * move(left, center);
         * 
         * moveTop2(left, right, center);
         * 
         * move(right, center);
         * 
         * moveTop2(center, left, right);
         * 
         * move(right, left);
         * 
         * moveTop2(right, center, left);
         * move(center, left);
         * // Bring in next piece
         * 
         * // Purple
         * moveTop2(left, right, center);
         * 
         * move(center, right);
         * 
         * moveTop2(center, left, right);
         * 
         * move(left, right);
         * 
         * moveTop2(right, center, left);
         * 
         * move(left, center);
         * 
         * moveTop2(left, right, center);
         * move(center, right);
         * 
         * moveTop2(center, left, right);
         * 
         * move(right, left);
         * 
         * moveTop2(right, center, left);
         * 
         * move(center, left);
         * 
         * moveTop2(left, right, center);
         * 
         * move(center, right);
         * 
         * moveTop2(center, left, right);
         */

    }

    public static void main(String[] args) {
        new Main();
    }

    public void move(ArrayList<Integer> end, ArrayList<Integer> start) {
        end.add(0, start.get(0));
        start.remove(0);
    }

    public void moveTop2(ArrayList<Integer> to, ArrayList<Integer> from, ArrayList<Integer> other) {
        move(other, from);
        move(to, from);
        move(to, other);
    }

}
