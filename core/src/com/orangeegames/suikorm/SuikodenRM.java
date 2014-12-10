package com.orangeegames.suikorm;

import gamestate.GameStateManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SuikodenRM extends Game {
	public static GameStateManager gsm;
	public static boolean debug = false;
	public static float scale = 1f;
	
	@Override
	public void create() {	
		gsm = new GameStateManager(this);
		Gdx.input.setInputProcessor(gsm);
		setScreen(gsm.getScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
	public void changeScreen() {
		setScreen(gsm.getScreen());
	}
}