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

    public double mean()
    {
        return StdStats.mean(ratioOfOpenSites);
    }

    public double stddev()
    {
        return StdStats.stddev(ratioOfOpenSites);
    }

    public double confidenceLow()
    {
        return (mean() - ((1.96 * stddev()) / Math.sqrt(experimentsTotal)));
    }

    public double confidenceHigh()
    {
       return (mean() + ((1.96 * stddev()) / Math.sqrt(experimentsTotal)));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 1000);
        StdOut.println("Mean: " + percolationStats.mean());
        StdOut.println("Standard Deviation: " + percolationStats.stddev());
        StdOut.println("Confidence Low: " + percolationStats.confidenceLow());
        StdOut.println("Confidence High: " + percolationStats.confidenceHigh());
    }

}