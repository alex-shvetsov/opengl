package game.grid;

import math.Point3D;
import objects.Object3D;

public class Cell {
    public static final float SIZE_X = 16;
    public static final float SIZE_Z = 16;

    private Point3D position;
    private Object3D object;

    public Cell(Point3D position) {
        this.position = position;
    }

    public Object3D getObject() {
        return object;
    }

    public void attachObject(Object3D object) {
        this.object = object;
    }

    public Point3D getPosition() {
        return position;
    }
}
