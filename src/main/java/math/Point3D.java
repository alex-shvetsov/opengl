package math;

public class Point3D  {
    private float x, y, z;

    public static Point3D getOrigin() {
        return new Point3D( new float[] {0.0f, 0.0f, 0.0f} );
    }

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(float[] components) {
        this.x = components[0];
        this.y = components[1];
        this.z = components[2];
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

}
