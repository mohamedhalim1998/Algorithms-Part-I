
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final int size;
    private final WeightedQuickUnionUF unionUF;
    private int countOpen = 0;
    private final int virFirst;
    private final int virLast;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        this.grid = new boolean[n][n];
        this.unionUF = new WeightedQuickUnionUF(n * n + 2);
        this.virFirst = 0;
        this.virLast = n * n + 1;
        for (int i = 1; i <= n; i++) {
            unionUF.union(virFirst, i);
            unionUF.union(virLast, virLast - i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return;
        }
        countOpen++;
        row--;
        col--;
        grid[row][col] = true;

        if (row > 0 && grid[row - 1][col]) {
            unionUF.union(gridToLinear(row, col), gridToLinear(row - 1, col));
        }
        if (row < size - 1 && grid[row + 1][col]) {
            unionUF.union(gridToLinear(row, col), gridToLinear(row + 1, col));
        }
        if (col > 0 && grid[row][col - 1]) {
            unionUF.union(gridToLinear(row, col), gridToLinear(row, col - 1));
        }
        if (col < size - 1 && grid[row][col + 1]) {
            unionUF.union(gridToLinear(row, col), gridToLinear(row, col + 1));
        }


    }

    private int gridToLinear(int row, int col) {

        return row * size + col + 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }


        return isOpen(row, col) && isOpen(row, col) && unionUF.find(virFirst) == unionUF.find(gridToLinear(row - 1, col - 1));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionUF.find(virFirst) == unionUF.find(virLast);
    }
}