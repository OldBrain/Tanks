package ru.mygdx.game.avbugorov.unit;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.mygdx.game.avbugorov.MainTanks;
import ru.mygdx.game.avbugorov.Utils.Utils;
import ru.mygdx.game.avbugorov.Weapon;

public abstract class Tank {
    Weapon weapon;
    MainTanks game;
    float speed;

    Vector2 position;
    float angle;
    float turretAngle;
    TextureRegion tank;
    TextureRegion tower;
    TextureRegion textureHp;
    int wight;
    int height;
    float fireTimer;

    int hp;
    int hpMax;
    Circle circle;
    float pursuitPosition;

    public Tank(MainTanks game) {
        this.game = game;
    }

    public Circle getCircle() {
        return circle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void takeDamage(float damage) {
    hp -= damage;

    if (hp<0) {
        destroy();
    }
}

    public abstract void destroy();

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void render(SpriteBatch batch) {
//        batch.draw(tank, x, y);


        batch.draw(tank, position.x - wight /2, position.y - height/2, wight /2, height/2, wight, height, 1, 1, angle);
       batch.draw(weapon.getTexture(), position.x - wight /2, position.y - height/2, wight /2, height/2, wight, height, 1.5f, 1, turretAngle);

//        batch.draw(textureHp,circle.x,circle.y);

        if (hp<hpMax) {
            batch.setColor(0,0,0,1f);
            batch.draw(textureHp,position.x- wight /2-1,position.y+ height/2 +10-1,44,5.5f);
            batch.setColor(0,1,0,1f);
            batch.draw(textureHp,position.x- wight /2,position.y+ height/2 +10,(float)hp/hpMax*40,4);
            batch.setColor(1,1,1,1);
        }
    }

    public void update(float dt)
    {
        fireTimer+=dt;
        if (position.x < 0.0f) {
            position.x = 0.0f;
        }
        if (position.x> Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth();

        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        }
        if (position.y> Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight();

        }
        circle.setPosition(position);

    }

    public void fire(float dt) {
        fireTimer += dt;
        if (fireTimer>=weapon.getFirePeriod())
        {
        fireTimer = 0.0f;
        float angleRad = (float) Math.toRadians(turretAngle);
        game.getBulletEmitter().activate(this,position.x, position.y, 300.0f * (float) Math.cos(angleRad), 300.0f * (float) Math.sin(angleRad),weapon.getDamage());
    }
    }



    public void rotateTurretToPoint(float pointX, float pointY,float dt) {
        float angleTo = Utils.grtAngle(position.x, position.y, pointX, pointY);
        turretAngle = Utils.makeRotation(turretAngle, angleTo, 170.0f, dt);
        turretAngle = Utils.angleToFromNegPiToPosPi(turretAngle);
    }
}
