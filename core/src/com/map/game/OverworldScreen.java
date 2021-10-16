package com.map.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import model.OverworldModel;
import model_tiles.Tile;
import model_tiles.TileType;

public class OverworldScreen implements Screen{

	/////////////////////////
	///// INSTANCE VARS /////
	/////////////////////////

	// fundamental game stuff
	private final MapGame game;
	private OrthographicCamera camera;

	// textures
	private Texture worldTexture;
	private Texture playerTexture;

	// logic
	private OverworldModel model;


	///////////////////////
	///// CONSTRUCTOR /////
	///////////////////////

	public OverworldScreen(final MapGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 128, 128);

		worldTexture = new Texture(Gdx.files.internal("img/world.png"));
		playerTexture = new Texture(Gdx.files.internal("img/kirbus.png"));

		//		// set up the model
		//		int tileWidth = 16;
		//		int tileHeight = 16;
		//		int screenWidth = 128;
		//		int screenHeight = 128;
		//		
		//		Rectangle playerRect = new Rectangle(0, 0, tileWidth, tileHeight);
		//		playerRect.x = 16*1 - tileWidth/2;
		//		playerRect.y = 16*7 - tileHeight/2;
		//		
		//		Rectangle currentFrame = new Rectangle(0, 0, screenWidth, screenHeight);
		//		
		//		model = new OverworldModel(playerRect, currentFrame);
		model = new OverworldModel(0, 0, 4, 0);
	}



	////////////////////////////
	///// USEFUL OVERRIDES /////
	////////////////////////////

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		this.render_draw();
		this.render_takeInputsAndUpdate();
	}

	private void render_draw() {
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(worldTexture, 0, 0); // draw the world
		for(Tile[] row : model.tileMap.getAllTiles()) { // draw the tiles
			for(Tile tile : row) {
				if(tile.getTexture() == null)
					continue;
				game.batch.draw(tile.getTexture(), tile.getX(), tile.getY());				
			}
		}
		// draw the player
		game.batch.draw(playerTexture, model.getPlayerX(), model.getPlayerY(), model.getPlayerWidth(), model.getPlayerHeight());
		// write the screen's coordinates
		game.font.draw(game.batch, 
				"(" + model.getFrameXOrdinal() + "," + model.getFrameYOrdinal() + ")",
				10 + model.getFrameX(), 
				120 + model.getFrameY());
		game.batch.end();
	}

	private void render_takeInputsAndUpdate() {
		// move the player (and the frame, if necessary)
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			model.movePlayer(Input.Keys.LEFT);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			model.movePlayer(Input.Keys.RIGHT);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			model.movePlayer(Input.Keys.UP);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			model.movePlayer(Input.Keys.DOWN);
		}

		model.alignCamera(camera);
		
		// place tiles when clicked / touched
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			
			model.tileMap.addTile(touchPos.x, touchPos.y, TileType.rock);
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		worldTexture.dispose();
		playerTexture.dispose();
	}



	///////////////////////////////
	///// UNTOUCHED OVERRIDES /////
	///////////////////////////////

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}



	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}



}
