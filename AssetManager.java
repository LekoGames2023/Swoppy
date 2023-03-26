import java.util.LinkedList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class AssetManager {
    private Game game;
    public AssetManager(Game game) {
        this.game = game;
    }
    private LinkedList<String> readFileToStringList(String filePath) {
        LinkedList<String> lines = new LinkedList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while(line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch(IOException e) {
            this.game.getLogger();
        }
        return lines;
    }
    public TileType[][] translateFileToGrid(String filePath) {
        LinkedList<String> lines = this.readFileToStringList(filePath);
        HashMap<String, TileType> tileTypeById = new HashMap<String, TileType>();
        LinkedList<String> tileGridLines = new LinkedList<String>();
        TileType[][] tileGrid = null;
        int height = 0;
        int width = 0;
        for(String line : lines) {
            if(line.startsWith("dimensions")) {
                String[] splitLine = line.split(" ");
                width = Integer.parseInt(splitLine[1]);
                height = Integer.parseInt(splitLine[2]);
                tileGrid = new TileType[Integer.parseInt(splitLine[1])][Integer.parseInt(splitLine[2])];
            }else if(line.startsWith("tile")) {
                String[] splitLine = line.split(" ");
                switch(splitLine[0]) {
                    case "tile.air": tileTypeById.put(splitLine[1], TileType.Air); break;
                    case "tile.stone": tileTypeById.put(splitLine[1], TileType.Stone); break;
                    case "tile.prototype": tileTypeById.put(splitLine[1], TileType.Prototype); break;
                }
            } else if(!line.isEmpty()) {
                tileGridLines.add(line.replace(" ", ""));
            }
        }
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                tileGrid[x][height - y - 1] = tileTypeById.get(tileGridLines.get(y).charAt(2*x) + "" + tileGridLines.get(y).charAt(2*x + 1));
            }
        }
        return tileGrid;
    }
}