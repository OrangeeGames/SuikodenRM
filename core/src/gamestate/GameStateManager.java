package gamestate;

import animations.ImageCache;

import com.badlogic.gdx.InputProcessor;
import com.orangeegames.suikorm.SuikodenRM;

import entities.Door;
import entities.GameCharacter;
import fighting.FightingState;

public class GameStateManager implements InputProcessor{

	public static boolean PAUSED = false;
	
	GameState[] gameState;
	SuikodenRM relation;
	
	public int currentState;
	
	public static final int NUMGAMESTATES = 3;
	public static final int LEVELSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int ATTACKSTATE = 2;
			
	public GameStateManager (SuikodenRM rel) {
		gameState = new GameState[NUMGAMESTATES];
		ImageCache.load();
		relation = rel;
		
		currentState = LEVELSTATE;
		loadState(currentState);

	}
	
	private void loadState (int state) {
		if(state == LEVELSTATE) {
			gameState[state] = new BoxWorld(new Door("inner1", 1));
		}
		if(state == ATTACKSTATE) {
			//gameState[state] = new FightingState((BoxWorld) gameState[LEVELSTATE]);
		}
	}
	
	private void unloadState(int state) {
		gameState[state] = null;
	}
	
	public void setState(int state) {
		unloadState(state);
		currentState = state;
		loadState(state);
		relation.changeScreen();
	}
	
	public void setPauseState() {
		BoxWorld oldState = (BoxWorld) gameState[currentState];
		int oldStateNumber = currentState;
		gameState[currentState].pause();
		currentState = MENUSTATE;
		PAUSED = true;
		gameState[currentState] = new InfoState(oldState, oldStateNumber, "Paused");
		relation.changeScreen();
	}
	
	public void setFightState() {
		BoxWorld oldState = (BoxWorld) gameState[currentState];
		int oldStateNumber = currentState;
		gameState[currentState].pause();
		currentState = ATTACKSTATE;
		gameState[currentState] = new FightingState(oldState, oldStateNumber);
		relation.changeScreen();
	}
	
	public void unpauseState(int state) {
		unloadState(currentState);
		currentState = state;
		PAUSED = false;
		gameState[currentState].resume();
		relation.changeScreen();
	}
	
	public void changeWorld(Door door) {
		unloadState(currentState);
		gameState[currentState] = new BoxWorld(door);
		relation.changeScreen();
	}
	
	public void setInfo(String infoString) {
		BoxWorld oldState = (BoxWorld) gameState[currentState];
		int oldStateNumber = currentState;
		gameState[currentState].pause();
		currentState = MENUSTATE;
		PAUSED = true;
		gameState[currentState] = new InfoState(oldState, oldStateNumber, infoString);
		relation.changeScreen();
	}
	
	public void setMessage(GameCharacter character) {
		BoxWorld oldState = (BoxWorld) gameState[currentState];
		int oldStateNumber = currentState;
		gameState[currentState].pause();
		currentState = MENUSTATE;
		PAUSED = true;
		gameState[currentState] = new ChatState(oldState, oldStateNumber, character);
		relation.changeScreen();
	}
	
	public void update(float delta){
		gameState[currentState].update(delta);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		gameState[currentState].keyPressed(keycode);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		gameState[currentState].keyReleased(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		gameState[currentState].touchDown(screenX, screenY, pointer, button);
		System.out.println(screenX + " : " + screenY + " : " + pointer + " : " + button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		gameState[currentState].touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public GameState getScreen() {
		return gameState[currentState];
	}
}
