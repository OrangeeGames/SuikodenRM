package fighting;

import fighting.characters.FighterClive;
import fighting.characters.FighterHighlander;
import fighting.characters.FighterKilley;
import gamestate.GameState;
import gamestate.GameStateManager;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.decals.SimpleOrthoGroupStrategy;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.orangeegames.suikorm.SuikodenRM;

public class FightingTestState extends GameState{
	DecalBatch batch;
	Array<Decal> decals = new Array<Decal>();
	Array<DecalSprite> decalSprites = new Array<DecalSprite>();
	PerspectiveCamera camera;
	FPSLogger logger = new FPSLogger();
	float prevX = 0;
	float prevY = 0;
	float scale;
	Array<Fighter> party = new Array<Fighter>();
	Array<Fighter> enemy = new Array<Fighter>();
	Vector3 dir = new Vector3();
	CameraGroupStrategy cgs;
	SimpleOrthoGroupStrategy sogs;
	class DecalComparator implements Comparator<Decal> {

		@Override
		public int compare(Decal arg0, Decal arg1) {
			return Float.compare(arg1.getPosition().x, arg0.getPosition().x);
		}
		
	}
	
	public FightingTestState() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		scale = ((float)height)/((float)width);
		camera = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.near = 1;
		camera.far = 300;
		camera.position.set(0.1f, 0.1f, 5);
		cgs = new CameraGroupStrategy(camera);
		sogs = new SimpleOrthoGroupStrategy();
		
		batch = new DecalBatch(cgs);
		
		
		party.add(new FighterKilley(1.3f, -0.7f, -0.01f, 6));
		party.add(new FighterKilley(2f, -0.6f, -0.01f, 6));
		party.add(new FighterClive(2.7f, -0.5f, -0.01f, 6));
		party.add(new FighterClive(1.7f, -1.2f, -0.01f, 5));
		party.add(new FighterClive(2.4f, -1.1f, -0.01f, 5));
		party.add(new FighterClive(3.1f, -1.0f, -0.01f, 5));
		
		enemy.add(new FighterHighlander(0.2f, 0.3f, -0.01f, 3));
		enemy.add(new FighterHighlander(-0.5f, 0.2f, -0.01f, 3));
		enemy.add(new FighterHighlander(-1.2f, 0.1f, -0.01f, 3));
		enemy.add(new FighterHighlander(-0.2f, 0.8f, -0.01f, 4));
		enemy.add(new FighterHighlander(-0.9f, 0.7f, -0.01f, 4));
		enemy.add(new FighterHighlander(-1.6f, 0.6f, -0.01f, 4));
		
		DecalSprite bg = new DecalSprite().build("squares");

        bg.sprite.setPosition(-8, 10, -10);
		bg.sprite.rotateX(45);

		//bg.sprite.rotateZ(45);
		bg.sprite.rotateY(45);
		//bg.sprite.rotateZ(10);
        bg.sprite.setDimensions(20, 20);
		decalSprites.add(bg);
		

		DecalSprite bg2 = new DecalSprite().build("squares");
        bg2.sprite.rotateX(45);
		bg2.sprite.rotateX(90);
		bg2.sprite.rotateZ(45);
        bg2.sprite.setDimensions(20, 20);
        bg2.sprite.setPosition(0, 0, -10);
		decalSprites.add(bg2);
		
		

		DecalSprite bg3 = new DecalSprite().build("squares");
		bg3.sprite.rotateZ(45);
		bg3.sprite.rotateY(90);
		bg3.sprite.rotateY(45);
		//bg3.sprite.rotateX(45);
        bg3.sprite.setDimensions(20, 20);
        bg3.sprite.setPosition(5, 5, -10);
		decalSprites.add(bg3);
	}
		
	
	public void render (float delta) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glEnable(GL30.GL_DEPTH_TEST);
		//camera.rotate(1, 0, 1, 0);
		camera.update();
		for(int i = 0; i < decalSprites.size; i++) {
			batch.add(decalSprites.get(i).sprite);
		}
		
		for (int i = 0; i < decals.size; i++) {
			Decal decal = decals.get(i);
			//if (billboard) {
			// billboarding for ortho cam :)
			//dir.set(-camera.direction.x, -camera.direction.y, -camera.direction.z);
			//decal.setRotation(dir, Vector3.Y);
			decal.lookAt(camera.position, camera.up);
			//}
			batch.add(decal);
		}
		Array<Decal> fighters = new Array<Decal>();
		for(int i = 0; i < enemy.size; i++) {
			Fighter fighter = enemy.get(i);
			fighter.update(delta);
			Decal decal = Decal.newDecal(fighter.getTextureWidth()*scale/45.0f, fighter.getTextureHeight()*scale/45.0f, fighter.getTextureRegion(), true);
			decal.setPosition(fighter.pos);
			decal.lookAt(camera.position, camera.up);
			decal.value = fighter.decalBatchNo;
			
			batch.add(decal);
			sogs.decideGroup(decal);
			fighters.add(decal);
			
		}
		cgs.beforeGroup(1, fighters);
		Array<Decal> parties = new Array<Decal>();
		for(int i = 0; i < party.size; i++) {
			Fighter fighter = party.get(i);
			fighter.update(delta);
			Decal decal = Decal.newDecal(fighter.getTextureWidth()*scale/45.0f, fighter.getTextureHeight()*scale/45.0f, fighter.getTextureRegion(), true);
			decal.setPosition(fighter.pos);
			decal.lookAt(camera.position, camera.up);
			decal.value = fighter.decalBatchNo;
			//Decal decal2 = Decal.newDecal(fighter.getTextureHeight()*scale/44.0f, fighter.getTextureHeight()*scale/44.0f, fighter.getTextureRegion(), true);
			//decal2.setColor(0, 0, 0, 0.5f);
			//decal2.setPosition(fighter.pos);
			//decal2.lookAt(camera.position, camera.up);
			batch.add(decal);
			//batch.add(decal2);
			sogs.decideGroup(decal);
			parties.add(decal);
		}
		cgs.beforeGroup(2, parties);
		batch.flush();
		logger.log();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int k) {
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		if(k == Keys.NUM_1) {
			party.get(1).attackMode(enemy.get(1).origPos);
		}
		if(k == Keys.NUM_3) {
			party.get(3).attackMode(enemy.get(1).origPos);
		}
		if(k == Keys.NUM_6) {
			Vector3 attkPos = party.get(5).origPos.cpy();
			attkPos.add(-0.6f, 1f, 0.0f);
			enemy.get(1).attackMode(attkPos);
		}
		if(k == Keys.ESCAPE) {
			SuikodenRM.gsm.unpauseState(GameStateManager.LEVELSTATE);
		}
		if(k == Keys.Z) {
			camera.fieldOfView -= 10;
		}
		if(k == Keys.M) {
			camera.project(new Vector3(camera.position.x - 1.0f, camera.position.y, camera.position.z));
		}
		if(k == Keys.J) {
			camera.lookAt(enemy.get(0).origPos);
		}
		if(k == Keys.K) {
			camera.lookAt(0, 0, 0);
		}
		if(k == Keys.L) {
			camera.lookAt(party.get(0).origPos);
		}
		if(k == Keys.X) {
			camera.fieldOfView += 10;
		}
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		System.out.println(screenX % enemy.size + " " + screenY % party.size);
		enemy.get(screenX % enemy.size).attackMode(party.get(screenY % party.size).origPos);
		
	}

	@Override
	public void touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		float yDiff = screenY - prevY;
		float xDiff = screenX - prevX;
		float angle = screenX < prevX ? 1f : -1f;
		camera.rotate(angle, xDiff/ (float) Gdx.graphics.getWidth(), yDiff/ (float) Gdx.graphics.getHeight(), 1);
		//camera.rotate(angle, 0, yDiff/Gdx.graphics.getHeight(), 0);
		
		prevY = screenY;
		prevX = screenX;
	}
}
