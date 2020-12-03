import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Stack;

public class Solver {
    private final ArrayList<Board> solution;
    private int moves;
    private boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if(initial == null){
            throw new IllegalArgumentException();
        }
        solution = new ArrayList<>();
        solve(initial);
    }

    private void solve(Board initial) {
        MinPQ<Node> origin = new MinPQ<>();
        origin.insert(new Node(initial, 0, null));
        Node currOrigin = origin.delMin();

        MinPQ<Node> twin = new MinPQ<>();
        twin.insert(new Node(initial.twin(), 0, null));
        Node currTwin = twin.delMin();
        int count = 4;
        while (true) {
         //   System.out.println(count);
            if (currOrigin.board.isGoal()) {
                isSolvable = true;
                moves = currOrigin.moves;
                while (currOrigin != null) {
                    solution.add(0, currOrigin.board);
                    currOrigin = currOrigin.prev;
                }

                break;
            }
            Board prevBoard = null;
            if (currOrigin.prev != null) {
                prevBoard = currOrigin.prev.board;
            }
            for (Board board : currOrigin.board.neighbors()) {
                if (board == null) {
                    System.out.println("null");
                }
                if (prevBoard == null || !prevBoard.equals(board)) {
                    origin.insert(new Node(board, currOrigin.moves + 1, currOrigin));
                    count++;
                }
            }
            currOrigin = origin.delMin();
            count++;

            if (currTwin.board.isGoal()) {
                isSolvable = false;
                break;
            }
            prevBoard = null;
            if (currTwin.prev != null) {
                prevBoard = currTwin.prev.board;
            }
            for (Board board : currTwin.board.neighbors()) {
                if (prevBoard == null || !prevBoard.equals(board)) {
                    twin.insert(new Node(board, currTwin.moves + 1, currTwin));
                    count++;
                }
            }
            currTwin = twin.delMin();
            count++;

            if(count >= 10000000){
                isSolvable = false;
                break;
            }
        }

    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable)
            return moves;
        else
            return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable) {
            return solution;
        } else
            return null;
    }

    private static class Node implements Comparable<Node> {
        Board board;
        int moves;
        int manhattan;
        Node prev;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.manhattan = board.manhattan();
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(manhattan + moves, node.manhattan + node.moves);

        }

    }

    // test client (see below)
    public static void main(String[] args) {

    }

}