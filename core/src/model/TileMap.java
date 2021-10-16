package model;

import java.util.Iterator;

import model_tiles.Tile;
import model_tiles.TileType;

public class TileMap {

	private Tile[][] map; // row 0 is the bottom-most row of tiles
	
	private int tileWidth;
	private int tileHeight;
	
	
	
	public TileMap(int worldWidth, int worldHeight, int tileWidth, int tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		int xTilesInWorld = worldWidth / tileWidth;
		int yTilesInWorld = worldHeight / tileHeight;
		
		map = new Tile[yTilesInWorld][xTilesInWorld];
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				Tile tile = new Tile(tileWidth*j, tileHeight*i, tileWidth, tileHeight, TileType.empty);
				map[i][j] = tile;
			}
		}
	}
	
	
	
	public void addTile(float tileX, float tileY, TileType type) {
		int tileXCoord = (int)(tileX / tileWidth);
		int tileYCoord = (int)(tileY / tileHeight);
		
		tileX = tileXCoord * tileWidth;
		tileY = tileYCoord * tileHeight;
		
		Tile tile = new Tile(tileX, tileY, tileWidth, tileHeight, type);
		map[tileYCoord][tileXCoord] = tile;
	}
	
	
	public Tile getTileByOrdinal(int tileX, int tileY) {
		if(tileY > map.length || tileX > map[0].length)
			throw new IllegalArgumentException("Invalid tile coordinates");
		
		return map[tileY][tileX];
	}
	
	public Tile getTileByLocation(float tileX, float tileY) {
		return getTileByOrdinal((int)(tileX/tileWidth), (int)(tileY/tileHeight));
	}
	
	
	
	public Tile[][] getAllTiles() {
		return map;
	}
}
