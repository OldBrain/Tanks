package ru.mygdx.game.avbugorov.unit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.mygdx.game.avbugorov.MainTanks;
import ru.mygdx.game.avbugorov.Utils.Direction;
import ru.mygdx.game.avbugorov.Weapon;


public class BotTank extends Tank{

    Direction preferredDirection;
    float aiTimer;
    float aiTimerTo;
    boolean active;


    public BotTank(MainTanks game, TextureAtlas atlas) {
        super(game);
        this.weapon = new Weapon(atlas);
        this.tank = atlas.findRegion("tank");
        this.textureHp = atlas.findRegion("hp");
        this.position = new Vector2(200, 200);
        this.speed = 100.0f;
        this.wight = tank.getRegionWidth();
        this.height = tank.getRegionHeight();
        this.hpMax = 3;
        this.hp = this.hpMax;
        this.aiTimerTo = 3.0f;
        this.preferredDirection = Direction.UP;
        this.pursuitPosition = 300.0f;
        this.circle  =new Circle(position.x,position.y,(wight +height)/2);
//        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void activate(float x,float y) {
        hpMax = 3;
        hp = hpMax;
        preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length-1)];
        angle = preferredDirection.getAngle();
        position.set(x, y);
        aiTimer = 0.0f;
        active = true;
    }

    @Override
    public void destroy() {
        active=false;
    }

    public void update(float dt) {
        aiTimer += dt;
        if (aiTimer>=aiTimerTo) {
            aiTimer = 0;
            aiTimerTo = MathUtils.random(2.0f, 4.0f);
            preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length-1)];
            angle = preferredDirection.getAngle();
        }
        position.add(speed * preferredDirection.getVx()*dt, speed * preferredDirection.getVy()*dt);

        float dst = this.position.dst(game.getPlayer().getPosition());
        if (dst<pursuitPosition) {
            rotateTurretToPoint(game.getPlayer().getPosition().x,game.getPlayer().getPosition().y,dt);
            fire(dt);
        }
            if (Gdx.input.isTouched()||Gdx.input.justTouched()) {

        }
        super.update(dt);

    }
}
