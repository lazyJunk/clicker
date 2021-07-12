package lazy.clicker.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import lazy.clicker.logic.*;
import lazy.clicker.api.*;
import com.badlogic.gdx.audio.*;
import lazy.clicker.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.files.*;
import java.nio.file.*;
import com.badlogic.gdx.utils.*;
import lazy.clicker.utils.*;
import lazy.clicker.effect.*;
import lazy.clicker.implementation.*;

public class PlayScreen extends ScreenAdapter
{

	private final SpriteBatch spriteBatch = new SpriteBatch();
	private final SpriteBatch uiBatch = new SpriteBatch();
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private BitmapFont fontRenderer;

	private final float width = 20;
	private final float ppu = Gdx.graphics.getWidth() / width;
	private final float height = Gdx.graphics.getHeight() / ppu;

	private String text = "";

	private final OrthographicCamera cam = new OrthographicCamera(width, height);

	private IClicker randomClicker;

	private long numClicks = GameData.numClicks;

	private Sound clickSound;
	
	private FreeTypeFontGenerator generator;
	
	private Sprite sound = new Sprite(new Texture("soundoff.png"));
	
	private ParticleSystem system = new ParticleSystem();
	
	public PlayScreen(){
		this.generator = new FreeTypeFontGenerator(Gdx.files.internal("font/regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 36;
		parameter.color = Core.currentScheme.textColor;
		this.fontRenderer = this.generator.generateFont(parameter);
	}

	@Override
	public void show()
	{
		cam.position.set(width / 2, height / 2, 0);
		cam.update();
		
		randomClicker = Register.get("better");
		this.clickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
	
		this.sound.setSize(2.5f, 2.5f);
		this.sound.setPosition(cam.viewportWidth - 3, .5f);
	}

	@Override
	public void render(float delta)
	{
		ScreenUtils.clear(Core.currentScheme.primaryColor);

		this.cam.update();
		this.system.update(delta);
		
		if(Gdx.input.justTouched()){
			Rectangle area = new Rectangle(this.cam.viewportWidth - 3f, .5f, 2.5f, 2.5f);
			if(Utils.clickedArea(this.cam, area)){
				GameData.clickSound = !GameData.clickSound;
				GameData.save();
			}
		}
		
		this.sound.setTexture(!GameData.clickSound ? new Texture("soundoff.png"): new Texture("soundon.png"));
		
		this.shapeRenderer.setProjectionMatrix(this.cam.combined);
		this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		this.clicked();
		this.shapeRenderer.rect(cam.viewportWidth / 2 - 5, cam.viewportHeight / 2 - 5, 10, 10);
		this.shapeRenderer.setColor(GameData.clickSound ? Color.GREEN : Color.RED);
		this.shapeRenderer.end();

		this.spriteBatch.begin();
		this.spriteBatch.setProjectionMatrix(this.cam.combined);
		this.sound.draw(this.spriteBatch);
		this.spriteBatch.end();
	
		this.uiBatch.begin();
		this.fontRenderer.draw(this.uiBatch, "HinumClicks: " + GameData.numClicks, 30, Gdx.graphics.getHeight() - 30);
		this.system.render(this.uiBatch);
		this.fontRenderer.draw(this.uiBatch, "numClicks: " + numClicks, 30, Gdx.graphics.getHeight() - 70);
		text = Gdx.input.getX() + " | " + Gdx.input.getY();
		this.fontRenderer.draw(this.uiBatch, Gdx.graphics.getFramesPerSecond() + "", 30, 40);
		this.uiBatch.end();
	}

	@Override
	public void dispose()
	{
		this.shapeRenderer.dispose();
		this.spriteBatch.dispose();
		this.uiBatch.dispose();
		this.clickSound.dispose();
		this.fontRenderer.dispose();
		this.generator.dispose();
	}

	@Override
	public void hide()
	{
		GameData.numClicks = this.numClicks;
		GameData.save();
	}

	private boolean clicked()
	{
		Vector3 temp = new Vector3(
			Gdx.input.getX(),
			Gdx.input.getY(),
			0f
		);
		Vector3 touchPos = this.cam.unproject(temp);

		float x = cam.position.x - cam.viewportWidth + touchPos.x;
		float y = cam.position.y - cam.viewportHeight + touchPos.y;

		if (Gdx.input.justTouched()
			&& x > -5
			&& x < 5
			&& y > -5
			&& y < 5)
		{
			this.shapeRenderer.setColor(new Color(this.randomClicker.color()));
			this.numClicks += this.randomClicker.score();
			if (GameData.clickSound) this.clickSound.play();
			Vector3 proj = cam.project(new Vector3(touchPos.x, touchPos.y, 0));
			this.system.add(new TextParticle(proj.x, proj.y, "+" + this.randomClicker.score()));
			return true;
		}

		this.shapeRenderer.setColor(Color.WHITE);
		return false;
	}
}
