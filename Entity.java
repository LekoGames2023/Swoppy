public abstract class Entity {
    protected Rectangle hitbox;
    protected Texture texture;
    protected Point position;
    protected long tick;
    
    protected Vector velocity;
    protected Vector acceleration;
    
    protected Entity(Point position, Rectangle hitbox, Texture texture) {
        this.texture = texture;
        this.hitbox = hitbox;
        this.position = position;
        this.tick = 0;
        
        this.velocity = new Vector(0, 10);
        this.acceleration = new Vector(0, -40);
    }
    public abstract void start();
    public void update() {
        this.tick++;
        
        this.position.add(this.velocity.getX() * Game.getDelta(), this.velocity.getY() * Game.getDelta());
        this.velocity.add(this.acceleration.getX() * Game.getDelta(), this.acceleration.getY() * Game.getDelta());
        
    }
    public Point getPosition() {
        return this.position;
    }
    public Texture getTexture() {
        return this.texture;
    }
    public Rectangle getHitbox() {
        return this.hitbox;
    }
}