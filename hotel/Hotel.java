package hotel;
import java.util.Calendar;
public abstract class Hotel{//因為有用 absract 方法，所以 hotel 類別要設為 abstract
  private String name, tele;
  protected String type;
  private Calendar check_in_date, check_out_date;
  private int people, room;
  private static Calendar before_date = Calendar.getInstance();
  private static Calendar after_date = Calendar.getInstance();
  
  //hotel建構元
  public Hotel(){
    name = "";
    tele = "";
    type = "";
    //set the date of today to the CID and COD, after_date is 3 month later than now
    after_date.add(Calendar.MONTH,3);
    check_in_date = before_date;
    check_out_date = after_date;
    people = 0;
    room = 0;
  }
  
  public void setName(String n) throws IllegalArgumentException{
    //name can just contain letters
    if(n.matches(".*[^A-Za-z].*")){
      throw new IllegalArgumentException("Illegal name.");
    }
    name = n;
  }
  public void setTele(String t) throws IllegalArgumentException{
    //form of tele must be XXXX-XXXXXX
    if(t.matches("[0-9]{4}-[0-9]{6}")){
    }
    else{
      throw new IllegalArgumentException("Illegal telephone number.");
    }
    tele = t;
  }
  public abstract void setType();
  public void setCID(int y, int m, int d) throws IllegalArgumentException{
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR,y);
    if(m < 0 || m > 12){
      throw new IllegalArgumentException("Illegal date!");
    }
    date.set(Calendar.MONTH,m - 1);
    if(d > date.getActualMaximum(Calendar.DAY_OF_MONTH) || d < date.getActualMinimum(Calendar.DAY_OF_MONTH)){
      throw new IllegalArgumentException("Illegal date!");
    }
    date.set(Calendar.DAY_OF_MONTH,d);
    //check in date must be after the date of today
    if(date.compareTo(before_date) < 0){
      throw new IllegalArgumentException("Check in date can't be before the date of today.");
    }
    //check in date must be before check out date
    if(date.after(check_out_date)){
      throw new IllegalArgumentException("Check in date must be before check out date");
    }
    check_in_date = date;
  }
  public void setCOD(int y, int m, int d) throws IllegalArgumentException{
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR,y);
    if(m < 0 || m > 12){
      throw new IllegalArgumentException("Illegal date!");
    }
    date.set(Calendar.MONTH,m - 1);
    if(d > date.getActualMaximum(Calendar.DAY_OF_MONTH) || d < date.getActualMinimum(Calendar.DAY_OF_MONTH)){
      throw new IllegalArgumentException("Illegal date!");
    }
    date.set(Calendar.DAY_OF_MONTH,d);
    //check out date must be after the date of today
    if(date.compareTo(before_date) < 0){
      throw new IllegalArgumentException("Check out date can't be before the date of today.");
    }
    //check out date must be after check in date
    if(date.before(check_in_date)){
      throw new IllegalArgumentException("Check out date must be after check in date");
    }
    check_out_date = date;
  }
  public void setPeople(int p) throws IllegalArgumentException{
    //the number of people can't be more than 4 or less than 0
    if(p <= 0 || p > 4){
      throw new IllegalArgumentException("The number of people is out of range.");
    }
    people = p;
  }
  public void setRoom(int r) throws IllegalArgumentException{
    //the number of room can't be less than 0
    if(r <= 0){
      throw new IllegalArgumentException("The number of room can't be 0 or below.");
    }
    room = r;
  }
  public String getName(){
    return name;
  }
  public String getTele(){
    return tele;
  }
  public String getType(){
    return type;
  }
  public Calendar getCID(){
    return check_in_date;
  }
  public Calendar getCOD(){
    return check_out_date;
  }
  public String getCID_format(){
    int m = check_in_date.get(Calendar.MONTH) + 1;
    String d = check_in_date.get(Calendar.YEAR) + "/" + m + "/" + check_in_date.get(Calendar.DAY_OF_MONTH);
    return d;
  }
  public String getCOD_format(){
    int m = check_out_date.get(Calendar.MONTH) + 1;
    String d = check_out_date.get(Calendar.YEAR) + "/" + m + "/" + check_out_date.get(Calendar.DAY_OF_MONTH);
    return d;
  }
  public int getPeople(){
    return people; 
  }
  public int getRoom(){
    return room;
  }
  /*public static void checkIn(){
    
  }*/
  /*public static void checkOut(){
    
  }*/
  public abstract int computePrice();
  public abstract String toString();
  public boolean equals(Hotel h){
    if(name.equals(h.getName()) && tele.equals(h.getTele()) && type.equals(h.getType()) && people == h.getPeople() && room == h.getRoom() && getCID_format().equals(h.getCID_format()) && getCOD_format().equals(h.getCOD_format())){
      return true;
    }
    else{
      return false;
    }
  }
  
}