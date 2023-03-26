import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
public class Texture {
    private BufferedImage bi;
    private Painter painter;
    private int gsf; // Graphics Scale Factor
    private double xOffset;
    private double yOffset;
    public Texture(double height, double width, double xOffset, double yOffset) {
        this.gsf = Game.getHandle().getGraphics().getScaleFactor();
        this.bi = new BufferedImage((int) (height * gsf * 16), (int) (width * gsf * 16), BufferedImage.TYPE_INT_ARGB);
        this.painter = new Painter((Graphics2D) this.bi.getGraphics());
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    public BufferedImage getBufferedImage() {
        return this.bi;
    }
    public double getXOffset() {
        return this.xOffset;
    }
    public double getYOffset() {
        return this.yOffset;
    }
    public Painter getPainter() {
        return this.painter;
    }
    public class Painter {
        private Graphics2D g;
        private Painter(Graphics2D g) {
            this.g = g;    
        }
        public void drawImage(BufferedImage bi, double x, double y) {
            this.g.drawImage(bi, (int) (x * gsf * 16), (int) (y * gsf * 16), null);
        }
    }
}