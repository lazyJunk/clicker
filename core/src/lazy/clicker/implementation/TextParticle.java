package lazy.clicker.implementation;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import lazy.clicker.api.*;

public class TextParticle implements IParticle
{
	public String text;
	public float x;
	public float y;
	//This is bad, dont do this.
	public BitmapFont font = new BitmapFont();
	
	public int timeLeft;
	public float alpha = 1f;
	
	public TextParticle(float x, float y, String text){
		this.text = text;
		this.x = x;
		this.y = y;
		font.getData().scale(2.5f);
	}

	@Override
	public void update(float delta)
	{
		if(this.lifetime() > this.timeLeft){
			this.timeLeft++;
			this.alpha -= .01f;
			this.x += this.speed().x;
			this.y += this.speed().y;
		}
	}

	@Override
	public void render(SpriteBatch batch)
	{
		if(this.lifetime() > this.timeLeft){
			this.font.setColor(0f, 0f, 0f, this.alpha);
			this.font.draw(batch, this.text, this.x, this.y);
			this.font.setColor(1f, 1f, 1f, this.alpha);
			this.font.draw(batch, this.text, this.x + .5f, this.y + .5f);
		}
	}

	@Override
	public void dispose()
	{
		this.font.dispose();
	}

	@Override
	public int lifetime()
	{
		return 100;
	}

	@Override
	public Vector2 speed()
	{
		return new Vector2(MathUtils.random(-3f, 3f), 2.5f);
	}

	@Override
	public Map<String, Object> props()
	{
		Map<String, Object> props = new HashMap<>();
		props.put("done", this.timeLeft);
		return props;
	}

	
	
}
