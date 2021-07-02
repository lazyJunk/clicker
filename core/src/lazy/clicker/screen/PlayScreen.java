package lazy.clicker.screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

public class PlayScreen extends ScreenAdapter
{

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(1f, 1f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
