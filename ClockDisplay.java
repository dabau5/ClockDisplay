
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private int timeOfDay; // 0 for am, 1 for pm, 0 default in contructor
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        timeOfDay = 0;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters. 
     * 
     * tod is Time of Day. 0 = am, 1 = pm
     */
    public ClockDisplay(int hour, int minute, int tod)
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        timeOfDay = tod;
        setTime(hour, minute, tod);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if (hours.getValue() == 12) {  //time of day just changed
            if (timeOfDay == 0)
            timeOfDay = 1;
            else timeOfDay = 0;
           }
        }
        if (hours.getValue() == 0) {  //it just rolled over
            hours.setValue(1); // can't be 0
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, int tod)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        timeOfDay = tod;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
        if (timeOfDay == 0)
        displayString = displayString + "am";
        else
        displayString = displayString + "pm";
                       
                    
    }
}
