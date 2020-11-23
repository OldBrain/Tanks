package ru.mygdx.game.avbugorov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.mygdx.game.avbugorov.unit.Tank;

public class Bullet {
    private Tank owner;
    private boolean active;
    private float speed;
    //    private Texture bullet;
    private Vector2 position;
    private Vector2 velocity;
    public float damage;

    public float getDamage() {
        return damage;
    }

    public Bullet() {
//        this.bullet = new Texture("bullet.png");
        this.velocity = new Vector2();
        this.position = new Vector2();
        this.active = false;
        this.damage = 1.0f;


    }

    public Tank getOwner() {
        return owner;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    //    public void render(SpriteBatch batch) {
//        batch.draw(bullet,position.x-8,position.y-8);
//    }
    public void diactive() {
        active = false;
    }

    public void active(Tank owner,float x, float y, float vx, float vy,int damage) {
        this.owner = owner;
        this.active = true;
        this.position.set(x, y);
        this.velocity.set(vx, vy);

    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0 || position.x > Gdx.graphics.getWidth() || position.y < 0 || position.y > Gdx.graphics.getHeight()) {
            diactive();
        }
    }
}


