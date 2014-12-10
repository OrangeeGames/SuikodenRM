package animations;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimation implements ApplicationListener {
	
	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 3;
	
	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	SpriteBatch spBatch;
	TextureRegion currentFrame;
	
	float stateTime;
	
	@Override
	public void create() {
		walkSheet = new Texture(Gdx.files.internal("animation_sheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for(int i = 0; i < FRAME_ROWS; i++) {
			for(int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		
		walkAnimation = new Animation(0.025f, walkFrames);
		stateTime = 0f;
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		spBatch.begin();
		spBatch.draw(currentFrame, 50, 50);
		spBatch.end();
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

}
