package project.gui.components;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import project.gui.forms.MainFrame;
import project.gui.events.MainFrameMenuListener;
import project.code.fileHandling.ProjectIO;
import project.code.GraphicalMyClass;

public class MainFrameMenu extends JMenuBar
{
	//instance variables
	private JMenu[] menu;
	private JMenuItem[] menuItem;
	private MainFrameMenuListener listener;
	private MainFrame mainFrame;

	//getters and setters
	public JMenu[] getMenu() 
	{
		return this.menu;
	}
	public void setMenu(JMenu[] menu) 
	{
		this.menu = menu;
	}
	public JMenuItem[] getMenuItem() 
	{
		return this.menuItem;
	}
	public void setMenuItem(JMenuItem[] menuItem) 
	{
		this.menuItem = menuItem;
	}
	public MainFrameMenuListener getListener() 
	{
		return this.listener;
	}
	public void setListener(MainFrameMenuListener listener) 
	{
		this.listener = listener;
	}
	public MainFrame getMainFrame()
	{
		return this.mainFrame;
	}
	public void setMainFrame(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	//real methods
	public MainFrameMenu(MainFrame mainFrame)
	{
		this.setMainFrame(mainFrame);
		
		this.menu = new JMenu[4];
		this.menuItem = new JMenuItem[7];
		this.listener = new MainFrameMenuListener(this.getMainFrame());
		
		this.setPreferences();
		this.updateFileOptions();
	}
	
	private void setPreferences()
	{
		//main menus
		this.getMenu()[0] = new JMenu("File");
		
		this.getMenu()[1] = new JMenu("Class");
		this.add(this.getMenu()[0]);
		this.add(this.getMenu()[1]);
		
		//"Class" menu-items
		this.getMenuItem()[3] = new JMenuItem("Add New Class");
		this.getMenuItem()[3].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
                java.awt.Event.CTRL_MASK));
		this.getMenuItem()[4] = new JMenuItem("Add Field to Class");
		this.getMenuItem()[4].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F,
                java.awt.Event.CTRL_MASK));
		this.getMenuItem()[5] = new JMenuItem("Add Method to Class");
		this.getMenuItem()[5].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M,
                java.awt.Event.CTRL_MASK));
		this.getMenuItem()[0] = new JMenuItem("Add Relationship Between Classes");
		this.getMenuItem()[0].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,
                java.awt.Event.CTRL_MASK));
		this.getMenuItem()[6] = new JMenuItem("Delete Class");
		this.getMenuItem()[6].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,
                java.awt.Event.CTRL_MASK));
		//setting event listener
		this.getMenuItem()[5].addActionListener(this.getListener());
		this.getMenuItem()[4].addActionListener(this.getListener());
		this.getMenuItem()[3].addActionListener(this.getListener());
		this.getMenuItem()[0].addActionListener(this.getListener());
		this.getMenuItem()[6].addActionListener(this.getListener());
		//adding to its location
		this.getMenu()[1].add(this.getMenuItem()[3]);
		this.getMenu()[1].add(this.getMenuItem()[4]);
		this.getMenu()[1].add(this.getMenuItem()[5]);
		this.getMenu()[1].add(this.getMenuItem()[0]);
		this.getMenu()[1].add(this.getMenuItem()[6]);
		
		//sub-menus
		this.getMenu()[2] = new JMenu("Delete Class");
		//adding to its location
		//this.getMenu()[1].add(this.getMenu()[2]);
		
		//"File" menus
		this.getMenu()[3] = new JMenu("Open Project");
		//adding to its location
		this.getMenu()[0].add(this.getMenu()[3]);
		
		//"File" menu-items
		
		this.getMenuItem()[1] = new JMenuItem("Save Project");
		this.getMenuItem()[1].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.Event.CTRL_MASK));
		this.getMenuItem()[6] = new JMenuItem("Export Java Classes");
		this.getMenuItem()[6].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E,
                java.awt.Event.CTRL_MASK));
		this.getMenuItem()[2] = new JMenuItem("Quit");
		this.getMenuItem()[2].setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q,
                java.awt.Event.CTRL_MASK));
		//setting event listeners
		this.getMenuItem()[1].addActionListener(this.getListener());
		this.getMenuItem()[6].addActionListener(this.getListener());
		this.getMenuItem()[2].addActionListener(this.getListener());
		//adding to its location
		this.getMenu()[0].add(this.getMenuItem()[1]);
		this.getMenu()[0].add(this.getMenuItem()[6]);
		this.getMenu()[0].add(this.getMenuItem()[2]);
	}
	
	public void updateClassOptions()
	{
		ArrayList<String> classNames = new ArrayList<String>();
		for(GraphicalMyClass c : this.getMainFrame().getPanelCanvas().getMyClasses())
		{
			classNames.add(c.getName());
		}
		Collections.sort(classNames);
		
		this.getMenu()[2].removeAll();
		for(String c : classNames)
		{
			JMenuItem item = new JMenuItem(c + ".java");
			item.addActionListener(this.getListener());
			this.getMenu()[2].add(item);
		}
	}
	
	public void updateFileOptions()
	{
		ProjectIO io = new ProjectIO(this.getMainFrame());
		String[] files = io.getSavedProjects();
		this.getMenu()[3].removeAll();
		
		for(String name : files)
		{
			JMenuItem item = new JMenuItem(name);
			item.addActionListener(this.getListener());
			System.out.println(name);
			this.getMenu()[3].add(item);
		}
	}
}
