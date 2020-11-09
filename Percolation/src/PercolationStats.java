import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    private final double[] res;
    private final int trials;
    private final double CONFIDENT_CONST = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        res = new double[trials];
        for (int i = 0; i < trials; i++) {

            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {

                percolation.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            res[i] = percolation.numberOfOpenSites() * 1.0 / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(res);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
//        double ans = 0.0;
//        double mean = mean();
//        for (double x : res) {
//            ans += (x - mean) * (x - mean);
//        }
        return StdStats.stddev(res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENT_CONST * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENT_CONST * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}