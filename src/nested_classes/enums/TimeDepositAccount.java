package nested_classes.enums;

import java.util.Calendar;
import java.util.Date;

public class TimeDepositAccount extends Account {

    private final Date maturityDate;

    // Constructor now takes in an Enum as the second arg
    public TimeDepositAccount(double balance, DepositLength duration) {
        super(balance);
        // Calendar.getInstance() returns a Calendar object initialised to
        // the current date and time (for the default locale)
        // Calendar is very useful for manipulating dates e.g. adding.
        Calendar cal = Calendar.getInstance();
        // add 90 or 180 days to today (with today represented as days
        // i.e. 10th Feb would be day 41 (31 for Jan + 10)
        // API - "The first day of the year has value 1." (as opposed to 0)
        cal.add(Calendar.DAY_OF_YEAR, duration.getDays());
        // convert to a Date object
        this.maturityDate = cal.getTime();
    }

    @Override
    public boolean withdraw(double amount) {
        Date today = new Date();
        if(today.after(maturityDate)) {
            if(amount <= balance) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Time Deposit Account " + maturityDate;
    }

}