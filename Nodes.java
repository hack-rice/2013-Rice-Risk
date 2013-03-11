 

import java.util.*;
public class Nodes
{
	ArrayList<String> neighbors = new ArrayList<String>();
	ArrayList<AUnit> units = new ArrayList<AUnit>();
	private int Nodevalue = 0;
	private String name = "";
	private Player owner;
	private String SpecialAbility = "";
	private int streakers;
	private int drunks;
	private int athletes;
	private int freshmen;
	private int engineers;
	private TransButton button;
	
public Nodes(String node, List<String> nbrs, List<AUnit> unit, String Special, int Value, Player own){
	// Creates a node name node, neighbors nbr, units unit, ability code Special, owner own, and value Value
	neighbors.addAll(nbrs);
	units.addAll(unit);
	Nodevalue = Value;
	name = node;
	owner = own;
	SpecialAbility = Special;
	for(AUnit i:unit)
	{
		if(i instanceof Streaker)
		{
			streakers ++;
		}
		else if(i instanceof Drunks)
		{
			drunks ++;
		}
		else if(i instanceof Athlete)
		{
			athletes ++;
		}
		else if(i instanceof Engineers)
		{
			engineers ++;
		}
		else
		{
			freshmen ++;
		}
	}
}

public void setNeighbors(List<String> N){
	neighbors.addAll(N);
}

public void setButton(TransButton b){
	button = b;
}
public TransButton getButton(){
	return button;
}
public ArrayList<String> getNeighbors(){
	return neighbors;
}

public void addUnit(AUnit i){
	units.add(i);
	if(i instanceof Streaker)
	{
		streakers ++;
	}
	else if(i instanceof Drunks)
	{
		drunks ++;
	}
	else if(i instanceof Athlete)
	{
		athletes ++;
	}
	else if(i instanceof Engineers)
	{
		engineers ++;
	}
	else
	{
		freshmen ++;
	}
}
public void removeUnit(AUnit i){
	units.remove(i);
	if(i instanceof Streaker)
	{
		streakers --;
	}
	else if(i instanceof Drunks)
	{
		drunks --;
	}
	else if(i instanceof Athlete)
	{
		athletes --;
	}
	else if(i instanceof Engineers)
	{
		engineers --;
	}
	else
	{
		freshmen --;
	}
}

public ArrayList<AUnit> getUnits(){
	return units;
}

public int getValue(){
	return Nodevalue;
}

public String getSpecialAbility(){
	return SpecialAbility;
}
public void setOwner(Player own){
	owner = own;
	button.setBackground(own.getColor());
}
public Player getOwner(){
	return owner;
}

public int getStreakers()
{
	return streakers;
}
public int getAthletes()
{
	return athletes;
}
public int getFreshmen()
{
	return freshmen;
}
public int getDrunks()
{
	return drunks;
}
public int getEngineers()
{
	return engineers;
}
public String getName(){
	return name;
}
}
