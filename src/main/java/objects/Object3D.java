package objects;

import camera.Camera;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;
import math.Matrix4;
import math.Point3D;
import math.Vertex3D;

import static com.jogamp.opengl.GL.*;

public class Object3D {
    private int[] vbo = new int[1];
    private int[] ibo = new int[1];
    protected int[] indices;
    protected Vertex3D[] vertices;
    protected int nIndices, nVertices;

    protected Point3D position;
    private Matrix4 model;

    private boolean initialized = false;
    protected boolean indexingUsed = false;

    public Object3D(Point3D position) {
        this.position = position;
        model = Matrix4.getIdentity();
        model.setElement(3, 0, position.getX());
        model.setElement(3, 1, position.getY());
        model.setElement(3, 2, position.getZ());
    }

    public Object3D(Point3D position, Vertex3D[] vertices) {
        this(position);
        this.nVertices = vertices.length;
        this.vertices = vertices;
        procVBO();
        initialized = !indexingUsed;
    }

    public Object3D(Point3D position, Vertex3D[] vertices, int[] indices, boolean indexingUsed) {
        this(position, vertices);
        this.nIndices = indices.length;
        this.indices = indices;
        if (indices != null && indices.length > 0) {
            this.indexingUsed = indexingUsed;
            procIBO();
            initialized = true;
        }
    }

    protected void procVBO() {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glGenBuffers(1, vbo, 0);
        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        gl.glBufferData(GL_ARRAY_BUFFER,
                     getVertices().length * 4,
                        Buffers.newDirectFloatBuffer(getVertices()),
                        GL_STATIC_DRAW);
    }

    protected void procIBO() {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glGenBuffers(1, ibo, 0);
        gl.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        gl.glBufferData(GL_ELEMENT_ARRAY_BUFFER,
                     getIndices().length * 4,
                        Buffers.newDirectIntBuffer(getIndices()),
                        GL_STATIC_DRAW);
    }

    public void render() {
        GL4 gl = (GL4) GLContext.getCurrentGL();

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(0);

        if (!indexingUsed) {
            gl.glDrawArrays(GL_TRIANGLES, 0, vertices.length);
        } else {
            gl.glDrawElements(GL_TRIANGLES, getIndices().length * 4, GL_UNSIGNED_INT, 0);
        }
    }

    public Matrix4 getModelView(Camera camera) {
        return model.multiply(camera.getView());
    }

    public Matrix4 getModel() {
        return model;
    }

    public int[] getVBO() {
        return vbo;
    }

    public int[] getIBO() {
        return ibo;
    }

    public int[] getIndices() {
        return indices;
    }

    public float[] getVertices() {
        return Vertex3D.getFloatArray(vertices);
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isIndexingUsed() {
        return indexingUsed;
    }

    public void useIndexing(boolean indexingUsed) {
        if (indices != null && indices.length > 0) {
            this.indexingUsed = indexingUsed;
        }
    }
}
