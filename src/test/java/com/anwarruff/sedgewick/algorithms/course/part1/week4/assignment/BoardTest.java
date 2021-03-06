package com.anwarruff.sedgewick.algorithms.course.part1.week4.assignment;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

/**
 * Created by aruff on 11/28/16.
 */
public class BoardTest {

    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> new Board(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testDimension() {
        int[][] initial = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(initial);
        assertEquals(3, board.dimension());
    }

    @Test
    public void testManhattan() {
        /*
         *   BoardTest
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 4 | 5 | 6 |
         * +---+---+---+
         * | 7 | 8 |   |
         * +---+---+---+
         *
         *    Entry
         * +---+---+---+
         * | 8 | 1 | 3 |
         * +---+---+---+
         * | 4 |   | 2 |
         * +---+---+---+
         * | 7 | 6 | 5 |
         * +---+---+---+
         *
         * value: (VerticalDistance, HorizontalDistance, SumHorizontalVerticalDistances)
         * 8: (2, 1, 3)
         * 1: (0, 1, 1)
         * 3: (0, 0, 0)
         * 4: (0, 0, 0)
         * 2: (1, 1, 2)
         * 7: (0, 0, 0)
         * 6: (1, 1, 2)
         * 5: (1, 1, 2)
         * Manhattan Distance = 3 + 1 + 0 + 0 + 2 + 0 + 2 + 2 = 10
         */
        int[][] initial = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(initial);
        assertEquals(10, board.manhattan());
    }

    @Test
    public void testHamming() {
        /*
         *   BoardTest
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 4 | 5 | 6 |
         * +---+---+---+
         * | 7 | 8 |   |
         * +---+---+---+
         *
         *    Entry
         * +---+---+---+
         * | 8 | 1 | 3 |
         * +---+---+---+
         * | 4 |   | 2 |
         * +---+---+---+
         * | 7 | 6 | 5 |
         * +---+---+---+
         *
         * If a tile is out of place, it's Hamming value is 1, otherwise it's 0.
         * The total hamming distance is the sum of the Hamming distance for each tile, precluding the empty tile.
         *
         *    Hamming
         * +---+---+---+
         * | 1 | 1 | 0 |
         * +---+---+---+
         * | 0 |   | 1 |
         * +---+---+---+
         * | 0 | 1 | 1 |
         * +---+---+---+
         *
         * Hamming Distance = 1 + 1 + 0 + 0 + 1 + 0 + 1 + 1 = 5
         */

        int[][] initial = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(initial);
        assertEquals(5, board.hamming());
    }

    @Test
    public void testIsGoal() {
        int[][] v1 = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        Board b1 = new Board(v1);
        assertFalse(b1.isGoal());

        int[][] v2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b2 = new Board(v2);
        assertTrue(b2.isGoal());
    }

    @Test
    public void testEquals() {
        int[][] v1 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] v2 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board b1 = new Board(v1);
        Board b2 = new Board(v2);
        assertTrue(b1.equals(b2));

        int[][] v3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b3 = new Board(v3);
        assertFalse(b1.equals(b3));
    }

    @Test
    public void testTwin() {
        int[][] v1 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(v1);
        Board twin = board.twin();
        assertFalse(twin.toString(), board.equals(twin));
    }

    @Test
    public void testIterable() {
        /*
         *  Board
         * +---+---+---+
         * |   | 1 | 2 |
         * +---+---+---+
         * | 3 | 4 | 5 |
         * +---+---+---+
         * | 6 | 7 | 8 |
         * +---+---+---+
         *
         *  Neighbors
         * +---+---+---+
         * | 3 | 1 | 2 |
         * +---+---+---+
         * |   | 4 | 5 |
         * +---+---+---+
         * | 6 | 7 | 8 |
         * +---+---+---+
         *
         * +---+---+---+
         * | 1 |   | 2 |
         * +---+---+---+
         * | 3 | 4 | 5 |
         * +---+---+---+
         * | 6 | 7 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(v1);
        Iterable<Board> iterable = board.neighbors();

        int[][] v2 = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board b2 = new Board(v2);
        int[][] v3 = {{1, 3, 0}, {4, 2, 5}, {7, 8, 6}};
        Board b3 = new Board(v3);
        int[][] v4 = {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}};
        Board b4 = new Board(v4);

        for (Board b : iterable) {
            assertTrue(b.equals(b2) || b.equals(b3) || b.equals(b4));
        }
    }

    @Test
    public void testCenterNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 7 | 0 | 6 |
         * +---+---+---+
         * | 5 | 4 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{1, 2, 3}, {7, 0, 6}, {5, 4, 8}};
        int[][][] results = {
                {{1, 0, 3}, {7, 2, 6}, {5, 4, 8}},
                {{1, 2, 3}, {7, 4, 6}, {5, 0, 8}},
                {{1, 2, 3}, {0, 7, 6}, {5, 4, 8}},
                {{1, 2, 3}, {7, 6, 0}, {5, 4, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }

    @Test
    public void testMiddleRightNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 7 | 6 | 0 |
         * +---+---+---+
         * | 5 | 4 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{1, 2, 3}, {7, 6, 0}, {5, 4, 8}};
        int[][][] results = {
                {{1, 2, 0}, {7, 6, 3}, {5, 4, 8}},
                {{1, 2, 3}, {7, 6, 8}, {5, 4, 0}},
                {{1, 2, 3}, {7, 0, 6}, {5, 4, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }

    @Test
    public void testMiddleLeftNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 0 | 7 | 6 |
         * +---+---+---+
         * | 5 | 4 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{1, 2, 3}, {0, 7, 6}, {5, 4, 8}};
        int[][][] results = {
                {{0, 2, 3}, {1, 7, 6}, {5, 4, 8}},
                {{1, 2, 3}, {5, 7, 6}, {0, 4, 8}},
                {{1, 2, 3}, {7, 0, 6}, {5, 4, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }

    @Test
    public void testTopLeftNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 0 | 2 | 3 |
         * +---+---+---+
         * | 1 | 7 | 6 |
         * +---+---+---+
         * | 5 | 4 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{0, 2, 3}, {1, 7, 6}, {5, 4, 8}};
        int[][][] results = {
                {{1, 2, 3}, {0, 7, 6}, {5, 4, 8}},
                {{2, 0, 3}, {1, 7, 6}, {5, 4, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }

    @Test
    public void testTopMiddleNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 2 | 0 | 3 |
         * +---+---+---+
         * | 1 | 7 | 6 |
         * +---+---+---+
         * | 5 | 4 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{2, 0, 3}, {1, 7, 6}, {5, 4, 8}};
        int[][][] results = {
                {{2, 7, 3}, {1, 0, 6}, {5, 4, 8}},
                {{0, 2, 3}, {1, 7, 6}, {5, 4, 8}},
                {{2, 3, 0}, {1, 7, 6}, {5, 4, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }
    @Test
    public void testTopRightNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 2 | 3 | 0 |
         * +---+---+---+
         * | 1 | 7 | 6 |
         * +---+---+---+
         * | 5 | 4 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{2, 3, 0}, {1, 7, 6}, {5, 4, 8}};
        int[][][] results = {
                {{2, 3, 6}, {1, 7, 0}, {5, 4, 8}},
                {{2, 0, 3}, {1, 7, 6}, {5, 4, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }
    @Test
    public void testBottomLeftNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 5 | 7 | 6 |
         * +---+---+---+
         * | 0 | 4 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{1, 2, 3}, {5, 7, 6}, {0, 4, 8}};
        int[][][] results = {
                {{1, 2, 3}, {0, 7, 6}, {5, 4, 8}},
                {{1, 2, 3}, {5, 7, 6}, {4, 0, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }
    @Test
    public void testBottomMiddleNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 5 | 7 | 6 |
         * +---+---+---+
         * | 4 | 0 | 8 |
         * +---+---+---+
         */
        int[][] v1 = {{1, 2, 3}, {5, 7, 6}, {4, 0, 8}};
        int[][][] results = {
                {{1, 2, 3}, {5, 0, 6}, {4, 7, 8}},
                {{1, 2, 3}, {5, 7, 6}, {0, 4, 8}},
                {{1, 2, 3}, {5, 7, 6}, {4, 8, 0}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }
    @Test
    public void testBottomRightNeighbors() {
        /*
         *  Board
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 5 | 7 | 6 |
         * +---+---+---+
         * | 4 | 8 | 0 |
         * +---+---+---+
         */
        int[][] v1 = {{1, 2, 3}, {5, 7, 6}, {4, 8, 0}};
        int[][][] results = {
                {{1, 2, 3}, {5, 7, 0}, {4, 8, 6}},
                {{1, 2, 3}, {5, 7, 6}, {4, 0, 8}},
        };
        Board board = new Board(v1);
        Iterable<Board> neighbors = board.neighbors();
        int i = 0;
        for (Board neighbor : neighbors) {
            Board expected = new Board(results[i]);
            assertTrue("expected: " + expected, neighbor.equals(expected));
            ++i;
        }
    }
}