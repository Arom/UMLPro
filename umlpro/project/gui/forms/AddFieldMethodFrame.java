package project.gui.forms;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import project.code.GraphicalMyClass;
import project.gui.events.AddFieldMethodFrameListener;

public class AddFieldMethodFrame extends JFrame
{
	//instance variables
	AddFieldMethodFrameListener listener;
	MainFrame mainFrame;
	JButton addButton;
	JButton cancelButton;
	JTextField textFieldName;
	JComboBox comboBoxAccess;
	JTextField textFieldType;
	JComboBox comboBoxClass;
	GraphicalMyClass selectedClass;

	byte type; // 0 = add Field; 1 = add Method
	
	//getters and setters
	public MainFrame getMainFrame() 
	{
		return this.mainFrame;
	}
	public void setMainFrame(MainFrame mainFrame) 
	{
		this.mainFrame = mainFrame;
	}
	public AddFieldMethodFrameListener getListener() 
	{
		return this.listener;
	}
	public void setListener(AddFieldMethodFrameListener listener) 
	{
		this.listener = listener;
	}
	public JButton getAddButton() 
	{
		return this.addButton;
	}
	public void setAddButton(JButton addButton) 
	{
		this.addButton = addButton;
	}
	public JButton getCancelButton() 
	{
		return this.cancelButton;
	}
	public void setCancelButton(JButton cancelButton) 
	{
		this.cancelButton = cancelButton;
	}
	public JTextField getTextFieldName() 
	{
		return this.textFieldName;
	}
	public void setTextFieldName(JTextField textFieldName) 
	{
		this.textFieldName = textFieldName;
	}
	public JComboBox getComboBoxAccess() 
	{
		return this.comboBoxAccess;
	}
	public void setComboBoxAccess(JComboBox comboBoxAccess) 
	{
		this.comboBoxAccess = comboBoxAccess;
	}
	public JTextField getTextFieldType() 
	{
		return this.textFieldType;
	}
	public void setTextFieldType(JTextField textFieldType) 
	{
		this.textFieldType = textFieldType;
	}
	
	public GraphicalMyClass getSelectedClass() {
		return selectedClass;
	}
	
	public void setSelectedClass(GraphicalMyClass selectedClass) {
		this.selectedClass = selectedClass;
	}
	
    
        
	public byte getTheType() 
	{
		return this.type;
	}
	public void setType(byte type) 
	{
		this.type = type;
	}
	
	//real methods
	public AddFieldMethodFrame(MainFrame mainFrame, byte type, GraphicalMyClass selectedClass)
	{
		this.setMainFrame(mainFrame);
		this.setType(type);
		this.addComponents();
		this.setPreferences();
		this.setSelectedClass(selectedClass);
	}
	private void setPreferences()
	{
		this.pack();
		this.setLocationRelativeTo(this.getMainFrame());
		this.setTitle((this.getTheType() == 0) ? "Add Field to Class" : "Add Method to Class");
		this.setResizable(false);
		this.setVisible(true);
	}
	private void addComponents()
	{
		this.setListener(new AddFieldMethodFrameListener(this));
		
		JPanel panel = new JPanel();
		this.setAddButton((this.getTheType() == 0) ? new JButton("Add Field") : new JButton("Add Method")); // difference between method and field
		this.getAddButton().addActionListener(this.listener);
		this.setCancelButton(new JButton("Cancel"));
		this.getCancelButton().addActionListener(this.listener);
		this.setTextFieldName(new JTextField(10));
		
		this.setComboBoxAccess(new JComboBox());
		this.getComboBoxAccess().addItem("Private");
		this.getComboBoxAccess().addItem("Public");
		
		this.setTextFieldType(new JTextField(5));
		
		panel.add(new JLabel("Access Modifier: "));
		panel.add(this.getComboBoxAccess());
		panel.add((this.getTheType() == 0) ? new JLabel("Type: ") : new JLabel("Return Type: ")); // difference between method and field
		panel.add(this.getTextFieldType());
		panel.add((this.getTheType() == 0) ? new JLabel("Field Name: ") : new JLabel("Method Name: ")); // difference between method and field
		panel.add(this.getTextFieldName());
		panel.add(this.getAddButton());
		panel.add(this.getCancelButton());
		
		this.add(panel);
	}
}
