package menus;

import entities.GameCharacter;
import gamestate.BoxWorld;
import gamestate.GameState;

import java.util.ArrayList;

import utilities.Manager;
import animations.ImageCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.orangeegames.suikorm.SuikodenRM;

public class MenuTestState extends GameState {

	private static int MAINWINDOW = 0;
	private static int ITEMWINDOW = 1;
	private static int STRWINDOW = 2;
	private static int EQWINDOW = 3;
	
	private static int CHOSENEQ = 4;
	
	private BoxWorld levelState;
	private BitmapFont font;
	private Skin skin;
	private SpriteBatch spriteBatch;
	private int returnState;
	
	Container<Table> mainContainer;
	Container<Table> itemContainer;
	Container<Table> strengthContainer;
	LabelStyle origButton;
	LabelStyle hoverButton;
	
	int windowChooser;
	
	int itemWindowRow = 0;
	int itemWindowColumn = 0;
	int mainWindowRow = 0;
	int mainWindowColumn = 0;
	int strengthWindowRow = 0;
	
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
	
	CharacterWindow cw;
	EquipmentWindow ew;
	ItemMenu im;
	
	Image characterImage;
	
	public MenuTestState(BoxWorld ls, int state, String infoString) {
		windowChooser = MAINWINDOW;
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
		
		hoverButton = new LabelStyle();
		
		//style.up = skin.getDrawable("neat9patch");
		hoverButton.background = skin.getDrawable("menuSelected");
		//hoverButton.down = skin.getDrawable("neat9patch");
		hoverButton.font = font;
		
		origButton = new LabelStyle();
		origButton.font = font;

		
		Table table1 = new Table();
		table1.setFillParent(false);

		table1.setHeight(height/3);
		table1.setWidth(width/3);
		
		
		mainContainer = new Container<Table>(table1);
		mainContainer.setWidth(width/2);
		mainContainer.setHeight(height/3);
		mainContainer.setX(20);
		mainContainer.setY(height - 20 - height/3);
		mainContainer.setBackground(skin.getDrawable("neat9patch"));
		
		table1.pad(10);
		table1.row();

		Label items = new Label("Item", origButton);
		//Label items = new Label("Atem", suikoButton);
		table1.add(items).width((mainContainer.getWidth()-40)/2).height((mainContainer.getHeight()-20)/3).pad(3);
		
		Label strength = new Label("Strength", origButton);
		table1.add(strength).width((mainContainer.getWidth()-40)/2).height((mainContainer.getHeight()-20)/3).pad(3);
		
		table1.row();
		
		Label runes = new Label("Rune", origButton);
		table1.add(runes).width((mainContainer.getWidth()-40)/2).height((mainContainer.getHeight()-20)/3).pad(3);
		
		Label formation = new Label("Formation", origButton);
		table1.add(formation).width((mainContainer.getWidth()-40)/2).height((mainContainer.getHeight()-20)/3).pad(3);
		
		table1.row();
		
		Label equipments = new Label("Equipment", origButton);
		table1.add(equipments).width((mainContainer.getWidth()-40)/2).height((mainContainer.getHeight()-20)/3).pad(3);
		
		Label settings = new Label("Settings", origButton);
		table1.add(settings).width((mainContainer.getWidth()-40)/2).height((mainContainer.getHeight()-20)/3).pad(3);
		
		stage.addActor(mainContainer);
		
		
		// Item Container
		
		Table table2 = new Table();
		table2.setFillParent(false);

		table2.setHeight(height/3);
		table2.setWidth(width/3);
		
		
		itemContainer = new Container<Table>(table2);
		itemContainer.setWidth(width/9*4);
		itemContainer.setHeight(height/9*5);
		itemContainer.setX(20);
		itemContainer.setY(mainContainer.getY() - 20 - itemContainer.getHeight());
		itemContainer.setBackground(skin.getDrawable("neat9patch"));
		
		table2.row();

		Label button1 = new Label("Party's", origButton);
		table2.add(button1).width(itemContainer.getWidth()/2 - 20).height(40);
		
		Label button2 = new Label("Special", origButton);
		table2.add(button2).width(itemContainer.getWidth()/2 - 20).height(40);
		
		for(int i = 0; i < Manager.party.size(); i++) {
			if(i % 2 == 0) {
				table2.row();
			}
			GameCharacter gc = Manager.party.getCharacter(i);
			
			Label button = new Label(gc.getName() + "\n" + gc.getPlayerStatus().getHP() + "/" + gc.getPlayerStatus().getMAX_HP() + "\n" + gc.getPlayerStatus().showRemainingRune(), origButton);
			
			table2.add(button).width((itemContainer.getWidth()-40)/2).height(((itemContainer.getHeight() - 60))/3);

		}
		
		stage.addActor(itemContainer);
		
		
		
		((Label) mainContainer.getActor().getChildren().items[0]).setStyle(hoverButton);
		
		// Strength Window
		Table strengthTable = new Table();
		strengthTable.setFillParent(false);

		strengthTable.setHeight(height/3);
		strengthTable.setWidth(width/3);
		
		strengthContainer = new Container<Table>(strengthTable);
		strengthContainer.setWidth(width/4);
		strengthContainer.setHeight(260);
		strengthContainer.setX(60);
		strengthContainer.setY(height/2 - strengthContainer.getHeight()/2);
		strengthContainer.setBackground(skin.getDrawable("neat9patch"));
		
		for(int i = 0; i < Manager.party.size(); i++) {
			strengthTable.row();
			
			GameCharacter gc = Manager.party.getCharacter(i);
			
			Label button = new Label(gc.getName(), origButton);
			
			strengthTable.add(button).width(strengthContainer.getWidth() - 20).height((strengthContainer.getHeight()-20)/6);

		}
		
		

		
		// Character Window
		cw = new CharacterWindow(skin, stage);
		// Item Window
		im = new ItemMenu();
		// Equipment Window
		ew = new EquipmentWindow(skin, stage);
		characterImage = new Image();
		characterImage.setWidth(PORTRAIT_WIDTH);
		characterImage.setHeight(PORTRAIT_HEIGHT);
		characterImage.setX(itemContainer.getX() + itemContainer.getWidth() - PORTRAIT_WIDTH - 20);
		characterImage.setY(itemContainer.getY() + itemContainer.getHeight() - 20);
		PotchWindow pw = new PotchWindow(skin, stage);
		pw.generateWindow();
	}

	@Override
	public void show() {
		skin = new Skin();
		font = new BitmapFont(Gdx.files.internal("data/whiteFont.fnt"), false);
		
		skin.addRegions(ImageCache.atlas);
		skin.add("fontNormal", font);
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
		if(windowChooser == MAINWINDOW) {
			updateMainMenu(k);
		}
		else if(windowChooser == ITEMWINDOW) {
			updateItemMenu(k);
		} else if(windowChooser == STRWINDOW) {
			updateStrengthMenu(k);
		} else if(windowChooser == CHOSENEQ) {
			ew.update(k);
		}
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
		stage.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button) {
		stage.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public void touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateMainMenu(int k) {
		((Label) mainContainer.getActor().getChildren().items[mainWindowRow*2 + mainWindowColumn]).setStyle(origButton);
		
		if(k == Keys.LEFT) {
			mainWindowColumn = (mainWindowColumn + 1) % 2;
		}
		if(k == Keys.RIGHT) {
			mainWindowColumn = (mainWindowColumn + 1) % 2;
		}
		if(k == Keys.UP) {
			mainWindowRow = (mainWindowRow + 3 - 1) % 3;
		}
		if(k == Keys.DOWN) {
			mainWindowRow = (mainWindowRow + 3 + 1) % 3;
		}
		
		int choice = mainWindowRow * 2 + mainWindowColumn;
		System.out.println(choice);
		if(k == Keys.ENTER) {
			System.out.println(choice);
			if(choice == 0) {
				mainContainer.remove();
				((Label) itemContainer.getActor().getChildren().items[0]).setStyle(hoverButton);
				windowChooser = ITEMWINDOW;
			} else if(choice == 1) {
				mainContainer.remove();
				itemContainer.remove();
				((Label) strengthContainer.getActor().getChildren().items[0]).setStyle(hoverButton);
				cw.generateWindow(Manager.party.getCharacter(0));
				stage.addActor(strengthContainer);
				windowChooser = STRWINDOW;
			}
		} else {
			((Label) mainContainer.getActor().getChildren().items[choice]).setStyle(hoverButton);
		}
		
	}
	
	public void updateItemMenu(int k) {
		int lastChoice = itemWindowRow*2 + itemWindowColumn;
		if(lastChoice >= Manager.party.size() + 2) lastChoice = 0;
		
		((Label) itemContainer.getActor().getChildren().items[lastChoice]).setStyle(origButton);
		
		int partyHeight = (int) (Math.ceil(((float) Manager.party.size()/2)) + 1);
		
		if(k == Keys.LEFT) {
			itemWindowColumn = (itemWindowColumn + 1) % 2;
		}
		if(k == Keys.RIGHT) {
			itemWindowColumn = (itemWindowColumn + 1) % 2;
		}
		if(k == Keys.UP) {
			itemWindowRow = (itemWindowRow + partyHeight - 1) % partyHeight;
		}
		if(k == Keys.DOWN) {
			itemWindowRow = (itemWindowRow + 1) % partyHeight;
		}
		
		int choice = (itemWindowRow * 2 + itemWindowColumn);
		if(choice >= Manager.party.size() + 2) choice = 0;
		
		characterImage.remove();
		if(choice >= 2) {
			characterImage.setDrawable(new TextureRegionDrawable(Manager.party.getCharacter(choice-2).getFacePicture()));
			stage.addActor(characterImage);
		}
		System.out.println(choice);
		if(k == Keys.ENTER) {
			if(choice == 0) {
				windowChooser = MAINWINDOW;
			}
			if(choice >= 2) {
				ew.enterWindow();
				windowChooser = CHOSENEQ;	
			}
		} else {
			((Label) itemContainer.getActor().getChildren().items[choice]).setStyle(hoverButton);
			if(choice >= 2) {
				ew.generateWindow(Manager.party.getCharacter(choice-2));
			}
		}
	}
	
	public void updateStrengthMenu(int k) {
		((Label) strengthContainer.getActor().getChildren().items[strengthWindowRow]).setStyle(origButton);
		
		int partyHeight = Manager.party.size();
		
		if(k == Keys.UP) {
			strengthWindowRow = (strengthWindowRow + partyHeight - 1) % partyHeight;
		}
		if(k == Keys.DOWN) {
			strengthWindowRow = (strengthWindowRow + 1) % partyHeight;
		}
		
		((Label) strengthContainer.getActor().getChildren().items[strengthWindowRow]).setStyle(hoverButton);
		
		cw.generateWindow(Manager.party.getCharacter(strengthWindowRow));
	}
}
