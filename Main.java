public class Main {
    public static boolean isRunning = true;
    public static void main() {
        int tickTime = 10;
        long previousTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        long accumulatedTime = 0L;
        Game game = Game.getHandle();
        Thread thread = new Thread(() -> { // Loads game in seperate thread.
            game.getLogger().log("Starting Swoppy");
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
            game.start();
        });        
        thread.start();
        while(isRunning) { // Main game loop.
            currentTime = System.currentTimeMillis();
            accumulatedTime += (currentTime - previousTime);
            previousTime = currentTime;
            while(accumulatedTime >= tickTime) {
                game.update();
                accumulatedTime -= tickTime;
            }
            game.draw();
        }
        game.end();
    }
}