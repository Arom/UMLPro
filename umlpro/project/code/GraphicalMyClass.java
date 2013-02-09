package project.code;

import java.awt.*;

public final class GraphicalMyClass extends MyClass
{
	//instance variables
	private int x;
	private int y;
	private int width;
	private int height;
	private int panelWidth;
	private int panelHeight;
	private Color borderColor;
	
	//getters and setters
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}	
	
	public void setX(int x) 
	{
		this.x = x;
		if(this.getWidth() > 0)
		{
			int difference = (this.getX() + this.getWidth()) - this.getPanelWidth();
			if(difference > 0)
			{
				this.x = (this.getX() - Math.abs(difference) - 5);
			}
			if(this.getX() <= 0)
				this.x = 5;
		}
	}

	public void setY(int y) 
	{
		this.y = y;
		if(this.getHeight() > 0)
		{
			int difference = (this.getY() + this.getHeight()) - this.getPanelHeight();
			if(difference > 0)
			{
				this.y = (this.getY() - Math.abs(difference) - 5);
			}
			if(this.getY() <= 0)
				this.y = 5;
		}
	}
	public int getWidth() 
	{
		return this.width;
	}
	public void setWidth(int width) 
	{
		this.width = width;
	}
	public int getHeight() 
	{
		return this.height;
	}
	public void setHeight(int height) 
	{
		this.height = height;
	}
	public int getPanelWidth() 
	{
		return this.panelWidth;
	}
	public void setPanelWidth(int panelWidth) 
	{
		this.panelWidth = panelWidth;
	}
	public int getPanelHeight() 
	{
		return this.panelHeight;
	}
	public void setPanelHeight(int panelHeight) 
	{
		this.panelHeight = panelHeight;
	}
	
	public Color getBorderColor() {
		return this.borderColor;
	}

	//real methods

	public void calculateWidthHeight(Graphics g)
	{
		this.setWidth(g.getFontMetrics().stringWidth(this.getName()));
		this.setHeight(((this.getMethods().size() + this.getInstanceVariables().size()) * g.getFontMetrics().getHeight()) + 60);
		
		this.setX(this.getX());
		this.setY(this.getY());

		//checking whether we have an instance variable whose name is wider than the class' name
		for(MyInstanceVariable v : this.getInstanceVariables())
		{
			String completeName = v.getFullName();
			int newWidth = g.getFontMetrics().stringWidth(completeName);
			if(newWidth > this.getWidth())
				this.setWidth(newWidth);
		}
		
		//checking whether we have a method whose name is wider than the class' name
		for(MyMethod m : this.getMethods())
		{
			String completeName = m.getFullName();
			int newWidth = g.getFontMetrics().stringWidth(completeName);
			if(newWidth > this.getWidth())
				this.setWidth(newWidth);
		}
		
		this.setWidth(this.getWidth() + 20);
	}
	
	public GraphicalMyClass(String name, int x, int y, int panelWidth, int panelHeight)
	{
		super(name);
		this.setX(x);
		this.setY(y);
		this.setPanelWidth(panelWidth);
		this.setPanelHeight(panelHeight);
		this.borderColor = Color.blue;
	}
	
	public boolean beenClicked(int x, int y) {
		if(x >= this.x && x <= (this.x + width) && y >= this.y && y <= (this.y + height)) {
			this.borderColor = Color.blue;
			return true;
		}
		else {
			this.borderColor = Color.black;
			return false;
		}
	}
}
