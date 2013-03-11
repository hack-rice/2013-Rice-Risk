 

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math.*;
import java.util.Random;



public class Battle {
  private Random rand;
  private Nodes[]buildings;
  private GameBoard g;
  private Nodes attack;
  private Nodes defend;
  private int attackLost = 0;
  private int defendLost = 0;
  private Boolean result = false;
  
  public Battle(Nodes attacks, Nodes defense, GameBoard gb, Nodes[]build){
    attack = attacks;
    defend = defense;
    rand = new Random();
    buildings = build;
    g = gb;
    
    
  }
  
  
  public int[] rollDice(){
    int[] rollList = new int[5];
    int highAttack = 0;
    int highDefense = 0;
    int lowAttack = 100;
    int lowDefense = 100;
    SoundClip d = new SoundClip("dicerollsound.wav");
    
    for (int i = 0; i < 3; i++){
      int roll = dice();
      rollList[i] = roll; 
      if (roll > highAttack){
        highAttack = roll;
      }
      if (roll > lowAttack && roll != highAttack ){
        lowAttack = roll;
      }
    }
    if (lowAttack == 100){
      lowAttack = highAttack;
    }
    
    rollList[3] = dice();
    rollList[4] = dice();
    if(rollList[3]>rollList[4])
    {
      highDefense = rollList[3];
      lowDefense = rollList[4];
    }
    else
    {
      highDefense = rollList[4];
      lowDefense = rollList[3];
    }
    double attackValue = 0;
    double defendValue = 0;
    if(defend.getUnits().size()>0 && attack.getUnits().size()>0)
    {
      
      for (int i = 0; i < attack.getUnits().size(); i++){
        attackValue += attack.getUnits().get(i).getAttack();
      }
      attackValue = attackValue/attack.getUnits().size();
      for (int i = 0; i < defend.getUnits().size();i++){
        defendValue += defend.getUnits().get(i).getDefense();
      }
      double newHighAttack = 0.0;
      double newHighDefense = 0.0;
      defendValue = defendValue/defend.getUnits().size();
      if (highAttack == 6){newHighAttack = 1.5;}if (highAttack == 5){newHighAttack = 1.25;}if (highAttack == 4){newHighAttack = 1;}
      if (highAttack == 3){newHighAttack = 1;}if (highAttack == 2){newHighAttack = .75;}if (highAttack == 1){newHighAttack = .5;}
      if (highDefense == 6){newHighDefense = 1.5;}if (highDefense == 5){newHighDefense = 1.25;}if (highDefense == 4){newHighDefense = 1;}
      if (highDefense == 3){newHighDefense = 1;}if (highDefense == 2){newHighDefense = .75;}if (highDefense == 1){newHighDefense = .5;}
      
      if (newHighAttack * attackValue > newHighDefense * defendValue){
        defend.removeUnit(defend.getUnits().get(0));
        defendLost ++;
      }
      if (newHighAttack * attackValue < newHighDefense * defendValue){
        attack.removeUnit(attack.getUnits().get(0));
        attackLost++;
      }
      if (newHighAttack * attackValue == newHighDefense * defendValue){
        attack.removeUnit(attack.getUnits().get(0));
        attackLost++;
      }
    }
    if(defend.getUnits().size()>0 && attack.getUnits().size()>0)
    {
      double newLowAttack = 0.0;
      double newLowDefense = 0.0;
      if (lowAttack == 6){newLowAttack = 1.5;}if (lowAttack == 5){newLowAttack = 1.25;}if (lowAttack == 4){newLowAttack = 1;}
      if (lowAttack == 3){newLowAttack = 1;}if (lowAttack == 2){newLowAttack = .75;}if (lowAttack == 1){newLowAttack = .5;}
      if (lowDefense == 6){newLowDefense = 1.5;}if (lowDefense == 5){newLowDefense = 1.25;}if (lowDefense == 4){newLowDefense = 1;}
      if (lowDefense == 3){newLowDefense = 1;}if (lowDefense == 2){newLowDefense = .75;}if (lowDefense == 1){newLowDefense = .5;}
      
      if (newLowAttack * attackValue > newLowDefense * defendValue){
        defend.removeUnit(defend.getUnits().get(0));
        defendLost ++;
      }
      if (newLowAttack * attackValue < newLowDefense * defendValue){
        attack.removeUnit(attack.getUnits().get(0));
        attackLost++;
      }
      if (newLowAttack * attackValue == newLowDefense * defendValue){
        attack.removeUnit(attack.getUnits().get(0));
        attackLost++;
      }
    }
    
    if (defend.getUnits().size() == 0 ){
      defend.setOwner(attack.getOwner());
      result = true;
    }
    g.updateSidePanel(defend, buildings);
    return rollList;
  }
  
  
  public int dice(){
    return rand.nextInt(6)+1;
  }
  
  public Boolean getResult(){
    return result;
  }
  public int getAttackLost(){
    return attackLost;
  }
  public int getDefendLost(){
    return defendLost;
  }
  public Nodes getAttacker()
  {
    return attack;
  }
  public Nodes getDefender()
  {
    return defend;
  }
  public Nodes[] getBuildingList()
  {
    return buildings;
  }
  public GameBoard getGameBoard()
  {
    return g;
  }
}