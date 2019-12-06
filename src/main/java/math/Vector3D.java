package math;

public class Vector3D {
    private Point3D edge;

    public Vector3D(Point3D edge) { this.edge = edge; }
    public Vector3D(float x, float y, float z) {
        edge = new Point3D(x, y, z);
    }

    public Vector3D(float[] components){
        edge = new Point3D(components);
    }

    public float getLength() {
        return (float)Math.sqrt(edge.getX() * edge.getX() +
                                edge.getY() * edge.getY() +
                                edge.getZ() * edge.getZ());
    }

    public float dot(Vector3D vector) {
        return edge.getX() * vector.edge.getX() +
               edge.getY() * vector.edge.getY() +
               edge.getZ() * vector.edge.getZ();
    }

    public float[] get() {
        return new float[] { edge.getX(), edge.getY(), edge.getZ(), 0.0f };
    }
}
