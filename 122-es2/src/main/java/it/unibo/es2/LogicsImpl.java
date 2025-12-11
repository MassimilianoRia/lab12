package it.unibo.es2;

public class LogicsImpl implements Logics {
    
    private final String[][] grid; 

    public LogicsImpl(final int size) {
        this.grid = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = " ";
            }
        }
    }

    @Override
    public String toggle(final int x, final int y) {
        return this.grid[x][y] = this.grid[x][y].equals("*") ? " " : "*";
    }

    @Override
    public boolean isOver() {
        //rows check
        for (final String[] row : this.grid) {
            int counter = 0;
            for (final String s : row) {
                if (s.equals("*")) {
                    counter++;
                }
            }
            if (counter == grid.length) {
                return true;
            }
        }
        //columns check
        for (int j = 0; j < grid.length; j++) {
            int counter = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j].equals("*")) {
                    counter++;
                }
            }
            if (counter == grid.length) {
                return true;
            }
        } 
        return false;
    }

}
