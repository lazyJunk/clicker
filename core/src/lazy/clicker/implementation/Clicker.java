package lazy.clicker.implementation;
import lazy.clicker.api.*;

public class Clicker implements IClicker
{

	public final int score;
	public final int color;
	public final String regName;

	public Clicker(int score, int color, String regName)
	{
		this.score = score;
		this.color = color;
		this.regName = regName;
	}

	@Override
	public int score()
	{	
		return this.score;
	}

	@Override
	public int color()
	{	
		return this.color;
	}

	@Override
	public String regName()
	{
		return this.regName;
	}
}
