package lazy.clicker;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import lazy.clicker.screen.*;

public class Core extends Game
{
	@Override
	public void create()
	{
		Gdx.app.log("Core", "Game Initializing");
		this.setScreen(new PlayScreen());
	}
}
