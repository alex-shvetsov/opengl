package math;

public class Vertex3D {
    private Point3D location;
    private Vector3D normal;
    // texture coordinates
    private float s, t;

    public Vertex3D() {}

    public Vertex3D(Point3D location) {
        this.location = location;
    }

    public Vertex3D(float x, float y, float z) {
        location = new Point3D(x, y, z);
    }

    public void setLocation(Point3D location) {
        this.location = location;
    }

    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    public void setS(float s) {
        this.s = s;
    }

    public void setT(float t){
        this.t = t;
    }

    public float getS(){
        return s;
    }

    public float getT(){
        return t;
    }

    public Point3D getLocation(){
        return location;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public static float[] getFloatArray(Vertex3D[] vertexArray) {
        if (vertexArray == null || vertexArray.length == 0)
            return null;

        float[] result = new float[vertexArray.length * 3];

        for (int i = 0; i < vertexArray.length; i++) {
            result[3 * i + 0] = vertexArray[i].getLocation().getX();
            result[3 * i + 1] = vertexArray[i].getLocation().getY();
            result[3 * i + 2] = vertexArray[i].getLocation().getZ();
        }

        return result;
    }
}
