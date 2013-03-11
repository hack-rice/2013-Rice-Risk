 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;


public class BuyMenu extends JFrame implements ActionListener{
  //private JPanel label_input;
  private ArrayList<JFormattedTextField> textFields;

  private ArrayList<AUnit> unitTypes;
  private ArrayList<AUnit> units;
  private GameBoard g;
  private Nodes receiver;
  private Player buyer;
  private Nodes[] buildings;
  
  public BuyMenu(Player p, Nodes receiving_node, GameBoard gb, Nodes[] b){
    buyer = p;
    buildings = b;
    receiver = receiving_node;
    g = gb;
    g.updateTurnPanel();
    setTitle("How many units do you want to buy?");
    
    unitTypes = new ArrayList<AUnit>();
    unitTypes.add(new Freshmen());
    unitTypes.add(new Streaker());
    unitTypes.add(new Drunks());
    unitTypes.add(new Engineers());
    unitTypes.add(new Athlete());
    
    textFields = new ArrayList<JFormattedTextField>(unitTypes.size());
    for (int i = 0; i < unitTypes.size();i++){
      textFields.add(new JFormattedTextField(NumberFormat.getIntegerInstance()));
    }

    
    ArrayList<JPanel> panels = new ArrayList<JPanel>();
    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    setLayout(new GridLayout(unitTypes.size() + 2,1));
    
    JPanel money = new JPanel();
    JLabel howMuch = new JLabel("You have " + p.getMoney() + " dollars to spend");
    money.add(howMuch);
    this.add(money);
    
    // Create the entry fields for how many units to move
    for (int i = 0; i < unitTypes.size();i++){
      panels.add(new JPanel());
      //JPanel label_input = new JPanel();
      //JLabel label = new JLabel("How many of your " + unitTypes.get(i).getNumber() + " " + unitTypes.get(i).getName() + " do you want to move?");
      labels.add(new JLabel("How many " + unitTypes.get(i).getType() + " do you want to buy ($"+unitTypes.get(i).getCost()+"each)?"));
      //label_input.setLayout(new GridLayout(unitTypes.size(),2));
      //panels.get(i).setLayout(new GridLayout(unitTypes.size(),2));
      textFields.get(i).setColumns(10);
      textFields.get(i).addActionListener(this);
      /*
       label_input.add(label);
       label_input.add(textFields.get(i));
       this.add(label_input, BorderLayout.NORTH); */
      panels.get(i).add(labels.get(i));
      panels.get(i).add(textFields.get(i));
      this.add(panels.get(i),BorderLayout.AFTER_LAST_LINE);
      pack();
    }
    
    // Create the two buttons
    JPanel submitPanel = new JPanel();
    submitPanel.setLayout(new FlowLayout());
    JButton move = new JButton("Buy Units");
    move.addActionListener(this);
    submitPanel.add(move);
    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(this);
    submitPanel.add(cancel);
    //this.add(submitPanel,BorderLayout.CENTER);
    this.add(submitPanel);
    
    this.setSize(320,500);
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    
    //Set window to middle of screen
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int w = this.getSize().width;
    int h = this.getSize().height;
    int x = (dim.width-w)/2;
    int y = (dim.height-h)/2;
    this.setLocation(x, y);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Buy Units"))
    {
      for (int i = 0; i < unitTypes.size();i++){
        try{
          Long numberToMove = (Long)textFields.get(i).getValue();
         //System.out.println("Found " + numberToMove + " " + unitTypes.get(i).getName());
          
          while(numberToMove > 0){
            if (buyer.getMoney() >= unitTypes.get(i).getCost()){
              buyer.changeMoney(-1*unitTypes.get(i).getCost());
              if (unitTypes.get(i).getType()=="Freshmen"){
                  receiver.addUnit(new Freshmen());
                  numberToMove--;
                  }
              if (unitTypes.get(i).getType().equals("Engineers")){
                  receiver.addUnit(new Engineers());
                  numberToMove--;
                  }
              if (unitTypes.get(i).getType().equals("Streaker")){
                  receiver.addUnit(new Streaker());
                  numberToMove--;
                  }
              if (unitTypes.get(i).getType().equals("Athlete")){
                  receiver.addUnit(new Athlete());
                  numberToMove--;
                  }
              if (unitTypes.get(i).getType().equals("Drunks")){
                  receiver.addUnit(new Drunks());
                  numberToMove--;
                  }
            }
            else{
              break;
            }
          }
        }
        catch (Exception except){}
        g.updateTurnPanel();
        g.updateSidePanel(receiver,buildings);
        this.dispose();
      }
      
    }
    
    
    if(e.getActionCommand().equals("Cancel"))
    {
      this.dispose();
    }
  }
  
  
  
}