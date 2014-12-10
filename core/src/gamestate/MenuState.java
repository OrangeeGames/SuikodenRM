package gamestate;

import java.util.ArrayList;

import animations.ImageCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.orangeegames.suikorm.SuikodenRM;

public class MenuState extends GameState {

	private BoxWorld levelState;
	private BitmapFont font;
	private Skin skin;
	private SpriteBatch spriteBatch;
	private TextButton button;
	private int returnState;
	
	ArrayList<String> tryText = new ArrayList<String>();
	
	float deltaText = 0;
	TextButton welcomeLabel;
	
	int STD_TEXT_SPEED = 10;
	int PRESS_BUTTON_TEXT_SPEED = 50;
	int TXT_SPEED = STD_TEXT_SPEED;
	boolean b = false;
	int stringPosition = 0;
	
	private Stage stage;
	
	
	public MenuState(BoxWorld ls, int state) {
		this.levelState = ls;
		spriteBatch = (SpriteBatch) ls.mapRenderer.getBatch();
		this.returnState = state;
		tryText.add("A long test string in order for it to work I guess. Time to get to work, or what? Go go go. Now it should be long enough to start wrapping. Will that happen on the fly??? :O How long can the message be and when will it end. Will it go further than any man has gone before??? To the dark side of the moon perhaps?? :O This was not long enough but maybe this is? Or this? maybe this? You can be this, or that, or anywhere inbetween. And a little bit longer, and longer, and longer, and Chris Browner, and Chris Pronger and longeR?");
		tryText.add("String test 2 is a good test to die");
		
	}

	@Override
	public void render(float delta) {
		levelState.render(delta);
		spriteBatch.setProjectionMatrix(levelState.camera.combined);
		stage.act(delta);
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
		deltaText += delta*TXT_SPEED;
		welcomeLabel.align(Align.left + Align.top);

		if(deltaText < tryText.get(stringPosition).length()) {
			if(!(welcomeLabel.getHeight() < welcomeLabel.getMaxHeight())) {
				LabelStyle ss = new LabelStyle();
				ss.font = font;
				
				Label nl = new Label("", ss);
				nl.setHeight(welcomeLabel.getHeight());
				nl.setWidth(welcomeLabel.getWidth());
				nl.setAlignment(Align.left + Align.top);
				nl.setWrap(true);
				System.out.println(welcomeLabel.getHeight());
				nl.setText((String) tryText.get(stringPosition).subSequence(0, (int) deltaText)); 
				welcomeLabel.reset();
				welcomeLabel.align(Align.left + Align.top);
				welcomeLabel.add(nl);
			}
		} else {
			welcomeLabel.setText(tryText.get(stringPosition));
			b = true;
		}
		
	}

	@Override
	public void resize(int width, int height) {
		if(stage == null) {
			stage = new Stage(new ExtendViewport(width, height));
		}
		stage.clear();
		
		TextButtonStyle style = new TextButtonStyle();
		
		style.up = skin.getDrawable("neat9patch");
		style.down = skin.getDrawable("neat9patch");
		style.font = font;
		
		TextButtonStyle ss = new TextButtonStyle();
		ss.up = skin.getDrawable("neat9patch");
		ss.down = skin.getDrawable("neat9patch");
		ss.font = font;
		
		welcomeLabel = new TextButton("", ss);
		welcomeLabel.addListener( new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SuikodenRM.gsm.unpauseState(returnState);
			}
		
		});
		welcomeLabel.setHeight(height/4);
		welcomeLabel.setWidth(width - welcomeLabel.getHeight()/2);
		welcomeLabel.setX(width/2 - welcomeLabel.getWidth()/2);
		welcomeLabel.setY(0 + welcomeLabel.getHeight()/4);
		
		welcomeLabel.setHeight(height/4);
		
		button = new TextButton("Press Me HHHHHvHHHHH HHHHHvHHHHH HHHHHvHHHHHHHHHHvHHHHHHHHHHvHHHHHHHHHHvHHHHHHHHHHvHHHHHHHHHHvHHHHHHHHHHvHHHHHHHHHHvHHHHH HHHHHvHHHHHHHHHHvHHHHH HHHHHvHHHHH HHHHHvHHHHH HHHHHvHHHHH HHHHHvHHHHH ", style);
		button.setHeight(height/4);
		button.setWidth(width - button.getHeight()/2);
		button.setX(width/2 - button.getWidth()/2);
		button.setY(0 + button.getHeight()/4);
		
		button.addListener( new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SuikodenRM.gsm.unpauseState(returnState);
			}
		
		});
		
		stage.addActor(welcomeLabel);
		
		Image i = new Image(skin.getDrawable("head-kill"));
		i.setWidth(56*2);
		i.setHeight(64*2);
		i.setX(0 + welcomeLabel.getHeight()/2);
		i.setY(welcomeLabel.getY() + welcomeLabel.getHeight() + 20);
		stage.addActor(i);
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
		if(b) {
			b = false;
			stringPosition++;
			if(stringPosition > tryText.size() - 1) {
				stringPosition = tryText.size() - 1;
			} else {
				deltaText = 0;
			}
		}
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