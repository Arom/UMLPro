package project.gui.events;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import project.code.MyInstanceVariable;
import project.code.MyMethod;

import javax.swing.JOptionPane;
import project.gui.forms.AddFieldMethodFrame;
import project.code.GraphicalMyClass;

public class AddFieldMethodFrameListener implements ActionListener
{
	//instance variables
	private AddFieldMethodFrame myFrame;
	
	//getters and setters
	public AddFieldMethodFrame getMyFrame() 
	{
		return myFrame;
	}
	public void setMyFrame(AddFieldMethodFrame myFrame) 
	{
		this.myFrame = myFrame;
	}
	
	//real methods
	public AddFieldMethodFrameListener(AddFieldMethodFrame myFrame)
	{
		this.setMyFrame(myFrame);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Cancel"))
			this.getMyFrame().dispose();
		else if(e.getActionCommand().equals("Add Field"))
			this.addField();
		else
			this.addMethod();
	}
	private void addField()
	{
		String name = this.getMyFrame().getTextFieldName().getText().replaceAll("[^a-zA-Z]+","");
		String type = this.getMyFrame().getTextFieldType().getText().replaceAll("[^a-zA-Z]+","");
		String access = String.valueOf(this.getMyFrame().getComboBoxAccess().getSelectedItem());
		
		char accessControl;
		if(access.equalsIgnoreCase("Private"))
			accessControl = '-';
		else
			accessControl = '+';
		
		if(!name.equals(""))
		{
			MyInstanceVariable field = new MyInstanceVariable();
			field.setName(name);
			field.setType(type);
			field.setAccessControl(accessControl);
			
			GraphicalMyClass c = this.getMyFrame().getSelectedClass();
			
			c.getInstanceVariables().add(field);
			this.getMyFrame().getMainFrame().getPanelCanvas().repaint();
				
			if(accessControl == '-')
			{
				String newName = name.substring(0,1).toUpperCase() + name.substring(1);
				this.addMethod("get" + newName, type, '+', c);
				this.addMethod("set" + newName, "void", '+', c);
			}
			this.getMyFrame().dispose();
		}else{
			JOptionPane.showMessageDialog(this.getMyFrame(), "You must type the field name", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void addMethod(String name, String returnType, char accessControl, GraphicalMyClass myClass)
	{
		MyMethod method = new MyMethod();
		method.setName(name);
		method.setReturnType(returnType);
		method.setAccessControl(accessControl);
		myClass.getMethods().add(method);
		this.getMyFrame().getMainFrame().getPanelCanvas().repaint();
		this.getMyFrame().dispose();
	}
	
	public void addMethod()
	{
		String name = this.getMyFrame().getTextFieldName().getText().replaceAll("[^a-zA-Z]+","");
		String type = (this.getMyFrame().getTextFieldType().getText().equals("") ? "void" : this.getMyFrame().getTextFieldType().getText().replaceAll("[^a-zA-Z]+",""));
		String access = String.valueOf(this.getMyFrame().getComboBoxAccess().getSelectedItem());
		
		GraphicalMyClass myClass = this.getMyFrame().getSelectedClass();
		
		char accessControl;
		if(access.equals("Private"))
			accessControl = '-';
		else
			accessControl = '+';
		this.addMethod(name, type, accessControl, myClass);
	}
	
	public GraphicalMyClass getClassByName(String className)
	{
		for(GraphicalMyClass c : this.getMyFrame().getMainFrame().getPanelCanvas().getMyClasses())
		{
			if(c.getName().equals(className))
			{
				return c;
			}
		}
		return null;
	}
}
