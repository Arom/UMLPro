package project;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

import project.gui.forms.MainFrame;

public class Project 
{
	public static void main(String[] args)
	{
		splash();   //Splash execution
		MainFrame frame = new MainFrame();
	}
	public static void splash(){   //Class to handle splash screen
		   final SplashScreen splash = SplashScreen.getSplashScreen();
	        if (splash == null) {
	            System.out.println("SplashScreen.getSplashScreen() returned null");  //if splash not found then display message
	            
	            return;
	        }
	        Graphics2D g = splash.createGraphics();
	        if (g == null) {
	            System.out.println("g is null");
	            return;
	        }
	}
}
