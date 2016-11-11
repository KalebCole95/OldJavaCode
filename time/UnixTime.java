package unix.time;
import java.util.Scanner;
//Kaleb Cole (also this does the leap years)
public class UnixTime
{
    public static void main(String[] args)
    {
        int sec, min, hour, day, month, year, leaps = 0, count = 0;
        boolean isleap = false;
        Scanner input = new Scanner(System.in);
        System.out.println("What is the Hours, Minutes, and Seconds:");
        hour = input.nextInt();
        min = input.nextInt();
        sec = input.nextInt();
        System.out.println("What is the Day, Month, and Year:");
        day = input.nextInt();
        month = input.nextInt();
        year = input.nextInt();
        while (year > 1970)
        {
            if (year % 4 == 0){ 
                if (year % 100 == 0){
                    if (year % 400 == 0){
                        leaps = leaps + 1;
                    }
                } else {
                    leaps = leaps + 1;
                }
            }
            year = year - 1;
            if (leaps == 1 && count ==0){isleap = true;}
            count = count + 1;
        }
        if (isleap == true){if (month > 2){day = day + leaps;} else {day = day + (leaps - 1);}} else {day = day + leaps;}
        if (month >1){day = day + 31;}
        if (month >2){day = day + 28;}
        if (month >3){day = day + 31;}
        if (month >4){day = day + 30;}
        if (month >5){day = day + 31;}
        if (month >6){day = day + 30;}
        if (month >7){day = day + 31;}
        if (month >8){day = day + 31;}
        if (month >9){day = day + 30;}
        if (month >10){day = day + 31;}
        if (month >11){day = day + 30;}
        day = day - 1 + (count*365);
        hour = hour + (day*24);
        min = min + (hour*60);
        sec = sec + (min*60);
        System.out.println("The time is: " + hour + ":" + min + ":" + sec);
        System.out.println("The Date is: " + month + "/" + day + "/" + year);
        System.out.println("The unix time is: " + sec);
    } 
}