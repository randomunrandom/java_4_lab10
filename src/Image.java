import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

public class Image extends Frame {
    int[] pix;

    Image(int W, int H) {
        setSize(new Dimension(W, H));
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        int i = 0;
        pix = new int[getWidth() * getHeight()];
        for (int x = 0; x < getHeight(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int
                r = (x ^ y) & 0xff,
                g = (x + 2 * x * y + y) & 0xff,
                b = (x*4^y*4) & 0xff;
                pix[i] = 255 << 24 | r << 16 | g << 8 | b;
//                System.out.println();
                i++;
            }
        }
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        java.awt.Image img = createImage(new MemoryImageSource(getWidth(), getHeight(), pix, 0, getHeight()));
        g.drawImage(img, 0, 0, this);
        g.drawImage(getToolkit().getImage("sunflower.jpg"), 0, 0, this);
    }

    public static void main(String[] args) {
        Image screen = new Image(1000, 500);
    }
}
