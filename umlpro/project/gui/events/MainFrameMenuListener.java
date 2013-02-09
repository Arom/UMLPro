package project.gui.events;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import project.gui.forms.MainFrame;
import project.code.GraphicalMyClass;
import javax.swing.JOptionPane;
import project.gui.forms.AddFieldMethodFrame;
import project.gui.forms.ClassConnectionFrame;
import project.code.fileHandling.ProjectIO;

public class MainFrameMenuListener implements ActionListener
{
	//instance variables
	private MainFrame mainFrame;
	
	//real methods
	public MainFrameMenuListener(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
		//getter
	public MainFrame getMainFrame() 
	{
		return this.mainFrame;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		
		if("Quit".equals(actionCommand))
			System.exit(0);
		else if("Add New Class".equals(actionCommand))
			JOptionPane.showMessageDialog(this.getMainFrame(), "In order to create a new class, right-click on the white area and insert a new name for the new class.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		/*else if(actionCommand.contains(".java"))
			this.removeClass(actionCommand);*/
		else if("Add Field to Class".equals(actionCommand))
			this.addFieldMethodToClass((byte)0);
		else if("Add Method to Class".equals(actionCommand))
			this.addFieldMethodToClass((byte)1);
		else if("Save Project".equals(actionCommand))
			this.saveProject();
		else if(actionCommand.contains(".mproj"))
			this.openProject(actionCommand);
		else if("Add Relationship Between Classes".equals(actionCommand))
			this.createConnection();
		else if("Export Java Classes".equals(actionCommand))
			this.exportClasses();
		else if("Delete Class".equals(actionCommand))
			mainFrame.removeSelectedClass();
	}
	
	public void addNewClass(int x, int y)
	{
		String className = JOptionPane.showInputDialog(this.getMainFrame(), "Enter the name for the new class:", "New Class Name", JOptionPane.QUESTION_MESSAGE);
		
		// validate the input
		className = filterClassName(className);
		
		// check the name's suitability
		if(className != null && className.length() > 0) {
			GraphicalMyClass graphicalMyClass = new GraphicalMyClass(className, x, y, this.getMainFrame().getPanelCanvas().getWidth(), this.getMainFrame().getPanelCanvas().getHeight());
			if(this.mainFrame.getPanelCanvas().addMyClass(graphicalMyClass))
			{
				this.mainFrame.getPanelCanvas().repaint();
			}else{
				JOptionPane.showMessageDialog(null, "This class name is already in use. Try a different one.", "Warning", JOptionPane.WARNING_MESSAGE);
				this.addNewClass(x, y);
			}
		}
	}
	
	public void addFieldMethodToClass(byte type)
	{
		GraphicalMyClass selectedClass = this.mainFrame.checkSelected();		
		
		if(this.getMainFrame().getPanelCanvas().getMyClasses().size() == 0)
			JOptionPane.showMessageDialog(this.mainFrame, "There are no classes in the system. Create a new class by right-clicking the white space!", "Warning", JOptionPane.WARNING_MESSAGE);
		else if (selectedClass == null)
			JOptionPane.showMessageDialog(this.mainFrame, "You need to select a class first!", "Warning", JOptionPane.WARNING_MESSAGE);
		else {
			AddFieldMethodFrame frame = new AddFieldMethodFrame(this.getMainFrame(), type, selectedClass);
		}			
	}
	
	public void saveProject()
	{
		ProjectIO io = new ProjectIO(this.getMainFrame());
		io.saveProject();
	}
	
	public void openProject(String fileName)
	{
		ProjectIO io = new ProjectIO(this.getMainFrame());
		io.openProject(fileName);
	}
	
	public void createConnection()
	{
		if(this.getMainFrame().getPanelCanvas().getMyClasses().size() > 1)
		{
			ClassConnectionFrame frame = new ClassConnectionFrame(this.getMainFrame());
		}else{
			JOptionPane.showMessageDialog(this.mainFrame, "There are not enough classes in the system.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void exportClasses()
	{
		ProjectIO io = new ProjectIO(this.getMainFrame());
		io.exportProject();
	}
	
	public String filterClassName(String inputtedText) {
		
		if(inputtedText != null && inputtedText.length() > 0) {
			
			// capitalise the first letter of the class if user hasn't already done so
			inputtedText = inputtedText.substring(0,1).toUpperCase() + inputtedText.substring(1);
			// remove anything except letters and characters
			inputtedText = inputtedText.replaceAll("[^0-9a-zA-Z]+","");
			// remove white space from the class name
			inputtedText = inputtedText.replaceAll("\\s","");
			
		}
		
		return inputtedText;
	}
}
