package Smartphone.Meteo;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MeteoRessource extends Meteo {

    public Image getIcon(String iconCode) {
        Image tImage;

        try {
            URL url = new URL("http://openweathermap.org/img/wn/" + iconCode + "@2x.png");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return tImage = ImageIO.read(url);
            }
        } catch (Exception ex) {
            System.err.println("Request error: " + ex.getMessage());
        }
        return null;
    }

    public String getWeather(String location, String appId, String unit) {
        StringBuilder rt = new StringBuilder();


        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather"
                    + "?units="+unit
                    + "&q=" + location
                    + "&appid=" + appId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
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
            System.err.println("Request error: " + ex.getMessage());
        }
        return rt.toString();
    }

    public JPanel getSelectedMeteoInfo(String location, String appId, String unit) {
        String meteo = "";
        JPanel tPanel;
        JLabel description = new JLabel();
        JLabel city = new JLabel();
        JLabel country = new JLabel();
        JLabel jlSunrise = new JLabel();
        JLabel jlSunset = new JLabel();
        JLabel jlTempMin = new JLabel();
        JLabel jlTempMax = new JLabel();

        meteo = getWeather(location, appId, unit);
        System.out.println(meteo);

        JSONObject jsonObject = new JSONObject(meteo);

        city.setText("City: " + jsonObject.getString("name"));
        country.setText("Country: " + jsonObject.getJSONObject("sys").getString("country"));
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

    public String getForecast(String location, String appId) {
        StringBuilder rt = new StringBuilder();

        try {
            URL urlForecast = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + location + "&appid=" + appId);

            HttpURLConnection conn = (HttpURLConnection) urlForecast.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
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
            System.err.println("Request error: " + ex.getMessage());
        }
//        System.out.println(rt);

        return rt.toString();
    }

    public JPanel getSelectedForecastInfo(String location, String appId) {
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


        JSONObject jsonObject = new JSONObject(meteo);
        JSONArray geodata = jsonObject.getJSONArray("list");

        int n = geodata.length();


        for (int i = 0; i < n; i++) {
            JSONObject person = geodata.getJSONObject(i);

            if (firstTime) {
                firstHour = unixToHour(person.getInt("dt")).toUpperCase(Locale.ROOT);
                firstTime = false;
            }

            if (unixToHour(person.getInt("dt")).toUpperCase(Locale.ROOT).equals(firstHour)) {
                dayOfTheWeek = new JLabel();
                tempOfTheDay = new JLabel();
                dayOfTheWeek.setFont(new Font("Serif", Font.PLAIN, 12));
                tempOfTheDay.setFont(new Font("Serif", Font.PLAIN, 12));
                dayOfTheWeek.setText(unixToDate(person.getInt("dt")).toUpperCase(Locale.ROOT));
                tempOfTheDay.setText(convertToString(convertFahToCel(person.getJSONObject("main").getInt("temp"))));
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

    public String unixToDate(int unixSeconds) {
        Date date = new java.util.Date(unixSeconds * 1000L);
//        SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("HH:mm"); // convert seconds to miliseconds
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEE");                       // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));                                   // give a timezone reference for formatting (see comment at the bottom)
        String formattedDate = sdf.format(date);

        return formattedDate;
    }

    public String unixToHour(int unixSeconds) {
        Date date = new java.util.Date(unixSeconds * 1000L);                                          // convert seconds to miliseconds
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");                       // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));                                   // give a timezone reference for formatting (see comment at the bottom)
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String unixToHourMinute(int unixSeconds) {
        Date date = new java.util.Date(unixSeconds * 1000L);                                          // convert seconds to miliseconds
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");                       // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));                                   // give a timezone reference for formatting (see comment at the bottom)
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public double convertFahToCel(double fah) {
        double res;
        res = Math.round(((5.0 / 9.0) * (fah - 32) / 10) * 10d) / 10d;
        return res;
    }

    public String convertToString(double cel) {
        return Double.toString(cel);
    }

    public ImageIcon resize(ImageIcon img, int w, int h) {
        Image image = img.getImage(); // transform it
        Image newimg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return img = new ImageIcon(newimg);  // transform it back
    }

}
