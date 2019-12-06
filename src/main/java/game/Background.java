package game;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;

import java.nio.FloatBuffer;

import static com.jogamp.opengl.GL2ES3.GL_COLOR;

public class Background {
    public static void setColor(GL4 glContext, float r, float g, float b, float a) {
        FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(new float[]{r, g, b, a});
        glContext.glClearBufferfv(GL_COLOR, 0, bkgBuffer);
    }
}
