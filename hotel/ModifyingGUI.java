package hotel;
//GUI for modifying
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class ModifyingGUI extends JFrame{
  
  private LocalDateTime time = LocalDateTime.now();
  private Container cp;
  private JLabel[] labels = new JLabel[23]; //an Array to store JLabel
  private JTextField[] text = new JTextField[18]; //an Array to store JTextField
  private JRadioButton[] rb = new JRadioButton[12]; //an Array to store JRadioButton
  private ButtonGroup peopleGroup1 = new ButtonGroup(), typeGroup1 = new ButtonGroup(), peopleGroup2 = new ButtonGroup(), typeGroup2 = new ButtonGroup(); //ButtonGroup for Number of people & Room type
  private JButton confirm, cancel; //JButton for confirm and cancel
  private Font font_style = new Font("BIZ UDGothic", Font.ITALIC, 12);
  
  //an String Array to store Labels
  private static final String[] label = {"Room Modifying",
                                         "Name",
                                         "Tele",
                                         "Check In Date",
                                         "Check Out Date",
                                         "Number Of People",
                                         "Number Of Room",
                                         "Room Type",
                                         "Costal Room",
                                         "Mountain Room",
                                         " / "};
  
  public ModifyingGUI(){
    
    setSize(800,600);
    setTitle(label[0]);
    setLayout(new BorderLayout());
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setVisible(true);
    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        dispose();
      }
    });
    
    Color c = new Color(65,105,225);//create color: skyblue
    cp = getContentPane();
    cp.setBackground(Color.WHITE);
    JPanel[] panel = new JPanel[17]; //create 19 JPanel
    for(int i = 0; i < 17; i++){
      panel[i] = new JPanel();
      panel[i].setBackground(c.brighter().brighter().brighter());
    }
    
    //add JPanel to ContentPane
    cp.add(panel[0], BorderLayout.NORTH);
    cp.add(panel[1], BorderLayout.CENTER);
    cp.add(panel[2], BorderLayout.SOUTH);
    
    //set panel Layout
    panel[0].setLayout(new BorderLayout()); //title
    panel[1].setLayout(new BorderLayout()); //search & modify
    panel[2].setLayout(new GridLayout(1,2,20,10)); //confirm & cancel button
    //searching side
    panel[3].setLayout(new GridLayout(6,1,5,5));
    panel[4].setLayout(new GridLayout(1,2)); //name
    panel[5].setLayout(new GridLayout(1,2)); //tele
    panel[6].setLayout(new GridLayout(2,6,5,5)); //cid & cod
    panel[7].setLayout(new GridLayout(1,5)); //num of people
    panel[8].setLayout(new GridLayout(1,2)); //num of room
    panel[9].setLayout(new GridLayout(1,3)); //room type
    //modifying side
    panel[10].setLayout(new GridLayout(6,1,5,5));
    panel[11].setLayout(new GridLayout(1,2)); //name
    panel[12].setLayout(new GridLayout(1,2)); //tele
    panel[13].setLayout(new GridLayout(2,6,5,5)); //cid & cod
    panel[14].setLayout(new GridLayout(1,5)); //num of people
    panel[15].setLayout(new GridLayout(1,2)); //num of room
    panel[16].setLayout(new GridLayout(1,3)); //room type
    
    
    //set up panel[0]
    //create the title JLabel,set font style
    labels[0] = new JLabel(label[0],JLabel.CENTER);
    labels[0].setFont(new Font("BIZ UDGothic", Font.ITALIC, 40));
    panel[0].add(labels[0], BorderLayout.CENTER);
    
    //set up panel[1]
    panel[1].add(panel[3], BorderLayout.WEST);
    panel[1].add(panel[10], BorderLayout.EAST);
    
    //set up panel[2]
    JPanel jp = new JPanel();//create another jpanel to keep butoon, or setSize cannot use and will be fixed by layout
    confirm = new JButton("Modify");
    confirm.setFont(font_style);
    jp.add(confirm);
    confirm.setBackground(Color.CYAN);
    confirm.setSize(20,10);
    confirm.addActionListener(new ConfirmButtonHandler());
    cancel = new JButton("Cancel");
    cancel.setFont(font_style);
    jp.add(cancel);
    cancel.setBackground(Color.CYAN);
    cancel.setSize(20,10);
    cancel.addActionListener(new CancelButtonHandler());
    panel[2].add(jp);   
    
    //set up panel[3]
    for(int i = 4; i < 10; i++){
      panel[3].add(panel[i]);
    }
    //set border
    TitledBorder tb1 = new TitledBorder("original data");
    tb1.setTitleJustification(TitledBorder.CENTER);
    tb1.setTitleFont(font_style);
    tb1.setTitleColor(Color.RED);
    panel[3].setBorder(tb1);
    
    //set up panel[4]
    labels[1] = new JLabel(label[1]);
    labels[1].setFont(font_style);                   
    text[0] = new JTextField();
    text[0].setDocument(new Index.NameTextField());
    panel[4].add(labels[1]);
    panel[4].add(text[0]);
    
    //set up panel[5]
    labels[2] = new JLabel(label[2]);
    labels[2].setFont(font_style);
    text[1] = new JTextField();
    text[1].setDocument(new Index.TeleTextField());
    text[1].addFocusListener(new Index.TeleEXHandler());
    panel[5].add(labels[2]);
    panel[5].add(text[1]);
    
    //set up panel[6]
    for(int i = 3; i < 5; i++){
      labels[i] = new JLabel(label[i]);
      labels[i].setFont(font_style);
    }
    for(int i = 2; i < 4; i++){
      text[i] = new JTextField(Integer.toString(time.getYear()));
    }
    for(int i = 4; i < 6; i++){
      text[i] = new JTextField(Integer.toString(time.getMonthValue()));
    }
    for(int i = 6; i < 8; i++){
      text[i] = new JTextField(Integer.toString(time.getDayOfMonth()));
    }
    for(int i = 5; i < 9; i++){
      labels[i] = new JLabel("              " + label[10]);
    }
    panel[6].add(labels[3]);
    panel[6].add(text[2]);
    text[2].setDocument(new Index.YearTextField());
    panel[6].add(labels[5]);
    panel[6].add(text[4]);
    text[4].setDocument(new Index.MonthTextField());
    panel[6].add(labels[6]);
    panel[6].add(text[6]);
    text[6].setDocument(new Index.DateTextField());
    panel[6].add(labels[4]);
    panel[6].add(text[3]);
    text[3].setDocument(new Index.YearTextField());
    panel[6].add(labels[7]);
    panel[6].add(text[5]);
    text[5].setDocument(new Index.MonthTextField());
    panel[6].add(labels[8]);
    panel[6].add(text[7]);
    text[7].setDocument(new Index.DateTextField());
    
    //set up panel[7]
    labels[9] = new JLabel(label[5]);
    labels[9].setFont(font_style);
    panel[7].add(labels[9]);
    for(int i = 0; i < 4; i++){
      if(i == 0){
        rb[i] = new JRadioButton(Integer.toString(i + 1), true);
      }
      else{
        rb[i] = new JRadioButton(Integer.toString(i + 1));
      }
      rb[i].setBackground(c.brighter().brighter().brighter());
      peopleGroup1.add(rb[i]);
      panel[7].add(rb[i]);
    }
    
    //set up panel[8]
    labels[10] = new JLabel(label[6]);
    labels[10].setFont(font_style);                  
    panel[8].add(labels[10]);
    text[8] = new JTextField();
    text[8].setDocument(new Index.RoomTextField());
    panel[8].add(text[8]);
    
    //set up panel[9]
    labels[11] = new JLabel(label[7]);
    labels[11].setFont(font_style);                  
    panel[9].add(labels[11]);
    for(int i = 4; i < 6; i++){
      if(i == 4){
        rb[i] = new JRadioButton(label[i + 4], true);
      }
      else{
        rb[i] = new JRadioButton(label[i + 4]);
      }
      rb[i].setBackground(c.brighter().brighter().brighter());
      typeGroup1.add(rb[i]);
      panel[9].add(rb[i]);
    }
    
    //set up panel[10]
    for(int i = 11; i < 17; i++){
      panel[10].add(panel[i]);
    }
    //set border
    TitledBorder tb2 = new TitledBorder("new data");
    tb2.setTitleJustification(TitledBorder.CENTER);
    tb2.setTitleFont(font_style);
    tb2.setTitleColor(Color.RED);
    panel[10].setBorder(tb2);
    
    
    //set up panel[11]
    labels[12] = new JLabel(label[1]);
    labels[12].setFont(font_style);                   
    text[9] = new JTextField();
    text[9].setDocument(new Index.NameTextField());
    panel[11].add(labels[12]);
    panel[11].add(text[9]);
    
    //set up panel[12]
    labels[13] = new JLabel(label[2]);
    labels[13].setFont(font_style);                 
    text[10] = new JTextField();
    text[10].setDocument(new Index.TeleTextField());
    text[10].addFocusListener(new Index.TeleEXHandler());
    panel[12].add(labels[13]);
    panel[12].add(text[10]);
    
    //set up panel[13]
    for(int i = 14; i < 16; i++){
      labels[i] = new JLabel(label[i - 11]);
      labels[i].setFont(font_style);   
      
    }
    for(int i = 11; i < 13; i++){
      text[i] = new JTextField(Integer.toString(time.getYear()));
    }
    for(int i = 13; i < 15; i++){
      text[i] = new JTextField(Integer.toString(time.getMonthValue()));
    }
    for(int i = 15; i < 17; i++){
      text[i] = new JTextField(Integer.toString(time.getDayOfMonth()));
    }
    for(int i = 16; i < 20; i++){
      labels[i] = new JLabel("            "+ label[10]);
    }
    panel[13].add(labels[14]);
    panel[13].add(text[11]);
    text[11].setDocument(new Index.YearTextField());
    panel[13].add(labels[16]);
    panel[13].add(text[13]);
    text[13].setDocument(new Index.MonthTextField());
    panel[13].add(labels[17]);
    panel[13].add(text[15]);
    text[15].setDocument(new Index.DateTextField());
    panel[13].add(labels[15]);
    panel[13].add(text[12]);
    text[12].setDocument(new Index.YearTextField());
    panel[13].add(labels[18]);
    panel[13].add(text[14]);
    text[14].setDocument(new Index.MonthTextField());
    panel[13].add(labels[19]);
    panel[13].add(text[16]);
    text[16].setDocument(new Index.DateTextField());
   
    //set up panel[14]
    labels[20] = new JLabel(label[5]);
    labels[20].setFont(font_style);                
    panel[14].add(labels[20]);
    for(int i = 6; i < 10; i++){
      if(i == 6){
        rb[i] = new JRadioButton(Integer.toString(i - 5), true);
      }
      else{
        rb[i] = new JRadioButton(Integer.toString(i - 5));
      }
      rb[i].setBackground(c.brighter().brighter().brighter());
      peopleGroup2.add(rb[i]);
      panel[14].add(rb[i]);
    }
    
    //set up panel[15]
    labels[21] = new JLabel(label[6]);
    labels[21].setFont(font_style);                   
    panel[15].add(labels[21]);
    text[17] = new JTextField();
    text[17].setDocument(new Index.RoomTextField());
    panel[15].add(text[17]);
    
    //set up panel[16]
    labels[22] = new JLabel(label[7]);
    labels[22].setFont(font_style);             
    panel[16].add(labels[22]);
    for(int i = 10; i < 12; i++){
      if(i == 10){
        rb[i] = new JRadioButton(label[i - 2], true);
      }
      else{
        rb[i] = new JRadioButton(label[i - 2]);
      }
      rb[i].setBackground(c.brighter().brighter().brighter());
      typeGroup2.add(rb[i]);
      panel[16].add(rb[i]);
    }
    
    
  }
  
  //confirm button Listener
  private class ConfirmButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      Hotel h = new Costal_Hotel(); //original h
      Hotel h1 = new Costal_Hotel(); //new h
      String p = "1";
      String p1 = "1";
      for(int i = 0; i < 4; i++){
        if(rb[i].isSelected()){
          p = rb[i].getText();
        }
      }
      for(int i = 4; i < 6; i++){
        if(rb[i].isSelected()){
          if(rb[i].getText().equals("Costal Room")){
            h = new Costal_Hotel();
          }
          else{
            h = new Mountain_Hotel();
          }
        }
      }
      for(int i = 6; i < 10; i++){
        if(rb[i].isSelected()){
          p1 = rb[i].getText();
        }
      }
      for(int i = 10; i < 12; i++){
        if(rb[i].isSelected()){
          if(rb[i].getText().equals("Costal Room")){
            h1 = new Costal_Hotel();
          }
          else{
            h1 = new Mountain_Hotel();
          }
        }
      }
      try{
        if(text[0].getText().equals("") || text[9].getText().equals("")){
          throw new IllegalArgumentException("The name column can't be empty.");
        }
        h.setName(text[0].getText());
        h.setTele(text[1].getText());
        h.setPeople(Integer.parseInt(p));
        h.setRoom(Integer.parseInt(text[8].getText()));
        h.setCID(Integer.parseInt(text[2].getText()), Integer.parseInt(text[4].getText()), Integer.parseInt(text[6].getText()));
        h.setCOD(Integer.parseInt(text[3].getText()), Integer.parseInt(text[5].getText()), Integer.parseInt(text[7].getText()));
        h1.setName(text[9].getText());
        h1.setTele(text[10].getText());
        h1.setPeople(Integer.parseInt(p1));
        h1.setRoom(Integer.parseInt(text[17].getText()));
        h1.setCID(Integer.parseInt(text[11].getText()), Integer.parseInt(text[13].getText()), Integer.parseInt(text[15].getText()));
        h1.setCOD(Integer.parseInt(text[12].getText()), Integer.parseInt(text[14].getText()), Integer.parseInt(text[16].getText()));
        JOptionPane.showMessageDialog(null, Index.d.modifying(h,h1));
      }
      catch(NumberFormatException error){
        JOptionPane.showMessageDialog(null,"Illegal input for date or room number!");
      }
      catch(IllegalArgumentException error){
        JOptionPane.showMessageDialog(null,error.getMessage());
      }
    }
  }
    
  //cancel button Listener
  private class CancelButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      dispose();
    }
  }
}