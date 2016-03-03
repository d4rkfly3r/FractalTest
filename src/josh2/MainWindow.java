package josh2;

import josh.Complex;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Joshua on 3/2/2016.
 * Project: Fractle
 */
public class MainWindow extends Frame implements WindowListener, MouseListener, ComponentListener, MouseMotionListener {

    private final boolean debug = true;
    private int w = 1000, h = 1000;
    private int recW = 10, recH = 10;
    private BufferedImage bi;
    private int myStartX;
    private int myStartY;
    private int myEndX;
    private int myEndY;
    private boolean keyHeldDown;
    private KeyEvent lastKey;
    private static double real;
    private static double imaginary;
    private static double realStart;
    private static double realEnd;
    private static double imaginaryStart;
    private static double imaginaryEnd;

    public static final int MAX_MAGNITUDE = 10;
    public static final int MAX_ITERATIONS = 500;
    public static final int CHAR_DISPLAY_HEIGHT = 15;
    public static final int CHAR_DISPLAY_WIDTH = 300;

    int chnkCnt = (w * h) / (recW * recH);
    int cCC = 0;


    public MainWindow() {

        this.addWindowListener(this);
        this.addMouseListener(this);
        this.addComponentListener(this);

        setSize(w, h);
        setLocationRelativeTo(null);
        setVisible(true);

        bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Random random = new Random();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                bi.setRGB(x, y, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
            }
        }
        repaint();

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

        System.err.println(chnkCnt + " | " + cCC);

        t1 = new Thread(group, () -> {
//            Color dC = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            Color dC = Color.red;
            do {
                int tLCCC = cCC;
                cCC++;

                int sX = (recW * tLCCC) % w;
                int sY = ((int) Math.floor((recW * tLCCC) / w)) * 10;
                for (int x = sX; x < sX + recW; x++) {
                    for (int y = sY; y < sY + recH; y++) {
                        if (debug) System.err.println(x + " | " + y);
                        bi.setRGB(x, y, dC.getRGB());
                    }
                }
                repaint();
            } while (cCC < chnkCnt);
        });
        t2 = new Thread(group, () -> {
//            Color dC = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
            Color dC = Color.green;
            do {
                int tLCCC = cCC;
                cCC++;

                int sX = (recW * tLCCC) % w;
                int sY = ((int) Math.floor((recW * tLCCC) / w)) * 10;
                for (int x = sX; x < sX + recW; x++) {
                    for (int y = sY; y < sY + recH; y++) {
                        if (debug) System.err.println(x + " | " + y);
                        bi.setRGB(x, y, dC.getRGB());
                    }
                }
                repaint();
            } while (cCC < chnkCnt);
        });
        t3 = new Thread(group, () -> {
//            Color dC = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            Color dC = Color.blue;
            do {
                int tLCCC = cCC;
                cCC++;

                int sX = (recW * tLCCC) % w;
                int sY = ((int) Math.floor((recW * tLCCC) / w)) * 10;
                for (int x = sX; x < sX + recW; x++) {
                    for (int y = sY; y < sY + recH; y++) {
                        if (debug) System.err.println(x + " | " + y);
                        bi.setRGB(x, y, dC.getRGB());
                    }
                }
                repaint();
            } while (cCC < chnkCnt);
        });
        t4 = new Thread(group, () -> {
//            Color dC = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            Color dC = Color.black;
            do {
                int tLCCC = cCC;
                cCC++;

                int sX = (recW * tLCCC) % w;
                int sY = ((int) Math.floor((recW * tLCCC) / w)) * 10;
                for (int x = sX; x < sX + recW; x++) {
                    for (int y = sY; y < sY + recH; y++) {
                        if (debug) System.err.println(x + " | " + y);
                        bi.setRGB(x, y, dC.getRGB());
                    }
                }
                repaint();
            } while (cCC < chnkCnt);
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();


    }

    @Override
    public void update(Graphics g) {
        g.drawImage(bi, 0, 0, null);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    int pi = 10;
    ThreadGroup group = new ThreadGroup("Rendering Threads");
    Thread t1, t2, t3, t4;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private void draw(int rowCnt, int colCnt, int minColCnt, int maxColCnt, int maxRowCnt, Color color) {//
        do {
            int startX = colCnt * pi;
            int endX = startX + pi;
            int startY = rowCnt * pi;
            int endY = startY + pi;
            colCnt++;
            if (colCnt >= maxColCnt) {
                colCnt = minColCnt;
                rowCnt++;
            }

            real = realStart;
            imaginary = imaginaryEnd;

            Color drawColor;
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    drawColor = setColor(iterate(new Complex(real, imaginary)));
                    bi.setRGB(x, y, drawColor.getRGB());
                    imaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;
                    repaint(x, y, 1, 1);
                }
                real = ((realEnd - realStart) / getWidth()) * x + realStart;
            }
        } while (rowCnt < maxRowCnt);
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

    public static Color setColor(int color) {
        Color c1;
        if (color == 0) {
            c1 = new Color(0, 153, 153);
            return c1;
        } else if (color == 1) {
            c1 = new Color(6, 67, 255);
            return c1;
        } else if (color == 2) {
            c1 = new Color(99, 7, 67);
            return c1;
        } else if (color == 3) {
            c1 = new Color(255, 88, 0);
            return c1;
        } else if (color == 4) {
            c1 = new Color(204, 0, 204);
            return c1;
        } else if (color > 5) {
            c1 = new Color((int) (13 + 13 * Math.sin(color)) % 256,
                    13 * color % 256,
                    (int) (200 + 200 * Math.cos(color)) % 256);
            return c1;
        } else if (color > 15) {
            c1 = new Color((int) (200 + 200 * Math.tan(color)) % 256,
                    200 * color % 256,
                    19 * color % 256);
            return c1;
        } else {
            return new Color(0, 0, 0);
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        myEndX = e.getX();
        myEndY = e.getY();
        double middleReal = ((realEnd - realStart) / getWidth()) * myEndX + realStart;
        double middleImaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * myEndY;
        double scaleFactor = 1.0;

        if (myEndX == myStartX || myEndY == myStartY) {
            if (keyHeldDown) {
                if (lastKey.getKeyCode() == KeyEvent.VK_F2) scaleFactor = 2.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_F3) scaleFactor = 3.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_F4) scaleFactor = 4.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_F5) scaleFactor = 5.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_F6) scaleFactor = 6.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_F7) scaleFactor = 7.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_F8) scaleFactor = 8.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_F9) scaleFactor = 9.0;
                else if (lastKey.getKeyCode() == KeyEvent.VK_2) scaleFactor = 0.5;
                else if (lastKey.getKeyCode() == KeyEvent.VK_3) scaleFactor = 0.3333333;
                else if (lastKey.getKeyCode() == KeyEvent.VK_4) scaleFactor = 0.25;
                else if (lastKey.getKeyCode() == KeyEvent.VK_5) scaleFactor = 0.2;
                else if (lastKey.getKeyCode() == KeyEvent.VK_6) scaleFactor = 0.1666667;
                else if (lastKey.getKeyCode() == KeyEvent.VK_7) scaleFactor = 0.1428571;
                else if (lastKey.getKeyCode() == KeyEvent.VK_8) scaleFactor = 0.125;
                else if (lastKey.getKeyCode() == KeyEvent.VK_9) scaleFactor = 0.111;
            }
            double deltaReal = (realEnd - realStart) / 2.0;
            double deltaImaginary = (imaginaryEnd - imaginaryStart) / 2.0;

            deltaReal *= scaleFactor;
            deltaImaginary *= scaleFactor;

            realStart = middleReal - deltaReal;
            realEnd = middleReal + deltaReal;
            imaginaryStart = middleImaginary - deltaImaginary;
            imaginaryEnd = middleImaginary + deltaImaginary;
        } else {
            int x = Math.min(myStartX, myEndX);
            int y = Math.min(myStartY, myEndY);
            double real1 = ((realEnd - realStart) / getWidth()) * x + realStart;
            double imaginary1 = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;

            x = Math.max(myStartX, myEndX);
            y = Math.max(myStartY, myEndY);
            double real2 = ((realEnd - realStart) / getWidth()) * x + realStart;
            double imaginary2 = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;

            realStart = real1;
            realEnd = real2;
            imaginaryStart = imaginary2;
            imaginaryEnd = imaginary1;
        }

        Random random = new Random();

//        if (t1 != null) t1.stop();
//        if (t2 != null) t2.stop();
//        if (t3 != null) t3.stop();
//        if (t4 != null) t4.stop();

//        t1 = new Thread(group, () -> {
//            int rowCnt = 0;
//            int colCnt = 0;
//            int minColCnt = 0;
//            int maxRowCnt = 50;
//            int maxColCnt = 50;
//
//            draw(rowCnt, colCnt, minColCnt, maxColCnt, maxRowCnt, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//        });
//        t2 = new Thread(group, () -> {
//            int rowCnt = 50;
//            int colCnt = 0;
//            int minColCnt = 0;
//            int maxRowCnt = 100;
//            int maxColCnt = 50;
//
//            draw(rowCnt, colCnt, minColCnt, maxColCnt, maxRowCnt, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//        });
//        t3 = new Thread(group, () -> {
//            int rowCnt = 50;
//            int colCnt = 50;
//            int minColCnt = 50;
//            int maxRowCnt = 100;
//            int maxColCnt = 100;
//
//            draw(rowCnt, colCnt, minColCnt, maxColCnt, maxRowCnt, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//        });
//        t4 = new Thread(group, () -> {
//            int rowCnt = 0;
//            int colCnt = 50;
//            int minColCnt = 50;
//            int maxRowCnt = 50;
//            int maxColCnt = 100;
//
//            draw(rowCnt, colCnt, minColCnt, maxColCnt, maxRowCnt, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//        });
//        t1.setPriority(Thread.MIN_PRIORITY);
//        t2.setPriority(Thread.MIN_PRIORITY);
//        t3.setPriority(Thread.MIN_PRIORITY);
//        t4.setPriority(Thread.MIN_PRIORITY);
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
        t1 = new Thread(group, () -> {
            int rowCnt = 0;
            int colCnt = 0;
            int minColCnt = 0;
            int maxRowCnt = 100;
            int maxColCnt = 100;

            draw(rowCnt, colCnt, minColCnt, maxColCnt, maxRowCnt, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        });
        t1.start();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        w = e.getComponent().getWidth();
        h = e.getComponent().getHeight();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics gr = getGraphics();
        int x = e.getX();
        int y = e.getY();
        gr.setColor(Color.black);
        gr.fillRect(0, 0, CHAR_DISPLAY_WIDTH, CHAR_DISPLAY_HEIGHT);
        gr.setColor(Color.white);
        gr.drawString("(" + x + "," + y + ")", 0, CHAR_DISPLAY_HEIGHT - 2);
        imaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;
        real = ((realEnd - realStart) / getWidth()) * x + realStart;
        gr.setColor(Color.black);
        gr.fillRect(getWidth() - CHAR_DISPLAY_WIDTH, 0, CHAR_DISPLAY_WIDTH, 30);
        gr.setColor(Color.white);
        gr.drawString("(" + real + "+" + imaginary + "i)", getWidth() - CHAR_DISPLAY_WIDTH, CHAR_DISPLAY_HEIGHT - 2);
        gr.drawLine(myStartX, myStartY, x, myStartY);
        gr.drawLine(myStartX, myStartY, myStartX, y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
