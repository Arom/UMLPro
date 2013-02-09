package project.gui.events;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import project.code.MyMethod;
import project.code.MyMethodParameter;
import project.gui.forms.ClassConnectionFrame;
import project.code.GraphicalMyClass;
import project.code.ClassRelationship;
import java.util.ArrayList;

public class ClassConnectionFrameListener implements ActionListener
{
	//instance variables
	private ClassConnectionFrame myFrame;
	
	//getters and setters
	public ClassConnectionFrame getMyFrame() 
	{
		return myFrame;
	}
	public void setMyFrame(ClassConnectionFrame myFrame) 
	{
		this.myFrame = myFrame;
	}
	
	//real methods
	public ClassConnectionFrameListener(ClassConnectionFrame myFrame)
	{
		this.setMyFrame(myFrame);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Cancel"))
			this.getMyFrame().dispose();
		else
			this.addConnection();
	}
	
	public void addConnection()
	{
		String[] myClass = new String[2];
		myClass[0] = this.getMyFrame().getMyComboBox()[0].getSelectedItem().toString();
		myClass[1] = this.getMyFrame().getMyComboBox()[1].getSelectedItem().toString();
		
		String cardinality = this.getMyFrame().getMyComboBox()[2].getSelectedItem().toString();
		
		if(myClass[0].equals(myClass[1]))
		{
			JOptionPane.showMessageDialog(this.getMyFrame(), "A class cannot have a relationship with itself", "Warning", JOptionPane.WARNING_MESSAGE);
		}else{
			MyMethod myMethod[] = new MyMethod[2];
			myMethod[0] = new MyMethod();
			myMethod[1] = new MyMethod();
			myMethod[0].setAccessControl('+');
			myMethod[0].setName("set" + myClass[1]);
			myMethod[0].setReturnType("void");
			
			MyMethodParameter parameter = new MyMethodParameter();
			parameter.setName(myClass[1].substring(0, 1).toLowerCase() + myClass[1].substring(1));
			
			if(cardinality.equals("0..1"))
			{
				parameter.setType(myClass[1]);
				
				myMethod[1].setAccessControl('+');
				myMethod[1].setName("get" + myClass[1]);
				myMethod[1].setReturnType(myClass[1]);
			}else{
				parameter.setType("ArrayList<" + myClass[1] + ">");
				
				myMethod[1].setAccessControl('+');
				myMethod[1].setName("get" + myClass[1]);
				myMethod[1].setReturnType("ArrayList<" + myClass[1] + ">");
			}
			myMethod[0].addParameter(parameter);
			
			ClassRelationship relationship = new ClassRelationship();
			relationship.setCardinality(cardinality);
			
			ArrayList<GraphicalMyClass> myClasses = this.getMyFrame().getMainFrame().getPanelCanvas().getMyClasses();
			for(GraphicalMyClass c : myClasses)
			{
				if(c.getName().equals(myClass[0]))
				{
					relationship.getMyClass()[0] = c;
					c.getMethods().add(myMethod[1]);
					c.getMethods().add(myMethod[0]);
					c.addClassRelationship(relationship);
				}
				else if(c.getName().equals(myClass[1]))
				{
					relationship.getMyClass()[1] = c;
				}
			}
			this.getMyFrame().getMainFrame().getPanelCanvas().repaint();
			this.getMyFrame().dispose();
		}
	}
}
