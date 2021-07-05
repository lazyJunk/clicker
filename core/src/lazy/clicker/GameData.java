package lazy.clicker;
import com.badlogic.gdx.*;

public class GameData
{
	public static boolean clickSound = true;
	public static long numClicks = 0;
	
	public static void load(){
		Preferences prefs = Gdx.app.getPreferences("gamedata");
		clickSound = prefs.getBoolean("click_sound", true);
		numClicks = prefs.getLong("num_clicks", 0);
	}
	
	public static void save(){
		Preferences prefs = Gdx.app.getPreferences("gamedata");
		prefs.putBoolean("click_sound", clickSound);
		prefs.putLong("num_clicks", numClicks);
		prefs.flush();
	}
}
