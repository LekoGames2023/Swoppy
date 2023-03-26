public enum TileType {
    Air("tile.air"), Stone("tile.stone"), Prototype("tile.prototype");
    private String textureId;
    TileType(String textureId) {
        this.textureId = textureId;
    }
    public String getTextureId() {
        return this.textureId;
    }
}