import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class User extends FlightInfo{

    Scanner sc = new Scanner(System.in);

    private String username, password;
    private boolean len; //true indicates round trip while false indicates one-way
    private boolean part;//true indicates first class, false indicates economy
    private double cost; //Indicates the cost of the flight
    private String[] loc = new String[2]; //Locations
    private String[] dates = new String[2];
    int b = 0;
    long bookingtime;

    //Implement timer cancellation

    Timer cancellation = new Timer();


    Map<String, String> prevBookings = new HashMap<>();
    //To hold previous bookings by company and flight date

    //Indicates number of needed tickets
    private int atickets;
    private int ctickets;
    private int totT;

    //True for NV, False for V
    private boolean[] mPlan = new boolean[totT];

    private int miles = 0;

    public User(String uName, String pWord) {
        this.username = uName;
        this.password = pWord;
        this.len = true; //Default Round trip
        this.part = false; //Default economy class

        //Initialize 0 tickets purchased
        this.cost = 0;
        this.atickets = 0;
        this.ctickets= 0;
        this.totT = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void menuScreen() {
        /*
         * Provide choices for user ranging between
         * 1. View booking history
         * 2. Review Flight
         * 3. Cancel Flight
         *
         *Based on the selection, it will run the appropriate method
         */
        System.out.println("Welcome "+getUsername()+"!");
        System.out.println("Press 1 to View booking history\nPress 2 to Review Flight\nPress 3 to Cancel Flight\nPress 4 to Book a new Flight");
        int choix = sc.nextInt();

        if(choix == 1){
            //Booking History
            viewBookingHistory();

        }else if(choix == 2){
            //Review flight
            reviewFlight();

        }else if(choix == 3){
            //Cancel flight
            cancelFlight();

        }else if(choix == 4){
            //Book flight
            selectChoices();
        }

    }

    public void viewBookingHistory() {
        //Prints out prevBookings to show all the flights taken
        System.out.println("Previous Bookings: ");
        for(Map.Entry<String, String> t : prevBookings.entrySet()){
            System.out.println("Company: "+t.getKey()+" Flight Date: "+t.getValue());

        }

    }

    public void reviewFlight() {
        /*
         * Will run BookingHistory and ask for a specific flight to be selected
         * Will ask for a score out of 5 and will add it to the appropriate Hashmap in Flightinfo with username, flightdate, and score
         */

        System.out.println("Select the flight based on its corresponding number: ");
        int i = 1;
        for(Map.Entry<String, String> t : prevBookings.entrySet()){
            System.out.println("Press "+i+" for your "+t.getKey()+" flight on "+t.getValue());
            i++;
        }

        int slctn = sc.nextInt();
        int j = 1;
        String cS = "";

        for(String s: prevBookings.keySet()){
            if(j == slctn){
                cS = s;
            }
            j++;
        }

        System.out.println("You have chosen to review your "+cS+" flight on "+prevBookings.get(cS));
        System.out.println("Give this flight a score out of 5: ");
        int score = sc.nextInt();

        if(cS == "United"){
            String[] userInfo = {username, prevBookings.get(cS)};
            super.Unitedreviews.put(score, userInfo);
        }else if(cS == "Delta"){
            String[] userInfo = {username, prevBookings.get(cS)};
            super.Deltareviews.put(score, userInfo);
        }else{
            String[] userInfo = {username, prevBookings.get(cS)};
            super.Americanreviews.put(score, userInfo);
        }

        System.out.println("Finished reviewing flight!");
    }

    public void cancelFlight() {
        //if the timer that was started in search() crossed the threshold of 10 minutes, print out that you cant cancel after 10 minutes
        //if the timer is behind 5 minutes, then the booking is removed from prevBookings and is given 250 miles
        System.out.println("Select the flight based on its corresponding number: ");
        int i = 1;
        for(Map.Entry<String, String> t : prevBookings.entrySet()){
            System.out.println("Press "+i+" for your "+t.getKey()+" flight on "+t.getValue());
            i++;
        }

        int slctn = sc.nextInt();
        int j = 1;
        String cS = "";

        for(String s: prevBookings.keySet()){
            if(j == slctn){
                cS = s;
            }
            j++;
        }

        System.out.println("You have chosen to cancel your "+cS+" flight on "+prevBookings.get(cS));



    }

    public void selectChoices() {
        System.out.println("Is this a round trip? Y for Yes and N for No");
        String r1 = sc.next();
        if(r1.equals("N")) {
            this.len = false;
        }
        //Based on this decision, it wil intake the dates for the flight, source and destination and number of tickets with the appropriate methods
        //Once all the info is collected, will run search() method

        //Dates
        if(this.len){
            System.out.println("Enter the two dates in MM/DD/YYYY format: ");
            String[] s = {sc.next(), sc.next()};
            setDates(s);
        }else{
            System.out.println("Enter your departure date in MM/DD/YYYY format: ");
            String[] s = {sc.next()};
            setDates(s);
        }


        //Num of tickets
        System.out.println("How many adults on this trip?: ");
        int a = sc.nextInt();

        System.out.println("How many children on this trip?: ");
        int c = sc.nextInt();


        setTickets(a,c);
        sc.nextLine();
        //Location
        System.out.println("Where are you leaving from?: ");
        String lev = sc.nextLine();
        System.out.println(lev);


        System.out.println("Where are you going to?: ");
        String going = sc.nextLine();
        System.out.println(going);

        String[] lox = {lev, going};

        setLoc(lox);

        System.out.println("Would you prefer to go in economy class? Enter Y or N: ");
        String r = sc.next();

        if(r.equals("Y")){
            this.part = true;
        }
        search();

    }


    public void setLen(boolean t) {
        this.len = t;
    }

    public void setDates(String[] date) {
        this.dates = date;
    }

    public void setLoc(String[] points) {
        this.loc = points;
    }

    public void setTickets(int a, int c) {
        this.atickets = a;
        this.ctickets = c;

        this.totT = a+c;
    }

    public void setMiles(int m) {
        this.miles = m;
    }

    public int getMiles() {
        return this.miles;
    }

    public boolean getLen() {
        return this.len;
    }

    public String[] getDates() {
        return this.dates;
    }

    public String[] getLocs() {
        return this.loc;
    }

    public int getTickets() {
        return this.totT;
    }

    public void setmealPreferences() {
        //Asks whether user would like a vegeterian or non-vegeterian meal plan
        //if v meal plan, change mplan to false in a for loop per ticket and places it into the array of meals per user
        for(int u = 0; u<this.totT; u++){
            System.out.println("Would ticket #"+(u+1)+" like a vegeterian meal non-vegeterian meal? V or NV");
            String m = sc.next();
            if(m == "V"){
                this.mPlan[u] = false;
            }else{
                this.mPlan[u] = true;
            }
        }

    }

    public int calcTotCost(Plane s) {
        //For loop through all tickets and run getCost method from the plane to get the seat ticket based on class
        //Run another for loop of mPlan to get cost of food choices which will be hardcoded
        //Run additionalLuggage with the numLuggage
        int proxyendResult  = 0;
        for(int u = 0; u< this.totT; u++){
            proxyendResult+=s.getCost(this.part);
        }

        for(boolean k: mPlan){
            if(k){
                proxyendResult+=25;
            }else{
                proxyendResult+=20;
            }
        }

        proxyendResult+=additionalLuggage(b);
        return proxyendResult;
    }

    public int additionalLuggage(int l) {
        return l*45;
    }

    public void search() {
        System.out.println("Searching for flights...");

        //For loop through all Planes where the number of seats available are >= tickets in allPlanes and will use the info in User class to find the right plane
        //Will also select preferred airline
        /*
         * Will offer sorting order options such as:
         * 1. Time
         * 2. Preferred Airline
         *
         * If none are available prints the message and asks whether you would like to run it again with different values
         * if yes, runs selectChoices();
         *
         * Available planes will be printed out from sorts and will ask for the index for selection
         *
         * Based on what plane is selected, for loop of plane's sellticket method will be run to place user's tickets in the appropriate seat in the appropriate class
         * if plane.atime[1]-plane.atime[0] >=3 hours, mealpreferences() will be called to save the user's mealpreferences
         *
         *Ask if additional luggage is necessary and save the number of bags
         *run calcTotCost and display the endCost
         *
         *
         * if(miles >= endCost){
         *pay by cash or by miles and
         *
         *if miles, miles-endCost
         *
         *Add flight to user's prevBooking map
         *
         *Start timer cancellation after flight is officially booked
         *
         * run menuScreen method
         */
        if (getLen()) {
            //Round-trip

            }else{
                //One-way
                int[] d = {Integer.valueOf(dates[0].substring(0, 2)), Integer.valueOf(dates[0].substring(3, 5)), Integer.valueOf(dates[0].substring(6))};

            System.out.println(a.availSeats(this.part).size()+" "+this.totT);
                ArrayList<Plane> hck = new ArrayList<Plane>();

                //Finds planes that fit the constraints and add them to an arrayList
                for (Plane a : super.allPlanes) {
                    System.out.println(a.getdLoc()+" "+this.getLocs()[1]);
                    System.out.println(a.getaLoc()+" "+this.getLocs()[0]);
                    System.out.println(a.getDays()[0]+" "+d[0]);
                    System.out.println(a.getDays()[1]+" "+d[1]);
                    System.out.println(a.getDays()[2]+" "+d[2]);
                    if ((a.getdLoc().equals(this.getLocs()[1])) && (a.getaLoc().equals(this.getLocs()[0])) && (a.getDays().equals(d)) && (a.availSeats(this.part).size() >= this.totT)) {
                        hck.add(a);
                    }
                }

                if (hck.isEmpty()) {
                    System.out.println("Sorry, there are no planes that match your request: ");
                    selectChoices();
                } else {
                    Plane select = null;
                    for (Plane c : hck) {
                        System.out.println(c);
                    }

                    boolean t = true;

                    System.out.println("Press S to sort, or F to Filter, or N to directly select a flight: ");
                    String sl = sc.next();


                    if (sl.equals("S")) {
                        int i = 1;
                        ArrayList<Plane> sorted = sort(hck);
                        for (Plane k : sorted) {
                            System.out.println("Press " + i + " for " + k.toString());
                            i++;
                        }
                        int c = sc.nextInt();
                        select = sorted.get(c - 1);
                        System.out.println("You have selected " + select);

                        System.out.println("Here are the seats available:" + select.availSeats(this.part));

                        for (int tk = 0; tk < this.totT; tk++) {
                            System.out.println("Enter seat selection #" + (tk + 1));
                            int st = sc.nextInt();
                            select.sellTicket(this, st);
                        }

                        if (select.getaTime() - select.getdTime() >= 3) {
                            System.out.println("You have to select meal plans");
                            setmealPreferences();
                        }

                        //Luggage
                        System.out.println("Do you need to account for any extra luggage? Y for Yes and N for No");
                        String l = sc.next();

                        if (l.equals("Y")) {
                            System.out.println("How many extra bags should we account for?");
                            b = sc.nextInt();
                        }

                        this.cost = calcTotCost(select);


                    } else if (sl.equals("F")) {
                        int i = 1;
                        ArrayList<Plane> sorted = filter(hck);
                        for (Plane k : sorted) {
                            System.out.println("Press " + i + " for " + k.toString());
                            i++;
                        }
                        int c = sc.nextInt();
                        select = sorted.get(c - 1);
                        System.out.println("You have selected " + select);

                        System.out.println("Here are the seats available:" + select.availSeats(this.part));

                        for (int tk = 0; tk < this.totT; tk++) {
                            System.out.println("Enter seat selection #" + (tk + 1));
                            int st = sc.nextInt();
                            select.sellTicket(this, st);
                        }

                        if (select.getaTime() - select.getdTime() >= 3) {
                            System.out.println("You have to select meal plans");
                            setmealPreferences();
                        }

                        //Luggage
                        System.out.println("Do you need to account for any extra luggage? Y for Yes and N for No");
                        String l = sc.next();

                        if (l.equals("Y")) {
                            System.out.println("How many extra bags should we account for?");
                            b = sc.nextInt();
                        }

                        this.cost = calcTotCost(select);


                    } else if (sl.equals("N")) {
                        int i = 1;
                        ArrayList<Plane> sorted = hck;
                        for (Plane k : sorted) {
                            System.out.println("Press " + i + " for " + k.toString());
                            i++;
                        }
                        int c = sc.nextInt();
                        select = sorted.get(c - 1);
                        System.out.println("You have selected " + select);

                        System.out.println("Here are the seats available:" + select.availSeats(this.part));

                        for (int tk = 0; tk < this.totT; tk++) {
                            System.out.println("Enter seat selection #" + (tk + 1));
                            int st = sc.nextInt();
                            select.sellTicket(this, st);
                        }

                        if (select.getaTime() - select.getdTime() >= 3) {
                            System.out.println("You have to select meal plans");
                            setmealPreferences();
                        }

                        //Luggage
                        System.out.println("Do you need to account for any extra luggage? Y for Yes and N for No");
                        String l = sc.next();

                        if (l.equals("Y")) {
                            System.out.println("How many extra bags should we account for?");
                            b = sc.nextInt();
                        }
                        this.cost = calcTotCost(select);

                    }
                    System.out.println("Total Cost: $" + this.cost);
                    if (miles >= this.cost) {
                        System.out.println("You can pay with cash or miles: 1 for cash, 2 for miles");
                        int p = sc.nextInt();

                        if (p == 1) {
                            System.out.println("Thank you for flying with us");
                            select.setBookingtime();

                        } else {
                            this.miles = (int) (miles - this.cost);
                            System.out.println("Thank you for flying with us");
                            select.setBookingtime();

                        }
                    } else {
                        System.out.println("Thank you for flying with us");
                        select.setBookingtime();
                    }
                    DateFormat dF = new SimpleDateFormat("MM/DD/YYYY");
                    Date date = new Date();
                    prevBookings.put(select.getpType(), dF.format(date));

                }
            }
            menuScreen();
        }

        public ArrayList<Plane> sort (ArrayList < Plane > mon) {
            System.out.println("Press 1 to sort by time\nPress 2 to sort by Airline");
            int s = sc.nextInt();

            if (s == 1) {
                ArrayList<Plane> j = mon;
                Plane temp;
                for (int i = 0; i < j.size(); i++) {
                    for (int m = i + 1; m < j.size(); m++) {
                        if (j.get(i).getaTime() > j.get(m).getaTime()) {
                            temp = j.get(i);
                            j.set(i, j.get(m));
                            j.set(m, temp);

                        }
                    }
                }
                return j;
            } else {
                ArrayList<Plane> j = mon;
                Plane temp;
                for (int i = 0; i < j.size(); i++) {
                    for (int m = i + 1; m < j.size(); m++) {
                        if (j.get(i).getpType().length() > j.get(m).getpType().length()) {
                            temp = j.get(i);
                            j.set(i, j.get(m));
                            j.set(m, temp);

                        }
                    }
                }
                return j;
            }

        }

        public ArrayList<Plane> filter (ArrayList <Plane> mon) {
            System.out.println("Press 1 to filter out your non-preferred flights\nPress 2 to filter out early-morning flights");
            int filt = sc.nextInt();
            ArrayList<Plane> don = mon;
            if (filt == 1) {
                System.out.println("Press 1 if your preferred flight is Delta\nPress 2 if your preferred flight is United\nPress 3 if your preferred flight is American:");
                int pr = sc.nextInt();
                String pf;
                if (pr == 1) {
                    pf = "Delta";
                } else if (pr == 2) {
                    pf = "United";
                } else {
                    pf = "American";
                }
                for (Plane a : mon) {
                    if (a.getpType().equals(pf)) {
                        don.add(a);
                    }
                }
                return don;
            } else {
                for (Plane a : mon) {
                    if (a.getdTime() >= 8) {
                        don.add(a);
                    }
                }
                return don;
            }
        }

    }
