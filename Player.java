 

import java.awt.Color;

public class Player
{
	private String name;
	private String college;
	private Color color;
	private int money;
	
	public Player(String n, String startc, String c)
	{
		name = n;
		college = startc;
		money = 0;
		if(c == "Green")
		{
			color = new Color(0,255,0,100);
		}
		else if(c == "Blue")
		{
			color = new Color(0,0,255,100);
		}
		else if(c == "Red")
		{
			color = new Color(255,0,0,100);
		}
		else if(c == "Yellow")
		{
			color = new Color(255,255,0,100);
		}
		else
		{
			color = new Color(128, 128, 128, 100);
		}
	}
	
	public void setColor(Color c)
	{
		color = c;
	}
	public Color getColor()
	{
		return color;
	}
	public String getCollege()
	{
		return college;
	}
	public int getMoney()
	{
		return money;
	}
	public void changeMoney(int m)
	{
		money += m;
	}
	public String getName()
	{
		return name;
	}
}
