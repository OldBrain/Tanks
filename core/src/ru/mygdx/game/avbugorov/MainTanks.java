package ru.mygdx.game.avbugorov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import ru.mygdx.game.avbugorov.unit.BotTank;
import ru.mygdx.game.avbugorov.unit.PlayerTank;
import ru.mygdx.game.avbugorov.unit.Tank;

public class MainTanks extends ApplicationAdapter {
	private SpriteBatch batch;
	private PlayerTank player;
	private float dt;
	private BulletEmitter bulletEmitter;
	private Map map;
	private BotEmitter botEmitter;
	private float gameTimer;


//	public Bullet getBullet() {
//		return bullet;
//	}


	public PlayerTank getPlayer() {
		return player;
	}

	public BulletEmitter getBulletEmitter() {
		return bulletEmitter;
	}

	@Override
	public void create() {
		TextureAtlas atlas = new  TextureAtlas("game.pak");

		batch = new SpriteBatch();
		player = new PlayerTank(this,atlas);
		bulletEmitter = new BulletEmitter(atlas);
		botEmitter = new BotEmitter(this,atlas);
		map = new Map(atlas);
	}

	@Override
	public void render() {
		dt = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0.5f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch);
		update();
		bulletEmitter.render(batch);
		player.render(batch);
		botEmitter.render(batch);
		batch.end();


	}

	private void update() {
		gameTimer += dt;
		if (gameTimer>5.0f) {
			gameTimer = 0.0f;
			botEmitter.activate(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
		}
		player.update(dt);
		bulletEmitter.update(dt);
		botEmitter.update(dt);
		checkCollisions();

	}
	public  void checkCollisions() {
		for (int i = 0; i <bulletEmitter.getBullets().length ; i++) {
			Bullet bullet = bulletEmitter.getBullets()[i];
			if (bullet.isActive()) {
				for (int j = 0; j < botEmitter.getBotTanks().length; j++) {
					BotTank bot = botEmitter.getBotTanks()[j];
					if (bot.isActive()) {
//						if (bullet.getOwner()!=bot && bot.getCircle().contains(bullet.getPosition())) {
						if (bullet.getOwner() instanceof PlayerTank && bot.getCircle().contains(bullet.getPosition())) {
							bullet.diactive();
							bot.takeDamage(bullet.getDamage());
						}
					}
				}
			}
		}
	}


	@Override
	public void dispose() {

	}
}
