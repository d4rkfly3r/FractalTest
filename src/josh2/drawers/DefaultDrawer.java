package josh2.drawers;

import josh2.MainWindow;

import java.awt.*;

/**
 * Created by Joshua on 3/3/2016.
 * Project: Fractal
 */
public class DefaultDrawer implements Runnable {


    private MainWindow mw;
    private Color dC;
    private boolean debug;

    public DefaultDrawer(MainWindow mw, Color dC, boolean debug) {
        this.mw = mw;
        this.dC = dC;
        this.debug = debug;
    }

    @Override
    public void run() {
        do {
            int tLCCC = mw.cCC;
            mw.cCC++;

            int sX = (mw.recW * tLCCC) % mw.w;
            int sY = ((int) Math.floor((mw.recW * tLCCC) / mw.w)) * 10;
            for (int x = sX; x < sX + mw.recW; x++) {
                for (int y = sY; y < sY + mw.recH; y++) {
                    if (debug) System.err.println(x + " | " + y);
                    mw.bi.setRGB(x, y, dC.getRGB());
                }
            }
            mw.repaint();
        } while (mw.cCC < mw.chnkCnt);

    }
}
