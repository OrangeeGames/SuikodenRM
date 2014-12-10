package fighting;

import gamestate.BoxWorld;
import gamestate.GameState;
import animations.ImageCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class FightingState extends GameState{

	Skin skin;
	BitmapFont font;
	Stage stage;
	Table table1, table2, table3, mainTable;
	
	int[] monsters;
	BoxWorld bw;
	int oldStateNumber;
	
	public FightingState(BoxWorld bw, int oldStateNumber) {
		skin = new Skin();
		// TODO Auto-generated method stub
		font = new BitmapFont(Gdx.files.internal("data/whiteFont.fnt"), false);
		skin.addRegions(ImageCache.atlas);
		this.oldStateNumber = oldStateNumber;
		this.bw = bw;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		bw.render(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		bw.resize(width, height);

		stage = new Stage(new ExtendViewport(width, height));
		
		stage.clear();
		
		table1 = new Table();
		table1.setFillParent(false);

		table1.setHeight(1*height/10);
		table1.setWidth(width);
		
		table2 = new Table();
		table2.setFillParent(false);

		table2.setHeight(7*height/10);
		table2.setWidth(width);
		
		table3 = new Table();
		table3.setFillParent(false);

		table3.setHeight(2*height/10);
		table3.setWidth(width);
		
		mainTable = new Table();
		mainTable.setFillParent(false);
		mainTable.setHeight(height);
		mainTable.setWidth(width);
		/**
		 * Add the top with the monsters
		 */
		TextButtonStyle style = new TextButtonStyle();
		
		style.up = skin.getDrawable("buttondown");
		style.down = skin.getDrawable("buttondown");
		style.font = font;
		
		for(int i = 0; i < 6; i++) {
			TextButton button;
			
			button = new TextButton("Monster " + i, style);
			button.setWidth(width/6);
			button.setHeight(height/10);
			table1.add(button).width(width/6).height(height/10);
		}
		
		TextButton attk = new TextButton("Attack", style);
		attk.setWidth(width / 3);
		attk.addListener(new ClickListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				bw.keyPressed(Keys.D);
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				bw.keyReleased(Keys.D);
			}
			
		});
		TextButton auto = new TextButton("Auto Attack", style);
		attk.setWidth(width / 3);
		TextButton flee = new TextButton("Flee", style);
		attk.setWidth(width / 3);
		
		table3.add(attk).width(width/3).height(2*height/10);
		table3.add(auto).width(width/3).height(2*height/10);
		table3.add(flee).width(width/3).height(2*height/10);
		
		mainTable.row().height(height/10).width(width).top().fill();
		mainTable.add(table1).fill();
		mainTable.row().height(7*height/10).fill();
		mainTable.add(table2).fill();
		mainTable.row().fillX();
		mainTable.add(table3);
		
		stage.addActor(mainTable);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		
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
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void keyPressed(int k) {
	
	}

	@Override
	public void keyReleased(int k) {
		
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
