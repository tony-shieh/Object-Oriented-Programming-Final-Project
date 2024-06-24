package hotel;
//GUI for booking
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class BookingGUI extends JFrame{
  
  private LocalDateTime time = LocalDateTime.now();
  private Container cp;
  private JLabel[] labels = new JLabel[12]; //an Array to store JLabel
  private JTextField[] text = new JTextField[9]; //an Array to store JTextField
  private JRadioButton[] rb = new JRadioButton[6]; //an Array to store JRadioButton
  private ButtonGroup peopleGroup = new ButtonGroup(), typeGroup = new ButtonGroup(); //ButtonGroup for Number of people & Room type
  private JButton confirm, cancel; //JButton for confirm and cancel
  private Font font_style = new Font("BIZ UDGothic", Font.ITALIC, 25);
  
  //an String Array to store Labels
  private static final String[] label = {"Room Booking",
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
  
  public BookingGUI(){
    
    setSize(800,600);
    setTitle(label[0]);
    setLayout(new GridLayout(8,1,10,20));
    setVisible(true);
    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        dispose();
      }
    });
    
    Color c = new Color(65,105,225);//create color: skyblue
    cp = getContentPane();
    cp.setBackground(Color.WHITE);
    JPanel[] panel = new JPanel[8]; //create 8 JPanel
    //add JPanel to ContentPane
    for(int i = 0; i < 8; i++){
      panel[i] = new JPanel();
      panel[i].setBackground(c.brighter().brighter().brighter());
      cp.add(panel[i]);
    }
    //set panel Layout
    panel[0].setLayout(new BorderLayout()); //title
    panel[1].setLayout(new GridLayout(1,2)); //name
    panel[2].setLayout(new GridLayout(1,2)); //tele
    panel[3].setLayout(new GridLayout(2,6,10,20)); //cid & cod
    panel[4].setLayout(new GridLayout(1,5)); //num of people
    panel[5].setLayout(new GridLayout(1,2)); //num of room
    panel[6].setLayout(new GridLayout(1,3)); //room type
    panel[7].setLayout(new GridLayout(1,2,20,10)); //confirm & cancel button
    
    //set up panel[0]
    //create the title JLabel,set font style
    labels[0] = new JLabel(label[0],JLabel.CENTER);
    labels[0].setFont(new Font("BIZ UDGothic", Font.ITALIC, 40));
    panel[0].add(labels[0], BorderLayout.CENTER);
    
    //set up panel[1]
    labels[1] = new JLabel(label[1]);
    labels[1].setFont(font_style);
    text[0] = new JTextField();
    text[0].setDocument(new Index.NameTextField());
    panel[1].add(labels[1]);
    panel[1].add(text[0]);
    
    //set up panel[2]
    labels[2] = new JLabel(label[2]);
    labels[2].setFont(font_style);
    text[1] = new JTextField();
    text[1].setDocument(new Index.TeleTextField());
    text[1].addFocusListener(new Index.TeleEXHandler());
    panel[2].add(labels[2]);
    panel[2].add(text[1]);
    
    //set up panel[3]
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
      labels[i] = new JLabel("                               " + label[10] );
    }
    panel[3].add(labels[3]);
    panel[3].add(text[2]);
    text[2].setDocument(new Index.YearTextField());
    panel[3].add(labels[5]);
    panel[3].add(text[4]);
    text[4].setDocument(new Index.MonthTextField());
    panel[3].add(labels[6]);
    panel[3].add(text[6]);
    text[6].setDocument(new Index.DateTextField());
    panel[3].add(labels[4]);
    panel[3].add(text[3]);
    text[3].setDocument(new Index.YearTextField());
    panel[3].add(labels[7]);
    panel[3].add(text[5]);
    text[5].setDocument(new Index.MonthTextField());
    panel[3].add(labels[8]);
    panel[3].add(text[7]);
    text[7].setDocument(new Index.DateTextField());
    
    //set up panel[4]
    labels[9] = new JLabel(label[5]);
    labels[9].setFont(font_style);
    panel[4].add(labels[9]);
    for(int i = 0; i < 4; i++){
      if(i == 0){
        rb[i] = new JRadioButton(Integer.toString(i + 1), true);
      }
      else{
        rb[i] = new JRadioButton(Integer.toString(i + 1));
      }
      rb[i].setBackground(c.brighter().brighter().brighter());
      peopleGroup.add(rb[i]);
      panel[4].add(rb[i]);
    }
    
    //set up panel[5]
    labels[10] = new JLabel(label[6]);
    labels[10].setFont(font_style);
    panel[5].add(labels[10]);
    text[8] = new JTextField();
    text[8].setDocument(new Index.RoomTextField());
    panel[5].add(text[8]);
    
    //set up panel[6]
    labels[11] = new JLabel(label[7]);
    labels[11].setFont(font_style);
    panel[6].add(labels[11]);
    for(int i = 4; i < 6; i++){
      if(i == 4){
        rb[i] = new JRadioButton(label[i + 4], true);
      }
      else{
        rb[i] = new JRadioButton(label[i + 4]);
      }
      rb[i].setBackground(c.brighter().brighter().brighter());
      typeGroup.add(rb[i]);
      panel[6].add(rb[i]);
    }
    
    //set up panel[7]
    JPanel jp = new JPanel();//create another jpanel to keep butoon, or setSize cannot use and will be fixed by layout
    confirm = new JButton("Confirm");
    jp.add(confirm);
    confirm.setBackground(Color.CYAN);
    confirm.setSize(20,10);
    confirm.setFont(font_style);
    confirm.addActionListener(new ConfirmButtonHandler());
    cancel = new JButton("Cancel");
    jp.add(cancel);
    cancel.setBackground(Color.CYAN);
    cancel.setSize(20,10);
    cancel.setFont(font_style);
    cancel.addActionListener(new CancelButtonHandler());
    panel[7].add(jp);   
  }
  
  //confirm button Listener
  private class ConfirmButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
        Hotel h = new Costal_Hotel();
        String p = "1";
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
        try{
          if(text[0].getText().equals("")){
            throw new IllegalArgumentException("The name column can't be empty.");
          }
          h.setName(text[0].getText());
          h.setTele(text[1].getText());
          h.setPeople(Integer.parseInt(p));
          h.setRoom(Integer.parseInt(text[8].getText()));
          h.setCID(Integer.parseInt(text[2].getText()), Integer.parseInt(text[4].getText()), Integer.parseInt(text[6].getText()));
          h.setCOD(Integer.parseInt(text[3].getText()), Integer.parseInt(text[5].getText()), Integer.parseInt(text[7].getText()));
          JOptionPane.showMessageDialog(null,Index.d.booking(h));
          dispose();
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