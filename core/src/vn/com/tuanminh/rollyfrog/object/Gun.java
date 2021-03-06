package vn.com.tuanminh.rollyfrog.object;

import vn.com.tuanminh.frogunnerframe.box2dhelper.B2dDef;
import vn.com.tuanminh.frogunnerframe.box2dhelper.B2dDef.DefBody;
import vn.com.tuanminh.frogunnerframe.tweens.SpriteAccessor;
import vn.com.tuanminh.rollyfrog.game.Assets;
import vn.com.tuanminh.rollyfrog.utils.Const;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Gun extends BaseObject implements Obstacle {
	private float angle;

	public Gun(World world, float x, float y, float angle, EditorWorld eWorld,
			TweenManager manager) {
		super(world, x, y);
		this.angle = angle;
		eBody = new EditorBody(eWorld, "gun");
		eBody.setSpriteTextureRegion(Assets.instance.texture.gun);
		this.createObstacleBody();

		// Tweening
		eBody.getSprite().setAlpha(0);
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		Tween.to(eBody.getSprite(), SpriteAccessor.ALPHA, 0.2f).target(1)
				.start(manager);
	}

	@Override
	public void createObstacleBody() {
		B2dDef.DefBody defBd = new DefBody();
		defBd.setBodyDef(BodyType.DynamicBody, initPos);
		defBd.setFixtureDef(null, Const.SPIKE_DENSITY, Const.SPIKE_FRICTION,
				Const.SPIKE_RESTITUTION);
		defBd.bdef.angle = angle * MathUtils.degreesToRadians;
		defBd.fdef.filter.maskBits = 0;
		defBd.bdef.awake = false;
		eBody.createEditorBody(defBd.bdef, defBd.fdef);
	}

	public void update(float delta) {

	}
}
