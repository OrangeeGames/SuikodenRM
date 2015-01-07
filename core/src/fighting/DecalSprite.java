package fighting;

import animations.ImageCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;

public class DecalSprite {

    public Decal sprite;

    public DecalSprite() {
            // constructor
    }

    public DecalSprite build(String imgPath) {
            TextureWrap texWrap = Texture.TextureWrap.ClampToEdge; // default
            return build(imgPath, texWrap);
    }

    public DecalSprite build(String imgPath, TextureWrap texWrap) {
            TextureRegion tr = ImageCache.getTexture(imgPath);
            float w = tr.getRegionWidth();
            float h = tr.getRegionHeight();
            sprite = Decal.newDecal(w, h, new TextureRegion(tr), true);
            return this;
    }

    public void faceCamera(Camera oCam) {
            sprite.lookAt(oCam.position.cpy(), oCam.up.cpy().nor());
    }

    public void update(float delta) {
            // sprite.setRotation(dir, up)
    }
}
