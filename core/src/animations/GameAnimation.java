package animations;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameAnimation {
	
	final TextureRegion[] keyFrames;
	final float frameDuration;
	private List<GameAnimation.AnimationEventListener> listeners;
	
	@SuppressWarnings("serial")
	public class AnimationEvent extends EventObject {
		
		public AnimationEvent(Object source) {
			super(source);
		}
	}
	
	public interface AnimationEventListener {
		public void onAnimationEnded(AnimationEvent e);
	}

	public GameAnimation(float frameDuration, TextureRegion[] keyFrames) {
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
		listeners = new ArrayList<GameAnimation.AnimationEventListener>();
	}
	
	public TextureRegion getKeyFrame(float stateTime, boolean loop) {
		int frameNumber = (int) (stateTime / frameDuration);
		
		if(!loop) {
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			if(frameNumber == keyFrames.length - 1) {
				sendEvent();
			}
		}
		else {
			frameNumber = frameNumber % keyFrames.length;
		}
		
		return keyFrames[frameNumber];
		
	}
	
	public void addEventListener(GameAnimation.AnimationEventListener listener) {
		listeners.add(listener);
	}
	
	public void removeEventListener(GameAnimation.AnimationEventListener listener) {
		listeners.remove(listener);
	}
	
	private void sendEvent() {
		AnimationEvent event = new AnimationEvent(this);
		int len = listeners.size();
		for(int i = 0; i < len; i++) {
			listeners.get(i).onAnimationEnded(event);
		}
	}
}
