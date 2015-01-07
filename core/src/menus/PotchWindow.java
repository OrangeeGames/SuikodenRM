package menus;

import utilities.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class PotchWindow {

	Skin skin;
	BitmapFont font;
	
	Stage stage;
	
	Array<Actor> thisWindow;
	
	public PotchWindow(Skin skin, Stage stage) {
		this.skin = skin;
		this.font = new BitmapFont(Gdx.files.internal("data/whiteFont.fnt"), false);
		
		this.stage = stage;
		
		this.thisWindow = new Array<Actor>();
	}
	
	public void generateWindow() {
		this.stage.getActors().removeAll(thisWindow, true);
		init();
	}
	
	public void init() {
		
		int mainWidth = Gdx.graphics.getWidth();
		int mainHeight = Gdx.graphics.getHeight();
		
		LabelStyle potchWindowStyle = new LabelStyle();
		potchWindowStyle.background = skin.getDrawable("neat9patch");
		potchWindowStyle.font = font;
		potchWindowStyle.fontColor = Color.WHITE;
		
		int windowWidth = mainWidth/4;
		//int windowHeight = mainHeight/2 + 20;
		
		
		Label label1 = new Label(Manager.inventory.getPotch() + " potch", potchWindowStyle);
		
		label1.setX(mainWidth/2 + 20);
		label1.setY(7*mainHeight/8);
		//label1.setHeight(windowHeight);
		label1.setWidth(windowWidth);
		
		stage.addActor(label1);
		
		/*
		Container mainContainer = new Container<Label>(label1);
		mainContainer.setWidth(width/2);
		mainContainer.setHeight(height/3);
		mainContainer.setX(20);
		mainContainer.setY(height - 20 - height/3);
		mainContainer.setBackground(skin.getDrawable("neat9patch"));
		*/
		
		
	}
}
