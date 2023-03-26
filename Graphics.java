import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.awt.image.BufferedImage;
public class Graphics {
    private JFrame jFrame;
    private BufferStrategy bs;
    private Graphics2D g;
    private Camera camera;
    private TextureSet textureSet;
    private BufferedImage tileGridBake = null;
    public Graphics() {
        this.jFrame = new JFrame(); // Creates the window in which the game will be drawn.
        this.jFrame.setUndecorated(true);
        this.jFrame.setPreferredSize(new Dimension((int) this.jFrame.getGraphicsConfiguration().getBounds().getWidth(), (int) this.jFrame.getGraphicsConfiguration().getBounds().getHeight()));
        this.jFrame.setTitle("Swoppy");
        this.jFrame.setResizable(false);
        this.jFrame.pack();
        this.jFrame.createBufferStrategy(2); // Creates JFrame and creates BufferStrategy for it.
        this.bs = this.jFrame.getBufferStrategy();
        this.g = (Graphics2D) this.bs.getDrawGraphics();
        this.camera = new Camera();
        this.textureSet = new TextureSet(this.camera.getScaleFactor());
    }
    public void showFrame() {
        this.jFrame.setVisible(true);
    }
    public void start() {
        Game.getHandle().getLogger().log("Graphics", "Loading textures");
        this.textureSet.start();
        Game.getHandle().getLogger().log("Graphics", "Textures loaded");
    }
    private Point convertPosition(Point position) {
        return position.copy()
            .subtract(this.camera.getPosition()) // get relative position
            .multiply(this.camera.getScaleFactor() * 16, -this.camera.getScaleFactor() * 16) // get position inside camera on screen
            .add(this.camera.getXOffset(), this.jFrame.getBounds().getHeight() - this.camera.getYOffset()); // add offsets + change y coordinates
    }
    public int getScaleFactor() {
        return this.camera.getScaleFactor();
    }
    public TextureSet getTextureSet() {
        return this.textureSet;
    }
    private BufferedImage bakeTileGrid(TileType[][] tileGrid) {
        BufferedImage tileGridBake = new BufferedImage(tileGrid.length*this.getScaleFactor()*16, tileGrid[0].length*this.getScaleFactor()*16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) tileGridBake.getGraphics();
        for(int x = 0; x < tileGrid.length; x++) {
            for(int y = 0; y < tileGrid[x].length; y++) {
                if(tileGrid[x][y] == TileType.Air) continue;
                BufferedImage tileImage = this.textureSet.getTexture(tileGrid[x][y].getTextureId());
                g.drawImage(tileImage, x*this.getScaleFactor()*16, tileGridBake.getHeight() - y*this.getScaleFactor()*16-tileImage.getHeight(), null);
            }
        }
        g.dispose();
        return tileGridBake;
    }
    public void draw(World world) {
        if(this.tileGridBake == null) this.tileGridBake = this.bakeTileGrid(world.getTileGrid());
        Point tileGridScreenPosition = convertPosition(new Point(0, 0));
        this.g.drawImage(this.tileGridBake, (int) tileGridScreenPosition.getX(), (int) tileGridScreenPosition.getY() - this.tileGridBake.getHeight(), null);
        
        for(Entity entity : world.getEntities()) {
            Point screenPosition = convertPosition(entity.getPosition());
            BufferedImage bi = entity.getTexture().getBufferedImage();
            
            if(true) {
                Graphics2D g = (Graphics2D) bi.getGraphics();
                g.setColor(Color.RED);
                g.drawRect(0, 0, bi.getWidth() - 1, bi.getHeight() - 1);
            }
            
            
            this.g.drawImage(bi, (int) screenPosition.getX(), (int) screenPosition.getY() - bi.getHeight(), null);
        }
        do {
            this.bs.show();
        } while(this.bs.contentsLost());
        this.g.setColor(Color.WHITE);
        this.g.fillRect(0, 0, this.jFrame.getWidth(), this.jFrame.getHeight());
        this.g.setColor(Color.BLACK);
    }
    private class Camera {
        private final int width = 16;
        private final int height = 9;
        private final int pointsPerUnit = 16;
        private final int scaleFactor;
        private final int xOffset;
        private final int yOffset;
        private Point position;
        public Camera() {
            int xScaleFactor = (int) (jFrame.getBounds().getWidth() / (this.width * this.pointsPerUnit));
            int yScaleFactor = (int) (jFrame.getBounds().getHeight() / (this.height * this.pointsPerUnit));
            this.scaleFactor = Math.min(xScaleFactor, yScaleFactor);
            this.xOffset = (int) (jFrame.getBounds().getWidth() - this.width * this.pointsPerUnit * scaleFactor) / 2;
            this.yOffset = (int) (jFrame.getBounds().getHeight() - this.height * this.pointsPerUnit * scaleFactor) / 2;
            this.position = new Point(0.0, 0.0);
        }
        public int getScaleFactor() {
            return this.scaleFactor;
        }
        public int getXOffset() {
            return this.xOffset;
        }
        public int getYOffset() {
            return this.yOffset;
        }
        public Point getPosition() {
            return this.position;
        }
    }
}