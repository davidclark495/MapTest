package com.map.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TitleScreen implements Screen{

	/////////////////////////
	///// INSTANCE VARS /////
	/////////////////////////

	private final MapGame game;
	private OrthographicCamera camera;



	///////////////////////
	///// CONSTRUCTOR /////
	///////////////////////

	public TitleScreen(final MapGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 400);
	}



	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Map Test: ", 20, 370);
		game.font.draw(game.batch, "Tap anywhere to begin.", 20, 320);
		game.batch.end();

		if(Gdx.input.isTouched()) {
			game.setScreen(new OverworldScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
