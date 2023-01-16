/*
    Name: Zehui Zhang
    PID:  A16151490
 */

/**
 * The weather monitor that could get how hot each day is compared to recent days
 * @author Zehui Zhang
 * @since  08/07/2021
 */
public class WeatherMonitor {
    
    IntStack temps; // Using the IntStack we created

    // Create the IntStack with capacity 100
    public WeatherMonitor() {

        this.temps = new IntStack(100);
    }

    /**
     * numDays should take the current day's temperature and return the consecutive
     * number of days that that day's temp is hotter than previous temperatures.
     * @param temp current day's temperature
     * @return the consecutive number of days which are hotter
     */
    public int numDays(int temp) {
        // if there is no previous record, return 0
        if (temps.size() == 0 ){
            temps.push(temp);
            return 0;
        }
        // push data insider the temps intstack
        IntStack cur = temps;
        int[] arr =  cur.multiPop(cur.size());
        for (int i = arr.length-1; i >=0 ; i--) {
            temps.push(arr[i]);
        }
        int count = 0;
        // Count the days which are hotter
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>=temp) {
                break;
            }else {
                count ++;
            }
        }
        temps.push(temp);
        return count; //  return the number of days
    }

    /**
     * Test for the method
     * @param args string list contains data
     */
    public static void main(String[] args){
        WeatherMonitor wm = new WeatherMonitor();
        System.out.println(wm.numDays(59));
        System.out.println(wm.numDays(60));
        System.out.println(wm.numDays(70));
        System.out.println( wm.numDays(69));
        System.out.println(wm.numDays(71));
    }
}
