package lazy.clicker;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import lazy.clicker.screen.*;
import lazy.clicker.logic.*;
import lazy.clicker.utils.*;

public class Core extends Game
{
	
	public static ColorScheme DARK = new ColorScheme(Color.BLACK, Color.WHITE);
	public static ColorScheme LIGHTER = new ColorScheme(Color.GRAY, Color.WHITE);
	
	public static ColorScheme currentScheme = LIGHTER;
	
	@Override
	public void create()
	{
		Gdx.app.log("Core", "Game Initializing");
		
		Register.init();
		
		GameData.load();
		
		this.setScreen(new PlayScreen());
	}
}
