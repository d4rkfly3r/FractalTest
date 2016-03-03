package josh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Joshua on 3/2/2016.
 * Project: Fractal
 */
public class Fractal {

    public static final int MAX_MAGNITUDE = 10;
    public static final int MAX_ITERATIONS = 500;
    public static final int CHAR_DISPLAY_HEIGHT = 15;
    public static final int CHAR_DISPLAY_WIDTH = 300;
    private static Graphics gr;
    private int Xmax;
    private int Ymax;
    int myStartX;
    int myStartY;
    int myEndX;
    int myEndY;
    private boolean keyHeldDown;
    private KeyEvent lastKey;
    private static double real;
    private static double imaginary;
    private static double realStart;
    private static double realEnd;
    private static double imaginaryStart;
    private static double imaginaryEnd;
    private static ArrayList<String> locs = new ArrayList<>();
    private static int saveNumber = -1;
    private static File dataFile = new File("Saved Fractals.txt");
    private static Writer writer;


    public Fractal() {
        lastKey = null;
        keyHeldDown = false;

        Xmax = 0;
        Ymax = 0;
        myStartX = 0;
        myStartY = 0;
        myEndX = 0;
        myEndY = 0;

        realStart = -2.0;
        realEnd = 2.0;
        imaginaryStart = -2.0;
        imaginaryEnd = 2.0;

        real = 0.0;
        imaginary = 0.0;

    }

    public static void dateTime(Writer writer) throws IOException {
        Date dateNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss a zzz 'on' E 'the' dd 'of' MM.yyy");
        writer = new BufferedWriter(new FileWriter(dataFile, true));
        writer.write("//" + ft.format(dateNow) + "------------------------------------------>\r\n\r\n");
        writer.close();
    }

    public void writeLoc() throws IOException {
        String picData = JOptionPane.showInputDialog(MainClass.window, "Please describe your picture so that you can come back to it easily.");
        if (picData != null) {
            saveNumber++;
            int codeSave = JOptionPane.showConfirmDialog(MainClass.window, "Would you like to save the code also to be able to start here normally?");
            writer = new BufferedWriter(new FileWriter(dataFile, true));
            writer.write("Your fractal can be described as: \r\n" + picData + "\r\nID#:" + saveNumber + "\r\n" + realStart + "," +
                    realEnd + "," + imaginaryStart + "," + imaginaryEnd);
            if (codeSave == 0) {
                writer.write("\r\nrealStart = " + realStart + ";\r\nrealEnd = " + realEnd + ";\r\nimaginaryStart = "
                        + imaginaryStart + ";\r\nimaginaryEnd = " + imaginaryEnd + ";\r\n\r\n\r\n");
            } else {
                writer.write("\r\n\r\n\r\n");
            }
            writer.close();
            readLoc();
        }
    }

    public void loadLoc() throws IOException {
        String locStuff = JOptionPane.showInputDialog(MainClass.window, "Plese type the ID# of the Fractal that you would like to load.");
        if (locStuff != null) {
            int locData = Integer.parseInt(locStuff.replace(" ", ""));
            int loc = -1;
            for (int rep = 0; rep < locs.size(); rep++) {
                if (locs.get(rep).equals("ID#:" + locData)) {
                    loc = rep;
                }
            }
            if (loc == -1) {
                JOptionPane.showMessageDialog(MainClass.window, "Invalid ID#.");
            } else {
                String[] place = locs.get(loc + 1).split(",");
                realStart = Double.parseDouble(place[0]);
                realEnd = Double.parseDouble(place[1]);
                imaginaryStart = Double.parseDouble(place[2]);
                imaginaryEnd = Double.parseDouble(place[3]);
                MainClass.window.repaint();
            }
        }
    }

    public static ArrayList<String> readLoc() throws IOException {
        FileReader reader = new FileReader(dataFile);
        Scanner in = new Scanner(reader);
        while (in.hasNext()) {
            locs.add(in.next());
        }
        for (int rep = locs.size() - 1; rep >= 0; rep--) {
            if (locs.get(rep).indexOf("ID#:") > -1) {
                saveNumber = Integer.parseInt(locs.get(rep).substring(4));
                break;
            }
        }
        in.close();
        return locs;
    }

    public static int iterate(Complex c) {
        Complex temp = new Complex(c);
        for (int loop = 1; loop <= MAX_ITERATIONS; loop++) {
            //temp = temp.multiply(temp).add(c);
            //temp = temp.multiply(temp.multiply(temp)).add(c.multiply(c));
            //temp = temp.multiply(temp).add(c.multiply(new Complex(0.5)));
            //temp = temp.multiply(temp).add(c.multiply(new Complex(-0.5)));
            //temp = temp.divide(c.add(temp)).add(new Complex(0.5)); // C curve
            //temp = temp.multiply(c.add(temp)).add(c);
            temp = temp.divide(c.add(temp)).add(c);
            //temp = temp.kick(c);
            if (temp.magnitude() > MAX_MAGNITUDE)
                return loop;
        }
        return 0;
    }

}
