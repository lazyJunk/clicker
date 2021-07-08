package lazy.clicker.utils;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

public class Utils
{
	public static boolean clickedArea(Camera camera, Rectangle rect){
		Vector3 temp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
		Vector3 touchPos = camera.unproject(temp);

		float x = touchPos.x;
		float y = touchPos.y;
		
		return x > rect.x
			&& x < rect.x + rect.width
			&& y > rect.y
			&& y < rect.y + rect.height;
	}
}
