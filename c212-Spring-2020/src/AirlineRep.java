import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AirlineRep extends FlightInfo {
    private String username, password, company;
    Scanner sc = new Scanner(System.in);
    Map<Integer, String[]> set;

    public AirlineRep(String uname, String pword, String type) {
        this.username = uname;
        this.password = pword;
        this.company = type;
    }

    public void menuScreen() {

        System.out.println("Welcome "+this.username);
        /*
         * Prints out a menu screen with choices ranging between
         * 1. Add Flights
         * 2. Remove Flights
         * 3. Gift miles to user
         * 4. Blacklist user
         * 5. View reviews
         *
         * Based on this selection an if statement will lead to the respective method
         */

        System.out.println("Press the appropriate method:");
        System.out.println("1. Add Flights\n2. Remove Flights\n3. Gift miles\n4. Blacklist user\n5. View reviews");

        int choix = sc.nextInt();
        if(choix == 1){
            //Add Flights
            System.out.println("Enter the flight departure:");
            String dep = sc.next();

            System.out.println("Enter the flight destination:");
            String ar = sc.next();

            String[] loc = {dep, ar};

            System.out.println("Enter the flight departure time: ");
            int depTime = sc.nextInt();

            System.out.println("Enter the flight arrival time: ");
            int arrTime = sc.nextInt();

            int[] fTimes = {depTime, arrTime};

            System.out.println("Enter the flight date in the format MM/DD/YYYY: ");
            String date = sc.next();

            int[] days;
            days = new int[]{Integer.valueOf(date.substring(0,2)), Integer.valueOf(date.substring(3,5)), Integer.valueOf(date.substring(6))};

            addFlights(loc, fTimes, days);

        }else if(choix == 2){
            //Remove Flights
            System.out.println("Enter the flight departure:");
            String dep = sc.nextLine();

            System.out.println("Enter the flight destination:");
            String ar = sc.nextLine();

            String[] loc = {dep, ar};

            System.out.println("Enter the flight departure time: ");
            int depTime = sc.nextInt();

            System.out.println("Enter the flight arrival time: ");
            int arrTime = sc.nextInt();

            int[] fTimes = {depTime, arrTime};

            System.out.println("Enter the flight date in the format MM/DD/YYYY: ");
            String date = sc.next();

            int[] days;
            days = new int[]{Integer.valueOf(date.substring(0,2)), Integer.valueOf(date.substring(3,5)), Integer.valueOf(date.substring(6))};

            removeFlights(loc, fTimes, days);

        }else if(choix == 3){
            //Gift miles
            System.out.println("Enter the username of the user: ");
            String mUname = sc.next();

            System.out.println("How many miles do you want to award?");
            int mi = sc.nextInt();

            giftMiles(mUname, mi);

        }else if(choix == 4){
            //Blacklist user
            System.out.println("Enter the username of the user you want to blacklist: ");
            String mUname = sc.next();

            blackList(mUname);
        }else if(choix == 5){
            //View Reviews
        }
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getCompany(){
        return this.company;
    }

    public void addFlights(String[] loc, int[] fTime, int[] days) {
        //Will define plane based on their company and add to allPlanes in FlightInfo if it isn't already in allPlanes
        Plane add = new Plane(loc, fTime, this.company, days);
        super.allPlanes.add(add);
        System.out.println("You have added a flight");
    }

    public void removeFlights(String[] loc, int[] fTime, int[] days) {
        //For loop to check for an equivalent flight with similar parameters in allPlanes in FlightInfo
        Plane rem = new Plane(loc, fTime, this.company, days);

        if(super.allPlanes.contains(rem)){
            for(int i = 0; i < super.allPlanes.size(); i++){
                Plane mer = super.allPlanes.get(i);

                if(rem.equals(mer)){
                    allPlanes.remove(i);
                }
            }
            System.out.println("Flight has been removed");
        }else{
            System.out.println("Cannot find the flight with your parameters");
        }
    }

    public void giftMiles(String mUn, int m) {
        //Finds user in TotUsers and adds m miles to their existing milecount
        for(User u: super.userBase){
            if(u.getUsername().equals(mUn)){
                u.setMiles(u.getMiles()+m);
                break;
            }
        }
        System.out.println("Found user "+mUn+" and added "+ m +" miles to their account");

    }

    public void blackList(String n) {
        //Add user to blacklist arraylist in totUser
        super.blackList.add(n);
        System.out.println("User "+ n+ " has been blacklisted");

    }

    public void viewReviews() {
        //Based on this.company, this method will access the appropriate HashMap in FlightInfo
        if(this.company == "United"){
            set = super.Unitedreviews;

        }else if(this.company == "Delta"){
            set = super.Deltareviews;

        }else{
            set = super.Americanreviews;

        }

        for(Map.Entry<Integer, String[]> t: set.entrySet()){
            System.out.println("Score "+t.getKey()+"/5 by User "+t.getValue()[0]+" who flew on "+t.getValue()[2]);
        }

    }


}
