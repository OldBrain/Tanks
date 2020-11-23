package ru.mygdx.game.avbugorov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.mygdx.game.avbugorov.unit.BotTank;

public class BotEmitter {
//    Texture botTexture;
    private BotTank[] botTanks;
    public static final int MAX_BOT_COUNT =50;




    public BotTank[] getBotTanks() {
        return botTanks;
    }

    public BotEmitter(MainTanks game, TextureAtlas atlas) {
//        this.botTexture = new Texture("tank.png");
        this.botTanks= new BotTank[MAX_BOT_COUNT];

        for (int i = 0; i < botTanks.length; i++) {
            this.botTanks[i] = new BotTank(game,atlas);

        }

    }
    public void activate(float x, float y) {
        for (int i = 0; i < botTanks.length; i++) {
            if (!botTanks[i].isActive()) {
                botTanks[i].activate(x,y);
                break;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < botTanks.length; i++) {
            if (botTanks[i].isActive()) {
//                batch.draw(bulletTexture, bullets[i].getPosition().x - 8, bullets[i].getPosition().y - 8);
            botTanks[i].render(batch);
            }
        }
    }
    public void update(float dt) {

        for (int i = 0; i < botTanks.length; i++) {
            if (botTanks[i].isActive() ) {
                botTanks[i].update(dt);
            }
        }
    }

}
