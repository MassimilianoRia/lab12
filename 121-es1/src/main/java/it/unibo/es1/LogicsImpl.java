package it.unibo.es1;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final List<Integer> values;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.values = IntStream.range(0, size)
        .mapToObj(i -> 0)
        .collect(Collectors.toList());
    }

    private boolean isEnabled(final int index) {
        return values.get(index) < values.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return values.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return List.copyOf(values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        return IntStream.range(0, values.size())
        .mapToObj(this::isEnabled)
        .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        final int updated = values.get(elem) + 1;
        values.set(elem, updated);
        return updated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        return values.stream()
            .map(String::valueOf)
            .collect(Collectors.joining("|", "<<", ">>"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return values.stream().distinct().count() == 1;
    }

}
