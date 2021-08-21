import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plane {

    private String Departure, Arrival, type;


    private int[] aTime; //Flight time
    private int[] dates; // Flight date
    private long bookingtime;

    private Map<User, Integer> directory;
    //indicates the entire flight directory based on User and seat number

    public Plane(String[] locs, int[] ftime, String pType, int[] days) {
        //Initialize Plane's instance variables

        this.Departure = locs[0];
        this.Arrival = locs[1];
        this.aTime = ftime;//Flight Time
        this.type = pType;
        this.directory = new HashMap<>();
        this.dates = days;//Flight date
    }

    public void setBookingtime(){
        this.bookingtime = System.nanoTime();
    }

    public String getaLoc() {
        return Arrival;
    }

    public String getdLoc() {
        return Departure;
    }

    public String getpType() {
        return type;
    }

    public int getdTime() {
        return aTime[0];
    }

    public int getaTime() {
        return aTime[1];
    }

    public int[] getDays(){
        return dates;
    }


    public void sellTicket(User user, int seatnum) {

        this.directory.put(user, seatnum);
        System.out.println("You have booked Seat #"+seatnum);
    }

    public ArrayList<Integer> availSeats(boolean suite){
        //Will Iterate through the all of the values in the directory and adds all numbers that are not present to an arraylist and returns it
        ArrayList<Integer> seatsA = new ArrayList<Integer>();
        for(int i = 1; i<=60; i++){
            if(!(this.directory.values().contains(i))){
                seatsA.add(i);
            }

        }
        //If statement to see what class they're looking for based on suite and will return the seats that are available in that class
        ArrayList<Integer> suitT = new ArrayList<Integer>();
        if(suite){
            for(int a: seatsA){
                if(a<=15){
                    suitT.add(a);
                }
            }
        }else{
            for(int a: seatsA){
                if(a>15){
                    suitT.add(a);
                }
            }
        }
        return suitT;

    }

    public int getCost(boolean suite) {
        //If statement to see whether user is in first or economy and will hardcode and return costs
        if(suite) {
            return 180;
        }else {
            return 100;
        }
    }

    public String toString(){
        return this.getpType()+" Departure: "+this.getdTime()+" Arrival: "+this.getaTime();
    }

}
