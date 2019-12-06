package game.grid;

import game.Field;
import math.Point3D;

public class Grid {
    public static final int CELL_COUNT_X = 32;
    public static final int CELL_COUNT_Z = 32;

    private Cell[][] cells = new Cell[CELL_COUNT_X][CELL_COUNT_Z];

    public Grid() {
        for (int i = 0; i < CELL_COUNT_X; i++) {
            for (int j = 0; j < CELL_COUNT_Z; j++) {
                cells[i][j] = new Cell(new Point3D(
                        i * Cell.SIZE_X + Field.ORIGIN_X,
                        Field.ORIGIN_Y,
                        j * Cell.SIZE_Z + Field.ORIGIN_Z
                ));
            }
        }
    }

    public Cell getCell(int cX, int cY) {
        return cells[cX][cY];
    }

    public void setCells(int cX, int cY, Cell cell) {
        cells[cX][cY] = cell;
    }
}
