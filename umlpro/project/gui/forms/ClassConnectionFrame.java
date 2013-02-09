package project.gui.forms;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import project.code.GraphicalMyClass;
import project.gui.events.ClassConnectionFrameListener;

public class ClassConnectionFrame extends JFrame
{
	//instance variables
	private JComboBox[] myComboBox;
	private MainFrame mainFrame;
	
	//getters and setters
	public JComboBox[] getMyComboBox() 
	{
		return this.myComboBox;
	}

	public void setMyComboBox(JComboBox[] myComboBox) 
	{
		this.myComboBox = myComboBox;
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
	public ClassConnectionFrame(MainFrame mainFrame)
	{
		this.setMainFrame(mainFrame);
		
		this.addComponents();
		this.setPreferences();
	}
	
	public void addComponents()
	{
		JPanel panel = new JPanel();
		ClassConnectionFrameListener listener = new ClassConnectionFrameListener(this);
		
		this.setMyComboBox(new JComboBox[3]);
		this.getMyComboBox()[0] = new JComboBox();
		this.getMyComboBox()[1] = new JComboBox();
		this.getMyComboBox()[2] = new JComboBox(new String[]{"0..1", "0..*"});
		this.calculateClassComboBox();
		
		panel.add(new JLabel("First Class "));
		panel.add(this.getMyComboBox()[0]);
		panel.add(new JLabel("Second Class "));
		panel.add(this.getMyComboBox()[1]);
		panel.add(new JLabel("Cardinality "));
		panel.add(this.getMyComboBox()[2]);
		
		JButton addButton = new JButton("Add Relationship");
		addButton.addActionListener(listener);
		panel.add(addButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(listener);
		panel.add(cancelButton);
		
		this.add(panel);
	}
	
	public void setPreferences()
	{
		this.pack();
		this.setLocationRelativeTo(this.mainFrame);
		this.setTitle("New Relationship");
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void calculateClassComboBox()
	{
		ArrayList<String> classNames = new ArrayList<String>();
		for(GraphicalMyClass c : this.getMainFrame().getPanelCanvas().getMyClasses())
		{
			classNames.add(c.getName());
		}
		Collections.sort(classNames);
		
		this.getMyComboBox()[0] = new JComboBox(classNames.toArray());
		this.getMyComboBox()[1] = new JComboBox(classNames.toArray());
	}
}
