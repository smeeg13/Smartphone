package Smartphone.Meteo;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class is used to select and get information from the api openweathermap
 * @author Thomas Cheseaux
 */

public class MeteoRessource extends Meteo{

    private int responseCode;

    /**
     * This method get the icon for the weekly or daily weather
     * @param iconCode iconName of the icon on openweathermap
     * @return an Image to add then to a JLabel and then to a JPanel
     */
    public Image getIcon(String iconCode) throws BusinessException {

        try {

            URL url = new URL("http://openweathermap.org/img/wn/" + iconCode + "@2x.png");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return ImageIO.read(url);
            }
        } catch (Exception ex) {
            throw new BusinessException("Request error to openweathermap.org",ex, ErrorCodes.REQUEST_FAIL);
        }

        return null;
    }

    /**
     * This method get the weather on openweathermap
     * @param location for the city to search
     * @param appId allows to use the website openweathermap
     * @param unit determine the unit to show
     * @return a String with the information of the daily weather
     */
    public String getWeather(String location, String appId, String unit) throws BusinessException {
        String weatherInfo = "";

        try {
            return weatherInfo = getOpenweathermapInfo(new URL("http://api.openweathermap.org/data/2.5/weather"
                    + "?units=" + unit
                    + "&q=" + location
                    + "&appid=" + appId));
        } catch (MalformedURLException e) {
            throw new BusinessException("URL is malformed", e, ErrorCodes.BAD_PARAMETER);
        }
    }


    /**
     * This method get the weekly forecast on openweathermap
     * @param location for the city to search
     * @param appId allows to use the website openweathermap
     * @return a String with all the 5 daily forecast
     */
    public String getForecast(String location, String appId) throws BusinessException {
        String weatherInfoWeek = "";

        try {
            return weatherInfoWeek = getOpenweathermapInfo(new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + location + "&appid=" + appId));
        } catch (MalformedURLException e) {
            throw new BusinessException("URL is malformed", e, ErrorCodes.BAD_PARAMETER);
        }

    }

    /**
     * This method choose the selected daily meteo Info with the string getting from the method getWeather
     * @param location for the city to search
     * @param appId allows to use the website openweathermap
     * @param unit determine the unit to show (metric,imperial,standard)
     * @return return a JPanel to add to the weather display
     * @throws BusinessException with the error code that request failed
     */
    public JPanel getSelectedMeteoInfo(String location, String appId, String unit) throws BusinessException {
        String meteo = "";
        JPanel tPanel;
        JLabel city = new JLabel();
        JLabel country = new JLabel();
        JLabel jlSunrise = new JLabel();
        JLabel jlSunset = new JLabel();
        JLabel jlTempMin = new JLabel();
        JLabel jlTempMax = new JLabel();

        meteo = getWeather(location, appId, unit);

        JSONObject jsonObject = new JSONObject(meteo);

        city.setText("City: " + jsonObject.getString("name"));
        country.setText(jsonObject.getJSONObject("sys").getString("country"));
        jlSunrise.setText("Sunrise: " + unixToHourMinute(jsonObject.getJSONObject("sys").getInt("sunrise")));
        jlSunset.setText("Sunset: " + unixToHourMinute(jsonObject.getJSONObject("sys").getInt("sunset")));

        if(unit.equals("metric")){
            jlTempMin.setText("Temp min: " + jsonObject.getJSONObject("main").getInt("temp_min")+" °C");
            jlTempMax.setText("Temp max: " + jsonObject.getJSONObject("main").getInt("temp_max")+" °C");
        }

        if(unit.equals("imperial")){
            jlTempMin.setText("Temp min: " + jsonObject.getJSONObject("main").getInt("temp_min")+" °F");
            jlTempMax.setText("Temp max: " + jsonObject.getJSONObject("main").getInt("temp_max")+" °F");
        }

        if(unit.equals("standard")){
            jlTempMin.setText("Temp min: " + jsonObject.getJSONObject("main").getInt("temp_min")+" K");
            jlTempMax.setText("Temp max: " + jsonObject.getJSONObject("main").getInt("temp_max")+" K");
        }


        tPanel = new PanelMeteo(city,country,jlSunrise,jlSunset,jlTempMin,jlTempMax);


        return tPanel;
    }

    /**
     * This method choose the selected weekly meteo Info with the string getting from the method getWeather
     * @param location for the city to search
     * @param appId allows to use the website openweathermap
     * @return return a JPanel to add to the weather display
     */
    public JPanel getSelectedForecastInfo(String location, String appId) throws BusinessException {
        JPanel tPanel = new JPanel();
        JPanel tPanelWeek = new JPanel();
        tPanelWeek.setLayout(new FlowLayout());
        tPanel.setPreferredSize(new Dimension(200, 70));
        String meteo = "";
        String firstHour = "";
        Boolean firstTime = true;
        JPanel[] tPanelForecast = new JPanel[5];
        JLabel dayOfTheWeek = new JLabel();
        JLabel tempOfTheDay = new JLabel();
        dayOfTheWeek.setFont(new Font("Serif", Font.PLAIN, 12));
        tempOfTheDay.setFont(new Font("Serif", Font.PLAIN, 12));
        ImageIcon iconDayWeather = new ImageIcon();
        int cptNbJour = 0;

        meteo = getForecast(location, appId);

        System.out.println(meteo);


        JSONObject jsonObject = new JSONObject(meteo);
        JSONArray geoData = jsonObject.getJSONArray("list");

        int n = geoData.length();


        for (int i = 0; i < n; i++) {
            JSONObject person = geoData.getJSONObject(i);

            if (firstTime) {
                firstHour = unixToHour(person.getInt("dt")).toUpperCase(Locale.ROOT);
                firstTime = false;
            }

            if (unixToHour(person.getInt("dt")).toUpperCase(Locale.ROOT).equals(firstHour)) {
                dayOfTheWeek = new JLabel();
                dayOfTheWeek.setFont(new Font("Serif", Font.PLAIN, 12));
                dayOfTheWeek.setText(unixToDate(person.getInt("dt")).toUpperCase(Locale.ROOT));

                tempOfTheDay = new JLabel();
                tempOfTheDay.setFont(new Font("Serif", Font.PLAIN, 12));
                tempOfTheDay.setText(convertToString(convertFahToCel(person.getJSONObject("main").getInt("temp"))).toString());

                JSONArray weatherArray = person.getJSONArray("weather");
                iconDayWeather.setImage(getIcon(weatherArray.getJSONObject(0).getString("icon")));
                JLabel iconDayWeatherLabel = new JLabel(resize(iconDayWeather, 40, 40));

                tPanelForecast[cptNbJour] = new PanelForecast(tempOfTheDay, dayOfTheWeek, iconDayWeatherLabel);
                cptNbJour++;

            }
        }

        //add to the JPanel
        for (int i = 0; i < tPanelForecast.length; i++) {
            tPanel.add(tPanelForecast[i]);
        }
        return tPanel;
    }

    /**
     * This method convert the number in unixSeconds in human Date
     * @param unixSeconds is given by then String on openweathermap
     * @return a String with the new format of date
     */
    public String unixToDate(int unixSeconds) {
        Date date = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEE");  // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));             // give a timezone reference for formatting (see comment at the bottom)
        String formattedDate = sdf.format(date);

        return formattedDate;
    }

    /**
     * This method convert the number in unixSecond in human Hour
     * @param unixSeconds is given by then String on openweathermap
     * @return a String with the new format of hour
     */
    public String unixToHour(int unixSeconds) {
        Date date = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    /**
     * This method convert the number in unixSecond in human Hour/Minute
     * @param unixSeconds is given by then String on openweathermap
     * @return a String with the new format of hour/minute
     */
    public String unixToHourMinute(int unixSeconds) {
        Date date = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    /**
     * This method convert the temperature variable fah in celsius
     * @param fah is the temperature
     * @return the new value of temperature in celsius
     */
    public double convertFahToCel(double fah) {
        double res;
        res = Math.round(((5.0 / 9.0) * (fah - 32) / 10) * 10d) / 10d;
        return res;
    }

    /**
     * This method convert the variable cel into a String
     * @param cel new temperature
     * @return a String
     */
    public String convertToString(double cel) {
        return Double.toString(cel);
    }

    /**
     * This method resize the icon we get from openweathermap
     * @param img is the icon from the weather website
     * @param w is the width we want to have
     * @param h is the height we want to have
     * @return an ImageIcon
     */
    public ImageIcon resize(ImageIcon img, int w, int h) {
        Image image = img.getImage(); // transform it
        Image newimg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return img = new ImageIcon(newimg);  // transform it back
    }

    /**
     * This method get the information with a URL
     * @param url for openweathermap to know which data we must get
     * @return return the result in a String
     * @throws BusinessException with an error that it doesn't work
     */
    public String getOpenweathermapInfo(URL url) throws BusinessException {
        StringBuilder rt = new StringBuilder();

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String readLine;

                while ((readLine = in.readLine()) != null) {
                    rt.append(readLine);
                }

                in.close();
            } else {
                System.err.println("Wrong response code: " + responseCode);
            }
        } catch (Exception ex) {
            throw new BusinessException("Request error to openweathermap.org", ex, ErrorCodes.REQUEST_FAIL);
        }

        return rt.toString();
    }


}
