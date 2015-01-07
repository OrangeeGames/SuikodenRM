package entities.game.characters;

import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import entities.CharacterEquipment;
import entities.GameCharacter;
import entities.Item;
import entities.PlayerStatus;
import entities.Weapon;

public class GameKilley extends GameCharacter {

	boolean idle = true;
	float animTime;
	/*
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
	 */
	public GameKilley() {
		super();
		animTime = 0.0f;
		
		name = "Killey";
		weapon = new Weapon(new String[]{"Shadow", "Shade", "Night"}, 10, Weapon.OTHER, null);
		equipment = new CharacterEquipment(Item.CAP + Item.LIGHT_ARMOR + Item.MALE);
		
		downAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("killeyWalkDown", 1), 
				ImageCache.getFrame("killeyWalkDown", 2), 
				ImageCache.getFrame("killeyWalkDown", 3), 
				ImageCache.getFrame("killeyWalkDown", 2)});
		
		leftAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("killeyWalkLeft", 1), 
				ImageCache.getFrame("killeyWalkLeft", 2), 
				ImageCache.getFrame("killeyWalkLeft", 3), 
				ImageCache.getFrame("killeyWalkLeft", 2)});
		
		upAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("killeyWalkUp", 1), 
				ImageCache.getFrame("killeyWalkUp", 2), 
				ImageCache.getFrame("killeyWalkUp", 3), 
				ImageCache.getFrame("killeyWalkUp", 2)});
		
		TextureRegion walkRight1 = 	new TextureRegion(ImageCache.getFrame("killeyWalkLeft", 1));
		TextureRegion walkRight2 =  new TextureRegion(ImageCache.getFrame("killeyWalkLeft", 2));
		TextureRegion walkRight3 =  new TextureRegion(ImageCache.getFrame("killeyWalkLeft", 3));
		
		walkRight1.flip(true, false);
		walkRight2.flip(true, false);
		walkRight3.flip(true, false);
		
		rightAnim = new GameAnimation(0.2f, new TextureRegion[]{
				walkRight1, 
				walkRight2, 
				walkRight3, 
				walkRight2});
		
		facePicture = ImageCache.getTexture("head-killey");
		
		this.ps = new PlayerStatus(2, PlayerStatus.TYPE_CP, PlayerStatus.TYPE_CP, PlayerStatus.TYPE_C, 
				PlayerStatus.TYPE_B, PlayerStatus.TYPE_B, PlayerStatus.TYPE_B, PlayerStatus.TYPE_CP, PlayerStatus.TYPE_C);
	}
	
}
