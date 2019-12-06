import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Shootah");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Game game = new Game(this);
        add(game.getCanvas());
        setVisible(true);

        FPSAnimator animator = new FPSAnimator(game.getCanvas(), 60);
        animator.start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
