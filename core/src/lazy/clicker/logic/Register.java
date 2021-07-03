package lazy.clicker.logic;

import java.util.*;

public class Register
{
	private static List<IClicker> allClicker = new ArrayList<>();

	public static void init(){
		allClicker.add(new IClicker(){

				@Override
				public String regName()
				{
					return "start";
				}
				
				@Override
				public int score()
				{
					return 1;
				}

				@Override
				public int color()
				{
					return 0xffffffff;
				}
		});
		
		allClicker.add(new IClicker(){

				@Override
				public String regName()
				{
					return "better";
				}

				@Override
				public int score()
				{
					return 2;
				}

				@Override
				public int color()
				{
					return 0xffff00ff;
				}
			});
	}
	
	public static IClicker get(String regname){
		for(IClicker clciker : allClicker){
			if(clciker.regName().equals(regname)){
				return clciker;
			}
		}
		return null;
	}
}
