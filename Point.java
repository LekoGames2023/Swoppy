public class Point {
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    
    public Point set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public Point add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }
    public Point multiply(double x, double y) {
         this.x *= x;
         this.y *= y;
         return this;
    }
    
    public Point multiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        return this;
    }
    
    public Point subtract(Point position) {
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
    
    public Point copy() {
        return new Point(this.x, this.y);
    }
    
    public String toString() {
        return "[Point; x: " + this.x + "; y: " + this.y + "]";
    }
    
}