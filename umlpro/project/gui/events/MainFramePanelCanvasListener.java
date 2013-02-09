package project.gui.events;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import project.gui.forms.MainFrame;
import project.code.GraphicalMyClass;

public class MainFramePanelCanvasListener implements MouseListener, MouseMotionListener
{
	//instance variables
	private MainFrame mainFrame;
	private GraphicalMyClass myClass;		// used only in tracking the class we're dragging
	private GraphicalMyClass selectedClass; // stores details of any selected class (blue border)
	

	//getters and setters
	public MainFrame getMainFrame() 
	{
		return this.mainFrame;
	}
	public void setMainFrame(MainFrame mainFrame) 
	{
		this.mainFrame = mainFrame;
	}
	public GraphicalMyClass getMyClass() 
	{
		return this.myClass;
	}
	public void setMyClass(GraphicalMyClass myClass) 
	{
		this.myClass = myClass;
	}
	
	public GraphicalMyClass getSelectedClass() {
		return selectedClass;
	}
	
	public void setSelectedClass(GraphicalMyClass selectedClass) {
		this.selectedClass = selectedClass;
	}
	
	//real methods
	public MainFramePanelCanvasListener(MainFrame mainFrame)
	{
		this.setMainFrame(mainFrame);
		this.setMyClass(null);
		this.setSelectedClass(null);
	}
	
	//MouseListener
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON3) { //checking right|left click
			this.getMainFrame().getMenu().getListener().addNewClass(e.getX(), e.getY());
		}
	}
	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		
		// if user clicked within the panel
		if(x > -1 && y > -1) {
			// highlights any class that the user has selected
			mainFrame.beenClicked(x, y);
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		this.setMyClass(null);
	}
	public void mouseEntered(MouseEvent e)
	{
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}
	
	//MouseMotionListener
	public void mouseDragged(MouseEvent e)
	{
		if(this.getMyClass() == null)
		{
			for(GraphicalMyClass c : this.mainFrame.getPanelCanvas().getMyClasses())
			{
				//if all these booleans are true, then the person has clicked inside a class, and we will drag it with the mouse
				boolean[] test = new boolean[4];
				test[0] = (c.getX() <= e.getX()); 
				test[1] = (c.getX() + c.getWidth() >= e.getX());
				test[2] = (c.getY() <= e.getY());
				test[3] = (c.getY() + c.getHeight() >= e.getY());
			
				if(test[0] && test[1] && test[2] && test[3])
				{
					this.myClass = c;
					this.mainFrame.getPanelCanvas().removeMyClass(c);
					break;
				}
			}
		}else{	
			this.myClass.setX(e.getX() - this.myClass.getWidth() / 2);
			this.myClass.setY(e.getY() - this.myClass.getHeight() / 2);
			
			this.mainFrame.getPanelCanvas().addMyClass(this.myClass);
			this.mainFrame.getPanelCanvas().repaint();
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		
	}
}
