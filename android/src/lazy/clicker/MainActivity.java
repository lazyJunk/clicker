package lazy.clicker;

import android.os.*;
import android.view.*;
import com.badlogic.gdx.backends.android.*;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			Window applicationWindow = getApplicationWindow();
			WindowManager.LayoutParams attrib = applicationWindow.getAttributes();
			attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
		}
		
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useImmersiveMode = true;
        initialize(new Core(), cfg);
    }
}
