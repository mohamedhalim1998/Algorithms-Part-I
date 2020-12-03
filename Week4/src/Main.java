import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                arr[i][j] = k++;
//            }
//        }
        int[][] arr = new int[3][3];
//        arr[0][0] = 8;
//        arr[0][1] = 1;
//        arr[0][2] = 3;
//        arr[1][0] = 4;
//        arr[1][1] = 0;
//        arr[1][2] = 2;
//        arr[2][0] = 7;
//        arr[2][1] = 5;
//        arr[2][2] = 6;
        arr[0][0] = 1;
        arr[0][1] = 2;
        arr[0][2] = 3;
        arr[1][0] = 4;
        arr[1][1] = 5;
        arr[1][2] = 6;
        arr[2][0] = 8;
        arr[2][1] = 7;
        arr[2][2] = 0;
//        Scanner scanner = new Scanner(new File("in.txt"));
//
//        int n = scanner.nextInt();
//        int[][] arr = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                arr[i][j] = scanner.nextInt();
//            }
//        }
        Board b1 = new Board(arr);
        Solver solver = new Solver(b1);
        System.out.println(solver.moves());

            for (Board b : solver.solution()) {
                System.out.println(b);
            }
    }
}
