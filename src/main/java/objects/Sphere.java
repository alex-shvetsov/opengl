package objects;

import math.Point3D;
import math.Vector3D;
import math.Vertex3D;

import static java.lang.Math.*;

public class Sphere extends Object3D {
    private int vPrec, hPrec;
    private float radius;

    public Sphere(Point3D position, int vPrec, int hPrec, float radius) {
        super(position);
        this.vPrec = vPrec;
        this.hPrec = hPrec;
        this.radius = radius;
        this.nVertices = (hPrec + 1) * (vPrec + 1);
        this.nIndices = 6 * hPrec * vPrec;
        this.vertices = new Vertex3D[nVertices];
        this.indices = new int[nIndices];
        init();
        indexingUsed = true;
    }

    public void init() {
        for (int i = 0; i <= vPrec; i++) {
            for (int j = 0; j <= hPrec; j++) {
                double angleV = toRadians(90 - i * 180 / vPrec);
                double angleH = toRadians(j * 360 / hPrec);
                float y = radius * (float)sin(angleV);
                float x = radius * (float)cos(angleV) * (float)cos(angleH);
                float z = radius * (float)cos(angleV) * (float)sin(angleH);

                vertices[i * (hPrec + 1) + j] = new Vertex3D(x, y, z);
                vertices[i * (hPrec + 1) + j].setS((float)(j / hPrec));
                vertices[i * (hPrec + 1) + j].setT((float)(i / vPrec));
                vertices[i * (hPrec + 1) + j].setNormal(new Vector3D(vertices[i * (hPrec + 1) + j].getLocation()));
            }
        }

        for (int i = 0; i < vPrec; i++) {
            for (int j = 0; j < hPrec; j++) {
                indices[6 * (i * hPrec + j) + 0] = i * (hPrec + 1) + j;
                indices[6 * (i * hPrec + j) + 1] = i * (hPrec + 1) + j + 1;
                indices[6 * (i * hPrec + j) + 2] = (i + 1) * (hPrec + 1) + j;
                indices[6 * (i * hPrec + j) + 3] = i * (hPrec + 1) + j + 1;
                indices[6 * (i * hPrec + j) + 4] = (i + 1) * (hPrec + 1) + j + 1;
                indices[6 * (i * hPrec + j) + 5] = (i + 1) * (hPrec + 1) + j;
            }
        }

        procVBO();
        procIBO();
    }
}
