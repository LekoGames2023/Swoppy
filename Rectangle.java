public class Rectangle {
    private double x;
    private double y;
    public Rectangle(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    
    public Rectangle set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public Rectangle add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }
    public Rectangle multiply(double x, double y) {
         this.x *= x;
         this.y *= y;
         return this;
    }
    
    public Rectangle multiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        return this;
    }
    
    public Rectangle subtract(Point position) {
        this.x -= position.getX();
        this.y -= position.getY();
        return this;
    }
    
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    
    public Rectangle copy() {
        return new Rectangle(this.x, this.y);
    }
    
    public String toString() {
        return "[Rectangle; x: " + this.x + "; y: " + this.y + "]";
    }
    
}