package hotel;
import java.util.Calendar;
//subclass of Hotel
public class Costal_Hotel extends Hotel{
  //price of room
  private static final int[] price = {1000, 1800, 2900, 3500};
  
  public Costal_Hotel(){
    super();
    setType();
  }
  
  public void setType(){
    type = "Costal Room";
  }
  public int computePrice(){
    int total = 0;
    ////compute time with the unit of  days, so change year, months to days
    int time = (getCOD().get(Calendar.YEAR)*365 + getCOD().get(Calendar.MONTH)*30 + getCOD().get(Calendar.DAY_OF_MONTH)) - (getCID().get(Calendar.YEAR)*365 + getCID().get(Calendar.MONTH)*30 + getCID().get(Calendar.DAY_OF_MONTH));
    if(time == 0){
      time = 1;
    }
    for(int i = 0; i < 4; i++){
      if(getPeople() == i + 1){
        total = price[i];
      }
    }
    return total*getRoom()*time;
  }
  
  public String toString(){
    return String.format("Name: %s\nTele: %s\nNum of People: %d\nNum of Room: %d\nRoom Type: Costal Room\nCheck In Date: %s\nCheck Out Date: %s\nPrice: %d", getName(), getTele(), getPeople(), getRoom(), getCID_format(), getCOD_format(), computePrice());
  }
}