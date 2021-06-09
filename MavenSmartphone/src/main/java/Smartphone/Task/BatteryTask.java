package Smartphone.Task;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import Smartphone.ToolBox;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class BatteryTask extends TimerTask {

    private JLabel batteryPercentage = new JLabel();
    private ToolBox toolBox = new ToolBox();

    public BatteryTask() throws BusinessException{
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

    public void run() {             //method run qui va excéctuer une commande sur un terminal
        String batteryLevel = "";
        Process process = null;

        if(toolBox.isMac()){
            try {
                process = Runtime.getRuntime().exec("pmset -g batt"); //commande pour vérifier niveau de batterie
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        if(toolBox.isWindows()){
            try {
                process = Runtime.getRuntime().exec("WMIC PATH Win32_Battery Get EstimatedChargeRemaining"); //commande pour vérifier niveau de batterie
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }


        batteryLevel = getPercentageBattery(getResultsBattery(process));  //recherche du pourcentage avec la méthode getPercentageBattery
        setBatteryPercentage(batteryLevel);                                //mise à jour du JLabel avec la nouvelle mesure de batterie

    }

    /**
     *
     * @param process
     * @return the whole String with the command line to get the battery Level
     * @throws IOException
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

    public String getPercentageBattery(String s) {
        String result = "";

        if(isWindows()){
            result = s.substring(25,29) + "%";
            result.replaceAll("\\s","");
            return result;
        }

        if(toolBox.isMac()){
            s.indexOf("%");
            return result = s.substring(s.indexOf("%") - 3, s.indexOf("%") + 1);
        }


        return result;
    }


}
