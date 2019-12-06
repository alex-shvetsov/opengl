package shader;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

public class GLUniform {
    private String name;

    public GLUniform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void update(ShaderProgram program, float[] data) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        int location = gl.glGetUniformLocation(program.getName(), name);
        gl.glUniformMatrix4fv(location, 1, false, data, 0);
    }
}
