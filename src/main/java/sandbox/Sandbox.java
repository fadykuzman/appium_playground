package sandbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Sandbox {
    public static void main(String[] args) {
        String mystring = " Hello this is fady";

        File file = new File("/Users/FadyK/Workspaces/Experiments/Oreilly/AppiumBookTutorial/src/test/resources/source.xml");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(mystring.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
