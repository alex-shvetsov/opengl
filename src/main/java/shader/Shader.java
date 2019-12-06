package shader;

import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;
import log.ErrorHandler;

import java.io.FileNotFoundException;

public class Shader {
    private String[] source;
    private String path;
    private int name;
    private int type;
    private boolean deleted = false;

    public Shader(String path, int shaderType) throws FileNotFoundException {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        this.name = gl.glCreateShader(shaderType);
        this.type = shaderType;
        this.path = path;
        this.source = GLSLReader.readShaderSource(path);
        if (source == null) {
            throw new FileNotFoundException();
        }

        gl.glShaderSource(name, source.length, source, null, 0);
        compile();
    }

    private void compile() {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        int[] shCompiled = new int[1];

        gl.glCompileShader(name);
        ErrorHandler.checkOpenGLError();
        gl.glGetShaderiv(this.name, GL2ES2.GL_COMPILE_STATUS, shCompiled, 0);
        if (shCompiled[0] == 1) {
            System.out.println("Shader compiled. Name: " + path);
        } else {
            System.out.println("Shader failed to compile. Name: " + path);
            ErrorHandler.printShaderLog(name);
        }
    }

    public void delete() {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glDeleteShader(name);
        deleted = true;
    }

    public int getName() {
        return name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getPath() {
        return  path;
    }

    public int getType() {
        return type;
    }
}
