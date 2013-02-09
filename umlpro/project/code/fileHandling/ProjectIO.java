package project.code.fileHandling;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import project.gui.forms.MainFrame;
import java.util.ArrayList;
import project.code.GraphicalMyClass;
import project.code.MyInstanceVariable;
import project.code.MyMethod;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class ProjectIO
{
	//instance variables
	private MainFrame frame;
	
	//getters and setters
	public void setMainFrame(MainFrame frame)
	{
		this.frame = frame;
	}
	public MainFrame getMainFrame()
	{
		return this.frame;
	}
	
	//real methods
	public ProjectIO(MainFrame frame)
	{
		this.setMainFrame(frame);
	}
	
	public void saveProject()
	{
		try
		{
			String fileName = (this.getMainFrame().getFileName().equals("")) ? 
							JOptionPane.showInputDialog(this.getMainFrame(), "Enter the project's name:", "Save Project", JOptionPane.QUESTION_MESSAGE) :
							this.getMainFrame().getFileName();
			if(fileName != null)
			{
				fileName = (fileName.contains(".mproj")) ? fileName : fileName + ".mproj";
				File preFile = new File(fileName);
				if(!fileName.equals("") && (!preFile.exists() || !this.getMainFrame().getFileName().equals("")))
				{
					//HOW TO SERIALIZE AN OBJECT
					FileOutputStream file = new FileOutputStream(fileName);
					ObjectOutputStream object = new ObjectOutputStream(file);
					object.writeObject(this.getMainFrame().getPanelCanvas().getMyClasses());
					object.close();
					file.close();
					//FINISH ON HOW TO SERIALIZE AN OBJECT
			
					this.getMainFrame().setFileName(fileName);
					JOptionPane.showMessageDialog(null, "Project Saved", "Save Project", JOptionPane.INFORMATION_MESSAGE);
					this.getMainFrame().getMenu().updateFileOptions();
				}else if(fileName.equals("")){
					JOptionPane.showMessageDialog(this.getMainFrame(), "You must enter a name for your project.", "Warning", JOptionPane.WARNING_MESSAGE);
					this.saveProject();
				}else if(preFile.exists()){
					JOptionPane.showMessageDialog(this.getMainFrame(), "This name is already in use. Try a different one.", "Warning", JOptionPane.WARNING_MESSAGE);
					this.saveProject();
				}
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void openProject(String fileName)
	{
		try
		{
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream object = new ObjectInputStream(file);
			try
			{
				ArrayList<GraphicalMyClass> myClasses = (ArrayList<GraphicalMyClass>)object.readObject();
				this.getMainFrame().getPanelCanvas().getMyClasses().clear();
				
				for(GraphicalMyClass c : myClasses)
				{
					this.getMainFrame().getPanelCanvas().addMyClass(c);
					//this.getMainFrame().getMenu().addClassName(c.getName());
				}
				
				this.getMainFrame().setFileName(fileName);
				this.getMainFrame().getPanelCanvas().repaint();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public String[] getSavedProjects()
	{
		File myFile = new File(".");
		ArrayList<String> toReturn_ = new ArrayList<String>();
		
		for(File file : myFile.listFiles())
		{
			if(file.getName().contains(".mproj"))
				toReturn_.add(file.getName());
		}
		
		String[] toReturn = new String[toReturn_.size()];
		int i = 0;
		for(String s : toReturn_)
		{
			toReturn[i] = s;
			System.out.println(toReturn[i]);
			i++;
		}
		
		return toReturn;
	}
	
	public void exportProject()
	{
		ArrayList<GraphicalMyClass> myClasses = this.getMainFrame().getPanelCanvas().getMyClasses();
		if(myClasses.size() == 0)
		{
			JOptionPane.showMessageDialog(this.getMainFrame(), "The project is empty. Not possible to generate .java files.", "Warning", JOptionPane.WARNING_MESSAGE);
		}else{
			for(GraphicalMyClass c : myClasses)
			{
				try
				{
					FileWriter writer = new FileWriter(c.getName() + ".java");
					BufferedWriter bWriter = new BufferedWriter(writer);
					bWriter.write("import java.util.*;");
					bWriter.newLine();bWriter.newLine();
					bWriter.write("public class " + c.getName() + " {");
					bWriter.newLine();
					for(MyInstanceVariable v : c.getInstanceVariables())
					{
						bWriter.newLine();
						bWriter.write("\t"); bWriter.write(v.getFullNameExport());
					}
					bWriter.newLine();
					for(MyMethod m : c.getMethods())
					{
						bWriter.newLine();
						bWriter.write("\t"); bWriter.write(m.getFullNameExport() + "{");
						bWriter.newLine(); bWriter.write("\t"); bWriter.write("}");
					}
					bWriter.newLine();
					bWriter.write("}");
					bWriter.flush();
					bWriter.close();
					writer.close();
				}catch(IOException e){
					JOptionPane.showMessageDialog(this.getMainFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			JOptionPane.showMessageDialog(this.getMainFrame(), "The classes have been exported successfully. Location: " + new File(".").getAbsolutePath(), "Export Classes", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
