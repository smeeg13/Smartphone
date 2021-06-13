package Smartphone.Task;


import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class BatteryTask extends TimerTask {

    private JLabel batteryPercentage = new JLabel();
    private ToolBox toolBox = new ToolBox();
    private final String commandBatteryOS = "pmset -g batt";
    private final String commandBatteryWIN = "WMIC PATH Win32_Battery Get EstimatedChargeRemaining";



    /**
     * This constructor initialize the BatteryTask and then run
     */
    public BatteryTask() {
        Timer timer;
        timer = new Timer();
        timer.schedule(this,1000,1000);

        run();
    }


    /**
     * This method get the batteryPercentenage
     * @return the JLabel
     */
    public JLabel getBatteryPercentage(){
        return batteryPercentage;
    }

    /**
     * This method set the batterpPercentage
     * @param batteryLevel
     */
    public void setBatteryPercentage(String batteryLevel){
        batteryPercentage.setText(batteryLevel);
    }

    /**
     * This method executed the code specified.
     * It check if the laptop is a mac or a windows
     */
    public void run() {
        String batteryLevel = "";
        Process process = null;

        if(toolBox.isMac()){
            try {
                process = Runtime.getRuntime().exec(commandBatteryOS);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        if(toolBox.isWindows()){
            try {
                process = Runtime.getRuntime().exec(commandBatteryWIN);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }


        batteryLevel = getPercentageBattery(getResultsBattery(process));  //recherche du pourcentage avec la méthode getPercentageBattery
        setBatteryPercentage(batteryLevel);                                //mise à jour du JLabel avec la nouvelle mesure de batterie

    }

    /**
     * @param process is the result of the terminal command to get the battery percentage
     * @return the whole String with the command line to get the battery Level
     * @throws IOException which means it has been a problem with the reading of the process
     */
    public String getResultsBattery(Process process) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        String result = "";

        try{
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * This method get the percentage of the battery from the String s
     * @param s is the String with the result from the terminal command
     * @return a String with the level of the battery percentage
     */
    public String getPercentageBattery(String s) {
        String result = "";

        if(s.length()==0) return "100%";
        if(toolBox.isWindows()){
            result = s.substring(25,29) + "%";
            result.replaceAll("\\s","");
            return result;
        }

        if(toolBox.isMac()){
            return result = s.substring(s.indexOf("%") - 3, s.indexOf("%") + 1);
        }


        return result;
    }


}
