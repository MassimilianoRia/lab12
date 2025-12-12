package it.unibo.es3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Model implementation.
 */
public class ModelImpl implements Model {

    private static final String STAR = "*";
    private static final String EMPTY = " ";
    private final String[][] cells;
    private final int width;

    /**
     * Creates a model with a square grid of the given size.
     * The grid is initialized with empty cells.
     *
     * @param width the grid dimension (rows = columns)
     */
    public ModelImpl(final int width) {
        this.width = width;
        this.cells = new String[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                this.cells[i][j] = EMPTY;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> init() {
        final Random random = new Random();
        final Set<Pair<Integer, Integer>> used = new HashSet<>();
        while (used.size() < 3) {
            final Pair<Integer, Integer> pair = new Pair<>(random.nextInt(width), random.nextInt(width));
            if (used.add(pair)) {
                this.cells[pair.x()][pair.y()] = STAR;
            }
        }
        return getCellsAsList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> computeNextStep() {
        final String[][] copy = new String[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                copy[i][j] = this.cells[i][j];
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // Check adjacent and diagonal cells for a STAR
                if (EMPTY.equals(copy[i][j]) && (
                        i > 0 && STAR.equals(copy[i - 1][j])
                    || i < width - 1 && STAR.equals(copy[i + 1][j])
                    || j > 0 && STAR.equals(copy[i][j - 1])
                    || j < width - 1 && STAR.equals(copy[i][j + 1])
                    || i > 0 && j > 0 && STAR.equals(copy[i - 1][j - 1])
                    || i > 0 && j < width - 1 && STAR.equals(copy[i - 1][j + 1])
                    || i < width - 1 && j > 0 && STAR.equals(copy[i + 1][j - 1])
                    || i < width - 1 && j < width - 1 && STAR.equals(copy[i + 1][j + 1])
                )) {
                    this.cells[i][j] = STAR;
                }
            }
        }
        return getCellsAsList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (EMPTY.equals(this.cells[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the grid cells as a linear list in row-major order.
     *
     * @return a list containing all cells, ordered by rows from top to bottom
     */
    private List<String> getCellsAsList() {
        return Arrays.stream(this.cells)
            .flatMap(Arrays::stream)
            .toList();
    }

}
