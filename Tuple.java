 
public class Tuple{
  private String name;
  private Integer number;
  
  public Tuple(String name_input, Integer number_input){
    name = name_input;
    number = number_input;
  }
  
  public void increment_number(){
    number++;
  }
  public String getName(){
    return name;
  }
  
  public Integer getNumber(){
    return number;
  }
  
}