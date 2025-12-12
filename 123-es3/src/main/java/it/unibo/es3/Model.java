package it.unibo.es3;

import java.util.List;

/**
 * Model interface.
 */
public interface Model {

    /**
     * Initializes the model state by activating a set of randomly chosen cells.
     *
     * @return the initial grid state as a linear list in row-major order
     */
    public List<String> init();

    /**
     * Updates the model state by applying one evolution step.
     *
     * @return the updated grid state as a linear list in row-major order
     */
    public List<String> computeNextStep();

    /**
     * Checks whether the grid is completely filled.
     *
     * @return true if all cells are occupied, false otherwise
     */
    public boolean isFull();

}
