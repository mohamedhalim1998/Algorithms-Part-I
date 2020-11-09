import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        Percolation percolation = new Percolation(20);

        System.out.println(percolation.isOpen(1,1));
        System.out.println(percolation.isFull(1,1));
    }
}
