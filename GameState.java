public abstract class GameState {
    abstract void start();
    abstract void end();
    abstract void pause();
    abstract void resume();
    abstract void update();
    abstract void draw();
}