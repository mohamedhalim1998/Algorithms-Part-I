import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class Board {
    private final int[][] tiles;
    private final int size;
    private Board twin;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        int n = tiles.length;
        if (n < 2) {
            throw new IllegalArgumentException();
        }
        int m = tiles[0].length;
        if (m != n) {
            throw new IllegalArgumentException();
        }
        this.tiles = new int[n][n];
        this.size = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size).append('\n');
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.append(" ").append(tiles[i][j]);
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) {
                    continue;
                }
                int val = getLocationValue(i, j);
                if (tiles[i][j] != val) {
                    count++;
                }
            }
        }
        return count;
    }

    private int getLocationValue(int x, int y) {
        return x * size + y + 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] == 0) {
                    continue;
                }
                int[] correctPos = getValueLocation(tiles[i][j]);
                count += Math.abs(i - correctPos[0]) + Math.abs(j - correctPos[1]);
            }
        }
        return count;
    }

    private int[] getValueLocation(int v) {
        v--;
        int x = v / size;
        int y = v % size;
        return new int[]{x, y};
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0;
    }

    // does this board equal y?


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        return this.hamming() == ((Board) o).hamming() && this.manhattan() == ((Board) o).manhattan();

        if (this.dimension() != ((Board) o).dimension()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != ((Board) o).tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        int[][] copy = new int[size][size];
        int blankX = -1;
        int blankY = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    blankX = i;
                    blankY = j;
                }
            }
        }
        int[] d = {1, 0, -1, 0, 0, 1, 0, -1};
        for (int i = 0; i < d.length; i += 2) {
            int x = blankX + d[i];
            int y = blankY + d[i + 1];
            if (valid(x, y)) {
                int temp = copy[x][y];
                copy[x][y] = copy[blankX][blankY];
                copy[blankX][blankY] = temp;
                boards.add(new Board(copy));
                temp = copy[x][y];
                copy[x][y] = copy[blankX][blankY];
                copy[blankX][blankY] = temp;
            }
        }
        return boards;
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (twin == null) {
            int[][] copy = new int[size][size];
            for (int i = 0; i < size; i++) {

                copy[i] = tiles[i].clone();

            }
            int a = StdRandom.uniform(0, size * size);
            while (copy[a / size][a % size] == 0) {
                a = StdRandom.uniform(0, size * size);
            }
            int b = StdRandom.uniform(0, size * size);
            while (a == b || copy[b / size][b % size] == 0) {
                b = StdRandom.uniform(0, size * size);
            }
            int temp = copy[a / size][a % size];
            copy[a / size][a % size] = copy[b / size][b % size];
            copy[b / size][b % size] = temp;
            twin = new Board(copy);
        }
        return twin;
    }

}