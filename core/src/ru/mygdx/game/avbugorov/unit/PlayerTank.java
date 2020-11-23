package ru.mygdx.game.avbugorov.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.mygdx.game.avbugorov.MainTanks;
import ru.mygdx.game.avbugorov.Weapon;

public class PlayerTank extends Tank {
    int lifes;




    public PlayerTank(MainTanks game, TextureAtlas atlas) {
        super(game);
        this.weapon = new Weapon(atlas);
        this.tank =  atlas.findRegion("tank1");
        this.textureHp = atlas.findRegion("hp");
        this.position = new Vector2(100, 100);
        this.speed = 100.0f;
        this.wight = tank.getRegionWidth();
        this.height = tank.getRegionHeight();
        this.hpMax = 10;
        this.hp = this.hpMax;
        this.circle  = new Circle(position.x,position.y,(wight +height)/2);
        this.lifes = 5;
    }

    @Override
    public void destroy() {
        lifes --;
        hp = hpMax;
    }

    public void update(float dt) {

        checkMovement(dt);
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();

        rotateTurretToPoint(mx, my,dt);

        if (Gdx.input.isTouched()||Gdx.input.justTouched()) {
            fire(dt);

        }
        super.update(dt);
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * dt;
            angle = 90;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * dt;
            angle = 270;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * dt;
            angle = 180;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * dt;
            angle = 0;
            return;
        }

    }
}
