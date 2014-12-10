package gamestate;

import com.badlogic.gdx.Screen;

public abstract class GameState implements Screen{
	public abstract void init();
	public abstract void update(float delta);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void touchDown(int screenX, int screenY, int pointer, int button);
	public abstract void touchUp(int screenX, int screenY, int pointer, int button);
}