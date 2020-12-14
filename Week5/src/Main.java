import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.enableDoubleBuffering();
//        KdTree kdTree = new KdTree();
//        int count = 0;
//        while (count < 5){
//            if (StdDraw.isMousePressed()) {
//                double x = StdDraw.mouseX();
//                double y = StdDraw.mouseY();
//                StdOut.printf("%8.6f %8.6f\n", x, y);
//                Point2D p = new Point2D(x, y);
//                kdTree.insert(p);
//                StdDraw.pause(200);
//                count++;
//            }
//        }
//        kdTree.insert(new Point2D(.8, .8));
//        kdTree.draw();
//        StdDraw.setPenColor(0,0,0);
//        StdDraw.rectangle(0,0,.5, .5);
//        StdDraw.show();
//        StdOut.println("---------------------------------------------------------------");
//        kdTree.nearest(new Point2D(.9, .9));
        Scanner scanner = new Scanner(new File("in.in"));
        KdTree kdTree = new KdTree();
        int n = 5;
        for (int i = 0; i < n; i++) {
            scanner.next();
            kdTree.insert(new Point2D(scanner.nextDouble(), scanner.nextDouble()));
        }
        System.out.println(kdTree.size());
        System.out.println(kdTree.nearest(new Point2D(0.145, 0.536)));
        kdTree.draw();
        StdDraw.setPenRadius(.01);
        StdDraw.point(.145, .536);
        StdDraw.show();

//        PointSET pointSET = new PointSET();
//        int count = 0;
//        while (count < 3) {
//            if (StdDraw.isMousePressed()) {
//                double x = StdDraw.mouseX();
//                double y = StdDraw.mouseY();
//                StdOut.printf("%8.6f %8.6f\n", x, y);
//                Point2D p = new Point2D(x, y);
//                pointSET.insert(p);
//                StdDraw.point(x,y);
//                StdDraw.pause(200);
//                count++;
//                StdDraw.show();
//            }
//        }
//
//        StdDraw.line(0,0,1,1);
//        StdDraw.setPenColor(0,0,0);
//        pointSET.draw();
//        StdOut.print("---------------------------------------------------------------");
//        for(Point2D point : pointSET.range(rect)){
//            System.out.println(point);
//        }


    }
}
