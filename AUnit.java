 
public abstract class AUnit {
  String type;
  int attack;
  int health;
  int defense;
  String SpecialAbility;
  int moves;
  int cost;
  
  public AUnit(int a, int d, int h, String t, String s, int c){
    setAttack(a);
    setDefense(d);
    setHealth(h);
    type = t;
    setAbility(s);
    moves = 1;
    cost = c;
  }
  public void resetMoves(){
    moves = 1;
  }
  public int getCost(){
	  return cost;
  }
  public void reduceMoves(){
    moves--;
  }
  public int getMoves(){
    return moves;
  }
  public void setAttack(int a){
    attack = a;
  }
  public int getAttack(){
    return attack;
  }
  public void setDefense(int d){
    defense = d;
  }
  public int getDefense(){
    return defense;
  }
  public void setHealth(int h){
    health = h;
  }
  public int getHealth(){
    return health;
  }
  public void setAbility(String s){
    SpecialAbility = s;
  }
  public String getAbility(){
    return SpecialAbility;
  }
  public String getType(){
    return type;
  }
}
