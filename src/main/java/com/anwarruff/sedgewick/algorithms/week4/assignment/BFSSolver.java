package com.anwarruff.sedgewick.algorithms.week4.assignment;

import com.anwarruff.sedgewick.algorithms.week4.MinPQ;

import java.util.ArrayList;

/**
 * Created by aruff on 12/1/16.
 */
public class BFSSolver {
    private ArrayList<Board> moves = new ArrayList<>();
    private boolean solved = false;
    private int numMoves = 0;

    private Board solution;
    private MinPQ<SearchNode> minPQ;

    public BFSSolver(SearchNode searchNode, Board solution) {
        this.solution = solution;
        minPQ = new MinPQ<>();
        minPQ.insert(searchNode);
    }

    public boolean step() {
        if (solved) {
            return solved;
        }

        SearchNode searchNode = minPQ.delMin();
        SearchNode previous = searchNode.getPrevious();

        moves.add(searchNode.getBoard());
        ++numMoves;

        if (searchNode.isBoardEqual(solution)) {
            solved = true;
            return solved;
        }
        else {
            for (Board neighbor : searchNode.getNeighbors()) {
                if ( previous == null || ! previous.isBoardEqual(neighbor)) {
                    minPQ.insert(new SearchNode(neighbor, searchNode, numMoves));
                }
            }

            return solved;
        }
    }

    public ArrayList<Board> getSolution() {
        return moves;
    }

    public int getNumMoves() {
        return numMoves;
    }
}
