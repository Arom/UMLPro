package project.gui.components;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import project.code.GraphicalMyClass;
import project.gui.events.MainFramePanelCanvasListener;
import project.gui.events.MainFrameMenuListener;
import project.gui.forms.MainFrame;
import project.code.MyInstanceVariable;
import project.code.MyMethod;
import project.code.ClassRelationship;

public class MainFramePanelCanvas extends JPanel
{
	//instance variables
	private MainFrame mainFrame;
	private MainFrameMenuListener mfml;
	private ArrayList<GraphicalMyClass> myClasses;
	private MainFramePanelCanvasListener listener;
	
	//getters and setters
	
	public ArrayList<GraphicalMyClass> getMyClasses() 
	{
		return this.myClasses;
	}
	public void setMyClasses(ArrayList<GraphicalMyClass> myClasses) 
	{
		this.myClasses = myClasses;
		this.repaint();
	}
	public MainFrame getMainFrame() 
	{
		return this.mainFrame;
	}
	public void setMainFrame(MainFrame mainFrame) 
	{
		this.mainFrame = mainFrame;
	}
	public MainFramePanelCanvasListener getListener() 
	{
		return this.listener;
	}
	public void setListener(MainFramePanelCanvasListener listener) 
	{
		this.listener = listener;
	}

	//real methods
	public MainFramePanelCanvas(MainFrame mainFrame, MainFrameMenuListener mfml)
	{
		this.setMainFrame(mainFrame);
		this.mfml = mfml;
		this.setMyClasses(new ArrayList<GraphicalMyClass>());
		this.setListener(new MainFramePanelCanvasListener(this.getMainFrame()));
		
		this.setPreferences();
	}
	
	public GraphicalMyClass checkSelected() {
		return this.listener.getSelectedClass();
	}
	
	private void setPreferences()
	{
		this.setPreferredSize(new Dimension(this.mainFrame.getWidth(), this.mainFrame.getHeight()));
		this.setBackground(Color.WHITE);
		this.addMouseListener(this.getListener());
		this.addMouseMotionListener(this.getListener());
	}
	
	public void paintComponent(Graphics g) //never to be called directly, but through repaint()
	{
		super.paintComponent(g); //must call the parent paintComponent method
		
		for(GraphicalMyClass c : this.getMyClasses())//loop through each class in the system
		{
			c.calculateWidthHeight(g); //calculates the width and height of the square so it can hold all the information
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(c.getX(), c.getY(), c.getWidth(), c.getHeight());
			g.setColor(c.getBorderColor());
			g.drawRect(c.getX(), c.getY(), c.getWidth(), c.getHeight());
			g.drawString(c.getName(), c.getX() + 10, c.getY() + g.getFontMetrics().getHeight()); //draws the class' name
			
			//drawing the instance variables
			int i = 3;
			for(MyInstanceVariable v : c.getInstanceVariables())
			{
				String toDraw = v.getFullName();
				g.drawString(toDraw, c.getX() + 10, c.getY() + (g.getFontMetrics().getHeight() * i));
				i++;
			}
			
			//drawing the methods
			i++;
			for(MyMethod m : c.getMethods())
			{
				String toDraw = m.getFullName();
				g.drawString(toDraw, c.getX() + 10, c.getY() + (g.getFontMetrics().getHeight() * i));
				i++;
			}
			
			//drawing the relationships
			for(ClassRelationship r : c.getClassRelationships())
			{
				r.calculateXY();
				this.drawArrowLine(g, r.getX()[0], r.getY()[0], r.getX()[1], r.getY()[1]);
				g.drawString(r.getCardinality(), r.getcX(), r.getcY());
			}
		}
		//updating the delete class menu items
		this.getMainFrame().getMenu().updateClassOptions();
	}
	
	public void removeSelectedClass() {
		GraphicalMyClass removeThisClass = listener.getSelectedClass();
		if(removeThisClass != null) {
			
			int response = JOptionPane.showConfirmDialog(null, "Do you really want to delete "+removeThisClass.getName()+"?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.YES_OPTION) {	
				this.mainFrame.getPanelCanvas().destroyMyClass(removeThisClass);
				this.listener.setSelectedClass(null);
				this.mainFrame.getPanelCanvas().repaint();
			}
		}
		else JOptionPane.showMessageDialog(this.mainFrame, "You need to select a class before you can delete it!", "Warning", JOptionPane.WARNING_MESSAGE);
	}
	
	public void destroyMyClass(GraphicalMyClass selectedClass) {
		// remove attributes & methods
		selectedClass.removeAllComponents();
		// remove the class itself
		this.removeMyClass(selectedClass);
	}
	
	public void removeMyClass(GraphicalMyClass selectedClass)
	{
		this.getMyClasses().remove(selectedClass);
	}
	
	public void beenClicked(int x, int y) {
		boolean beenClicked = false;
		for(GraphicalMyClass c : this.getMyClasses()) {
			if( c.beenClicked(x, y) == true) {
				listener.setSelectedClass(c);
				beenClicked = true;
			}
		}
		if(beenClicked == false) {
			listener.setSelectedClass(null);
		}
		this.repaint();
	}
	
	public boolean addMyClass(GraphicalMyClass myClass)
	{
		boolean errorFlag = false;
		for(GraphicalMyClass c : this.getMyClasses())
		{
			if(c.getName().equals(myClass.getName()))
			{
				errorFlag = true;
				break;
			}
		}
		if(errorFlag) {
			return false;
		} else {
			this.getMyClasses().add(myClass);
			this.listener.setSelectedClass(myClass);
			return true;
		}
	}
	
	// THIS METHOD IS NOT OF MY CREATION. I SAW IT AT: http://sunjava-expert.blogspot.com/2010/06/how-to-draw-arrow-line-in-applet-java.html
	public void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2)
    {
        double d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double d1 = (double)(-(x2 - x1)) / (double)(y2 - y1);
        double d2 = 6D / Math.sqrt(1.0D + d1 * d1);
        double d3 = 6D / Math.sqrt(1.0D + 1.0D / d1 / d1);
        if(d1 < 0.0D)
        {
            d3 = -d3;
        }
        double d4 = (double)x2 - (double)(10 * (x2 - x1)) / d;
        double d5 = (double)y2 - (double)(10 * (y2 - y1)) / d;
        g.drawLine(x1, y1, (int)d4, (int)d5);
        int ai[] = new int[]{(int)(d4 - d2), x2, (int)(d4 + d2)};
        int ai1[] = new int[]{(int)(d5 - d3), y2, (int)(d5 + d3)};
        g.fillPolygon(ai, ai1, 3);
    }
}
