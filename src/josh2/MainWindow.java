package josh2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Joshua on 3/2/2016.
 * Project: Fractle
 */
public class MainWindow extends Frame implements WindowListener, MouseListener, ComponentListener {

    private int w = 1001, h = 1001;
    private BufferedImage bi;

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
    Thread t1, t2, t3, t4;

    int rowCnt = 0, colCnt = 0, maxColCnt = getWidth() / pi, maxRowCnt = getHeight() / pi;
//new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))

    @Override
    public void mouseClicked(MouseEvent e) {

//        if (t1 != null) t1.stop();
//        if (t2 != null) t2.stop();
//        if (t3 != null) t3.stop();
//        if (t4 != null) t4.stop();

        t1 = new Thread(() -> {
            rowCnt = 0;
            colCnt = 0;
            maxColCnt = 50;
            maxRowCnt = 50;

            draw(rowCnt, colCnt, maxColCnt, maxRowCnt);
        });
        t2 = new Thread(() -> {
            rowCnt = 50;
            colCnt = 0;
            maxColCnt = 100;
            maxRowCnt = 50;

            draw(rowCnt, colCnt, maxColCnt, maxRowCnt);
        });
        t3 = new Thread(() -> {
            rowCnt = 50;
            colCnt = 50;
            maxColCnt = 100;
            maxRowCnt = 100;

            draw(rowCnt, colCnt, maxColCnt, maxRowCnt);
        });
        t4 = new Thread(() -> {
            rowCnt = 0;
            colCnt = 50;
            maxColCnt = 50;
            maxRowCnt = 100;

            draw(rowCnt, colCnt, maxColCnt, maxRowCnt);
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    private void draw(int rowCnt, int colCnt, int maxColCnt, int maxRowCnt) {
        do {
            int startX = colCnt * pi;
            int endX = startX + pi;
            int startY = rowCnt * pi;
            int endY = startY + pi;
            colCnt++;

            Color drawColor;
            Random random = new Random();
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    drawColor = Color.red;
                    bi.setRGB(x, y, drawColor.getRGB());
                    repaint(x, y, 1, 1);
                }
            }
            if (colCnt >= maxColCnt) {
                colCnt = 0;
                rowCnt++;
            }
        } while (rowCnt < maxRowCnt);

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
}
