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
    private int wait = 1000;


    public ClockTask(){
        java.util.Timer timer;
        timer = new Timer();
        timer.schedule(this,1000,5000);

        run();
    }

    public JLabel getTime(){
        return time;
    }


    @Override
    public void run() {
        Calendar maintenant = Calendar.getInstance();

        time.setText(formatDate.format(maintenant.getTime()));

    }
}
