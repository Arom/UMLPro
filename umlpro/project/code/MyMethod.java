package project.code;

import java.util.ArrayList;

public final class MyMethod extends MyClassComponent
{
	//instance variables
	private String returnType;
	private ArrayList<MyMethodParameter> parameters;

	//getters and setters
	public String getReturnType() 
	{
		return this.returnType;
	}
	public void setReturnType(String returnType) 
	{
		this.returnType = returnType;
	}
	public ArrayList<MyMethodParameter> getParameters() 
	{
		return this.parameters;
	}
	public void setParameters(ArrayList<MyMethodParameter> parameters) 
	{
		this.parameters = parameters;
	}
	public void addParameter(MyMethodParameter parameter)
	{
		this.getParameters().add(parameter);
	}
	
	//real methods
	public MyMethod()
	{
		this.setParameters(new ArrayList<MyMethodParameter>());
	}
	public String getFullName()
	{
		String fullName = this.getAccessControl() + " " + this.getName() + "(";
		int i = 0;
		for(MyMethodParameter p : this.getParameters())
		{
			if(i > 0) 
				fullName += ", ";
			fullName += p.getName() + " : " + p.getType();
			i++;
		}
		fullName += ") : " + this.getReturnType();
		return fullName;
	}
	public String getFullNameExport()
	{
		String fullName = String.valueOf(this.getAccessControl()).replace("+", "public").replace("-", "private") + " " + this.getReturnType() + " " + this.getName() + "(";
		int i = 0;
		for(MyMethodParameter p : this.getParameters())
		{
			if(i > 0) 
				fullName += ", ";
			fullName += p.getType() + p.getName();
			i++;
		}
		fullName += ")";
		return fullName;
	}
}
