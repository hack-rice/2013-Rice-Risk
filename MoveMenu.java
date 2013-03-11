 

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


public class MoveMenu extends JFrame implements ActionListener{
  //private JPanel label_input;
  private ArrayList<JFormattedTextField> textFields;
  //private JFormattedTextField[] textFields;
  private Nodes sender;
  private Nodes receiver;
  private ArrayList<Tuple> unitTypes;
  private ArrayList<AUnit> units;
  private GameBoard g;
  private Nodes[] Buildings;
  
  public MoveMenu(Nodes inputNode, Nodes outputNode, Nodes[] Building, GameBoard gb){
    g = gb;
	sender = inputNode;
    receiver = outputNode;
    setTitle("How many units do you want to move?");
    unitTypes = new ArrayList<Tuple>();
    units = inputNode.getUnits();
    Buildings = Building;
    //System.out.println(units.size() + " units found");
    for (int i = 0; i < units.size();i++){
      if (units.get(i).getMoves() > 0){
        String name = units.get(i).getType();
        Boolean newType = true;
        for (int j = 0; j < unitTypes.size();j++){
          if (unitTypes.get(j).getName().equals(name)){
            newType = false;
            unitTypes.get(j).increment_number();
            break;
          }
        }
        if (newType == true){
          unitTypes.add(new Tuple(new String(units.get(i).getType()),new Integer(1)));
        }
      }
    }
    textFields = new ArrayList<JFormattedTextField>(unitTypes.size());
    for (int i = 0; i < unitTypes.size();i++){
      textFields.add(new JFormattedTextField(NumberFormat.getIntegerInstance()));
    }
    //new GridLayout(2,2)
    
    ArrayList<JPanel> panels = new ArrayList<JPanel>();
    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    setLayout(new GridLayout(unitTypes.size() + 1,1));
    // Create the entry fields for how many units to move
    for (int i = 0; i < unitTypes.size();i++){
      panels.add(new JPanel());
      //JPanel label_input = new JPanel();
      //JLabel label = new JLabel("How many of your " + unitTypes.get(i).getNumber() + " " + unitTypes.get(i).getName() + " do you want to move?");
      labels.add(new JLabel("How many of your " + unitTypes.get(i).getNumber() + " " + unitTypes.get(i).getName() + " do you want to move?"));
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
    JButton move = new JButton("Move Units");
    move.addActionListener(this);
    submitPanel.add(move);
    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(this);
    submitPanel.add(cancel);
    //this.add(submitPanel,BorderLayout.CENTER);
    this.add(submitPanel);
    
    this.setSize(325,300);
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
    if(e.getActionCommand().equals("Move Units"))
    {
        for (int i = 0; i < unitTypes.size();i++){
          try{
            Long numberToMove = (Long)textFields.get(i).getValue();
            //System.out.println("Found " + numberToMove + " " + unitTypes.get(i).getName());
            
            Boolean typeFound = true;
            if(numberToMove<1)
            {
            	numberToMove = new Long(1);
            }
            else if(numberToMove>=units.size())
            {
            	numberToMove = new Long(units.size() - 1);
            }
            while(numberToMove > 0 && typeFound == true){
              typeFound = false;
              ArrayList<AUnit> inputUnits = sender.getUnits();
              for (int j = 0; j < inputUnits.size();j++){
                if (inputUnits.get(j).getMoves() > 0){
                  if (inputUnits.get(j).getType().equals(unitTypes.get(i).getName())){
                    typeFound = true;
                    inputUnits.get(j).reduceMoves();
                    receiver.addUnit(inputUnits.get(j));
                    sender.removeUnit(inputUnits.get(j));
                    numberToMove--;
                    break;
                  }
                }
              }
              
            }
        }
        catch (Exception except){}
        g.resetMove();
          
        this.dispose();
        }
        
      }
      
    
    if(e.getActionCommand().equals("Cancel"))
    {
      this.dispose();
    }
    g.updateSidePanel(sender, Buildings);
  }
  
 

}