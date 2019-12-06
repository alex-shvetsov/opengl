package math;

public class Matrix4 {
    private static final int SIZE = 4;

    private float elements[];

    public static Matrix4 getIdentity() {
        return new Matrix4(new float[] {
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
        });
    };

    public Matrix4(float[] elements) {
        this.elements = elements;
    }

    public boolean setElement(int row, int col, float value) {
        if (row > 3 || row < 0 ||
            col > 3 || col < 0) return false;
        elements[col + SIZE * row] = value;
        return true;
    }

    public void setElements(float[] elements){
        this.elements = elements;
    }

    public float getElement(int row, int col) {
        return elements[col + SIZE * row];
    }

    public float[] getElements() {
        return elements;
    }

    public Matrix4 multiply(Matrix4 another) {
        float[] result = new float[SIZE * SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                result[col + SIZE * row] =
                        another.elements[0 + SIZE * row] * this.elements[col + SIZE * 0] +
                        another.elements[1 + SIZE * row] * this.elements[col + SIZE * 1] +
                        another.elements[2 + SIZE * row] * this.elements[col + SIZE * 2] +
                        another.elements[3 + SIZE * row] * this.elements[col + SIZE * 3];
            }
        }
        return new Matrix4(result);
    }

    public Vector3D multiply(Vector3D vector) {
        float[] result = new float[SIZE];

        for (int i = 0; i < SIZE; i++) {
            result[i] = this.elements[0 + SIZE * i] * vector.get()[0] +
                        this.elements[1 + SIZE * i] * vector.get()[1] +
                        this.elements[2 + SIZE * i] * vector.get()[2] +
                        this.elements[3 + SIZE * i] * vector.get()[3];

        }
        return new Vector3D(result);
    }

    public Matrix4 transpose() {
        float[] result = new float[SIZE * SIZE];

        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col < SIZE; col++) {
                result[row + SIZE * col] = this.elements[col + SIZE * row];
            }
        }
        return new Matrix4(result);
    }

    public Matrix4 add(Matrix4 another) {
        float[] result = new float[SIZE * SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int e = col + SIZE * row;
                result[e] = this.elements[e] + another.elements[e];
            }
        }
        return new Matrix4(result);
    }

    public Matrix4 translate(float dx, float dy, float dz) {
        this.setElement(3, 0, this.getElement(3, 0) + dx);
        this.setElement(3, 1, this.getElement(3, 1) + dy);
        this.setElement(3, 2, this.getElement(3, 2) + dz);
        return this;
    }

    public static Matrix4 getTranslation(float dx, float dy, float dz) {
        return new Matrix4(new float[] {
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                dx, dy, dz, 1.0f
        });
    }

    public Matrix4 scale(float x, float y, float z) {
        this.setElement(0, 0, this.getElement(0, 0) * x);
        this.setElement(1, 1, this.getElement(1, 1) * y);
        this.setElement(2, 2, this.getElement(2, 2) * z);
        return this;
    }

    public Matrix4 scale(float all) {
        return this.scale(all, all, all);
    }

    public static Matrix4 getScaling(float x, float y, float z) {
        return new Matrix4(new float[] {
                x, 0.0f, 0.0f, 0.0f,
                0.0f, y, 0.0f, 0.0f,
                0.0f, 0.0f, z, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        });
    }

    public Matrix4 rotateX(float rad) {
        return this.multiply(getRotationX(rad));
    }

    public Matrix4 rotateY(float rad) {
        return this.multiply(getRotationY(rad));
    }

    public Matrix4 rotateZ(float rad) {
        return this.multiply(getRotationZ(rad));
    }

    public static Matrix4 getRotationX(float rad) {
        return new Matrix4(new float[] {
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, (float)Math.cos(rad), (float)Math.sin(rad), 0.0f,
                0.0f, -(float)Math.sin(rad), (float)Math.cos(rad), 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        });
    }

    public static Matrix4 getRotationY(float rad) {
        return new Matrix4(new float[] {
                (float)Math.cos(rad), 0.0f, -(float)Math.sin(rad), 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                (float)Math.sin(rad), 0.0f, (float)Math.cos(rad), 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        });
    }

    public static Matrix4 getRotationZ(float rad) {
        return new Matrix4(new float[]  {
                (float)Math.cos(rad), (float)Math.sin(rad), 0.0f, 0.0f,
                -(float)Math.sin(rad), (float)Math.cos(rad), 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        });
    }

    public static Matrix4 getPerspective(float fovy, float aspect, float n, float f) {
        float q = 1.0f / ((float)Math.tan(Math.toRadians(0.5f * fovy)));
        float A = q / aspect;
        float B = (n + f) / (n - f);
        float C = (2.0f * n * f) / (n - f);
        float[] elements = {
                A, 0.0f, 0.0f, 0.0f,
                0.0f, q, 0.0f, 0.0f,
                0.0f, 0.0f, B, -1.0f,
                0.0f, 0.0f, C, 0.0f
        };

        return new Matrix4(elements);
    }
}
