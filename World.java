import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
public class World {
    private LinkedList<Entity> entities;
    private TileType[][] tileGrid;
    public World(TileType[][] tileGrid) {
        this.entities = new LinkedList<Entity>();
        this.tileGrid = tileGrid;
    }
    public void update() {
        for(Entity entity : this.entities) {
            entity.update();
        }
    }
    public LinkedList<Entity> getEntities() {
        return this.entities;
    }
    public TileType[][] getTileGrid() {
        return this.tileGrid;
    }
    public void spawn(EntityType entityType, Point position) {
        Entity entity = null;
        switch(entityType) {
            case Rock: entity = new Rock(position);
            case Swoppy: entity = new Swoppy(position);
        }
        this.entities.add(entity);
        entity.start();
    }
}