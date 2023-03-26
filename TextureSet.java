import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class TextureSet {
    private HashMap<String, BufferedImage> textures;
    private int scaleFactor;
    public TextureSet(int scaleFactor) {
        this.textures = new HashMap<String, BufferedImage>();
        this.scaleFactor = scaleFactor;
    }
    public void start() {
        this.loadTexture("test");
        this.loadTexture("tile.stone");
        this.loadTexture("tile.prototype");
        this.loadTexture("tile.air");
        this.loadTexture("character");
        this.loadTexture("rock");
        this.loadTexture("entity.living.swoppy");
    }
    private void loadTexture(String textureId) {
        try {
            BufferedImage bi = this.scaleTexture(ImageIO.read(new File("assets/textures/" + textureId.replace(".", "/") + ".png")), this.scaleFactor);
            this.textures.put(textureId, bi);
        } catch(Exception e) {
            throw new Error("[Swoppy] An error occured trying to load a texture!");
        }
    }
    public BufferedImage getTexture(String textureId) {
        return this.textures.get(textureId);
    }
    private BufferedImage scaleTexture(BufferedImage texture, int factor) {
        BufferedImage scaledTexture = new BufferedImage(texture.getWidth() * factor, texture.getHeight() * factor, texture.getType());
        for(int x = 0; x < texture.getWidth(); x++) {
            for(int y = 0; y < texture.getHeight(); y++) {
                for(int x1 = x * factor; x1 < x * factor + factor; x1++) {
                    for(int y1 = y * factor; y1 < y * factor + factor; y1++) {
                        scaledTexture.setRGB(x1, y1, texture.getRGB(x,y));
                    } 
                }
            }
        }
        return scaledTexture;
    }
}