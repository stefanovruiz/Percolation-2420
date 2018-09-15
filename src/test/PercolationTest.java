package test;

import percolation.Percolation;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void Percolation()
    {
        Percolation p1;

        p1 = new Percolation(100);
        System.out.println(p1.isOpen(10, 10));


        assertFalse(p1.isOpen(10, 10));

        System.out.println(p1.isOpen(11,91));
        assertFalse(p1.isOpen(11, 91));
    }
    @org.junit.jupiter.api.Test
    void open() {
    }

    @org.junit.jupiter.api.Test
    void isOpen() {
        Percolation p1 = new Percolation(10);
        System.out.println(p1.isOpen(5, 5));
        assertFalse(p1.isOpen(5,5));



    }
}