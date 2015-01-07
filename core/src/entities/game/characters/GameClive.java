package entities.game.characters;

import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import entities.CharacterEquipment;
import entities.GameCharacter;
import entities.Item;
import entities.PlayerStatus;
import entities.Weapon;

public class GameClive extends GameCharacter {

	boolean idle = true;
	float animTime;
	
	GameAnimation fightingIdleAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("cliveFightingIdle", 1),
			ImageCache.getFrame("cliveFightingIdle", 2),
			ImageCache.getFrame("cliveFightingIdle", 3),
			ImageCache.getFrame("cliveFightingIdle", 4)
	});
	
	GameAnimation fightingAttackAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("cliveFightingAttack", 1),
			ImageCache.getFrame("cliveFightingAttack", 2),
			ImageCache.getFrame("cliveFightingAttack", 3),
			ImageCache.getFrame("cliveFightingAttack", 4),
			ImageCache.getFrame("cliveFightingAttack", 5),
			ImageCache.getFrame("cliveFightingAttack", 6),
			ImageCache.getFrame("cliveFightingAttack", 7),
			ImageCache.getFrame("cliveFightingAttack", 8),
			ImageCache.getFrame("cliveFightingAttack", 6),
			ImageCache.getFrame("cliveFightingAttack", 5),
			ImageCache.getFrame("cliveFightingAttack", 4),
			ImageCache.getFrame("cliveFightingAttack", 3),
			ImageCache.getFrame("cliveFightingAttack", 2),
			ImageCache.getFrame("cliveFightingAttack", 1)
	});

	public GameClive() {
		super();
		animTime = 0.0f;
		
		name = "Clive";
		weapon = new Weapon(new String[]{"Wind", "Storm", "Tornado"}, 14, Weapon.DARTS, null);
		equipment = new CharacterEquipment(Item.CAP + Item.LIGHT_ARMOR + Item.MALE);
		
		downAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveWalkDown", 1), 
				ImageCache.getFrame("cliveWalkDown", 2), 
				ImageCache.getFrame("cliveWalkDown", 3), 
				ImageCache.getFrame("cliveWalkDown", 2)});
		
		leftAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveWalkLeft", 1), 
				ImageCache.getFrame("cliveWalkLeft", 2), 
				ImageCache.getFrame("cliveWalkLeft", 3), 
				ImageCache.getFrame("cliveWalkLeft", 2)});
		
		upAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveWalkUp", 1), 
				ImageCache.getFrame("cliveWalkUp", 2), 
				ImageCache.getFrame("cliveWalkUp", 3), 
				ImageCache.getFrame("cliveWalkUp", 2)});
		
		TextureRegion walkRight1 = 	new TextureRegion(ImageCache.getFrame("cliveWalkLeft", 1));
		TextureRegion walkRight2 =  new TextureRegion(ImageCache.getFrame("cliveWalkLeft", 2));
		TextureRegion walkRight3 =  new TextureRegion(ImageCache.getFrame("cliveWalkLeft", 3));
		
		walkRight1.flip(true, false);
		walkRight2.flip(true, false);
		walkRight3.flip(true, false);
		
		rightAnim = new GameAnimation(0.2f, new TextureRegion[]{
				walkRight1, 
				walkRight2, 
				walkRight3, 
				walkRight2});
		
		facePicture = ImageCache.getTexture("head-clive");
		
		this.ps = new PlayerStatus(5, PlayerStatus.TYPE_CP, PlayerStatus.TYPE_S, PlayerStatus.TYPE_DP, PlayerStatus.TYPE_BP, 
				PlayerStatus.TYPE_C, PlayerStatus.TYPE_CP, PlayerStatus.TYPE_DP, PlayerStatus.TYPE_C);
	}
	
}
