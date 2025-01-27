package hotel;
//GUI for index
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
public class Index extends JFrame{

  //a String Array to store labels
  private String[] s = {"Unhappy Hotel", "Booking", "Searching", "Deleting", "Modifying"};
  private Container cp;
  private JLabel label;
  private JButton[] units =  new JButton[4]; //an Array to store JButton
  private static Calendar time = Calendar.getInstance();
  static Database d = new Database();
  
  public Index(){
    setSize(500,400);
    setTitle("Welcome to Unhappy Hotel");
    setLayout(new BorderLayout());
    setVisible(true);
    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });
    
    Color c = new Color(65,105,225);//create color: skyblue
    cp = getContentPane();
    cp.setBackground(Color.WHITE);//contentpane color is white
    JPanel panel1 = new JPanel(), panel2 = new JPanel();//create 2 Jpanel(North & South)
    panel1.setBackground(c.brighter().brighter().brighter()); //panel1 color is blue and make it birghter
    
    panel2.setBackground(Color.WHITE);//panel1 color is white
    //add panel to contentpane
    cp.add(panel1, BorderLayout.NORTH);
    cp.add(panel2, BorderLayout.SOUTH);
    //set panel layout
    panel1.setLayout(new BorderLayout());
    panel2.setLayout(new GridLayout(4,1,80,30));
    //create the title JLabel,set font style
    label = new JLabel(s[0],JLabel.CENTER);
    label.setFont(new Font("BIZ UDGothic", Font.ITALIC, 40));
    panel1.add(label, BorderLayout.CENTER);
    
    //create 4 JButtons
    for(int i = 0; i < 4; i++){
      JPanel jp = new JPanel();
      units[i] = new JButton(s[i + 1]);
      units[i].setBackground(Color.CYAN);// button color is cyan 
      units[i].setFont(new Font("BIZ UDGothic", Font.ITALIC, 25));//button's words decoration
      jp.setSize(20,10);
      panel2.add(jp);
      jp.add(units[i]);
    }
    //register
    units[0].addActionListener(new BookingButtonHandler());
    units[1].addActionListener(new SearchingButtonHandler());
    units[2].addActionListener(new DeletingButtonHandler());
    units[3].addActionListener(new ModifyingButtonHandler());
    
  }
  
  //Booking button Listener
  private class BookingButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      BookingGUI b = new BookingGUI();
    }
  }
  //Searching button Listener
  private class SearchingButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      SearchingGUI b = new SearchingGUI();
    }
  }
  //Deleting button Listener
  private class DeletingButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      DeletingGUI b = new DeletingGUI();
    }
  }
  //Modifying button Listener
  private class ModifyingButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      ModifyingGUI b = new ModifyingGUI();
    }
  }
  
  //a class to restrict the type-in content and length (name)
  static class NameTextField extends PlainDocument {
    public NameTextField() {
      super();
    }
    //BadLocationException 文檔中的??位置（嘗試引用不存在的位置）
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {//AttributeSet: to hold a set of Key,Value pair objects.
      if (str == null) {
        return;
      }
      
      char[] s = str.toCharArray();
      int length = 0;
      // type-in content can just be number
      for (int i = 0; i < s.length; i++) {
        if ((s[i] >= 'A') && (s[i] <= 'z')) {
          s[length] = s[i];
          length++;
        }
        // insert the content
        super.insertString(offset, new String(s, 0, length), attr);
      }
      
    }
  }
  //a class to restrict the type-in content and length (tele)
  static class TeleTextField extends PlainDocument {
    public TeleTextField() {
      super();
      //set default value
      try{
        super.insertString(0, "1234-567890", null);
      }
      catch(BadLocationException e){
        System.out.println(e.getMessage());
      }
    }
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null) {
        return;
      }
      //restrict length
      if(str.length() >= 11){
        return;
      }
      
      char[] s = str.toCharArray();
      int length = 0;
      // type-in content can just be number
      for (int i = 0; i < s.length; i++) {
        if(getLength() <= 10){
          if (s[i] == '-' || ((s[i] >= '0') && (s[i] <= '9'))) {
            s[length] = s[i];
            length++;
          }
          // insert the content
          super.insertString(offset, new String(s, 0, length), attr);
        }
      }
    }
  }
  //a class to restrict the type-in content and length (year)
  static class YearTextField extends PlainDocument {
    public YearTextField() {
      super();
      //set default value
      try{
        super.insertString(0, Integer.toString(time.get(Calendar.YEAR)), null);
      }
      catch(BadLocationException e){
        System.out.println(e.getMessage());
      }
    }
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null) {
        return;
      }
      //restrict length
      if(str.length() >= 4){
        return;
      }
      
      char[] s = str.toCharArray();
      int length = 0;
      // type-in content can just be number
      for (int i = 0; i < s.length; i++) {
        if(getLength() <= 3){
          if ((s[i] >= '0') && (s[i] <= '9')) {
            s[length] = s[i];
            length++;
          }
          // insert the content
          super.insertString(offset, new String(s, 0, length), attr);
        }
      }
    }
  }
  //a class to restrict the type-in content and length (month)
  static class MonthTextField extends PlainDocument {
    public MonthTextField() {
      super();
      //set default value
      try{
        super.insertString(0, Integer.toString(time.get(Calendar.MONTH) + 1), null);
      }
      catch(BadLocationException e){
        System.out.println(e.getMessage());
      }
    }
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null) {
        return;
      }
      //restrict length
      if(str.length() >= 2){
        return;
      }
      
      char[] s = str.toCharArray();
      int length = 0;
      // type-in content can just be number
      for (int i = 0; i < s.length; i++) {
        if(getLength() <= 1){
          if ((s[i] >= '0') && (s[i] <= '9')) {
            s[length] = s[i];
            length++;
          }
          // insert the content
          super.insertString(offset, new String(s, 0, length), attr);
        }
      }
    }
  }
  //a class to restrict the type-in content and length (date)
  static class DateTextField extends PlainDocument {
    public DateTextField() {
      super();
      //set default value
      try{
        super.insertString(0, Integer.toString(time.get(Calendar.DAY_OF_MONTH)), null);
      }
      catch(BadLocationException e){
        System.out.println(e.getMessage());
      }
    }
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null) {
        return;
      }
      //restrict length
      if(str.length() >= 2){
        return;
      }
      
      char[] s = str.toCharArray();
      int length = 0;
      // type-in content can just be number
      for (int i = 0; i < s.length; i++) {
        if(getLength() <= 1){
          if ((s[i] >= '0') && (s[i] <= '9')) {
            s[length] = s[i];
            length++;
          }
          // insert the content
          super.insertString(offset, new String(s, 0, length), attr);
        }
      }
    }
  }
  //a class to restrict the type-in content and length (number of room)
  static class RoomTextField extends PlainDocument {
    public RoomTextField() {
      super();
    }
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null) {
        return;
      }
      
      char[] s = str.toCharArray();
      int length = 0;
      // type-in content can just be number
      for (int i = 0; i < s.length; i++) {
        if ((s[i] >= '0') && (s[i] <= '9')) {
          s[length] = s[i];
          length++;
        }
        // insert the content
        super.insertString(offset, new String(s, 0, length), attr);
      }
    }
  }
  
  //clear the text in TeleTextField when it is focused
  static class TeleEXHandler implements FocusListener{
    public void focusGained(FocusEvent e){
      if(((JTextField)e.getSource()).getText().equals("1234-567890")){
        ((JTextField)e.getSource()).setText("");
      }
    }
    public void focusLost(FocusEvent e){
      if(((JTextField)e.getSource()).getText().equals("")){
        ((JTextField)e.getSource()).setDocument(new TeleTextField());
      }
    }
  }
  
}