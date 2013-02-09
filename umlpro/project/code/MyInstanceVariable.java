package project.code;

import java.io.Serializable;

public final class MyInstanceVariable extends MyClassComponent implements Serializable
{
	//instance variables
	private String type;
		
	//getters and setters
	public String getType() 
	{
		return this.type;
	}
	public void setType(String returnType) 
	{
		this.type = returnType;
	}
	
	public String getFullName()
	{
		String fullName = this.getAccessControl() + " " + this.getName() + ": " + this.getType();
		return fullName;
	}
	
	public String getFullNameExport()
	{
		String fullName = String.valueOf(this.getAccessControl()).replace("+", "public").replace("-", "private") + " " + this.getType() + " " + this.getName() + ";";
		return fullName;
	}
}
