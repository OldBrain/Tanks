package ru.mygdx.game.avbugorov;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {
    private TextureRegion mapTexture;
    private final int MAPX=32;
    private final int MAPY=15;
    private final int CELL_SIZE=40;

//    private final int =32;

    public Map(TextureAtlas atlas) {

        this.mapTexture = atlas.findRegion("grass");
//                new TextureRegion("grass.png");
    }
    public void render(SpriteBatch batch) {

        for (int x = 0; x < MAPX; x++) {
            for (int y = 0; y < MAPY; y++) {
                batch.draw(mapTexture,x*CELL_SIZE,y*CELL_SIZE);
            }
        }
    }

}
