package shader;

import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;
import log.ErrorHandler;

public class ShaderProgram {
    private int name;

    public ShaderProgram() {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        this.name = gl.glCreateProgram();
    }

    public void attach(Shader shader) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        int[] prLinked = new int[1];

        gl.glAttachShader(name, shader.getName());
        gl.glLinkProgram(name);
        ErrorHandler.checkOpenGLError();
        gl.glGetProgramiv(name, GL2ES2.GL_LINK_STATUS, prLinked, 0);
        System.out.println("Shader: " + shader.getPath());
        if (prLinked[0] == 1) {
            System.out.println("Linking state: SUCCESS");
        } else {
            System.out.println("Linking state: FAILURE");
            ErrorHandler.printProgramLog(name);
        }

        shader.delete();
    }

    public void detach(Shader shader) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glDetachShader(name, shader.getName());
    }

    public void use() {
        GL4 gl = (GL4)GLContext.getCurrentGL();
        gl.glUseProgram(name);
    }

    public int getName() {
        return name;
    }
}
