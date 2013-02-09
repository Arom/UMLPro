package project.code;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class ClassRelationship implements Serializable
{
	//instance variables
	private int[] x;
	private int[] y;
	private int cX;
	private int cY;
	private GraphicalMyClass[] myClass;
	private String cardinality;
	
	public int[] getX() 
	{
		return this.x;
	}
	public void setX(int[] x) 
	{
		this.x = x;
	}
	public int[] getY() 
	{
		return this.y;
	}
	public void setY(int[] y) 
	{
		this.y = y;
	}
	public int getcX() 
	{
		return this.cX;
	}
	public void setcX(int cX) 
	{
		this.cX = cX;
	}
	public int getcY() 
	{
		return this.cY;
	}
	public void setcY(int cY) 
	{
		this.cY = cY;
	}
	public GraphicalMyClass[] getMyClass() 
	{
		return this.myClass;
	}
	public void setMyClass(GraphicalMyClass[] myClass) 
	{
		this.myClass = myClass;
	}
	public String getCardinality() 
	{
		return this.cardinality;
	}
	public void setCardinality(String cardinality) 
	{
		this.cardinality = cardinality;
	}
	
	//real methods
	public ClassRelationship()
	{
		this.setX(new int[2]);
		this.setY(new int[2]);
		this.setMyClass(new GraphicalMyClass[2]);
	}
	public void calculateXY()
	{
		int class1X = this.getMyClass()[0].getX();
		int class1Y = this.getMyClass()[0].getY();
		int class2X = this.getMyClass()[1].getX();
		int class2Y = this.getMyClass()[1].getY();
		
		int class1W = this.getMyClass()[0].getWidth();
		int class1H = this.getMyClass()[0].getHeight();
		int class2W = this.getMyClass()[1].getWidth();
		int class2H = this.getMyClass()[1].getHeight();
		
		int[][] x = new int[2][3];
		int[][] y = new int[2][3];
		
		x[0][0] = class1X;
		x[0][1] = class1X + (class1W / 2);
		x[0][2] = class1X + class1W;
		
		x[1][0] = class2X;
		x[1][1] = class2X + (class2W / 2);
		x[1][2] = class2X + class2W;
		
		y[0][0] = class1Y + (class1H / 2);
		y[0][1] = class1Y;
		y[0][2] = class1Y + class1H;
		
		y[1][0] = class2Y + (class2H / 2);
		y[1][1] = class2Y;
		y[1][2] = class2Y + class2H;
		
		int difference = this.calculateDifference(x[0][2], y[0][0], x[1][0], y[1][0]);
		this.getX()[0] = x[0][2]; this.getY()[0] = y[0][0];
		this.getX()[1] = x[1][0]; this.getY()[1] = y[1][0];
		this.setcX(this.getX()[1] - 35); this.setcY(this.getY()[1]);
		
		if(this.calculateDifference(x[0][1], y[0][2], x[1][1], y[1][1]) < difference)
		{
			difference = this.calculateDifference(x[0][1], y[0][2], x[1][1], y[1][1]);
			this.getX()[0] = x[0][1]; this.getY()[0] = y[0][2];
			this.getX()[1] = x[1][1]; this.getY()[1] = y[1][1];
			this.setcX(this.getX()[1]); this.setcY(this.getY()[1] - 15);
		}
		if(this.calculateDifference(x[0][1], y[0][1], x[1][1], y[1][2]) < difference)
		{
			difference = this.calculateDifference(x[0][1], y[0][1], x[1][1], y[1][2]);
			this.getX()[0] = x[0][1]; this.getY()[0] = y[0][1];
			this.getX()[1] = x[1][1]; this.getY()[1] = y[1][2];
			this.setcX(this.getX()[1]); this.setcY(this.getY()[1] + 15);
		}
		if(this.calculateDifference(x[0][0], y[0][0], x[1][2], y[1][0]) < difference)
		{
			difference = this.calculateDifference(x[0][0], y[0][0], x[1][2], y[1][0]);
			this.getX()[0] = x[0][0]; this.getY()[0] = y[0][0];
			this.getX()[1] = x[1][2]; this.getY()[1] = y[1][0];
			this.setcX(this.getX()[1] + 10); this.setcY(this.getY()[1]);
		}
	}
	
	public int calculateDifference(int x1, int y1, int x2, int y2)
	{
		int dif[] = new int[2];
		dif[0] = Math.abs((x1 - x2));
		dif[1] = Math.abs((y1 - y2));
		int toReturn = (int)(Math.sqrt((dif[0] * dif[0]) + (dif[1] * dif[1])));
		return toReturn;
	}
}
