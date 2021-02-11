package com.CondeNast.Core;

import org.sikuli.script.Screen;

public class InitializeScreenInstance 
{
	private static InitializeScreenInstance instanceOfScreenClass=null;
	private Screen screen;
	   
	   private InitializeScreenInstance()
	   {
		   this.screen = new Screen();
	   }
	   
	   public static InitializeScreenInstance getInstanceOfScreenClass()
	   {
		   if(instanceOfScreenClass==null)
		   {
			   instanceOfScreenClass = new InitializeScreenInstance();
		   }
		   
		  return instanceOfScreenClass;
	   }


		public Screen getScreen() {
			return screen;
		}
}
