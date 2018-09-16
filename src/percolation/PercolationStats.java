package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int experimentsTotal;
    private double[] ratioOfOpenSites;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Constructor values can't be zero or less.");
        }
        experimentsTotal = T;
        ratioOfOpenSites = new double[T];
        for (int i = 0; i < experimentsTotal; i++) {
            Percolation newExperiment = new Percolation(N);
            int openSites = 0;
            while (newExperiment.percolates() == false) {
                int rand1 = StdRandom.uniform(N);
                int rand2 = StdRandom.uniform(N);
                if (newExperiment.isOpen(rand1, rand2) == false) {
                    newExperiment.open(rand1, rand2);
                    openSites += 1;
                }
            }
            ratioOfOpenSites[i] = ((double) openSites) / (N * N);
        }
    }

    /***
     * Gets the mean of percolation threshold
     *
     * @return
     */
    public double mean()
    {
        return StdStats.mean(ratioOfOpenSites);
    }

    /***
     * Gets the standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev()
    {
        return StdStats.stddev(ratioOfOpenSites);
    }

    /**
     * Gets the low  end endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLow()
    {
        return (mean() - ((1.96 * stddev()) / Math.sqrt(experimentsTotal)));
    }

    /***
     * Gets the high end point of 95% confidence interval
     *
     * @return
     */
    public double confidenceHigh()
    {
       return (mean() + ((1.96 * stddev()) / Math.sqrt(experimentsTotal)));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        StdOut.println("Mean: " + percolationStats.mean());
        StdOut.println("Standard Deviation: " + percolationStats.stddev());
        StdOut.println("Confidence Low: " + percolationStats.confidenceLow());
        StdOut.println("Confidence High: " + percolationStats.confidenceHigh());
    }

}