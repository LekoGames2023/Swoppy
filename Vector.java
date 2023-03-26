public class Vector {
    private double x;
    private double y;
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    
    public Vector set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public Vector add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }
    public Vector multiply(double x, double y) {
         this.x *= x;
         this.y *= y;
         return this;
    }
    
    public Vector multiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        return this;
    }
    
    public Vector subtract(Point position) {
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
    
    public Vector copy() {
        return new Vector(this.x, this.y);
    }
    
    public String toString() {
        return "[Vector; x: " + this.x + "; y: " + this.y + "]";
    }
    
}