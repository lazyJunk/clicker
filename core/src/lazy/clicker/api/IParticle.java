package lazy.clicker.api;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;

public interface IParticle
{
	
	int lifetime();
	Vector2 speed();
	void update(float delta);
	void render(SpriteBatch batch);
	void dispose();
	Map<String, Object> props();
}
