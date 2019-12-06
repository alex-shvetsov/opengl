package game;

import game.grid.Cell;
import game.grid.Grid;
import math.Point3D;
import math.Vertex3D;
import objects.Object3D;

public final class Field extends Object3D {
    public static final float ORIGIN_Y = -30;
    public static final float ORIGIN_X = 0;
    public static final float ORIGIN_Z = 0;
    public static final float SIZE_X = Grid.CELL_COUNT_X * Cell.SIZE_X;
    public static final float SIZE_Z = Grid.CELL_COUNT_Z * Cell.SIZE_Z;

    public Field() {
        super(
            new Point3D(ORIGIN_X, ORIGIN_Y, ORIGIN_Z),
            new Vertex3D[] {
                new Vertex3D(0.0f * SIZE_X, 0.0f, 0.0f * SIZE_Z),
                new Vertex3D(1.0f * SIZE_X, 0.0f, 0.0f * SIZE_Z),
                new Vertex3D(0.0f * SIZE_X, 0.0f, -1.0f * SIZE_Z),
                new Vertex3D(1.0f * SIZE_X, 0.0f, 0.0f * SIZE_Z),
                new Vertex3D(1.0f * SIZE_X, 0.0f, -1.0f * SIZE_Z),
                new Vertex3D(0.0f * SIZE_X, 0.0f, -1.0f * SIZE_Z)
            }
        );
    }
}
