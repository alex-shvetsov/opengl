package shader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public abstract class GLSLReader {
    public static String[] readShaderSource(String filename) {
        Vector<String> lines = new Vector<String>();
        Scanner sc;

        try {
            sc = new Scanner(new File("src/main/resources/" + filename));
        } catch (IOException e) {
            System.err.println("IOException reading file: " + e);
            return null;
        }

        while (sc.hasNext()) {
            lines.addElement(sc.nextLine());
        }

        String[] program = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            program[i] = (String)lines.elementAt(i) + "\n";
        }

        return program;
    }
}
