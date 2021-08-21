import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FlightInfo extends totUsers{

    //Location array that all planes will fly to
    String[] locs = {"New York","San Francisco","Los Angeles","Orlando"};

    //Will hardcode a variety of planes with a randomized locations from locs array
    ArrayList<Plane> allPlanes = new ArrayList<Plane>();

    String[] i= {locs[1], locs[2]};
    int [] d = {02,12,2001};
    int[] ftime = {6, 8};

    Plane a = new Plane(i,ftime, "Delta", d);

    //Flight Reviews
    Map<Integer, String[]> Deltareviews = new HashMap<>();
    //Will hold review <int/5, [username, flightdate in string format]>

    Map<Integer, String[]> Americanreviews = new HashMap<>();
    //Will hold review <int/5, [username, flightdate in string format]>

    Map<Integer, String[]> Unitedreviews = new HashMap<>();
    //Will hold review <int/5, [username, fligh12tdate in string format]>

    String[] locs1 = new String[] {"New York", "Orlando"};
    int[] ftTime1 = new int[] {4, 8};
    int[] days1 = new int[] {06, 03, 2020};
    Plane plane1 = new Plane(locs1, ftTime1, "Delta", days1);


    String[] locs2 = new String[] {"San Francisco", "Los Angeles"};
    int[] ftTime2 = new int[] {6, 13};
    int[] days2 = new int[] {06, 13, 2020};
    Plane plane2 = new Plane(locs2, ftTime2, "American", days2);

    String[] locs3 = new String[] {"Los Angeles", "Orlando"};
    int[] ftTime3 = new int[] {7, 10};
    int[] days3 = new int[] {12, 13, 2020};
    Plane plane3 = new Plane(locs3, ftTime3, "United", days3);

    String[] locs4 = new String[] {"Orlando", "San Francisco"};
    int[] ftTime4 = new int[] {2, 17};
    int[] days4 = new int[] {02, 21, 2020};
    Plane plane4 = new Plane(locs3, ftTime3, "Delta", days3);


}
