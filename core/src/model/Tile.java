package model;

import com.badlogic.gdx.math.Rectangle;

public class Tile {

	Rectangle collBox; // collision box
	
	public Tile(int x, int y, int width, int height) {
		collBox.x = x;
		collBox.y = y;
		collBox.width = width;
		collBox.height = height;
	}
	
}
