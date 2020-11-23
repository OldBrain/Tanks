package ru.mygdx.game.avbugorov;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class Weapon {
    TextureRegion texture;
    int damage;
    float fireTimer;
    float firePeriod;

    public TextureRegion getTexture() {
        return texture;
    }

    public int getDamage() {
        return damage;
    }

    public float getFireTimer() {
        return fireTimer;
    }

    public float getFirePeriod() {
        return firePeriod;
    }

    public Weapon(TextureAtlas atlas) {
        this.texture = atlas.findRegion("tower1");
//                new Texture("tower1.png");
        this.damage = 1;
        this.fireTimer = 0.0f;
        this.firePeriod = 0.4f;

    }
}
