package josh2.drawers;

import josh2.MainWindow;

import java.awt.*;

/**
 * Created by Joshua on 3/3/2016.
 * Project: Fractle
 */
public class FractalDrawer implements Runnable {
    private final MainWindow mw;
    private final boolean debug;

    public FractalDrawer(MainWindow mw, boolean debug) {
        this.mw = mw;
        this.debug = debug;
    }

    @Override
    public void run() {
        Color dC;
        do {

            int tLCCC = mw.cCC;
            mw.cCC++;

            mw.real = mw.realStart;
            mw.imaginary = mw.imaginaryEnd;

            int sX = (mw.recW * tLCCC) % mw.w;
            int sY = ((int) Math.floor((mw.recW * tLCCC) / mw.w)) * 10;

            int red, green, blue;
            int kc;

            for (int x = sX; x < sX + mw.recW; x++) {
                for (int y = sY; y < sY + mw.recH; y++) {

//                    for (kc = 0; kc < 256; kc++) {
//                        // various color palettes can be created here!
//                        red = (255 - (kc % 16) * 16);
//                        green = ((16 - kc % 16) * 16) % 255;
//                        blue = ((kc % 16) * 16);
//
//                        mw.bi.setRGB(x, y, new Color(red, green, blue).getRGB());
//                    }
                    red = x * y % 255;
                    green = x * y % 255;
                    blue = x * y % 255;
//                    red = (x / (y + 1)) % 255;
//                    green = (x / (y + 1)) % 255;
//                    blue = (x / (y+1)) % 255;

                    //red = (x * x + y * y) % 255;
                    //green = (x * x + y * y) % 255;
                    //blue = (x * x + y * y) % 255;

                    System.out.println(red + " : " + green + " : " + blue);
                    mw.bi.setRGB(x, y, new Color(red, green, blue).getRGB());
                }
            }
            mw.repaint();
        } while (mw.cCC < mw.chnkCnt);
    }
}
