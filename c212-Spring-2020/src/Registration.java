import java.util.ArrayList;
import java.util.Scanner;

public class Registration extends FlightInfo {


    Scanner sc = new Scanner(System.in);
    String uname, pword;

    public void register() {
        System.out.println("Do you have an account: Type Y for yes and N for no");
        String r1 = sc.next();

        if(r1.equals("Y")) {

            /*
             * Will check if username is not in blacklist, if not in blacklist, asks for password
             * if password is correct, based on whether the login ID has an -AR on the end of it, it opens up the appropriate menu screen
             * Will proceed to run selectChoices and so on and so forth
             */

            System.out.println("Enter your username: ");
            uname = sc.next();

            if(super.blackList.contains(uname)){
                System.out.println("You've been blacklisted so you can't book flights");
            }else if(uname.substring(uname.length()-3) == "-AR"){

                System.out.println("Enter your password: ");
                pword = sc.next();

                String type;
                System.out.println("What company are you working for?");
                System.out.println("Press 1 for Delta\nPress 2 for United\nPress 3 for American");
                int choix = sc.nextInt();

                if(choix == 1){
                    type = "Delta";
                }else if(choix == 2){
                    type = "United";
                }else{
                    type = "American";
                }

                AirlineRep check = new AirlineRep(uname, pword, type);
                if(!(super.APBase.contains(check))){
                    System.out.println("Wrong username, password, or type");
                }else{
                    for(AirlineRep a: super.APBase){
                        if((check.getCompany() == a.getCompany()) && (check.getPassword() == a.getPassword()) && (check.getUsername() == a.getUsername())){
                            a.menuScreen();
                        }
                    }
                }

            }else{

                System.out.println("Enter your password: ");
                pword = sc.next();

                User check = new User(uname, pword);
                if(!(super.userBase.contains(check))){
                    System.out.println("Wrong username or password");
                }else{
                    for(User a: super.userBase){
                        if((check.getUsername() == a.getUsername()) && (check.getPassword() == a.getPassword())){
                            a.menuScreen();
                        }
                    }
                }

            }

        }else if(r1.equals("N")) {
            System.out.println("Enter your username: ");
            uname = sc.next();

            System.out.println("Enter your password: ");
            pword = sc.next();

            if(uname.substring(uname.length()-3).equals("-AR")) {
                /*
                 * Will ask the airline rep to select their airline from choices of
                 * 1. Delta
                 * 2. United
                 * 3. American
                 */

                String type;
                System.out.println("What company are you working for?");
                System.out.println("Press 1 for Delta\nPress 2 for United\nPress 3 for American");
                int choix = sc.nextInt();

                if(choix == 1){
                    type = "Delta";
                }else if(choix == 2){
                    type = "United";
                }else{
                    type = "American";
                }

                //Will create and add airline representative to totUsers APBase arraylist
                //Will proceed to run menuScreen and so on and so forth

                AirlineRep att = new AirlineRep(uname, pword, type);
                super.APBase.add(att);
                att.menuScreen();

                //
            }else {
                //Will create and add user to totUsers APBase userbase if their username is not in the blacklist
                //Will proceed to run selectChoices and so on and so forth

               if(!(blackList.contains(uname))){
                   User set = new User(uname, pword);
                   super.userBase.add(set);
                   set.menuScreen();
               }else{
                   System.out.println("Your name is on the blacklist, so you cant book flights");
               }

            }

        }

        System.out.println("Would like to Logout? Type Y for yes and N for no");
        String r2 = sc.next();

        if(r2 == "Y") {
            System.out.println("You have been logged out");
            register();
        }
        //If they want to stay, re-run either selectChoices or menuScreen based on whether its a Airline rep or a user.

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Registration runner = new Registration();
        runner.register();

    }

}
