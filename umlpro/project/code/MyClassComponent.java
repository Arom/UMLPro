package project.code;

import java.io.Serializable;

public abstract class MyClassComponent implements Serializable
{
	//instance variables
	private char accessControl;
	private String name;
	
	//getters and setters
	public char getAccessControl() 
	{
		return this.accessControl;
	}
	public void setAccessControl(char accessControl) 
	{
		this.accessControl = accessControl;
	}
	public String getName() 
	{
		return this.name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	//abstract method to be implemented by class' children
	public abstract String getFullName();
}
