package josh;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Joshua on 3/2/2016.
 * Project: Fractle
 */
public class Window extends Frame implements MouseListener, MouseMotionListener, KeyListener, WindowListener {

    protected final BufferedImage data;
    private static double realStart;
    private static double realEnd;
    private static double imaginaryStart;
    private static double imaginaryEnd;
    private static boolean keyHeldDown;
    private static KeyEvent lastKey;

    public Window(BufferedImage data) throws HeadlessException {
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        addWindowListener(this);
        this.setTitle("Fractal Generator");
        this.data = data;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.yellow);
        g.drawRect(0,0,getWidth(),getHeight());
        g.drawImage(data, 0, 0, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getButton());
        new Thread(() -> {
            double real = realStart;
            double imaginary = imaginaryEnd;
            Random random = new Random();
            Color drawColor;
            for (int x = getWidth() / 2; x < getWidth(); x++) {
                for (int y = getHeight() / 2; y < getHeight(); y++) {
                    drawColor = setColor(Fractal.iterate(new Complex(real, imaginary)));
                    data.setRGB(x, y, drawColor.getRGB());
                    imaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;
                    repaint(x, y, 1, 1);
                }
                real = ((realEnd - realStart) / getWidth()) * x + realStart;
            }

        }).start();
        new Thread(() -> {
            double real = realStart;
            double imaginary = imaginaryEnd;
            Random random = new Random();
            Color drawColor;
            for (int x = getWidth() / 2; x < getWidth(); x++) {
                for (int y = 0; y < getHeight() / 2; y++) {
                    drawColor = setColor(Fractal.iterate(new Complex(real, imaginary)));
                    data.setRGB(x, y, drawColor.getRGB());
                    imaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;
                    repaint(x, y, 1, 1);
                }
                real = ((realEnd - realStart) / getWidth()) * x + realStart;
            }

        }).start();
        new Thread(() -> {
            double real = realStart;
            double imaginary = imaginaryEnd;
            Random random = new Random();
            Color drawColor;
            for (int x = 0; x < getWidth() / 2; x++) {
                for (int y = 0; y < getHeight() / 2; y++) {
                    drawColor = setColor(Fractal.iterate(new Complex(real, imaginary)));
                    data.setRGB(x, y, drawColor.getRGB());
                    imaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;
                    repaint(x, y, 1, 1);
                }
                real = ((realEnd - realStart) / getWidth()) * x + realStart;
            }

        }).start();
        new Thread(() -> {
            double real = realStart;
            double imaginary = imaginaryEnd;
            Random random = new Random();
            Color drawColor;
            for (int x = 0; x < getWidth() / 2; x++) {
                for (int y = getHeight() / 2; y < getHeight(); y++) {
                    drawColor = setColor(Fractal.iterate(new Complex(real, imaginary)));
                    data.setRGB(x, y, drawColor.getRGB());
                    imaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;
                    repaint(x, y, 1, 1);
                }
                real = ((realEnd - realStart) / getWidth()) * x + realStart;
            }
        }).start();
    }

    public static Color setColor(int color) {
        Color c1 = new Color(0, 0, 0);

        switch (color) {
            case 0:
                c1 = new Color(0, 153, 153);
                break;
            case 1:
                c1 = new Color(0, 153, 153);
                break;
            case 2:
                c1 = new Color(99, 7, 67);
                break;
            case 3:
                c1 = new Color(255, 88, 0);
                break;
            case 4:
                c1 = new Color(204, 0, 204);
                break;
            default:

        }

        if (color > 5 && color <= 15) {
            c1 = new Color((int) (13 + 13 * Math.sin(color)) % 256,
                    13 * color % 256,
                    (int) (200 + 200 * Math.cos(color)) % 256);
        }

        if (color > 15) {
            c1 = new Color((int) (200 + 200 * Math.tan(color)) % 256,
                    200 * color % 256,
                    19 * color % 256);
        }

        return c1;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainClass.fractal.myEndX = e.getX();
        MainClass.fractal.myEndY = e.getY();
        double middleReal = ((realEnd - realStart) / getWidth()) * MainClass.fractal.myEndX + realStart;
        double middleImaginary = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * MainClass.fractal.myEndY;
        double scaleFactor = 1.0;

        if (MainClass.fractal.myEndX == MainClass.fractal.myStartX || MainClass.fractal.myEndY == MainClass.fractal.myStartY) {
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
            int x = Math.min(MainClass.fractal.myStartX, MainClass.fractal.myEndX);
            int y = Math.min(MainClass.fractal.myStartY, MainClass.fractal.myEndY);
            double real1 = ((realEnd - realStart) / getWidth()) * x + realStart;
            double imaginary1 = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;

            x = Math.max(MainClass.fractal.myStartX, MainClass.fractal.myEndX);
            y = Math.max(MainClass.fractal.myStartY, MainClass.fractal.myEndY);
            double real2 = ((realEnd - realStart) / getWidth()) * x + realStart;
            double imaginary2 = imaginaryEnd - ((imaginaryEnd - imaginaryStart) / getHeight()) * y;

            realStart = real1;
            realEnd = real2;
            imaginaryStart = imaginary2;
            imaginaryEnd = imaginary1;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
}
