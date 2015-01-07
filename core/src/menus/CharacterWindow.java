package menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.orangeegames.suikorm.SuikodenRM;

import entities.GameCharacter;
import entities.PlayerStatus;
import entities.Weapon;

public class CharacterWindow {

	GameCharacter gc;
	Skin skin;
	BitmapFont font;
	
	Stage stage;
	
	Container<Table> underWindow;
	Label leftUpperWindow;
	Label rightUpperWindow;
	Array<Actor> thisWindow;
	
	int PORTRAIT_WIDTH = 56*2;
	int PORTRAIT_HEIGHT = 64*2;
	
	public CharacterWindow(Skin skin, Stage stage) {
		this.skin = skin;
		this.font = new BitmapFont(Gdx.files.internal("data/whiteFont.fnt"), false);
		this.stage = stage;

		this.thisWindow = new Array<Actor>();
	}
	
	
	public void generateWindow(GameCharacter gc) {
		this.stage.getActors().removeAll(thisWindow, true);
		this.gc = gc;
		init();
	}
	
	private void init() {
		
		int mainWidth = Gdx.graphics.getWidth();
		int mainHeight = Gdx.graphics.getHeight();
		
		LabelStyle underchat = new LabelStyle();
		underchat.background = skin.getDrawable("unchat");
		underchat.font = font;
		
		LabelStyle leftupperchat = new LabelStyle();
		leftupperchat.background = skin.getDrawable("upleftchat");
		leftupperchat.font = font;
		leftupperchat.fontColor = new Color(Color.WHITE);
		
		LabelStyle rightupperchat = new LabelStyle();
		rightupperchat.background = skin.getDrawable("uprightchat");
		rightupperchat.font = font;
		int windowWidth = mainWidth/2 - 80;
		int windowHeight = mainHeight/2 + 20;
		
		LabelStyle smallstatsstyle = new LabelStyle();
		smallstatsstyle.background = skin.getDrawable("neat9patch");
		smallstatsstyle.font = font;
		
		Table characterInformation = new Table();
		
		underWindow = new Container<Table>(characterInformation);
		
		underWindow.setHeight(windowHeight);
		underWindow.setWidth(windowWidth);
		underWindow.setX(mainWidth/2 - 20);
		underWindow.setY(mainHeight/8);
		underWindow.setBackground(skin.getDrawable("unchat"));
		
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
		
		leftUpperWindow = new Label(gc.getName(), leftupperchat);
		leftUpperWindow.setWidth(nameWidth);
		leftUpperWindow.setHeight(font.getLineHeight() + 10);
		leftUpperWindow.setX(underWindow.getX());
		leftUpperWindow.setY(underWindow.getY() + underWindow.getHeight());
		leftUpperWindow.setAlignment(Align.center + Align.bottom);
		
		rightUpperWindow = new Label("", rightupperchat);
		rightUpperWindow.setWidth(windowWidth - nameWidth);
		rightUpperWindow.setHeight(5);
		rightUpperWindow.setX(underWindow.getX() + leftUpperWindow.getWidth());
		rightUpperWindow.setY(underWindow.getY() + underWindow.getHeight());
		
		Image i = new Image(gc.getFacePicture());
		
		i.setWidth(PORTRAIT_WIDTH);
		i.setHeight(PORTRAIT_HEIGHT);
		
		i.setX(characterInformation.getX() + 270);
		i.setY(characterInformation.getY());
		
		
		//Label smallStats = new Label("", smallstatsstyle);

		Table smallStatsTable = new Table();
		smallStatsTable.setSkin(skin);
		
		Container<Table> smallStats = new Container<Table>(smallStatsTable);
		smallStats.setWidth(mainWidth/7);
		smallStats.setHeight(mainHeight/4);
		smallStats.setX(60);
		smallStats.setY(50);
		
		//smallStatsTable.setX(smallStats.getX());
		//smallStatsTable.setY(smallStats.getY());
		
		smallStats.setBackground(skin.getDrawable("neat9patch"));
		
		generateSmallData(smallStatsTable);
		
		Label strengthLabel = new Label("Strength", smallstatsstyle);
		strengthLabel.setX(60);
		strengthLabel.setY(mainHeight - 60 - strengthLabel.getHeight());
		
		stage.addActor(strengthLabel);
		stage.addActor(underWindow);
		stage.addActor(leftUpperWindow);
		stage.addActor(rightUpperWindow);
		stage.addActor(i);
		stage.addActor(smallStats);
		
		this.thisWindow.add(strengthLabel);
		this.thisWindow.add(underWindow);
		this.thisWindow.add(leftUpperWindow);
		this.thisWindow.add(rightUpperWindow);
		this.thisWindow.add(i);
		this.thisWindow.add(smallStats);
	}
	
	
	private void generateData(Table table) {
		
		PlayerStatus ps = gc.getPlayerStatus();
		
		int firstColumnWidth = 150;
		int secondColumnWidth = 100;
		
		table.row();
		
		
		table.add("Level", "fontNormal", Color.CYAN).width(firstColumnWidth).height(30);
		table.add(ps.getLEVEL() + "", "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		
		
		table.row();
		
		table.add("STR", "fontNormal", Color.GREEN).width(firstColumnWidth).height(30);
		table.add(ps.getSTRENGTH() + "", "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		
		table.add("EXP", "fontNormal", Color.GREEN).width(150).height(30);
		table.add(ps.getEXP() + "", "fontNormal", Color.WHITE).width(50).height(30);
		
		table.row();
		
		table.add("TECH", "fontNormal", Color.GREEN).width(firstColumnWidth).height(30);
		table.add(ps.getTECH() + "", "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		
		table.add("PROT", "fontNormal", Color.GREEN).width(150).height(30);
		table.add(ps.getPROT() + "", "fontNormal", Color.WHITE).width(50).height(30);
		
		table.row();
		
		table.add("MAGIC", "fontNormal", Color.GREEN).width(firstColumnWidth).height(30);
		table.add(ps.getMAGIC() + "", "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		
		table.add("M DEF", "fontNormal", Color.GREEN).width(150).height(30);
		table.add(ps.getM_DEF() + "", "fontNormal", Color.WHITE).width(50).height(30);
		
		table.row();
		
		table.add("SPEED", "fontNormal", Color.GREEN).width(firstColumnWidth).height(30);
		table.add(ps.getSPEED() + "", "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		
		table.add("LUCK", "fontNormal", Color.GREEN).width(150).height(30);
		table.add(ps.getLUCK() + "", "fontNormal", Color.WHITE).width(50).height(30);
		
		table.row();
	
		Table runes = new Table();
		runes.setSkin(skin);
		runes.setWidth(table.getWidth());
		
		if(ps.hasHeadRune()) {
			runes.add("H", "fontNormal", Color.CYAN).width(50).height(30);
			runes.add(ps.getHeadRune().getName(), "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		} else {
			runes.add("H", "fontNormal", Color.GRAY).width(50).height(30);
		}
		
		runes.row();
		
		if(ps.hasRightHandRune()) {
			runes.add("Rh", "fontNormal", Color.CYAN).width(50).height(30);
			runes.add(ps.getRightHandRune().getName(), "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		} else {
			runes.add("Rh", "fontNormal", Color.GRAY).width(50).height(30);
		}
		
		runes.row();
		
		if(ps.hasLeftHandRune()) {
			runes.add("Lh", "fontNormal", Color.CYAN).width(50).height(30);
			runes.add(ps.getLeftHandRune().getName(), "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		} else {
			runes.add("Lh", "fontNormal", Color.GRAY).width(50).height(30);
		}
		
		table.add(runes);
		
		table.row();
		
		table.add("ATT", "fontNormal", Color.GREEN).width(firstColumnWidth).height(30);
		table.add(gc.getATK() + "", "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		
		table.add("DEF", "fontNormal", Color.GREEN).width(150).height(30);
		table.add(gc.getDEF() + "", "fontNormal", Color.WHITE).width(50).height(30);
		
		table.row();
		
		
		Weapon weapon = gc.getWeapon();
		
		table.add(weapon.getNAME(), "fontNormal", Color.WHITE).width(firstColumnWidth).height(30);
		
		table.row();
		
		table.add("Level", "fontNormal", Color.CYAN).width(firstColumnWidth).height(30);
		table.add(weapon.getLEVEL() + "", "fontNormal", Color.WHITE).width(secondColumnWidth).height(30);
		
		table.add("ATT", "fontNormal", Color.CYAN).width(80).height(30);
		table.add(weapon.getATK() + "", "fontNormal", Color.WHITE).width(80).height(30);
		
		table.add(weapon.getTypeString(), "fontNormal", Color.WHITE).width(30).height(30);
		
		table.row();
		
		table.add("RUNE", "fontNormal", Color.CYAN).width(firstColumnWidth).height(30);
		
	}
	
	private void generateSmallData(Table table) {
		PlayerStatus ps = gc.getPlayerStatus();
		table.row();
		table.add("HP ", "fontNormal", Color.WHITE);
		table.add(ps.getHP() + "/" + ps.getMAX_HP(), "fontNormal", Color.WHITE);
		table.row();
		table.add("MP ", "fontNormal", Color.WHITE);
		table.add("LV ", "fontNormal", Color.WHITE);
		table.add("1 " + ps.getRUNE_1() + "/" + ps.getMAX_RUNE_1(), "fontNormal", Color.WHITE);
		table.row();
		table.add();
		table.add();
		table.add("2 " + ps.getRUNE_2() + "/" + ps.getMAX_RUNE_2(), "fontNormal", Color.WHITE);
		table.row();
		table.add();
		table.add();
		table.add("3 " + ps.getRUNE_3() + "/" + ps.getMAX_RUNE_3(), "fontNormal", Color.WHITE);
		table.row();
		table.add();
		table.add();
		table.add("4 " + ps.getRUNE_4() + "/" + ps.getMAX_RUNE_4(), "fontNormal", Color.WHITE);
		
	}
	
}
