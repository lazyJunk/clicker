package lazy.clicker.effect;
import lazy.clicker.api.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;

public class ParticleSystem
{
	
	private final List<IParticle> ALL = new ArrayList<>();

	public void add(IParticle par){
		ALL.add(par);
	}
	
	public void update(float delta){
		for(IParticle particle : ALL){
			particle.update(delta);
			if((int)particle.props().get("done") > particle.lifetime()){
				particle.dispose();
				ALL.remove(particle);
			}
		}
	}
	
	public void render(SpriteBatch batch){
		for(IParticle particle : ALL){
			particle.render(batch);
		}
	}
	
	public int count(){
		return ALL.size();
	}
}
