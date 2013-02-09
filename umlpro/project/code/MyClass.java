package project.code;

import java.util.ArrayList;
import java.io.Serializable;

public abstract class MyClass implements Serializable
{
	//instance variables
	private String name;
	private ArrayList<MyMethod> methods;
	private ArrayList<MyInstanceVariable> instanceVariables;
	private ArrayList<ClassRelationship> classRelationships;
	
	//getters and setters
	public String getName() 
	{
		return this.name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public ArrayList<MyMethod> getMethods() 
	{
		return this.methods;
	}
	public void setMethods(ArrayList<MyMethod> methods) 
	{
		this.methods = methods;
	}
	public ArrayList<MyInstanceVariable> getInstanceVariables() 
	{
		return this.instanceVariables;
	}
	public void setInstanceVariables(ArrayList<MyInstanceVariable> instanceVariables) 
	{
		this.instanceVariables = instanceVariables;
	}
	public ArrayList<ClassRelationship> getClassRelationships() 
	{
		return this.classRelationships;
	}
	public void setClassRelationships(ArrayList<ClassRelationship> classRelationships) 
	{
		this.classRelationships = classRelationships;
	}
	public void addClassRelationship(ClassRelationship classRelationship)
	{
		this.getClassRelationships().add(classRelationship);
	}
	
	//real methods
	public MyClass(String name)
	{
		this.setName(name);
		this.setInstanceVariables(new ArrayList<MyInstanceVariable>());
		this.setMethods(new ArrayList<MyMethod>());
		this.setClassRelationships(new ArrayList<ClassRelationship>());
	}
	public void removeAllComponents() {
		// remove attributes
		for(int i = 0; i < this.getInstanceVariables().size(); i++) {
			this.getInstanceVariables().remove(i);
		}
		// remove methods
		for(int i = 0; i < this.getMethods().size(); i++) {
			this.getMethods().remove(i);
		}
	}
}
