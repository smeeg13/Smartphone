package Smartphone.Task;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ClockTask extends TimerTask {
    private DateFormat formatDate = new SimpleDateFormat("HH:mm");
    private Calendar maintenant = Calendar.getInstance();
    private JLabel time = new JLabel(formatDate.format(maintenant.getTime()) + "   ");


    /**
     * This constructor initialize the timer
     * delay is the second before starting
     * period is the waiting for each running
     */
    public ClockTask(){

        Timer timer = new Timer();
        timer.schedule(this,1000,5000);

        run();
    }

    /**
     * This method get the time in the JLabel
     * @return the JLabel with the current time
     */
    public JLabel getTime(){
        return time;
    }


    /**
     * This method executed the code specified.
     */
    @Override
    public void run() {
        Calendar maintenant = Calendar.getInstance();

        time.setText(formatDate.format(maintenant.getTime()));

    }
}
