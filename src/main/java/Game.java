import camera.Camera;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import game.Background;
import game.Field;
import game.events.MouseEvent;
import math.Matrix4;
import math.Point3D;
import objects.Sphere;
import shader.GLUniform;
import shader.Shader;
import shader.ShaderProgram;

import javax.swing.*;
import java.awt.event.WindowEvent;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER;
import static com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER;

public final class Game implements GLEventListener {
    private JFrame frame;
    private GLCanvas canvas;
    private MouseEvent mouseEvent;

    // vertex array object
    private int[] vao = new int[1];

    private ShaderProgram program;
    private Shader vertexShader, fragmentShader;

    // uniforms
    private GLUniform projU = new GLUniform("proj_matrix");
    private GLUniform mvU = new GLUniform("mv_matrix");

    private Camera camera;
    private Field field;

    // TEST
    private Sphere sphere1, sphere2;
    private int t = 1;

    public Game(JFrame frame) {
        this.frame = frame;
        canvas = new GLCanvas();
        canvas.addGLEventListener(this);
    }

    public void init(GLAutoDrawable glAutoDrawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        program = new ShaderProgram();

        // shader initialization
        try {
            vertexShader = new Shader("shaders/vertex0.glsl", GL_VERTEX_SHADER);
            program.attach(vertexShader);
            fragmentShader = new Shader("shaders/fragment0.glsl", GL_FRAGMENT_SHADER);
            program.attach(fragmentShader);
        } catch (Exception ex) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }

        program.use();

        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);

        float aspect = (float) canvas.getWidth() / (float) canvas.getHeight();
        Matrix4 persp = Matrix4.getPerspective(60.0f, aspect, 0.1f, 1000.0f);
        camera = new Camera(Point3D.getOrigin(), persp);
        field = new Field();

        Point3D p1 = new Point3D(-10, 10, -20);
        Point3D p2 = new Point3D(10, 10, -30);
        sphere1 = new Sphere(p1, 64, 64, 4);
        sphere2 = new Sphere(p2, 64, 64, 4);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glClear(GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL_DEPTH_TEST);

        Background.setColor(gl, 0.0f, 0.0f, 0.0f, 1.0f);
        //camera.setPerspective(camera.getPerspective().rotateY(0.02f));
        projU.update(program, camera.getPerspective().getElements());

        Matrix4 mvSphere1 = sphere1.getModelView(camera);
        mvU.update(program, mvSphere1.getElements());
        sphere1.render();

        Matrix4 mvSphere2 = sphere2.getModelView(camera);
        mvU.update(program, mvSphere2.getElements());
        sphere2.render();

        Matrix4 mvField = field.getModelView(camera);
        mvU.update(program, mvField.getElements());
        field.render();
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {
        // not implemented
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        // not implemented
    }

    public GLCanvas getCanvas() {
        return canvas;
    }
}
