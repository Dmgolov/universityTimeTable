package antColony.timetable;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CalendarFinder {

    public static void main(String[] args) {
        int n=10 ;// no. of persons
        int m=30 ;//no.of days
        Map<String,int[][]> personCalendar = preparePersonCalendars(n,m);
        printPersonCalendar(personCalendar);
        Map<String,int[][]> testData = prepareTestData(n,m);
        printPersonCalendar(testData);
        meetingSlotFinder(testData);
        meetingSlotFinder(personCalendar);
    }

    private static Map<String,int[][]> preparePersonCalendars(int n, int m) {
        Random rnd = new Random();
        Map<String,int[][]> personCalendar = new TreeMap<String,int[][]>();
        for(int i=0; i<m; i++) { //iterate over days
            int[][] daysSlots = new int[n][16];
            for(int j=0;j<n;j++) { //iterate over persons
                for(int slot=0;slot<16;slot++) {
                    daysSlots[j][slot] = rnd.nextInt(2);
                }
            }
            personCalendar.put(i<9 ? "Day_0"+(i+1) : "Day_"+(i+1), daysSlots);
        }
        return personCalendar;
    }

    private static Map<String,int[][]> prepareTestData(int n, int m) {
        Map<String,int[][]> testData = new TreeMap<String,int[][]>();
        for(int i=0; i<m; i++) { //iterate over days
            int[][] daysSlots = new int[n][16];
            for(int j=0;j<n;j++) { //iterate over persons
                for(int slot=0;slot<16;slot++) {
                    daysSlots[j][slot] = i%5==0 && slot%6==0 ? 1 : 0;
                }
            }
            testData.put(i<9 ? "Day_0"+(i+1) : "Day_"+(i+1), daysSlots);
        }
        return testData;
    }

    private static void printPersonCalendar(Map<String,int[][]> personCalendar) {
        for(Map.Entry<String, int[][]> calendar: personCalendar.entrySet()) {
            System.out.println("Printing Calendar availability for : " + calendar.getKey());
            int[][] pCalArray = calendar.getValue();
            for(int i=0; i<pCalArray.length; i++) {
                System.out.println("Person : " + (i+1));
                for(int j=0;j<pCalArray[0].length;j++) {
                    System.out.print(" " + pCalArray[i][j]);
                }
                System.out.print("\r\n");
            }
        }
    }

    private static void meetingSlotFinder(Map<String,int[][]> personCalendar) {
        int ctr=0;
        for(Map.Entry<String, int[][]> calendar: personCalendar.entrySet()) {
            int[][] pCalArray = calendar.getValue();
            for(int j=0;j<pCalArray[0].length;j++) { // iterate over slots
                int result = 1;
                for(int i=0; i<pCalArray.length-1; i++) { //iterate over persons
                    ctr++;
                    result = result & pCalArray[i][j]& pCalArray[i+1][j];
                    if(result==0) break;
                }
                if(result == 1)
                    System.out.println("**** Meeting match at Day : " +
                            calendar.getKey() + " and at slot: " + j);
                else
                    System.out.println("Couldn't find any meeting match at Day : " +
                            calendar.getKey() + " and at slot: " + j);
            }
        }
        System.out.println("#@$&* Total Iterations performed : " + ctr);
    }

}
