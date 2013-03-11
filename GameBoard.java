 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.math.*;

public class GameBoard
{
    private Player p1;
    private Player p2;
    private Player neutral;
    private JFrame frame;
    private Container pane;
    private JPanel sidePanel;
    private JPanel actionPanel;
    private JPanel turnPanel;
    private Counter counter;
    
    private Nodes Baker;
    private Nodes Brown;
    private Nodes Hanszen;
    private Nodes Wiess;
    private Nodes Jones;
    private Nodes McMurtry;
    private Nodes Duncan;
    private Nodes WillRice;
    private Nodes Lovett;
    private Nodes SidRich;
    private Nodes Martel;
    private Nodes LovettHall;
    private Nodes Herzstein;
    private Nodes Sewall;
    private Nodes Rayzor;
    private Nodes AndersonHall;
    private Nodes DuncanHall;
    private Nodes Abercrombie;
    private Nodes Fondren;
    private Nodes HandD;
    private Nodes Shepard;
    private Nodes JonesBusiness;
    private Nodes RMC;
    private Nodes Rec;
    private Nodes Tudor;
    private Nodes Brockman;
    private Nodes Brochstein;
    private Nodes Football;
    private Nodes Baseball;
    private Nodes AndersonLabs;
    private Nodes Soccer;
    private Nodes RUPD;
    private Nodes Herring;
    private Nodes BakerInstitute;
    private Nodes HermanBrown;
    private Nodes Keck;
    private Nodes Mech;
    private Nodes HUMA;
    private Nodes Hamman;
    private Nodes GeorgeRBrown;
    private Nodes CohenHouse;
    private Nodes AllenCenter;
    private Nodes DellButcher;
    Boolean move_clicked = false;
    Boolean attack_clicked = false;
    Nodes prev_node;
    
    public GameBoard(){
        //Get Player info (name and starting college)
        counter = new Counter();
        String name = JOptionPane.showInputDialog("Player 1, enter your name!");
        Object[] collegeList = {"Baker", "Brown","Duncan","Hanszen","Jones","Lovett", "Martel", "McMurtry","Sid Rich","Wiess", "Will Rice"};
        String college = (String)JOptionPane.showInputDialog(null, "Player 1, choose your college! (or Martel Dormatory)", "College Select", JOptionPane.INFORMATION_MESSAGE, null, collegeList, collegeList[0]);
        Object[] colorList = {"Blue", "Green", "Red", "Yellow"};
        String color = (String)JOptionPane.showInputDialog(null, "Player 1, choose your color!", "Color Select", JOptionPane.INFORMATION_MESSAGE, null, colorList, colorList[0]);
        p1 = new Player(name, college, color);
        String name2 = JOptionPane.showInputDialog("Player 2, enter your name!");
        String college2 = (String)JOptionPane.showInputDialog(null, "Player 2, choose your college! (or Martel Dormatory)", "College Select", JOptionPane.INFORMATION_MESSAGE, null, collegeList, collegeList[0]);
        while(college2 == college)
        {
            college2 = (String)JOptionPane.showInputDialog(null, "Player 1 chose that college. You must choose diferently!", "College Select", JOptionPane.INFORMATION_MESSAGE, null, collegeList, collegeList[0]);
        }
        String color2 = (String)JOptionPane.showInputDialog(null, "Player 2, choose your color!", "Color Select", JOptionPane.INFORMATION_MESSAGE, null, colorList, colorList[0]);
        while(color2 == color)
        {
            color2 = (String)JOptionPane.showInputDialog(null, "Player 1 chose that color. You must choose diferently!", "Color Select", JOptionPane.INFORMATION_MESSAGE, null, colorList, colorList[0]);
        }
        p2 = new Player(name2, college2, color2);
        neutral = new Player("Neutral", null, "GREY");
        //Main Gameboard Window
        frame = new JFrame();
        pane = frame.getContentPane();
        
        //Center Map Panel
        try {
            JPanel mapPanel = new JPanel(){
                //java.net.URL mapURL = getClass().getResource("/img/RiceRiskMap.png");
                BufferedImage map = ImageIO.read(new File("RiceRiskMap_NEW.png"));
                public void paintComponent(Graphics g){
                    super.paintComponent(g);
                    //Image map = Toolkit.getDefaultToolkit().getImage(mapURL);
                    g.drawImage(map, 0, 0, 1367, 752, this);
                }
            };
            mapPanel.setLayout(null);
            createNodes();
            createButtons(mapPanel);
            frame.add(mapPanel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Side Panel
        sidePanel = new JPanel();
        sidePanel.add(  new JLabel("Select a Location"));
        pane.add(sidePanel, BorderLayout.LINE_END);
        
        //Action Panel
        actionPanel = new JPanel();
        actionPanel.add(new JLabel("Select a Location"));
        pane.add(actionPanel, BorderLayout.PAGE_END);
        
        //Turn Panel
        turnPanel = new JPanel();
        updateTurnPanel();
        pane.add(turnPanel, BorderLayout.PAGE_START);
        
        //Display Window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1500);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void createNodes()
    {
                ArrayList<AUnit> bunit = new ArrayList<AUnit>();
                bunit.add(new Freshmen());
                bunit.add(new Freshmen());
                Baker = new Nodes("Baker", Arrays.asList("Rayzor", "HandD", "WillRice", "Hanszen", "Wiess", "SidRich", "Lovett", "HUMA", "AllenCenter", "CohenHouse","Sewall"), bunit, "Baker 13", 1, neutral);
                Brown = new Nodes("Brown", Arrays.asList("Jones", "McMurtry", "Martel", "Duncan"), bunit, "Brown Defense", 1, neutral);
                Hanszen = new Nodes("Hanszen", Arrays.asList("CohenHouse", "AllenCenter","HandD", "WillRice", "Wiess", "SidRich", "Lovett", "Baker"), bunit, "None", 1, neutral);
                Wiess = new Nodes("Wiess", Arrays.asList("HandD", "WillRice", "Hanszen", "CohenHouse","AllenCenter","SidRich", "Lovett", "Baker", "Soccer"), bunit, "All the money", 1, neutral);
                Jones = new Nodes("Jones", Arrays.asList("Brown", "McMurtry", "Martel", "Duncan"), bunit, "None", 1, neutral);
                McMurtry = new Nodes("McMurtry", Arrays.asList("Brown", "Jones","Martel", "Duncan", "DuncanHall", "Herzstein"), bunit, "None", 1, neutral);
                Duncan = new Nodes("Duncan", Arrays.asList("Brown", "Jones", "McMurtry", "Martel"), bunit, "None", 1, neutral);
                WillRice = new Nodes("WillRice", Arrays.asList("HandD", "Hanszen", "Wiess", "SidRich", "Lovett", "AllenCenter","CohenHouse","Baker"),bunit, "None", 1, neutral);
                Lovett = new Nodes("Lovett", Arrays.asList("HandD", "WillRice", "Hanszen", "Wiess", "SidRich", "Baker","CohenHouse","AllenCenter"), bunit, "None", 1, neutral);
                SidRich = new Nodes("SidRich", Arrays.asList("HandD", "WillRice", "Hanszen", "Wiess", "Lovett", "Baker","CohenHouse","AllenCenter"), bunit, "None", 1, neutral);
                Martel = new Nodes("Martel", Arrays.asList("Brown", "Jones", "McMurtry","Duncan"), bunit, "None", 1, neutral);
                LovettHall = new Nodes("LovettHall", Arrays.asList("HUMA","Herzstein", "Rayzor", "Fondren", "Herring", "Brochstein", "RMC", "AndersonHall", "Sewall"), bunit, "None", 1, neutral);
                Herzstein = new Nodes("Herzstein", Arrays.asList("HUMA","LovettHall", "Rayzor", "Fondren", "Herring", "Brochstein", "RMC", "AndersonHall", "Sewall", "DuncanHall", "McMurtry"), bunit, "None", 1, neutral);
                Sewall = new Nodes("Sewall", Arrays.asList("HUMA","LovettHall", "Herzstein", "Rayzor", "Fondren", "Herring", "Brochstein", "RMC", "AndersonHall", "AllenCenter", "CohenHouse"), bunit, "None", 1, neutral);
                Rayzor = new Nodes("Rayzor", Arrays.asList("HUMA","LovettHall", "Herzstein", "Fondren", "Herring", "Brochstein", "RMC", "AndersonHall", "Sewall"), bunit, "None", 1, neutral);
                Fondren = new Nodes("Fondren", Arrays.asList("HUMA","LovettHall", "Herzstein", "Rayzor", "Herring", "Brochstein", "RMC", "AndersonHall", "Sewall"), bunit, "None", 1, neutral);
                AndersonHall = new Nodes("AndersonHall", Arrays.asList("HUMA","LovettHall", "Herzstein", "Rayzor", "Fondren", "Herring", "Brochstein", "RMC", "Keck", "Sewall"), bunit, "None", 1, neutral);
                DuncanHall = new Nodes("DuncanHall", Arrays.asList("Abercrombie","DellButcher", "Herzstein", "McMurtry", "Keck", "AndersonLabs", "Hamman", "Brockman", "Mech", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
                Abercrombie = new Nodes("Abercrombie", Arrays.asList("DellButcher","DuncanHall", "Keck", "AndersonLabs", "Hamman", "Brockman", "Mech", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
                HandD = new Nodes("HandD", Arrays.asList("WillRice", "Hanszen", "Herring", "Wiess", "SidRich", "Lovett", "Baker","CohenHouse", "AllenCenter"), bunit, "None", 1, neutral);
                Shepard = new Nodes("Shepard", Arrays.asList("RUPD", "Football", "Tudor", "JonesBusiness", "BakerInstitute", "Rec"), bunit, "None", 1, neutral);
                JonesBusiness = new Nodes("JonesBusiness", Arrays.asList("Rec", "BakerInstitute", "RMC", "AndersonLabs", "Shepard"), bunit, "None", 1, neutral);
                RMC = new Nodes("RMC", Arrays.asList("HUMA", "LovettHall", "Herzstein", "Rayzor", "Fondren", "Herring", "Brochstein", "JonesBuisness", "Rec","AndersonLabs","AndersonHall", "Sewall"), bunit, "None", 1, neutral);
                Rec = new Nodes("Rec", Arrays.asList("JonesBusiness", "AndersonLabs", "RMC", "Shepard", "BakerInstitute"), bunit, "None", 1, neutral);
                Tudor = new Nodes("Tudor", Arrays.asList("RUPD", "Football", "Baseball", "Soccer", "Shepard"), bunit, "None", 1, neutral);
                Brockman = new Nodes("Brockman", Arrays.asList("DellButcher","Abercrombie", "DuncanHall", "Keck", "AndersonLabs", "Hamman", "Mech", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
                Brochstein = new Nodes("Brochstein", Arrays.asList("LovettHall", "Herzstein", "Rayzor", "Fondren", "Herring", "RMC", "AndersonHall", "Sewall", "HUMA"), bunit, "None", 1, neutral);
                Football = new Nodes("Football", Arrays.asList("RUPD", "Shepard", "Baseball", "Soccer", "Tudor"), bunit, "None", 1, neutral);
                Baseball = new Nodes("Baseball", Arrays.asList("RUPD", "Football", "Soccer", "Tudor"), bunit, "None", 1, neutral);
                AndersonLabs = new Nodes("AndersonLabs", Arrays.asList("DellButcher","Abercrombie", "DuncanHall", "Keck", "RMC", "Rec", "JonesBuisness", "Hamman", "Brockman", "Mech", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
                Soccer = new Nodes("Soccer", Arrays.asList("Wiess", "Football", "Baseball", "Tudor"), bunit, "None", 1, neutral);
                RUPD = new Nodes("RUPD", Arrays.asList("Shepard", "Football", "Baseball", "Soccer", "Tudor"), bunit, "None", 1, neutral);
                Herring = new Nodes("Herring", Arrays.asList("HandD", "LovettHall", "Herzstein", "Rayzor", "Fondren", "BakerInstitute", "Brochstein", "RMC", "AndersonHall", "Sewall", "HUMA"), bunit, "None", 1, neutral);
                BakerInstitute = new Nodes("BakerInstitute", Arrays.asList("Shepard", "JonesBusiness", "Herring", "Rec"), bunit, "None", 1, neutral);
                HermanBrown = new Nodes("HermanBrown", Arrays.asList("Abercrombie", "DuncanHall", "Keck", "AndersonLabs", "Hamman", "Brockman", "Mech", "GeorgeRBrown", "HearmanBrown"), bunit, "None", 1, neutral);
                Keck = new Nodes("Keck", Arrays.asList("DellButcher","Abercrombie", "DuncanHall", "AndersonHall", "AndersonLabs", "Hamman", "Brockman", "Mech", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
                Mech = new Nodes("Mech", Arrays.asList("DellButcher","Abercrombie", "DuncanHall", "Keck", "AndersonLabs", "Hamman", "Brockman", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
                HUMA= new Nodes("HUMA", Arrays.asList("LovettHall", "Herzstein", "Rayzor", "Fondren", "Herring", "Brochstein", "RMC", "AndersonHall", "Sewall", "Baker"), bunit, "None", 1, neutral);
                Hamman = new Nodes("Hamman", Arrays.asList("DellButcher","Abercrombie", "DuncanHall", "Keck", "AndersonLabs", "Mech", "Brockman", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
                GeorgeRBrown= new Nodes("GeorgeRBrown", Arrays.asList("DellButcher","Abercrombie", "DuncanHall", "Keck", "AndersonLabs", "Hamman", "Brockman", "Mech", "HermanBrown"), bunit, "None", 1, neutral);
                CohenHouse = new Nodes("CohenHouse", Arrays.asList("AllenCenter", "Sewall", "Lovett", "WillRice", "Wiess", "SidRich", "Hanszen", "HandD", "Baker"), bunit, "None", 1, neutral);
                AllenCenter= new Nodes("AllenCenter", Arrays.asList("CohenHouse", "Baker", "Sewall", "Hanszen", "WillRice", "SidRich", "Lovett", "Wiess", "HandD"), bunit, "None", 1, neutral);
                DellButcher= new Nodes("DellButcher", Arrays.asList("Hamman","Abercrombie", "DuncanHall", "Keck", "AndersonLabs", "Mech", "Brockman", "GeorgeRBrown", "HermanBrown"), bunit, "None", 1, neutral);
    }
    public void createButtons(JPanel mapPanel)
    {
        final Nodes[] BuildingList = {Baker, Brown, Hanszen, Wiess, Jones, McMurtry, Duncan, Lovett, WillRice, SidRich, Martel, LovettHall, Herzstein, Sewall, Rayzor, AndersonHall, DuncanHall, Abercrombie, Fondren, HandD, Shepard, JonesBusiness, RMC, Rec, Tudor, Brockman, Brochstein, Football, Baseball, AndersonLabs, Soccer, RUPD, Herring, BakerInstitute, HermanBrown, Keck, Mech, HUMA, Hamman, GeorgeRBrown, CohenHouse, AllenCenter, DellButcher};
        ArrayList<TransButton> buttons = new ArrayList<TransButton>();
        
        TransButton brown = new TransButton("Brown");
        brown.setBounds(1200, 180, 100, 20);
        buttons.add(brown);
        Brown.setButton(brown);
        brown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateSidePanel(Brown, BuildingList);
            }
        });
        
        TransButton jones = new TransButton("Jones");
        jones.setBounds(1145, 135, 100, 20);
        buttons.add(jones);
        Jones.setButton(jones);
        jones.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateSidePanel(Jones, BuildingList);
            }
        });
        
        TransButton martel = new TransButton("Martel");
        martel.setBounds(1125,75,100,20);
        buttons.add(martel);
        Martel.setButton(martel);
        martel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateSidePanel(Martel, BuildingList);
            }
        });
        
        TransButton mcmurtry = new TransButton("McMurtry");
        mcmurtry.setBounds(1057, 185, 100, 20);
        buttons.add(mcmurtry);
        McMurtry.setButton(mcmurtry);
        mcmurtry.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateSidePanel(McMurtry, BuildingList);
            }
        });
        
        TransButton duncan = new TransButton("Duncan");
        duncan.setBounds(1053, 76, 100, 20);
        buttons.add(duncan);
        Duncan.setButton(duncan);
        duncan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateSidePanel(Duncan, BuildingList);
            }
        });
        
        TransButton baker = new TransButton("Baker");
        baker.setBounds(858, 405, 100, 20);
        buttons.add(baker);
        Baker.setButton(baker);
        baker.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateSidePanel(Baker, BuildingList);
            }
        });
        
        TransButton hanszen = new TransButton("Hanszen");
        hanszen.setBounds(750, 460, 100, 20);
        buttons.add(hanszen);
        Hanszen.setButton(hanszen);
        hanszen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateSidePanel(Hanszen, BuildingList);
            }
        });
        
        TransButton willrice = new TransButton("Will Rice");
        willrice.setBounds(835, 460, 100, 20);
        buttons.add(willrice);
        WillRice.setButton(willrice);
        willrice.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
         {
          updateSidePanel(WillRice, BuildingList);
         }
        });
          
          TransButton wiess = new TransButton("Wiess");
          wiess.setBounds(695, 500, 100, 20);
          buttons.add(wiess);
          Wiess.setButton(wiess);
          wiess.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Wiess, BuildingList);
           }
          });
          
          TransButton lovett = new TransButton("Lovett");
          lovett.setBounds(903, 490, 100, 20);
          buttons.add(lovett);
          Lovett.setButton(lovett);
          lovett.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Lovett, BuildingList);
           }
          });
          
          TransButton sidrich = new TransButton("Sid Rich");
          sidrich.setBounds(811, 547, 100, 20);
          buttons.add(sidrich);
          SidRich.setButton(sidrich);
          sidrich.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(SidRich, BuildingList);
           }
          });
          
          TransButton lovetthall = new TransButton("Lovett Hall");
          lovetthall.setBounds(1010, 290, 100, 20);
          buttons.add(lovetthall);
          LovettHall.setButton(lovetthall);
          lovetthall.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(LovettHall, BuildingList);
           }
          });
          
          TransButton herzstein = new TransButton("Herzstein");
          herzstein.setBounds(970, 260, 100, 20);
          buttons.add(herzstein);
          Herzstein.setButton(herzstein);
          herzstein.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Herzstein, BuildingList);
           }
          });
          
          TransButton sewall = new TransButton("Sewall");
          sewall.setBounds(970, 332, 100, 20);
          buttons.add(sewall);
          Sewall.setButton(sewall);
          sewall.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Sewall, BuildingList);
           }
          });
          
          TransButton rayzor = new TransButton("Rayzor");
          rayzor.setBounds(895, 333, 100, 20);
          buttons.add(rayzor);
          Rayzor.setButton(rayzor);
          rayzor.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Rayzor, BuildingList);
           }
          });
          
          TransButton fondren = new TransButton("Fondren");
          fondren.setBounds(833, 300, 100, 20);
          buttons.add(fondren);
          Fondren.setButton(fondren);
          fondren.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Fondren, BuildingList);
           }
          });
          
          TransButton andersonhall = new TransButton("Anderson Hall");
          andersonhall.setBounds(855, 260, 150, 20);
          buttons.add(andersonhall);
          AndersonHall.setButton(andersonhall);
          andersonhall.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(AndersonHall, BuildingList);
           }
          });
          
          TransButton duncanhall = new TransButton("Duncan Hall");
          duncanhall.setBounds(975, 190, 110, 20);
          buttons.add(duncanhall);
          DuncanHall.setButton(duncanhall);
          duncanhall.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(DuncanHall, BuildingList);
           }
          });
          
          TransButton abercrombie = new TransButton("Abercrombie");
          abercrombie.setBounds(975, 135, 110, 20);
          buttons.add(abercrombie);
          Abercrombie.setButton(abercrombie);
          abercrombie.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Abercrombie, BuildingList);
           }
          });
          
          TransButton handd = new TransButton("H & D");
          handd.setBounds(765, 383, 100, 20);
          buttons.add(handd);
          HandD.setButton(handd);
          handd.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(HandD, BuildingList);
           }
          });
          
          TransButton shepard = new TransButton("Shepard");
          shepard.setBounds(457, 296, 100, 20);
          buttons.add(shepard);
          Shepard.setButton(shepard);
          shepard.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Shepard, BuildingList);
           }
          });
          
          TransButton jonesbusiness = new TransButton("Jones Business");
          jonesbusiness.setBounds(534, 255, 200, 20);
          buttons.add(jonesbusiness);
          JonesBusiness.setButton(jonesbusiness);
          jonesbusiness.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(JonesBusiness, BuildingList);
           }
          });
          
          TransButton rmc = new TransButton("RMC");
          rmc.setBounds(703, 257, 100, 20);
          buttons.add(rmc);
          RMC.setButton(rmc);
          rmc.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(RMC, BuildingList);
           }
          });
          
          TransButton rec = new TransButton("Rec");
          rec.setBounds(610, 185, 100, 20);
          buttons.add(rec);
          Rec.setButton(rec);
          rec.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Rec, BuildingList);
           }
          });
          
          TransButton tudor = new TransButton("Tudor");
          tudor.setBounds(495, 409, 100, 20);
          buttons.add(tudor);
          Tudor.setButton(tudor);
          tudor.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Tudor, BuildingList);
           }
          });
          
          TransButton brockman = new TransButton("Brockman");
          brockman.setBounds(784, 133, 100, 20);
          buttons.add(brockman);
          Brockman.setButton(brockman);
          brockman.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Brockman, BuildingList);
           }
          });
          
          TransButton brochstein = new TransButton("Brochstein");
          brochstein.setBounds(772, 300, 100, 20);
          buttons.add(brochstein);
          Brochstein.setButton(brochstein);
          brochstein.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Brochstein, BuildingList);
           }
          });
          
          TransButton football = new TransButton("Stadium");
          football.setBounds(187, 160, 100, 20);
          buttons.add(football);
          Football.setButton(football);
          football.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Football, BuildingList);
           }
          });
          
          TransButton dellbutcher = new TransButton("Dell Butcher");
          dellbutcher.setBounds(685, 100, 150, 20);
          buttons.add(dellbutcher);
          DellButcher.setButton(dellbutcher);
          dellbutcher.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(DellButcher, BuildingList);
           }
          });
          
          TransButton baseball = new TransButton("Baseball");
          baseball.setBounds(425, 500, 100, 20);
          buttons.add(baseball);
          Baseball.setButton(baseball);
          baseball.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Baseball, BuildingList);
           }
          });
          
          TransButton andersonlabs = new TransButton("Anderson Labs");
          andersonlabs.setBounds(680, 193, 150, 20);
          buttons.add(andersonlabs);
          AndersonLabs.setButton(andersonlabs);
          andersonlabs.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(AndersonLabs, BuildingList);
           }
          });
          
          TransButton soccer = new TransButton("Track");
          soccer.setBounds(535, 610, 100, 20);
          buttons.add(soccer);
          Soccer.setButton(soccer);
          soccer.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Soccer, BuildingList);
           }
          });
          
          TransButton rupd = new TransButton("RUPD");
          rupd.setBounds(270, 401, 100, 20);
          buttons.add(rupd);
          RUPD.setButton(rupd);
          rupd.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(RUPD, BuildingList);
           }
          });
          
          TransButton herring = new TransButton("Herring");
          herring.setBounds(730, 345, 100, 20);
          buttons.add(herring);
          Herring.setButton(herring);
          herring.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Herring, BuildingList);
           }
          });
          
          TransButton bakerinstitute = new TransButton("Baker Institute");
          bakerinstitute.setBounds(565, 330, 200, 20);
          buttons.add(bakerinstitute);
          BakerInstitute.setButton(bakerinstitute);
          bakerinstitute.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(BakerInstitute, BuildingList);
           }
          });
          
          TransButton hermanbrown = new TransButton("Herman Brown");
          hermanbrown.setBounds(852, 145, 125, 20);
          buttons.add(hermanbrown);
          HermanBrown.setButton(hermanbrown);
          hermanbrown.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(HermanBrown, BuildingList);
           }
          });
          
          TransButton keck = new TransButton("Keck");
          keck.setBounds(885, 183, 100, 20);
          buttons.add(keck);
          Keck.setButton(keck);
          keck.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Keck, BuildingList);
           }
          });
          
          TransButton mech = new TransButton("Mech E");
          mech.setBounds(925, 97, 100, 20);
          buttons.add(mech);
          Mech.setButton(mech);
          mech.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Mech, BuildingList);
           }
          });
          
          TransButton huma = new TransButton("HUMA");
          huma.setBounds(850, 345, 100, 20);
          buttons.add(huma);
          HUMA.setButton(huma);
          huma.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(HUMA, BuildingList);
           }
          });
          
          TransButton hamman = new TransButton("Hamman");
          hamman.setBounds(786, 100, 100, 20);
          buttons.add(hamman);
          Hamman.setButton(hamman);
          hamman.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(Hamman, BuildingList);
           }
          });
          
          TransButton georgerbrown = new TransButton("George R Brown");
          georgerbrown.setBounds(762, 175, 150, 20);
          buttons.add(georgerbrown);
          GeorgeRBrown.setButton(georgerbrown);
          georgerbrown.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(GeorgeRBrown, BuildingList);
           }
          });
          
          TransButton cohenhouse = new TransButton("Cohen House");
          cohenhouse.setBounds(1000, 415, 120, 20);
          buttons.add(cohenhouse);
          CohenHouse.setButton(cohenhouse);
          cohenhouse.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(CohenHouse, BuildingList);
           }
          });
          
          TransButton allencenter = new TransButton("Allen Center");
          allencenter.setBounds(938, 384, 150, 20);
          buttons.add(allencenter);
          AllenCenter.setButton(allencenter);
          allencenter.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
            updateSidePanel(AllenCenter, BuildingList);
           }
          });
        
        for(TransButton b: buttons)
        {
            b.setBackground(new Color(200,200,200,150));
            mapPanel.add(b);
        }
        String c1 = p1.getCollege();
        String c2 = p2.getCollege();
        
        if(c1 == "Baker"){
            Baker.setOwner(p1);
        for(int i = 0; i<3; i++){
        Baker.addUnit(new Freshmen());}}
        else if(c1 == "Brown"){
            for(int i = 0; i<3; i++){
        Brown.addUnit(new Freshmen());}
            Brown.setOwner(p1);     }
        else if(c1 == "Jones"){
            for(int i = 0; i<3; i++){
        Jones.addUnit(new Freshmen());}
            Jones.setOwner(p1);     }
        else if(c1 == "Martel"){
            for(int i = 0; i<3; i++){
        Martel.addUnit(new Freshmen());}
            Martel.setOwner(p1);        }
        else if(c1 == "McMurtry"){
            for(int i = 0; i<3; i++){
        McMurtry.addUnit(new Freshmen());}
            McMurtry.setOwner(p1);      }
        else if(c1 == "Duncan"){
            for(int i = 0; i<3; i++){
        Duncan.addUnit(new Freshmen());}
            Duncan.setOwner(p1);        }
        else if(c1 == "Sid Rich"){
            for(int i = 0; i<3; i++){
        SidRich.addUnit(new Freshmen());}
            SidRich.setOwner(p1);       }
        else if(c1 == "Lovett"){
            for(int i = 0; i<3; i++){
        Lovett.addUnit(new Freshmen());}
            Lovett.setOwner(p1);        }
        else if(c1 == "Hanszen"){
            for(int i = 0; i<3; i++){
        Hanszen.addUnit(new Freshmen());}
            Hanszen.setOwner(p1);       }
        else if(c1 == "Wiess"){
            for(int i = 0; i<3; i++){
        Wiess.addUnit(new Freshmen());}
            Wiess.setOwner(p1);     }
        else if(c1 == "Will Rice"){
            for(int i = 0; i<3; i++){
        WillRice.addUnit(new Freshmen());}
            WillRice.setOwner(p1);      }
        
        if(c2== "Baker"){
            Baker.setOwner(p2);
        for(int i = 0; i<3; i++){
        Baker.addUnit(new Freshmen());}}
        else if(c2 == "Brown"){
            for(int i = 0; i<3; i++){
        Brown.addUnit(new Freshmen());}
            Brown.setOwner(p2);     }
        else if(c2 == "Jones"){
            for(int i = 0; i<3; i++){
        Jones.addUnit(new Freshmen());}
            Jones.setOwner(p2);     }
        else if(c2 == "Martel"){
            for(int i = 0; i<3; i++){
        Martel.addUnit(new Freshmen());}
            Martel.setOwner(p2);        }
        else if(c2 == "McMurtry"){
            for(int i = 0; i<3; i++){
        McMurtry.addUnit(new Freshmen());}
            McMurtry.setOwner(p2);      }
        else if(c2 == "Duncan"){
            for(int i = 0; i<3; i++){
        Duncan.addUnit(new Freshmen());}
            Duncan.setOwner(p2);        }
        else if(c2 == "Sid Rich"){
            for(int i = 0; i<3; i++){
        SidRich.addUnit(new Freshmen());}
            SidRich.setOwner(p2);       }
        else if(c2 == "Lovett"){
            for(int i = 0; i<3; i++){
        Lovett.addUnit(new Freshmen());}
            Lovett.setOwner(p2);        }
        else if(c2 == "Hanszen"){
            for(int i = 0; i<3; i++){
        Hanszen.addUnit(new Freshmen());}
            Hanszen.setOwner(p2);       }
        else if(c2 == "Wiess"){
            for(int i = 0; i<3; i++){
        Wiess.addUnit(new Freshmen());}
            Wiess.setOwner(p2);     }
        else if(c2 == "Will Rice"){
            for(int i = 0; i<3; i++){
        WillRice.addUnit(new Freshmen());}
            WillRice.setOwner(p2);      }
    }
    public void updateSidePanel(Nodes n, Nodes[] BuildingList)
    {
        if (move_clicked == false && attack_clicked == false){
            JPanel units = new JPanel();
            units.setLayout(new BoxLayout(units,BoxLayout.PAGE_AXIS));
            JLabel location = new JLabel(n.getName());
            units.add(location);
            JLabel owner = new JLabel("Owned by "+n.getOwner().getName());
            units.add(owner);
            JLabel streak = new JLabel(n.getStreakers()+" Streakers");
            units.add(streak);
            JLabel athlete = new JLabel(n.getAthletes()+" Athletes");
            units.add(athlete);
            JLabel drunk = new JLabel(n.getDrunks()+" Drunks");
            units.add(drunk);
            JLabel freshmen = new JLabel(n.getFreshmen()+" Freshmen");
            units.add(freshmen);
            JLabel engineers = new JLabel(n.getEngineers()+" Engineers");
            units.add(engineers);
            sidePanel.removeAll();
            sidePanel.add(units);
            updateActionPanel(n);
            for (int i =0; i<BuildingList.length; i++){
                if (BuildingList[i].getNeighbors().contains(n.getName()))
                {
                    BuildingList[i].getButton().setBackground(new Color(255,255,0,100));
                }
                else
                {
                    BuildingList[i].getButton().setBackground(BuildingList[i].getOwner().getColor());
                }
            }
        }
        else if(move_clicked == true)
        {
            int count = counter.getCount();
            Player cur;
            if(count%2 == 0)
            {
                cur = p2;
            }
            else
            {
                cur = p1;
            }
            if(n.getOwner()==(cur) && n.getNeighbors().contains(prev_node.getName()))
            {
                MoveMenu mm = new MoveMenu(prev_node, n,BuildingList, this);
            }
            else
            {
                move_clicked = false;
            }
        }
        else
        {
            int count = counter.getCount();
            final Player cur;
            if(count%2 == 0)
            {
                cur = p2;
            }
            else
            {
                cur = p1;
            }
            if(n.getOwner()!=cur && n.getNeighbors().contains(prev_node.getName()) && prev_node.getUnits().size()>1)
            {
                resetAttack();
                final Battle b = new Battle(prev_node, n, this, BuildingList);
                final JFrame dice = new JFrame();
                JButton roll = new JButton("Roll!");
                roll.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        int[] rolls = b.rollDice();
                        JFrame jf = new JFrame();
                        Container j = jf.getContentPane();
                        j.setLayout(new BoxLayout(j, BoxLayout.PAGE_AXIS));
                        JLabel attack = new JLabel("You rolled: ", JLabel.CENTER);
                        JPanel attackdice = new JPanel();
                        attackdice.add(attack);
                        JLabel die1 = new JLabel();
                        switch(rolls[0])
                        {
                            case 1: die1.setIcon(new ImageIcon("redone.png"));
                                break;
                            case 2: die1.setIcon(new ImageIcon("redtwo.png"));
                                break;
                            case 3: die1.setIcon(new ImageIcon("redthree.png"));
                                break;
                            case 4: die1.setIcon(new ImageIcon("redfour.png"));
                                break;
                            case 5: die1.setIcon(new ImageIcon("redfive.png"));
                                break;
                            case 6: die1.setIcon(new ImageIcon("redsix.png"));
                                break;
                        }
                        attackdice.add(die1);
                        JLabel die2 = new JLabel();
                        switch(rolls[1])
                        {
                            case 1: die2.setIcon(new ImageIcon("redone.png"));
                            break;
                            case 2: die2.setIcon(new ImageIcon("redtwo.png"));
                            break;
                            case 3: die2.setIcon(new ImageIcon("redthree.png"));
                            break;
                            case 4: die2.setIcon(new ImageIcon("redfour.png"));
                            break;
                            case 5: die2.setIcon(new ImageIcon("redfive.png"));
                            break;
                            case 6: die2.setIcon(new ImageIcon("redsix.png"));
                            break;
                        }
                        attackdice.add(die2);
                        JLabel die3 = new JLabel();
                        switch(rolls[2])
                        {
                            case 1: die3.setIcon(new ImageIcon("redone.png"));
                            break;
                            case 2: die3.setIcon(new ImageIcon("redtwo.png"));
                            break;
                            case 3: die3.setIcon(new ImageIcon("redthree.png"));
                            break;
                            case 4: die3.setIcon(new ImageIcon("redfour.png"));
                            break;
                            case 5: die3.setIcon(new ImageIcon("redfive.png"));
                            break;
                            case 6: die3.setIcon(new ImageIcon("redsix.png"));
                            break;
                        }
                        attackdice.add(die3);
                        j.add(attackdice);
                        
                        JPanel defensedice = new JPanel();
                        JLabel defend = new JLabel("Your opponent rolled: ");
                        defensedice.add(defend);
                        JLabel die4 = new JLabel();
                        switch(rolls[3])
                        {
                            case 1: die4.setIcon(new ImageIcon("blueone.png"));
                                break;
                            case 2: die4.setIcon(new ImageIcon("bluetwo.png"));
                                break;
                            case 3: die4.setIcon(new ImageIcon("bluethree.png"));
                                break;
                            case 4: die4.setIcon(new ImageIcon("bluefour.png"));
                                break;
                            case 5: die4.setIcon(new ImageIcon("bluefive.png"));
                                break;
                            case 6: die4.setIcon(new ImageIcon("bluesix.png"));
                                break;
                        }
                        defensedice.add(die4);
                        JLabel die5 = new JLabel();
                        switch(rolls[4])
                        {
                            case 1: die5.setIcon(new ImageIcon("blueone.png"));
                            break;
                            case 2: die5.setIcon(new ImageIcon("bluetwo.png"));
                            break;
                            case 3: die5.setIcon(new ImageIcon("bluethree.png"));
                            break;
                            case 4: die5.setIcon(new ImageIcon("bluefour.png"));
                            break;
                            case 5: die5.setIcon(new ImageIcon("bluefive.png"));
                            break;
                            case 6: die5.setIcon(new ImageIcon("bluesix.png"));
                            break;
                        }
                        defensedice.add(die5);
                        j.add(defensedice);
                        jf.pack();
                        jf.setVisible(true);
                        
                        Player NotCur = (p1==cur)?p2:p1;
                        if(b.getResult() == true||b.getAttacker().getUnits().size()<=1)
                        {
                            dice.setVisible(false);
                            if(b.getResult()==true)
                            {
                                JFrame congrats = new JFrame();
                                JOptionPane.showMessageDialog(congrats,"Congratulations! You conquered!");
                                boolean killed = true;
                                for(Nodes n: b.getBuildingList())
                                {
                                    if(n.getOwner().getName() == NotCur.getName())
                                    {
                                        killed = false;
                                    }
                                }
                                if(killed)
                                {
                                    jf.dispose();
                                    JFrame victory = new JFrame();
                                    ImageIcon vpicture = new ImageIcon("leebron.png");
                                    JOptionPane.showMessageDialog(victory,"","Victory",JOptionPane.PLAIN_MESSAGE,vpicture);
                                    frame.dispose();
                                }
                                else
                                {
                                    MoveMenu mm = new MoveMenu(b.getAttacker(), b.getDefender(), b.getBuildingList(), b.getGameBoard());
                                }
                            }
                        }
                    }
                    });
                dice.add(roll);
                dice.pack();
                dice.setVisible(true);
            }
            else
            {
                attack_clicked = false;
            }
        }
    }
    
    public void updateActionPanel(Nodes n)
    {
        final Nodes pn = n;
        int count = counter.getCount();
        Player cur;
        if(count%2 == 0)
        {
            cur = p2;
        }
        else
        {
            cur = p1;
        }
        if(n.getOwner()==(cur))
        {
            JButton move = new JButton("Move from Here");
            move.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    move_clicked = true;
                    prev_node = pn;
                }
                });
            actionPanel.removeAll();
            actionPanel.add(move);
            JButton attack = new JButton("Attack from Here");
            attack.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    attack_clicked = true;
                    prev_node = pn;
                }
                });
            actionPanel.add(attack);
        }
        frame.validate();
    }
    public void updateTurnPanel()
    {
        turnPanel.removeAll();
        int count = counter.getCount();
        Player cur;
        if(count%2 == 0)
        {
            cur = p2;
        }
        else
        {
            cur = p1;
        }
        turnPanel.add(new JLabel(cur.getName()+"'s Turn"));
        turnPanel.add(new JLabel("\t"+"You have $"+cur.getMoney()));
        JButton endTurn = new JButton("End Turn");
        endTurn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                counter.increaseCount();
                changeTurn();
            }
        });
        turnPanel.add(endTurn);
        frame.validate();
    }
    
    public void resetMove()
    {
        move_clicked = false;
    }
    public void resetAttack()
    {
        attack_clicked = false;
    }
    public void changeTurn()
    {
        final Nodes[] BuildingList = {Baker, Brown, Hanszen, Wiess, Jones, McMurtry, Duncan, Lovett, WillRice, SidRich, Martel, LovettHall, Herzstein, Sewall, Rayzor, AndersonHall, DuncanHall, Abercrombie, Fondren, HandD, Shepard, JonesBusiness, RMC, Rec, Tudor, Brockman, Brochstein, Football, Baseball, AndersonLabs, Soccer, RUPD, Herring, BakerInstitute, HermanBrown, Keck, Mech, HUMA, Hamman, GeorgeRBrown, CohenHouse, AllenCenter, DellButcher};
        boolean owns_NorthColleges = true;
        boolean owns_SouthColleges = true;
        boolean owns_Quad = true;
        boolean owns_North = true;
        boolean owns_Athletics = true;
        boolean owns_West = true;
        final ArrayList<Nodes> NorthColleges = new ArrayList<Nodes>(Arrays.asList(McMurtry, Duncan, Martel, Jones, Brown));
        final ArrayList<Nodes> SouthColleges = new ArrayList<Nodes>(Arrays.asList(Baker, WillRice, Wiess, Hanszen, Lovett, SidRich, HandD, CohenHouse, AllenCenter));
        final ArrayList<Nodes> Quad = new ArrayList<Nodes>(Arrays.asList(LovettHall, Sewall, Herzstein, Rayzor, Fondren, Brochstein, AndersonHall, HUMA, Herring, RMC));
        final ArrayList<Nodes> West = new ArrayList<Nodes>(Arrays.asList(JonesBusiness, Shepard, BakerInstitute, Rec));
        final ArrayList<Nodes> Athletics = new ArrayList<Nodes>(Arrays.asList(RUPD, Football, Soccer, Baseball, Tudor));
        final ArrayList<Nodes> North = new ArrayList<Nodes>(Arrays.asList(HermanBrown, DellButcher, Keck, Brockman, Abercrombie, Mech, DuncanHall, GeorgeRBrown, Hamman, AndersonLabs));
        double income = 0;
        int count = counter.getCount();
        Nodes home = null;
        Player cur;
        if(count%2 == 0)
        {
            cur = p2;
        }
        else
        {
            cur = p1;
        }
        
        for (int i =0; i<BuildingList.length; i++)
        {
            BuildingList[i].getButton().setBackground(BuildingList[i].getOwner().getColor());
            for (int j = 0; j<(BuildingList[i].getUnits()).size(); j++)
            {
                (BuildingList[i].getUnits()).get(j).resetMoves();
            }
            if (BuildingList[i].getOwner()==cur)
            {
                income += BuildingList[i].getValue();
            }
            if (BuildingList[i].getName()==cur.getCollege())
            {
                home = BuildingList[i];
            }
            else
            {
                if (NorthColleges.contains(BuildingList[i]))
                    {
                    owns_NorthColleges = false;
                    }
                if (West.contains(BuildingList[i]))
                    {
                    owns_West = false;
                    }
                if (SouthColleges.contains(BuildingList[i]))
                    {
                    owns_SouthColleges = false;
                    }
                if (Quad.contains(BuildingList[i]))
                    {
                    owns_Quad = false;
                    }
                if (Athletics.contains(BuildingList[i]))
                    {
                    owns_Athletics = false;
                    }
                if (North.contains(BuildingList[i]))
                    {
                    owns_North = false;
                    }
            }
        }
        income /= 2;
        income = Math.floor(income)+2;
        if (owns_NorthColleges==true)
        {
            income += 3;
        }
        if (owns_SouthColleges==true)
        {
            income += 6;
        }
        if (owns_North==true)
        {
            income += 7;
        }
        if (owns_Quad==true)
        {
            income += 7;
        }
        if (owns_West==true)
        {
            income += 2;
        }
        if (owns_Athletics==true)
        {
            income += 3;
        }
        if (count>2){
        cur.changeMoney((int)income);
            BuyMenu bm = new BuyMenu(cur, home, this, BuildingList);    
        }
        else
        {
            updateTurnPanel();
        }
    }
}
