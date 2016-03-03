package josh2;

import josh.Complex;
import josh2.drawers.DefaultDrawer;
import josh2.drawers.FractalDrawer;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by Joshua on 3/2/2016.
 * Project: Fractal
 */
public class MainWindow extends Frame implements WindowListener, MouseListener, ComponentListener, MouseMotionListener {

    public final boolean debug = false;
    private final DrawerType drawer = DrawerType.FRACTAL_DRAWER;


    public int w = 1000;
    private int h = 1000;
    public int recW = 10;
    public int recH = 10;

    public static final int MAX_MAGNITUDE = 10;
    public static final int MAX_ITERATIONS = 500;
    public static final int CHAR_DISPLAY_HEIGHT = 15;
    public static final int CHAR_DISPLAY_WIDTH = 300;


    public BufferedImage bi;

    private int myStartX = 0;
    private int myStartY = 0;
    private int myEndX = 0;
    private int myEndY = 0;

    private boolean keyHeldDown;
    private KeyEvent lastKey;

    public double real = 0.0;
    public double imaginary = 0.0;
    public double realStart = -2.0;
    public double realEnd = 2.0;
    public double imaginaryStart = -2.0;
    public double imaginaryEnd = 2.0;

    public int chnkCnt = (w * h) / (recW * recH);
    public int cCC = 0;

    ThreadGroup group = new ThreadGroup("Rendering Threads");
    Thread t1, t2, t3, t4;

    public MainWindow() {

        this.addWindowListener(this);
        this.addMouseListener(this);
        this.addComponentListener(this);

        setSize(w, h);
        setLocationRelativeTo(null);
        setVisible(true);

        bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        switch (drawer) {
            case DEF_DRAWER:
                t1 = new Thread(group, new DefaultDrawer(this, Color.red, debug));
                t2 = new Thread(group, new DefaultDrawer(this, Color.green, debug));
                t3 = new Thread(group, new DefaultDrawer(this, Color.black, debug));
                t4 = new Thread(group, new DefaultDrawer(this, Color.blue, debug));
                break;

            case FRACTAL_DRAWER:
                t1 = new Thread(group, new FractalDrawer(this, debug));
                t2 = new Thread(group, new FractalDrawer(this, debug));
                t3 = new Thread(group, new FractalDrawer(this, debug));
                t4 = new Thread(group, new FractalDrawer(this, debug));
                break;
        }
        if (t1 != null) t1.start();
        if (t2 != null) t2.start();
        if (t3 != null) t3.start();
        if (t4 != null) t4.start();


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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public int iterate(Complex c) {
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

    public Color setColor(int color) {
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

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
