import java.awt.Color;

public class Rock extends Entity {
    public Rock(Point position) {
        super(position, new Rectangle(11.0/16.0, 11.0/16.0), new Texture(11.0/16.0, 11.0/16.0, 0, 0));
    }
    public void start() {
        this.texture.getPainter().drawImage(Game.getHandle().getGraphics().getTextureSet().getTexture("rock"), 0, 0);
    }
    @Override
    public void update() {
        super.update();
    }
}