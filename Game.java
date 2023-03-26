import java.util.Stack;
import java.util.Date;
public class Game {
    private static Game handle = new Game();
    private static final double delta = 0.01;
    public static Game getHandle() {
        return handle;
    }
    public static double getDelta() {
        return delta;
    }
    
    private final Stack<GameState> states; // Gamestates determine the current state of the game (starting, menu, ingame etc.).
    private final Graphics graphics; // Main graphics class of the game.
    private World world; // World that is currently drawn and updated.
    private UserInterface ui; // UI that is currently drawn and updated.
    private PerformanceManager pm; // Give some info about the games performance.
    private AssetManager am;
    private Logger logger;
    
    private Game() {
        handle = this;
        this.logger = new Logger(); // DS: None
        this.states = new Stack<GameState>(); // DS: None
        
        this.pm = new PerformanceManager();
        this.am = new AssetManager(this);
        
        this.graphics = new Graphics(); // DS: (Game) Logger
        
        this.ui = new UserInterface(); // DS: None
        this.world = new World(this.am.translateFileToGrid("assets/friendlyforest.swoppyworld"));
        
        this.pushState(new StartGameState()); // Start gamestate plays loading animation while Game.start() is being called.
        this.graphics.showFrame();
    }
    public void start() { // Called at game start. Loads the game.
        this.graphics.start();
        this.pushState(new IngameGameState()); // Game is fully loaded. MenuGameState is set to be the active gamestate.
    }
    public void end() { // Called at game end. Example: Saving
        
        Main.isRunning = false; // Ends the application loop.
    }
    public void update() { // Called 100 times per second. Updates game physics
        this.pm.update();
        this.states.peek().update();
    }
    public void draw() { // Called as often as possible. Draws current frame.
        this.pm.draw(); // Registers frame in the performance manager.
        this.states.peek().draw();
    }
    public void pushState(GameState gameState) {
        this.states.push(gameState);
        gameState.start();
    }
    public AssetManager getAssetManager() {
        return this.am;
    }
    public Graphics getGraphics() {
        return this.graphics;
    }
    public World getWorld() {
        return this.world;
    }
    public Logger getLogger() {
        return this.logger;
    }
    private class PerformanceManager {
        private long tick = 0;
        private int frames = 0;
        public void update() {
            if(++tick % 500 == 0) {
                logger.log("Performance", "Frames per second (5s avg): " + this.frames / 5 + " - Memory Usage (Megabytes): " + (int) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000));
                this.frames = 0;
                System.gc();
            }
        }
        public void draw() {
            this.frames++;
        }
    }
    public class Logger {
        public void log(String prefix, String message) {
            Date date = new Date();
            System.out.println(String.format("[%02d:%02d:%02d]", date.getHours(), date.getMinutes(), date.getSeconds()) + "[" + prefix + "] " + message);
        }
        public void log(String message) {
            Date date = new Date();
            System.out.println(String.format("[%02d:%02d:%02d]", date.getHours(), date.getMinutes(), date.getSeconds()) + "[Swoppy] " + message);
        }
        public void debug(Object... messages) {
            Date date = new Date();
            String output = String.format("[%02d:%02d:%02d]", date.getHours(), date.getMinutes(), date.getSeconds()) + "[Debug] ";
            for(Object message : messages) {
                output += message.toString() + " ";
            }
            System.out.println(output);
        }
    }
}