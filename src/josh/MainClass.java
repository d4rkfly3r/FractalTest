package josh;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Joshua on 3/2/2016.
 * Project: Fractle
 */
public class MainClass {
    static Window window;
    static Fractal fractal;
    private static Writer writer;


    public static void main(String[] args) throws IOException {
        String sizeData = JOptionPane.showInputDialog(null, "How big do you want your fractal to be? i.e. \"400x400\"", "400x400");
        if (sizeData == null) {
            System.exit(1);
        }
        sizeData = sizeData.replace(" ", "");
        int loc = sizeData.indexOf("x");
        int width = Integer.parseInt(sizeData.substring(0, loc));
        int height = Integer.parseInt(sizeData.substring(loc + 1));

        window = new Window(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                window.data.setRGB(x,y, 0x00FF00);
            }
        }

        fractal = new Fractal();

        window.setSize(width, height);
        window.setVisible(true);
        window.setLayout(new FlowLayout());
        fractal.dateTime(writer);
        fractal.readLoc();

    }
}
