public class Swoppy extends Entity {
    public Swoppy(Point position) {
        super(position, new Rectangle(0.75, 1), new Texture(0.75, 1, 0, 0));
    }
    public void start() {
        this.texture.getPainter().drawImage(Game.getHandle().getGraphics().getTextureSet().getTexture("entity.living.swoppy"), 0, 0);
    }
    @Override
    public void update() {
        super.update();
    }
}