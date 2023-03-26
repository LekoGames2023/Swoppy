public class IngameGameState extends GameState {
    private Game game;
    public IngameGameState() {
        this.game = Game.getHandle();
    }
    public void start() {
        game.getLogger().log("GameState set to Ingame");
        game.getWorld().spawn(EntityType.Swoppy, new Point(1.0, 1.0));
    }
    public void end() {
        
    }
    public void pause() {
        
    }
    public void resume() {
        
    }
    public void update() {
        game.getWorld().update();
    }
    public void draw() {
        game.getGraphics().draw(game.getWorld());
    }
}