package log;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.glu.GLU;

import static com.jogamp.opengl.GL.GL_NO_ERROR;
import static com.jogamp.opengl.GL2ES2.GL_INFO_LOG_LENGTH;

public abstract class ErrorHandler {
    public static void printShaderLog(int shader) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        int[] len = new int[1];
        int[] chWrittn = new int[1];
        byte[] log = null;

        gl.glGetShaderiv(shader, GL_INFO_LOG_LENGTH, len, 0);
        if (len[0] > 0) {
            log = new byte[len[0]];
            gl.glGetShaderInfoLog(shader, len[0], chWrittn, 0, log, 0);
            System.out.println("Shader Info Log: ");
            for (int i = 0; i < log.length; i++) {
                System.out.print((char)log[i]);
            }
        }
    }

    public static void printProgramLog(int prog) {
        GL4 gl = (GL4)GLContext.getCurrentGL();
        int[] len = new int[1];
        int[] chWrittn = new int[1];
        byte[] log = null;

        gl.glGetProgramiv(prog, GL_INFO_LOG_LENGTH, len, 0);
        if (len[0] > 0) {
            log = new byte[len[0]];
            gl.glGetProgramInfoLog(prog, len[0], chWrittn, 0, log, 0);
            System.out.println("Program Info Log: ");
            for (int i = 0; i < log.length; i++) {
                System.out.print((char)log[i]);
            }
        }
    }

    public static boolean checkOpenGLError() {
        GL4 gl = (GL4)GLContext.getCurrentGL();
        boolean foundError = false;
        GLU glu = new GLU();

        int glErr = gl.glGetError();
        while (glErr != GL_NO_ERROR) {
            System.err.println("glError: " + glu.gluErrorString(glErr));
            foundError = true;
            glErr = gl.glGetError();
        }

        return foundError;
    }
}
