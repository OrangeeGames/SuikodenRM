package menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.orangeegames.suikorm.SuikodenRM;

import entities.CharacterEquipment;
import entities.GameCharacter;
import entities.Item;

public class EquipmentWindow {
	GameCharacter gc;
	Skin skin;
	BitmapFont font;
	
	Stage stage;
	
	Container<Table> underWindow;
	Array<Actor> thisWindow;
	
	int PORTRAIT_WIDTH = 56*2;
	int PORTRAIT_HEIGHT = 64*2;
	
	ArrayList<Label> equipment;
	
	LabelStyle hoverButton;
	LabelStyle origButton;
	
	int chosenItem = 0;
	
	public EquipmentWindow(Skin skin, Stage stage) {
		this.skin = skin;
		this.font = new BitmapFont(Gdx.files.internal("data/whiteFont.fnt"), false);
		this.stage = stage;
		
		this.equipment = new ArrayList<Label>();
		this.thisWindow = new Array<Actor>();

	}
	
	
	public void generateWindow(GameCharacter gc) {
		this.stage.getActors().removeAll(thisWindow, true);
		this.equipment.clear();
		this.gc = gc;
		init();
	}
	
	private void init() {
		
		int mainWidth = Gdx.graphics.getWidth();
		int mainHeight = Gdx.graphics.getHeight();
		
		hoverButton = new LabelStyle();		
		hoverButton.background = skin.getDrawable("menuSelected");
		hoverButton.font = font;
		
		origButton = new LabelStyle();
		origButton.font = font;
		
		int windowWidth = mainWidth/2 - 80;
		int windowHeight = mainHeight/2 + 20;
		
		Table characterInformation = new Table();
		
		underWindow = new Container<Table>(characterInformation);
		
		underWindow.setHeight(windowHeight);
		underWindow.setWidth(windowWidth);
		underWindow.setX(mainWidth/2 - 20);
		underWindow.setY(mainHeight/8);
		underWindow.setBackground(skin.getDrawable("neat9patch"));
		
		characterInformation.setHeight(underWindow.getHeight() - 20);
		characterInformation.setWidth(underWindow.getWidth());
		characterInformation.align(Align.top + Align.left);
		characterInformation.setX(underWindow.getX() + 20);
		characterInformation.setY(underWindow.getY() + underWindow.getHeight() - 20);
		characterInformation.setSkin(skin);
		characterInformation.setFillParent(false);
		
		generateData(characterInformation);
		
		
		int nameWidth = (int) font.getWrappedBounds(gc.getName(),windowWidth).width + 40;
		if(SuikodenRM.debug) System.out.println(nameWidth);
		if (nameWidth < PORTRAIT_WIDTH + 10) nameWidth = PORTRAIT_WIDTH + 10;
		if(SuikodenRM.debug) System.out.println(nameWidth);
		
			
		stage.addActor(underWindow);
		
		this.thisWindow.add(underWindow);
	}
	
	
	private void generateData(Table table) {
		
		CharacterEquipment ce = gc.getEquipment();
		
		float namePos = table.getWidth() - 60;
		float equipmentPos = table.getWidth() - 40;
		
		table.left();
		
		table.row().width(namePos);
		
		
		table.add("Helmet", "fontNormal", Color.CYAN);
		table.row().width(equipmentPos);
		
		Item currentGear = ce.getHeadGear();
		Label headGear;
		if(currentGear != null) {
			headGear = new Label(currentGear.getName(), origButton);
		} else {
			headGear = new Label(" ", origButton);
		}
		headGear.setWidth(underWindow.getWidth() - 20);
		equipment.add(headGear);
		table.add(headGear);
		table.row().width(namePos);
		
		table.add("Armor", "fontNormal", Color.CYAN);
		table.row().width(equipmentPos);
		
		currentGear = ce.getBodyGear();
		Label bodyGear;
		if(currentGear != null) {
			bodyGear = new Label(currentGear.getName(), origButton);
		} else {
			bodyGear = new Label(" ", origButton);
		}
		bodyGear.setWidth(underWindow.getWidth() - 20);
		equipment.add(bodyGear);
		table.add(bodyGear);
		table.row().width(namePos);;
		
		table.add("Shield", "fontNormal", Color.CYAN);
		table.row().width(equipmentPos);
		
		currentGear = ce.getShield();
		Label shield;
		if(currentGear != null) {
			shield = new Label(ce.getShield().getName(), origButton);	
		} else {
			shield = new Label(" ", origButton);
		}
		shield.setWidth(underWindow.getWidth() - 20);
		if(ce.canUseShield()) {
			equipment.add(shield);
		}
		table.add(shield);
		
		table.row().width(namePos);
		
		
		table.add("Other", "fontNormal", Color.CYAN);
		
		table.row().width(equipmentPos);
		
		for(int i = 0; i < 3; i++) {
			currentGear = ce.getAccessory(i);
			Label other;
			if(currentGear != null) {
				other = new Label(currentGear.getName(), origButton);
			} else {
				other = new Label(" ", origButton);
			}
			other.setWidth(underWindow.getWidth() - 20);
			equipment.add(other);
			table.add(other);
			table.row().width(equipmentPos);
			
		}
	}
	
	public void enterWindow() {
		equipment.get(chosenItem).setStyle(hoverButton);
	}
	
	public void update(int k) {
		equipment.get(chosenItem).setStyle(origButton);
		
		int eqHeight = equipment.size();
		
		if(k == Keys.UP) {
			chosenItem = (chosenItem + eqHeight - 1) % eqHeight;
		}
		if(k == Keys.DOWN) {
			chosenItem = (chosenItem + 1) % eqHeight;
		}
		
		equipment.get(chosenItem).setStyle(hoverButton);

	}
	
}
