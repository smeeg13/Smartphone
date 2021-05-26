package Smartphone.Task;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class BatteryTask extends TimerTask{

    private JLabel batteryPercentage = new JLabel();

    public BatteryTask() {
        Timer timer;
        timer = new Timer();
        timer.schedule(this,1000,1000);

        run();
    }

    public JLabel getBatteryPercentage(){
        return batteryPercentage;
    }

    public void setBatteryPercentage(String batteryLevel){
        batteryPercentage.setText(batteryLevel);
    }


    @Override
    public void run() {
        String batteryLevel = "";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("pmset -g batt");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Process process = Runtime.getRuntime().exec("pmset -g batt | grep -Eo \"\\d+%\" | cut -d% -f1");
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try {
            batteryLevel = getPercentageBattery(getResultsBattery(process));
            setBatteryPercentage(batteryLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    public String getResultsBattery(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        String result = "";
        while ((line = reader.readLine()) != null) {
            result += line;
        }
        return result;
    }

    public String getPercentageBattery(String s) {
        String result = "";
        s.indexOf("%");

        return result = s.substring(s.indexOf("%") - 3, s.indexOf("%") + 1);

    }
}
