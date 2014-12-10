package gamestate;

import java.util.ArrayList;

import animations.ImageCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.orangeegames.suikorm.SuikodenRM;

public class InfoState extends GameState {

	private BoxWorld levelState;
	private BitmapFont font;
	private Skin skin;
	private SpriteBatch spriteBatch;
	private int returnState;
	
	ArrayList<String> tryText = new ArrayList<String>();
	
	float deltaText = 0;
	Label infoWindow;
	Label leftUpperInfoWindow;
	Label rightUpperInfoWindow;
	
	// Text
	int STD_TEXT_SPEED = 10;
	int PRESS_BUTTON_TEXT_SPEED = 50;
	int TXT_SPEED = STD_TEXT_SPEED;
	
	// Character
	int PORTRAIT_WIDTH = 56*2;
	int PORTRAIT_HEIGHT = 64*2;
	boolean b = false;
	int stringPosition = 0;
	
	private Stage stage;
	
	
	public InfoState(BoxWorld ls, int state, String infoString) {
		this.levelState = ls;
		spriteBatch = (SpriteBatch) ls.mapRenderer.getBatch();
		this.returnState = state;
		tryText.add(infoString);
	}

	@Override
	public void render(float delta) {
		levelState.render(delta);
		spriteBatch.setProjectionMatrix(levelState.camera.combined);
		stage.act(delta);
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		if(stage == null) {
			stage = new Stage(new ExtendViewport(width, height));
		}
		levelState.resize(width, height);
		stage.clear();
		
		TextButtonStyle style = new TextButtonStyle();
		
		style.up = skin.getDrawable("neat9patch");
		style.down = skin.getDrawable("neat9patch");
		style.font = font;
		
		LabelStyle infoWindowStyle = new LabelStyle();
		infoWindowStyle.background = skin.getDrawable("neat9patch");
		infoWindowStyle.font = font;
		infoWindowStyle.fontColor = new Color(Color.CYAN);
		
		int infoWindowWidth = width - height/8;
		int infoWindowHeight = height/10;
		infoWindow = new Label(tryText.get(0), infoWindowStyle);
		
		infoWindow.setHeight(infoWindowHeight);
		infoWindow.setWidth(infoWindowWidth);
		infoWindow.setX(width/2 - infoWindowWidth/2);
		infoWindow.setY(0 + height/2 - infoWindowHeight/2);
		infoWindow.setAlignment(Align.center);
		
		stage.addActor(infoWindow);
	}

	@Override
	public void show() {
		skin = new Skin();
		font = new BitmapFont(Gdx.files.internal("data/whiteFont.fnt"), false);
		
		skin.addRegions(ImageCache.atlas);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		skin.dispose();
		font.dispose();
		stage.dispose();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void keyPressed(int k) {
		TXT_SPEED = PRESS_BUTTON_TEXT_SPEED;
		if(k == Keys.Q) SuikodenRM.gsm.unpauseState(returnState);
		if(k == Keys.ESCAPE) SuikodenRM.gsm.unpauseState(returnState);
	}

	@Override
	public void keyReleased(int k) {
		TXT_SPEED = STD_TEXT_SPEED;
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
		stage.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button) {
		stage.touchUp(screenX, screenY, pointer, button);
	}
}
