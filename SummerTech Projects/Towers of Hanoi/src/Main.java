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
        
        System.out.println(left);
    }

    public static void main(String[] args) {
        new Main();
    }

}
