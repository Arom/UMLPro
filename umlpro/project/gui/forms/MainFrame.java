package project.gui.forms;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import project.code.GraphicalMyClass;
import project.gui.components.MainFrameMenu;
import project.gui.components.MainFramePanelCanvas;
import project.gui.events.MainFrameMenuListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainFrame extends JFrame {
	
	//instance variables
	private MainFrameMenu menu;
	private MainFramePanelCanvas panelCanvas;
	private String fileName;
	
	//getters and setters
	public MainFrameMenu getMenu() {
		return this.menu;
	}
	
	public GraphicalMyClass checkSelected() {
		return panelCanvas.checkSelected();
	}
	
	public void removeSelectedClass() {
		panelCanvas.removeSelectedClass();
	}
	
	public void beenClicked(int x, int y) {
		panelCanvas.beenClicked(x, y);
	}
	
	public void setMenu(MainFrameMenu menu) {
		this.menu = menu;
	}
	
	public MainFramePanelCanvas getPanelCanvas() {
		return this.panelCanvas;
	}
	
	public void setPanelCanvas(MainFramePanelCanvas panelCanvas) {
		this.panelCanvas = panelCanvas;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//real methods
	public MainFrame() {
		this.addComponents();
		this.setPreferences();
	}
	
	private void setPreferences() {
		this.setFileName("");
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)screen.getWidth() - 100, (int)screen.getHeight() - 100);
		
		this.setLocationRelativeTo(null); // centre JFrame on screen
		this.setTitle("2012/1 Project");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void addComponents() {
		
		this.menu = new MainFrameMenu(this);
		this.setJMenuBar(this.menu);
		
		MainFrameMenuListener listener = new MainFrameMenuListener(this);
		
		this.panelCanvas = new MainFramePanelCanvas(this, listener);
		this.add(this.panelCanvas, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();

		JButton button0 = new JButton("Add New Class");
		button0.addActionListener(listener);
		button0.setToolTipText("Adds a new class");
		buttonPanel.add(button0);
		JButton button1 = new JButton("Add Field to Class");
		button1.addActionListener(listener);
		button1.setToolTipText("Adds a field to the class");
		buttonPanel.add(button1);
		JButton button2 = new JButton("Add Method to Class");
		button2.addActionListener(listener);
		button2.setToolTipText("Adds a method to the class");
		buttonPanel.add(button2);
		JButton button3 = new JButton("Add Relationship Between Classes");
		button3.addActionListener(listener);
		button3.setToolTipText("Sets relationships to the classes");
		buttonPanel.add(button3);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
}
