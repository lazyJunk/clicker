package lazy.clicker.logic;

import java.util.*;
import lazy.clicker.api.*;
import lazy.clicker.implementation.*;

public class Register
{
	private static List<IClicker> allClicker = new ArrayList<>();

	private static final IClicker BASIC =  new Clicker(1, 0xffffff, "Basic");
	
	public static void init(){
		allClicker.add(BASIC);
		allClicker.add(new Clicker(2, 0xff0000, "Double"));
		allClicker.add(new Clicker(4, 0x00ff00, "Double x 2"));
	}
	
	public static IClicker get(String regname){
		for(IClicker clciker : allClicker){
			if(clciker.regName().equals(regname)){
				return clciker;
			}
		}
		return BASIC;
	}
}
