package enumdemo;

/**
 *
 *
 * Created by hdhamee on 12/9/15.
 */
public enum TimeTable {
    SUNDAY("Math","Science"),
    MONDAY("English","Nepali"),
    TUESDAY("Health","Social");
    // more days here

    private String firstSub = null;
    private String secondSub = null;

    TimeTable(String firstSub,String secSub) {
        this.firstSub = firstSub;
        this.secondSub = secSub;

    }

    public String  getTimeTable(){
        return firstSub + " " +secondSub;
    }
}
