package shader;

public class GLAttribute {
    private int location;

    public GLAttribute(int location) {
        this.location = location;
    }

    public void update(ShaderProgram program) {
        // not implemented
    }

    public int getLocation(){
        return location;
    }
}
