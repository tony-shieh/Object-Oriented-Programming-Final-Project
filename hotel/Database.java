package hotel;
//method for booking, deleting, searchond, and modifying
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
class Database{

  private ArrayList< Hotel > detail = new ArrayList< Hotel >(); //an ArrayList to store Hotel object
  private FileWriter fw;
  private PrintWriter writeFile; //a PrintWriter
  private FileReader fr;
  private BufferedReader readFile; //a BufferedReader
  private StringTokenizer st; //a StringTokenizer to deal with deleting, searching, and modifying
  
  Database(){
    fw = null;
    writeFile = null;
    fr = null;
    readFile = null;
    readData();
  }
  
  //booking method
  String booking(Hotel h0){
    
    try{
      fw = new FileWriter("Booking Details.txt",true);
      writeFile  = new PrintWriter(fw);
      writeFile.println(h0.toString() + "\n"); //write details of objects in the ArrayList to the txt
      writeFile.close();
    }
    catch(FileNotFoundException e){
      writeFile.close();
      return "File not found!";
    }
    catch(IOException e){
      writeFile.close();
      return "Error";
    }
    return "You booked the room successfully!\n\n" + h0.toString();
  }
  
  //deleting method
  String deleting(Hotel h0){
    readData();
    
    Hotel h = searching(h0); //a Hotel object that will be compared to the object that users want to search
    if(h != null){
      for(int i = 0; i < detail.size(); i++){
        if(detail.get(i).equals(h)){
          detail.remove(detail.get(i)); //remove the object from the ArrayList
        }
      }
      
      //re-write the file
      try{
        fw = new FileWriter("Booking Details.txt");
        writeFile  = new PrintWriter(fw);
      }
      catch(FileNotFoundException e){
        writeFile.close();
        readData();
        return "File not found!";
      }
      catch(IOException e){
        writeFile.close();
        readData();
        return "Error";
      }
      for(int j = 0; j < detail.size(); j++){
        Hotel ho = detail.get(j);
        booking(ho);
      }
      writeFile.close();
      readData();
      return "You deleted the info successfully!\n\n" + h0.toString();
    }
    readData();
    return "Sorry, we didn't find your Info!";
  }
  
  //searching method
  Hotel searching(Hotel h0){
    readData();
    
    for(int i = 0; i < detail.size(); i++){
      //if h matches the object who call the method
      if(detail.get(i).equals(h0)){
        readData();
        return h0;
      }
    }
    readData();
    return null;
  }
  
  //modifying method
  String modifying(Hotel originalh, Hotel newh){
    readData();
    
    Hotel h = searching(originalh); //a Hotel object that will be compared to the object that users want to search
    if(h != null){
      for(int i = 0; i < detail.size(); i++){
        if(detail.get(i).equals(h)){
          //modify the info of detail.get(i)
          if(newh.getType().equals("Costal Room")){
            detail.set(i,new Costal_Hotel());
          }
          else{
            detail.set(i,new Mountain_Hotel());
          }
          detail.get(i).setName(newh.getName());
          detail.get(i).setTele(newh.getTele());
          detail.get(i).setPeople(newh.getPeople());
          detail.get(i).setRoom(newh.getRoom());
          detail.get(i).setCID(newh.getCID().get(Calendar.YEAR), newh.getCID().get(Calendar.MONTH) + 1, newh.getCID().get(Calendar.DAY_OF_MONTH));
          detail.get(i).setCOD(newh.getCOD().get(Calendar.YEAR), newh.getCOD().get(Calendar.MONTH) + 1, newh.getCOD().get(Calendar.DAY_OF_MONTH));
        }
      }
      
      //re-write the file
      try{
        fw = new FileWriter("Booking Details.txt");
        writeFile  = new PrintWriter(fw);
      }
      catch(FileNotFoundException e){
        writeFile.close();
        readData();
        return "File not found!";
      }
      catch(IOException e){
        writeFile.close();
        readData();
        return "Error";
      }
      
      for(int j = 0; j < detail.size(); j++){
        Hotel ho = detail.get(j);
        booking(ho);
      }
      writeFile.close();
      readData();
      return "You modified the info successfully!\n\n" + originalh.toString() + "\n          " + '\u2193' + "\n" + newh.toString();//'\u2193' is the unicode of downward arrow
    }
    readData();
    return "Sorry, we didn't find your Info!";
  }
  
  //read data method
  private void readData(){
    
    //confirm if the ArrayList is empty to avoid loading the same data more than once
    for(int i = 0; i < detail.size(); i++){
      if(detail.get(i) != null){
        detail.clear();
      }
    }

    String data = "", line; //data is a String to store all info, line is a String to store readLine String
    try{
      fw = new FileWriter("Booking Details.txt",true);
      writeFile  = new PrintWriter(fw);
      fr = new FileReader("Booking Details.txt");
      readFile = new BufferedReader(fr);
      //if line isn't null, read it to data
      do{
        line = readFile.readLine();
        if(line != null){
          data += line + "\n";
        }
      }while(line != null);
      readFile.close();
      st = new StringTokenizer(data,"\n"); //a StringTokenizer to seperate every single booking data
      while(st.hasMoreTokens()){
        Hotel h = new Costal_Hotel(); //a Hotel object that will be add to ArrayList
        for(int j = 0; j < 8; j++){
          String cache = st.nextToken(); //to store every single data
          String pere = cache.substring(cache.indexOf(":") + 2); //to store the info following ": "
          //name info
          if(j == 0){
            h.setName(pere);
          }
          //tele info
          else if(j == 1){
            h.setTele(pere);
          }
          //people info
          else if(j == 2){
            h.setPeople(Integer.parseInt(pere));
          }
          //room info
          else if(j == 3){
            h.setRoom(Integer.parseInt(pere));
          }
          //type info
          else if(j == 4){
            if(pere.equals("Mountain Room")){
              Hotel h0 = new Mountain_Hotel();
              h0.setName(h.getName());
              h0.setTele(h.getTele());
              h0.setPeople(h.getPeople());
              h0.setRoom(h.getRoom());
              h = h0;
            }
          }
          //check in date info
          else if(j == 5){
            StringTokenizer slash = new StringTokenizer(pere,"/"); //a StringTokenizer to seperate the date with "/"
            int[] time = new int[3]; //to store year, month and date
            for(int i = 0; i < 3; i++){
              int date = Integer.parseInt(slash.nextToken());
              time[i] = date;
            }
            h.setCID(time[0],time[1],time[2]);
          }
          //check out date info
          else if(j == 6){
            StringTokenizer slash = new StringTokenizer(pere,"/"); //a StringTokenizer to seperate the date with "/"
            int[] time = new int[3]; //to store year, month and date
            for(int i = 0; i < 3; i++){
              int date = Integer.parseInt(slash.nextToken());
              time[i] = date;
            }
            h.setCOD(time[0],time[1],time[2]);
          }
        }
        detail.add(h); //add the new Hotel object to ArrayList
      }
    }
    catch(IOException e){
      System.out.println("There's no file");
    }
    catch(IllegalArgumentException e){
      System.out.println(e.getMessage());
    }

  }
}