package lazy.clicker.screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import lazy.clicker.logic.*;

public class PlayScreen extends ScreenAdapter
{

	private final SpriteBatch spriteBatch = new SpriteBatch();
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private final BitmapFont fontRenderer = new BitmapFont();
	
	private final float width = 20;
	private final float ppu = Gdx.graphics.getWidth() / width;
	private final float height = Gdx.graphics.getHeight() / ppu;

	private String text = "";
	
	private final OrthographicCamera cam = new OrthographicCamera(width, height);
	
	private IClicker randomClicker;
	
	private int score;
	
	@Override
	public void show()
	{
		cam.position.set(width / 2, height / 2, 0);
		cam.update();
		fontRenderer.setScale(2f);
		
		randomClicker = Register.get("better");
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(1f, 1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.cam.update();
	
		this.shapeRenderer.setProjectionMatrix(this.cam.combined);
		this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		this.clicked();
		this.shapeRenderer.rect(cam.viewportWidth / 2 - 5, cam.viewportHeight /2 - 5, 10, 10);
		this.shapeRenderer.end();
		
		this.spriteBatch.begin();
		//this.spriteBatch.setProjectionMatrix(this.cam.combined);
		this.fontRenderer.draw(this.spriteBatch, "Score: " + score, 30, 30);
		this.spriteBatch.end();
	}

	@Override
	public void dispose()
	{
		this.shapeRenderer.dispose();
	}
	
	private boolean clicked(){
		Vector3 touchPos = this.cam.unproject(new Vector3(
			Gdx.input.getX(),
			Gdx.input.getY(),
			0f
		));
		
		float x = cam.position.x - cam.viewportWidth + touchPos.x;
		float y = cam.position.y - cam.viewportHeight + touchPos.y;
		
		if(Gdx.input.justTouched()
			&&x > -5
			&& x < 5
			&& y > -5
			&& y < 5){
				this.shapeRenderer.setColor(new Color(this.randomClicker.color()));
				this.score += this.randomClicker.score();
				return true;
		}
			
		this.shapeRenderer.setColor(Color.WHITE);
		text = x + " " + y;
		return false;
	}
}
