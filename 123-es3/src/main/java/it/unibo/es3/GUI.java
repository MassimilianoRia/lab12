package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Model model;
    private final List<JButton> buttons = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        if (width % 2 != 0) {
            throw new IllegalArgumentException("Only even numbers are accepted as argument.");
        }

        // Set the Model holding the game state
        this.model = new ModelImpl(width);

        // Create a panel with a grid layout
        final JPanel grid = new JPanel(new GridLayout(width, width));

        // Create buttons and add them to the panel
        for (final String label : this.model.init()) {
                final JButton button = new JButton(label);
                grid.add(button);
                this.buttons.add(button);
        }

        // Create another panel with a Border Layout for insert "Next Step" button
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(grid, BorderLayout.CENTER);
        final JButton nextStepButton = new JButton(">");
        nextStepButton.addActionListener(e -> {
            final var it = this.model.computeNextStep().iterator();
            for (final JButton button : this.buttons) {
                button.setText(it.next());
            }
            if (this.model.isFull()) {
                dispose();
            }
        });
        panel.add(nextStepButton, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        pack();
        this.setVisible(true);
    }
}
