package project.code;

import java.io.Serializable;

public class MyMethodParameter implements Serializable
{
	private String type;
	private String name;
	
	public String getType() 
	{
		return this.type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	public String getName() 
	{
		return this.name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
}
