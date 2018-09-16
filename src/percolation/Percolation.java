package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    final int ONE = 1;
    final int TWO = 2;

    private int n;
    private int size; // Size of Array in terms of this.n
    private int virtualTop;
    private int virtualBottom;
    private boolean grid[]; // Makes a n x n 2-D Array where we can see if the value is true or false.
    WeightedQuickUnionUF percChecker;
    WeightedQuickUnionUF unionFind2;

    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("NO NEGATIVE NUMBERS ARE ALLOWED");
        }
        this.n = N;
        size = N * N;

        grid = new boolean[N];

        percChecker = new WeightedQuickUnionUF(size + TWO);
        unionFind2 = new WeightedQuickUnionUF(size + ONE);
        grid = new boolean[size];
        virtualTop = size;
        virtualBottom = size + ONE;

        for (int i = 0; i < size; i++)
        {
            grid[i] = false;
        }
    }


    /***
     * Open's site (row i, column j) if it is not open already
     * @param i
     * @param j
     * */
    public void open(int i, int j)
    {

        grid[location(i, j)] = true;

        if(i < 0 || i > this.n || j < 0 || j > this.n)
        {
            throw new IllegalArgumentException("No negative numbers are allowed.");
        }


        if(i < n - 1 && isOpen(i, j + 1))
        {
            union(location(i, j), location(i, j) + 1);
        }

        if (j > 0 && isOpen(i, j - 1))
        {
            union(location(i, j), location(i, j) - 1);
        }

        if (i > 0 && isOpen(i - 1, j))
        {
            union(location(i, j),  location(i, j) - n);
        }

        else if (i == 0)
        {
            union(location(i, j), virtualTop);
        }

        if (i < n - 1 && isOpen(i + 1, j))
        {
            union(location(i, j), location(i, j) + n);
        }

        else if (i == n - 1)
        {
            percChecker.union(location(i, j), virtualBottom);
        }
    }

    /**
     * Checks to see if the site is open (row i, column j) open
     *
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j) {
        if(j == n) {
            j = n - 1;
        }
        isValidInput(i, j);
        return grid[location(i, j)] == true;
    }

    /***
     * Checks if sites are full
     *
     * @param i
     * @param j
     * @return returns connected locations
     */
    public boolean isFull(int i, int j) {
        isValidInput(i, j);
        return unionFind2.connected(location(i, j), virtualTop);
    }

    /**
     * Converts the 2d coordinates that are given to 1d coordinates.
     * @param i row
     * @param j column
     * @return where the node is in a 1d array
     */
    private int location(int i, int j) {
        return (i * n) + j;
    }

    /**
     * Merges the component containing site p with the component containing site q in both WeightedQuickUnionUF objects.
     * @param p integer representing one site
     * @param q integer representing other site
     */
    private void union(int p, int q) {
        percChecker.union(p, q);
        unionFind2.union(p, q);
    }

    /**
     * Checks if system percolates.
     *
     * @return
     * */
    public boolean percolates() {
        return percChecker.connected(virtualTop, virtualBottom); // From virtual sites bottom and top.
    }


    /***
     * Function checks which sites are open.
     * @return number of sites open in integer value.
     */
    public String numberOfOpenSites()
    {
        int count = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++ )
            {
                if(isOpen(i, j))
                {
                    count++;
                }

            }
        }

        return Integer.toString(count);
    }


    /***
     * Checks if the given in put is valid
     *
     * @param i
     * @param j
     */
    private void isValidInput(int i, int j) {
        if (i < 0 || i > (n - 1))
            throw new IndexOutOfBoundsException("row index i = " + i + " must be between 0 and " + (n - 1));
        if (j < 0 || j > (n - 1))
            throw new IndexOutOfBoundsException("column index j = " + j + " must be between 0 and " + (n - 1));
    }

}

