import java.util.ArrayList;

public class Main {
    ArrayList<Integer> left = new ArrayList<Integer>();
    ArrayList<Integer> right = new ArrayList<Integer>();
    ArrayList<Integer> center = new ArrayList<Integer>();
    int towerHeight = 7;

    public Main() {
        for (int i = 0; i < towerHeight; i++) {
            left.add(i);
        }


        moveTop2(center, left, right);

        move(right, left);
        //Green 
        moveTop2(right, center, left);

        move(center, left);
        //Light Blue

        moveTop2(left, right, center);

        move(center, right);

        moveTop2(center, left, right);

        move();
        // Dark Blue
        mo

        System.out.println(left);
        System.out.println(center);
        System.out.println(right);
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
