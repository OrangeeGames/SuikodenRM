package gamestate;

import java.util.ArrayList;
import java.util.Iterator;

import net.dermetfan.gdx.physics.box2d.Box2DMapObjectParser;
import utilities.CharacterGeneration;
import utilities.OrthogonalCustomRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.orangeegames.suikorm.SuikodenRM;

import entities.AbstractChest;
import entities.Door;
import entities.DrawableBox2D;
import entities.GameCharacter;
import entities.Player;
import entities.Spawn;

public class BoxWorld extends GameState {

	World world;
	protected OrthogonalCustomRenderer mapRenderer;
	Box2DDebugRenderer box2drenderer;
	OrthographicCamera camera;
	Player player;
	ArrayList<TiledMapTileLayer> foregrounds, backgrounds, objectLayers;
	ArrayList<DrawableBox2D> drawableBoxes;

	boolean disposeThis = false;
	
	Door fromDoor;
	Door nextDoor;
	public ArrayList<Door> walkableDoors;
	public ArrayList<Spawn> mapSpawns;
	public static short PLAYER = 0x0001;
	public static short DOOR = 0x0002;
	
	Door swapDoor;
	
	public BoxWorld(Door fromDoor) {
		this.fromDoor = fromDoor;
		
		foregrounds = new ArrayList<TiledMapTileLayer>();
		backgrounds = new ArrayList<TiledMapTileLayer>();
		objectLayers = new ArrayList<TiledMapTileLayer>();

		drawableBoxes = new ArrayList<DrawableBox2D>();

		walkableDoors = new ArrayList<Door>();
		
		mapSpawns = new ArrayList<Spawn>();
		
		initiate(fromDoor);
	}
	
	
	public void initiate(Door door) {
		world = new World(new Vector2(0, 0), true);
		camera = new OrthographicCamera();
		TiledMap map = new TmxMapLoader().load("maps/" + door.toMapName + ".tmx");
		Box2DMapObjectParser parser = new Box2DMapObjectParser(SuikodenRM.scale);

		parser.load(world, map);
		
		Iterator<MapLayer> mlIterator = map.getLayers().iterator();
		while(mlIterator.hasNext()) {
			MapLayer ml = mlIterator.next();
			
			String layerTypeString = ml.getName();
			System.out.println(layerTypeString);
		
			if(layerTypeString.equals("foreground")) {
				System.out.println("Test1");
				foregrounds.add((TiledMapTileLayer) ml);
			}
			if(layerTypeString.equals("background")) {
				System.out.println("Test2");
				backgrounds.add((TiledMapTileLayer) ml);
			}
			if(layerTypeString.equals("2.5")) {
				System.out.println("Test3");
				objectLayers.add((TiledMapTileLayer) ml);
			}
			if(layerTypeString.equals("chests")) {
				System.out.println("Test4");
				Iterator<MapObject> moIterator = ml.getObjects().iterator();
				while(moIterator.hasNext()) {
					RectangleMapObject mo = (RectangleMapObject) moIterator.next();
					AbstractChest ac = new AbstractChest(false, this, (mo.getRectangle().x + mo.getRectangle().width/2)*SuikodenRM.scale, (mo.getRectangle().y + mo.getRectangle().height/2)*SuikodenRM.scale);
					drawableBoxes.add(ac);
					System.out.println(mo.getRectangle().x);
					//AbstractChest ac = new AbstractChest(false, this, 180, 180);
					System.out.println("Test5");
				}
			}
			if(layerTypeString.equals("doors")) {
				System.out.println("Doors");
				Iterator<MapObject> moIterator = ml.getObjects().iterator();
				while(moIterator.hasNext()) {
					RectangleMapObject mo = (RectangleMapObject) moIterator.next();
					
					String toName = (String) mo.getProperties().get("toName");
					Integer toNumber = Integer.parseInt((String) mo.getProperties().get("toSpawnNumber"));
					System.out.println(toName);
					System.out.println(toNumber);
					PolygonShape ps = new PolygonShape();
					ps.setAsBox((mo.getRectangle().width/2)*SuikodenRM.scale, (mo.getRectangle().height/2)*SuikodenRM.scale);
					FixtureDef fixtureDoor = new FixtureDef();
					fixtureDoor.filter.categoryBits = DOOR;
					fixtureDoor.filter.maskBits = PLAYER;
					fixtureDoor.isSensor = true;
					fixtureDoor.shape = ps;
					BodyDef doorBodyDef = new BodyDef();
					doorBodyDef.position.set(new Vector2((mo.getRectangle().x + mo.getRectangle().width/2)*SuikodenRM.scale, (mo.getRectangle().y + mo.getRectangle().height/2)*SuikodenRM.scale));
					Body doorBody = world.createBody(doorBodyDef);
					
					doorBody.createFixture(fixtureDoor);
					Door newDoor = new Door(toName, toNumber);
					doorBody.setUserData(newDoor);
				}
			}
			if(layerTypeString.equals("spawns")) {
				System.out.println("Spawny Time");
				Iterator<MapObject> moIterator = ml.getObjects().iterator();
				while(moIterator.hasNext()) {
					RectangleMapObject mo = (RectangleMapObject) moIterator.next();
					
					int spawnNumber = Integer.parseInt((String) mo.getProperties().get("spawnNumber"));
					
					float x = mo.getRectangle().x*SuikodenRM.scale;
					float y = mo.getRectangle().y*SuikodenRM.scale;
					
					mapSpawns.add(new Spawn(spawnNumber, x, y));
				}
			}
			if(layerTypeString.equals("characters")) {
				System.out.println("Character Generation");
				Iterator<MapObject> moIterator = ml.getObjects().iterator();
				while(moIterator.hasNext()) {
					RectangleMapObject mo = (RectangleMapObject) moIterator.next();
					
					float x = (mo.getRectangle().x + mo.getRectangle().width/2)*SuikodenRM.scale;
					float y = (mo.getRectangle().y + mo.getRectangle().height/2)*SuikodenRM.scale;
					
					GameCharacter gc = CharacterGeneration.getCharacter((String) mo.getProperties().get("character"), this, x, y);
					gc.setMessage(Integer.parseInt((String) mo.getProperties().get("startMessage")), Integer.parseInt((String) mo.getProperties().get("stopMessage")));
					drawableBoxes.add(gc);
				}
			}
			
		}
		
		BodyDef playerDef = new BodyDef();
		playerDef.type = BodyDef.BodyType.DynamicBody;
		float playerX = 0;
		float playerY = 0;
		for(Spawn spawn : mapSpawns) {
			if(fromDoor.toSpawnNumber == spawn.spawnNumber) {
				playerX = spawn.x;
				playerY = spawn.y;
				break;
			}
		}
		playerDef.position.set(new Vector2(playerX*SuikodenRM.scale, playerY*SuikodenRM.scale));

		Body body = world.createBody(playerDef);
		
		
		Vector2[] vec = new Vector2[4];
		vec[0] = new Vector2(7.8f*SuikodenRM.scale, 7.8f*SuikodenRM.scale);
		vec[1] = new Vector2(0f*SuikodenRM.scale, 3.9f*SuikodenRM.scale);
		vec[2] = new Vector2(7.8f*SuikodenRM.scale, 0f*SuikodenRM.scale);
		vec[3] = new Vector2(15.6f*SuikodenRM.scale, 3.9f*SuikodenRM.scale);
		
		PolygonShape shape = new PolygonShape();
		shape.set(vec);

		FixtureDef fd = new FixtureDef();
		fd.filter.categoryBits = PLAYER;
		fd.shape=shape;
		body.createFixture(fd);
		shape.dispose();
		
		player = new Player(body);
		drawableBoxes.add(player);
		
		world.setContactListener(new ContactListener() {

			@Override
			public void beginContact(Contact contact) {
				if(SuikodenRM.debug) {
					System.out.println("Contact");
				}
				
				Body bodyA = contact.getFixtureA().getBody();
				Body bodyB = contact.getFixtureB().getBody();
				
				if(bodyA.getUserData() instanceof Door || bodyB.getUserData() instanceof Door) {
					Door door;
					if(bodyA.getUserData() instanceof Door) {
						door = (Door) bodyA.getUserData();
					} else {
						door = (Door) bodyB.getUserData();
					}
					disposeThis = true;
					swapDoor = door;
				}
			}
			@Override
			public void endContact(Contact contact) {}
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {}
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {}
		});
		
		mapRenderer = new OrthogonalCustomRenderer(map);
		box2drenderer = new Box2DDebugRenderer();
		box2drenderer.setDrawAABBs(true);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void dispose() {

		mapRenderer.dispose();
		box2drenderer.dispose();
		world.dispose();		
	
	}

	@Override
	public void hide() {
		if(disposeThis) {
			dispose();
		} else {
			pause();
		}
	}

	@Override
	public void pause() {
		// Do nothing, right now. Implement for Android.
	}
	

	@Override
	public void render(float delta) {
		if(!disposeThis) {
			Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
			
			if(!GameStateManager.PAUSED) {
				world.step(1 / 60f, 8, 3);
				player.update2(delta);
			}
			
			camera.zoom = 1;
			camera.position.set(player.getBody().getPosition().x + 7.9f*SuikodenRM.scale, player.getBody().getPosition().y - 50*SuikodenRM.scale, 0);
			camera.update();
	
			mapRenderer.setView(camera);
			mapRenderer.getBatch().begin();
			
			for(TiledMapTileLayer background : backgrounds) {
				mapRenderer.renderTileLayer(background);
			}
			
			mapRenderer.renderTileLayer(objectLayers, drawableBoxes);

			for(TiledMapTileLayer foreground : foregrounds) {
				mapRenderer.renderTileLayer(foreground);
			}
			
			mapRenderer.getBatch().end();
			if(SuikodenRM.debug) {
				box2drenderer.render(world, camera.combined);
			}
		} else {
			SuikodenRM.gsm.changeWorld(swapDoor);
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width/2*SuikodenRM.scale;
		camera.viewportHeight = height/2*SuikodenRM.scale;
		camera.update();
		
	}

	@Override
	public void resume() {
		Input input = Gdx.input;
		if(input.isKeyPressed(Keys.W)) {
			player.setUp(true);
		} else {
			player.setUp(false);
		}
		if(input.isKeyPressed(Keys.A)) {
			player.setLeft(true);
		} else {
			player.setLeft(false);
		}
		if(input.isKeyPressed(Keys.S)) {
			player.setDown(true);
		} else {
			player.setDown(false);
		}
		if(input.isKeyPressed(Keys.D)) {
			player.setRight(true);
		} else {
			player.setRight(false);
		}
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update(float delta) {
		if(SuikodenRM.debug) System.out.println("update");
	}

	@Override
	public void keyPressed(int k) {
		if(!disposeThis) {
			if(k == Keys.W) player.setUp(true);
			if(k == Keys.A) player.setLeft(true);
			if(k == Keys.S) player.setDown(true);
			if(k == Keys.D) player.setRight(true);
			if(k == Keys.ESCAPE) {
				SuikodenRM.gsm.setPauseState();
			}
			if(k == Keys.Q) {
				QueryCallback callback = new QueryCallback() {
					@Override
					public boolean reportFixture(Fixture fixture) {
						if(SuikodenRM.debug) {
							System.out.println("Fixture");
						}
						if(fixture.getBody().getUserData() instanceof DrawableBox2D) {
							DrawableBox2D db2d = ((DrawableBox2D) fixture.getBody().getUserData());
							db2d.interact(player);
							return true;
						}
						return true;
					}
				};
			
				float xPos = player.getBody().getPosition().x;
				float yPos = player.getBody().getPosition().y;
				float viewDistance = 5f * SuikodenRM.scale;
				if(player.faceUp()) {
					world.QueryAABB(callback, 
							xPos, 
							yPos+6f*SuikodenRM.scale, 
							xPos+15.6f*SuikodenRM.scale, 
							yPos+7.8f*SuikodenRM.scale + viewDistance);
				}
				
				else if(player.faceDown()) {
					world.QueryAABB(callback, 
							xPos, 
							yPos-viewDistance, 
							xPos+15.6f*SuikodenRM.scale, 
							yPos);
				}
				else if(player.faceLeft()) {
					world.QueryAABB(callback, 
							xPos-viewDistance, 
							yPos, 
							xPos, 
							yPos+7.8f*SuikodenRM.scale);
				}
				else if(player.faceRight()) {
					world.QueryAABB(callback, 
							xPos+15.6f*SuikodenRM.scale, 
							yPos, 
							xPos+15.6f*SuikodenRM.scale + viewDistance, 
							yPos+7.8f*SuikodenRM.scale);
				}
			}
			if(k == Keys.O) {
				SuikodenRM.debug = !SuikodenRM.debug;
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		if(!disposeThis) {
			if(k == Keys.W) player.setUp(false);
			if(k == Keys.A) player.setLeft(false);
			if(k == Keys.S) player.setDown(false);
			if(k == Keys.D) player.setRight(false);
			if(k == Keys.LEFT) camera.rotate(10f, 0, 1, 0);
			if(k == Keys.UP) camera.rotate(10f, 1, 0, 0);
			if(k == Keys.DOWN) camera.rotate(10f, -1, 0, 0);
			if(k == Keys.RIGHT) camera.rotate(10f, 0, -1, 0);
		}
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
		
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button) {
		
	}
	
	public World getWorld() {
		return world;
	}
	
}
