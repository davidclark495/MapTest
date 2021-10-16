package model;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class OverworldModel {

	private Rectangle playerRect;
	private Rectangle currentFrame;
	private Rectangle fullMap;

	private int tileWidth = 16;
	private int tileHeight = 16;
	private int screenWidth = 128;
	private int screenHeight = 128;

	private double playerSpeed = 2;
	private int cameraSpeed = screenWidth / 16;

	public OverworldModel(Rectangle playerRect, Rectangle currentFrame) {
		this.playerRect = playerRect;
		this.currentFrame = currentFrame;
	}
	
	public OverworldModel(int startFrameX, int startFrameY, int startTileX, int startTileY) {
		this.playerRect = new Rectangle();
		playerRect.x = startFrameX*screenWidth + startTileX*tileWidth;
		playerRect.y = startFrameY*screenHeight + startTileY*tileHeight;
		playerRect.width = tileWidth;
		playerRect.height = tileHeight;
		
		this.currentFrame = new Rectangle();
		currentFrame.x = startFrameX*screenWidth;
		currentFrame.y = startFrameY*screenHeight;
		currentFrame.width = screenWidth;
		currentFrame.height = screenHeight;
	}

	/**
	 * Move the player when given an arrow-key input.
	 * If the player would move out of frame, 
	 * then move the frame and camera too.
	 * 
	 * @param keyCode
	 */
	public void movePlayer(int keyCode) {
		switch(keyCode) {
		case Keys.LEFT:
			playerRect.x -= playerSpeed;
			break;
		case Keys.RIGHT:
			playerRect.x += playerSpeed;
			break;
		case Keys.UP:	
			playerRect.y += playerSpeed;
			break;
		case Keys.DOWN:
			playerRect.y -= playerSpeed;
			break;
		}

		// move the frame if the player goes out of frame
		if(playerRect.x + playerRect.width > currentFrame.x + screenWidth) { // player is too far right
			currentFrame.x += screenWidth;
		}
		if(playerRect.x < currentFrame.x) { // player is too far left
			currentFrame.x -= screenWidth;
		}
		if(playerRect.y + playerRect.height > currentFrame.y + screenHeight) { // player is above frame
			currentFrame.y += screenHeight;
		}
		if(playerRect.y < currentFrame.y) { // player is below frame
			currentFrame.y -= screenHeight;
		}


	}

	/**
	 * If the camera isn't focused on the current frame, move the camera a small amount.
	 * 
	 * @param camera
	 */
	public void alignCamera(OrthographicCamera camera) {
		// scroll the camera into the new frame
		if(camera.position.x - screenWidth/2 < currentFrame.x) { // camera is too far left
			camera.translate(cameraSpeed, 0);
		}
		if(camera.position.x - screenWidth/2 > currentFrame.x) { // camera is too far right
			camera.translate(-cameraSpeed, 0);
		}
		if(camera.position.y - screenHeight/2 < currentFrame.y) { // camera is below frame
			camera.translate(0, cameraSpeed);
		}
		if(camera.position.y - screenHeight/2 > currentFrame.y) { // camera is above frame
			camera.translate(0, -cameraSpeed);
		}
	}
	
	
	
	public float getPlayerX() {
		return playerRect.x;
	}
	public float getPlayerY() {
		return playerRect.y;
	}
	
	public float getPlayerWidth() {
		return playerRect.width;
	}
	public float getPlayerHeight() {
		return playerRect.height;
	}
	
	public float getFrameX() {
		return currentFrame.x;
	}
	public float getFrameY() {
		return currentFrame.y;
	}

	public int getFrameXOrdinal() {
		return (int)(currentFrame.x/screenWidth);
	}
	public int getFrameYOrdinal() {
		return (int)(currentFrame.y/screenHeight);
	}

}
