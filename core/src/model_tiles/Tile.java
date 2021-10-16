package model_tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tile {
	

	private TileType type;
	private Texture texture;
	private Rectangle collBox; // collision box
	private boolean walkable = false;

	public Tile(float x, float y, float width, float height, TileType type) {
		collBox = new Rectangle();
		collBox.x = x;
		collBox.y = y;
		collBox.width = width;
		collBox.height = height;
		this.type = type;
		
		switch(type) {
		case grass:
			texture = new Texture(Gdx.files.internal("img/tiles/grass.png"));
			walkable = true;
			break;
		case rock:
			texture = new Texture(Gdx.files.internal("img/tiles/gray_rock.png"));
			walkable = false;
			break;
		case tree:
			texture = new Texture(Gdx.files.internal("img/tiles/tree.png"));
			walkable = false;
			break;
		default:
			walkable = true;
		}
	}
	


	///////////////////////////////

	///// Getters and Setters /////

	///////////////////////////////

	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	
	
	/**
	 * 
	 * @return the tile's bottom-left x coordinate
	 */
	public float getX() {
		return collBox.x;
	}
	
	/**
	 * 
	 * @return the tile's bottom-left y coordinate
	 */
	public float getY() {
		return collBox.y;
	}
	
	public float getWidth() {
		return collBox.width;
	}
	
	public float getHeight() {
		return collBox.height;
	}
	
	public void moveTile(float deltaX, float deltaY) {
		collBox.x += deltaX;
		collBox.y += deltaY;
	}
	
	
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
}
