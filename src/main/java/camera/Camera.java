package camera;

import math.Matrix4;
import math.Point3D;

public class Camera {
    private Point3D position;
    private Matrix4 view;
    private Matrix4 perspective;

    public Camera(Point3D position, Matrix4 perspective) {
        this.position = position;
        this.perspective = perspective;
        view = Matrix4.getIdentity();
        view.setElement(3, 0, -position.getX());
        view.setElement(3, 1, -position.getY());
        view.setElement(3, 2, -position.getZ());
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public Matrix4 getView() {
        return view;
    }

    public Matrix4 getPerspective() {
        return perspective;
    }

    public void setPerspective(Matrix4 perspective) {
        this.perspective = perspective;
    }
}
