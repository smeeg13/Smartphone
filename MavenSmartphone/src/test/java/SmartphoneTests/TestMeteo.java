package SmartphoneTests;

import Smartphone.Errors.BusinessException;
import Smartphone.Errors.ErrorCodes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestMeteo {


    @Test
    void checkBoxSelected() {
        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox checkboxTest;
        Checkbox checkboxMetric = new Checkbox("metric", cbg, true);
        Checkbox checkboxImperial = new Checkbox("imperial", cbg, false);
        Checkbox checkboxStandard = new Checkbox("standard", cbg, false);

        assertEquals("metric", checkboxMetric.getLabel());
        assertEquals("imperial", checkboxImperial.getLabel());
        assertEquals("standard", checkboxStandard.getLabel());


        cbg.setSelectedCheckbox(checkboxMetric);
        assertEquals(cbg.getSelectedCheckbox().getLabel(), "metric");

        cbg.setSelectedCheckbox(checkboxImperial);
        assertEquals(cbg.getSelectedCheckbox().getLabel(), "imperial");

        cbg.setSelectedCheckbox(checkboxStandard);
        assertEquals(cbg.getSelectedCheckbox().getLabel(), "standard");
    }

    @Test
    void pingOpenweathermapTest() {
        String OS = System.getProperty("os.name").toLowerCase();
        String host = "api.openweathermap.org";
        int pingResult = 100;

        try {
            String cmd = "";
            if (OS.contains("win")) {
                // For Windows
                cmd = "ping -n 1 " + host;
            }
            if (OS.contains("mac")) {
                cmd = "ping -c 1 " + host;
            }

            Process pingProcess = Runtime.getRuntime().exec(cmd);
            pingProcess.waitFor();

            pingResult = pingProcess.exitValue();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(pingResult, 0); //si le ping a fonctionné
//        assertEquals(pingResult,64);//si le ping a échoué

    }

    @Test
    void getWeatherTest() throws BusinessException {

        BusinessException e;

        e = assertThrows(BusinessException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        String weatherInfo = "";
                        StringBuilder rt = new StringBuilder();
                        int responseCode;
                        String apiKey = "282e58d6c4f45693bc47da6aa566cab5";

                        try {
                            URL url = new URL("http://apiopenweathermap.org/data/2.5/weather"
                                    + "?units=" + "metric"
                                    + "&q=martigny"
                                    + "&appid=" + apiKey);

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
                        } catch (IOException e) {
                            throw new BusinessException("URL is malformed", e, ErrorCodes.IO_ERROR);
                        }
                    }
                });

        assertEquals(ErrorCodes.IO_ERROR.getCode(), e.getErrorCode());


        e = assertThrows(BusinessException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        String weatherInfo = "";
                        StringBuilder rt = new StringBuilder();
                        int responseCode;
                        String apiKey = "282e58d6c4f45693bc47da6aa566cab5";

                        try {
                            URL url = new URL("http://api.openweathermap.org/data/2.5/weather"
                                    + "?units=" + "metric"
                                    + "&q=murtigny"
                                    + "&appid=" + apiKey);

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
                                throw new BusinessException("Wrong city name",ErrorCodes.BAD_CITYNAME);
                            }
                        } catch (IOException e) {
                            throw new BusinessException("URL is malformed", e, ErrorCodes.BAD_PARAMETER);
                        }
                    }
                });

        assertEquals(ErrorCodes.BAD_CITYNAME.getCode(), e.getErrorCode());

    }

    @Test
    public void getSelectedMeteoTest() {
        String city = "London";
        String country = "GB";
        String weatherLondon = "{coord:{lon:-0.1257,lat:51.5085},weather:[{id:800,main:Clear,description:clear sky,icon:01d}],base:stations,main:{temp:21.2,feels_like:20.86,temp_min:18.21,temp_max:23.3,pressure:1028,humidity:57},visibility:10000,wind:{speed:1.03,deg:0},clouds:{all:8},dt:1623573857,sys:{type:2,id:2019646,country:GB,sunrise:1623555785,sunset:1623615494},timezone:3600,id:2643743,name:London,cod:200}";

        JSONObject jsonObjectWeather = new JSONObject(weatherLondon);

        assertEquals(city, jsonObjectWeather.getString("name"));
        assertEquals(country, jsonObjectWeather.getJSONObject("sys").getString("country"));


    }

    @Test
    public void unixToDateTest(){
        Date date;
        SimpleDateFormat sdf;
        String formattedDate;

        int lundi = 1623650400;
        date = new java.util.Date(lundi * 1000L);
        sdf = new java.text.SimpleDateFormat("EEE");  // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));             // give a timezone reference for formatting (see comment at the bottom)
        formattedDate = sdf.format(date);

        assertEquals("lun.",formattedDate);

        int mardi = 1623769200;
        date = new java.util.Date(mardi * 1000L);
        sdf = new java.text.SimpleDateFormat("EEE");  // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));             // give a timezone reference for formatting (see comment at the bottom)
        formattedDate = sdf.format(date);

        assertEquals("mar.",formattedDate);

        int mercredi = 1623844800;
        date = new java.util.Date(mercredi * 1000L);
        sdf = new java.text.SimpleDateFormat("EEE");  // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));             // give a timezone reference for formatting (see comment at the bottom)
        formattedDate = sdf.format(date);

        assertEquals("mer.",formattedDate);

        int jeudi = 1623931200;
        date = new java.util.Date(jeudi * 1000L);
        sdf = new java.text.SimpleDateFormat("EEE");  // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));             // give a timezone reference for formatting (see comment at the bottom)
        formattedDate = sdf.format(date);

        assertEquals("jeu.",formattedDate);

        int vendredi = 1623996000;
        date = new java.util.Date(vendredi * 1000L);
        sdf = new java.text.SimpleDateFormat("EEE");  // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));             // give a timezone reference for formatting (see comment at the bottom)
        formattedDate = sdf.format(date);

        assertEquals("ven.",formattedDate);

        int dimanche = 1623585600;
        date = new java.util.Date(dimanche * 1000L);
        sdf = new java.text.SimpleDateFormat("EEE");  // the format of your date
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));             // give a timezone reference for formatting (see comment at the bottom)
        formattedDate = sdf.format(date);

        assertEquals("dim.",formattedDate);

    }

    @Test
    public void unixToHourTest(){
        int dt = 1623650400;

        Date date = new java.util.Date(dt * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);

        assertEquals("08",formattedDate);
    }

    @Test
    public void unixToHourMinute(){
        int dt = 1623650400;

        Date date = new java.util.Date(dt * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);

        assertEquals("08:00",formattedDate);
    }

    @Test
    public void convertFahToCelTest(){
        double res;
        double fah = 90;
        res = Math.round(((5.0 / 9.0) * (fah - 32) / 10) * 10d) ;

        assertEquals(32.00,res);
    }

    @Test
    public void getSelectedForecastInfo() {


        String weekForecastLondon = "{\"cod\":\"200\",\"message\":0,\"cnt\":40,\"list\":[{\"dt\":1623574800,\"main\":{\"temp\":294.58,\"feels_like\":294.24,\"temp_min\":294.58,\"temp_max\":294.67,\"pressure\":1028,\"sea_level\":1028,\"grnd_level\":1025,\"humidity\":56,\"temp_kf\":-0.09},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":1.32,\"deg\":269,\"gust\":1.54},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-13 09:00:00\"},{\"dt\":1623585600,\"main\":{\"temp\":296.06,\"feels_like\":295.71,\"temp_min\":296.06,\"temp_max\":299.01,\"pressure\":1027,\"sea_level\":1027,\"grnd_level\":1023,\"humidity\":50,\"temp_kf\":-2.95},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":7},\"wind\":{\"speed\":1.39,\"deg\":267,\"gust\":1.37},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-13 12:00:00\"},{\"dt\":1623596400,\"main\":{\"temp\":298.57,\"feels_like\":298.34,\"temp_min\":298.57,\"temp_max\":300.56,\"pressure\":1025,\"sea_level\":1025,\"grnd_level\":1021,\"humidity\":45,\"temp_kf\":-1.99},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":3},\"wind\":{\"speed\":1.01,\"deg\":213,\"gust\":1.61},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-13 15:00:00\"},{\"dt\":1623607200,\"main\":{\"temp\":298.11,\"feels_like\":298.1,\"temp_min\":298.11,\"temp_max\":298.11,\"pressure\":1024,\"sea_level\":1024,\"grnd_level\":1021,\"humidity\":55,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"clouds\":{\"all\":16},\"wind\":{\"speed\":2.38,\"deg\":205,\"gust\":2.72},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-13 18:00:00\"},{\"dt\":1623618000,\"main\":{\"temp\":293.28,\"feels_like\":293.15,\"temp_min\":293.28,\"temp_max\":293.28,\"pressure\":1024,\"sea_level\":1024,\"grnd_level\":1021,\"humidity\":69,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":2.48,\"deg\":205,\"gust\":4.13},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-13 21:00:00\"},{\"dt\":1623628800,\"main\":{\"temp\":291.16,\"feels_like\":290.87,\"temp_min\":291.16,\"temp_max\":291.16,\"pressure\":1023,\"sea_level\":1023,\"grnd_level\":1020,\"humidity\":71,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":46},\"wind\":{\"speed\":1.64,\"deg\":195,\"gust\":3.87},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-14 00:00:00\"},{\"dt\":1623639600,\"main\":{\"temp\":289.44,\"feels_like\":289.03,\"temp_min\":289.44,\"temp_max\":289.44,\"pressure\":1022,\"sea_level\":1022,\"grnd_level\":1019,\"humidity\":73,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":11},\"wind\":{\"speed\":1.8,\"deg\":215,\"gust\":4.23},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-14 03:00:00\"},{\"dt\":1623650400,\"main\":{\"temp\":291.35,\"feels_like\":290.84,\"temp_min\":291.35,\"temp_max\":291.35,\"pressure\":1022,\"sea_level\":1022,\"grnd_level\":1018,\"humidity\":62,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":5},\"wind\":{\"speed\":2.36,\"deg\":228,\"gust\":4.42},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-14 06:00:00\"},{\"dt\":1623661200,\"main\":{\"temp\":296.19,\"feels_like\":295.83,\"temp_min\":296.19,\"temp_max\":296.19,\"pressure\":1021,\"sea_level\":1021,\"grnd_level\":1018,\"humidity\":49,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":4},\"wind\":{\"speed\":3.42,\"deg\":264,\"gust\":4.58},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-14 09:00:00\"},{\"dt\":1623672000,\"main\":{\"temp\":299.39,\"feels_like\":299.39,\"temp_min\":299.39,\"temp_max\":299.39,\"pressure\":1020,\"sea_level\":1020,\"grnd_level\":1017,\"humidity\":47,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"clouds\":{\"all\":13},\"wind\":{\"speed\":3.2,\"deg\":281,\"gust\":4.02},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-14 12:00:00\"},{\"dt\":1623682800,\"main\":{\"temp\":298.49,\"feels_like\":298.46,\"temp_min\":298.49,\"temp_max\":298.49,\"pressure\":1020,\"sea_level\":1020,\"grnd_level\":1017,\"humidity\":53,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":4.71,\"deg\":312,\"gust\":4.38},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-14 15:00:00\"},{\"dt\":1623693600,\"main\":{\"temp\":295.36,\"feels_like\":295.33,\"temp_min\":295.36,\"temp_max\":295.36,\"pressure\":1020,\"sea_level\":1020,\"grnd_level\":1017,\"humidity\":65,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":84},\"wind\":{\"speed\":4.28,\"deg\":345,\"gust\":5.14},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-14 18:00:00\"},{\"dt\":1623704400,\"main\":{\"temp\":292.17,\"feels_like\":292.09,\"temp_min\":292.17,\"temp_max\":292.17,\"pressure\":1022,\"sea_level\":1022,\"grnd_level\":1019,\"humidity\":75,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.77,\"deg\":30,\"gust\":5.64},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-14 21:00:00\"},{\"dt\":1623715200,\"main\":{\"temp\":290.13,\"feels_like\":289.79,\"temp_min\":290.13,\"temp_max\":290.13,\"pressure\":1022,\"sea_level\":1022,\"grnd_level\":1019,\"humidity\":73,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.9,\"deg\":8,\"gust\":5.55},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-15 00:00:00\"},{\"dt\":1623726000,\"main\":{\"temp\":288.5,\"feels_like\":287.92,\"temp_min\":288.5,\"temp_max\":288.5,\"pressure\":1022,\"sea_level\":1022,\"grnd_level\":1019,\"humidity\":70,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.07,\"deg\":30,\"gust\":4.25},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-15 03:00:00\"},{\"dt\":1623736800,\"main\":{\"temp\":287.77,\"feels_like\":286.83,\"temp_min\":287.77,\"temp_max\":287.77,\"pressure\":1023,\"sea_level\":1023,\"grnd_level\":1020,\"humidity\":59,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.92,\"deg\":34,\"gust\":3.96},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-15 06:00:00\"},{\"dt\":1623747600,\"main\":{\"temp\":291.95,\"feels_like\":291.19,\"temp_min\":291.95,\"temp_max\":291.95,\"pressure\":1023,\"sea_level\":1023,\"grnd_level\":1019,\"humidity\":50,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":98},\"wind\":{\"speed\":1.96,\"deg\":44,\"gust\":1.55},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-15 09:00:00\"},{\"dt\":1623758400,\"main\":{\"temp\":297.42,\"feels_like\":296.87,\"temp_min\":297.42,\"temp_max\":297.42,\"pressure\":1021,\"sea_level\":1021,\"grnd_level\":1018,\"humidity\":37,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":70},\"wind\":{\"speed\":0.27,\"deg\":38,\"gust\":1.52},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-15 12:00:00\"},{\"dt\":1623769200,\"main\":{\"temp\":298.44,\"feels_like\":297.89,\"temp_min\":298.44,\"temp_max\":298.44,\"pressure\":1019,\"sea_level\":1019,\"grnd_level\":1016,\"humidity\":33,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}],\"clouds\":{\"all\":30},\"wind\":{\"speed\":0.18,\"deg\":291,\"gust\":2.7},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-15 15:00:00\"},{\"dt\":1623780000,\"main\":{\"temp\":294.86,\"feels_like\":294.34,\"temp_min\":294.86,\"temp_max\":294.86,\"pressure\":1018,\"sea_level\":1018,\"grnd_level\":1015,\"humidity\":48,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":61},\"wind\":{\"speed\":2.7,\"deg\":70,\"gust\":2.65},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-15 18:00:00\"},{\"dt\":1623790800,\"main\":{\"temp\":291.67,\"feels_like\":291.43,\"temp_min\":291.67,\"temp_max\":291.67,\"pressure\":1018,\"sea_level\":1018,\"grnd_level\":1015,\"humidity\":71,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":43},\"wind\":{\"speed\":2.28,\"deg\":170,\"gust\":4.83},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-15 21:00:00\"},{\"dt\":1623801600,\"main\":{\"temp\":289.78,\"feels_like\":289.51,\"temp_min\":289.78,\"temp_max\":289.78,\"pressure\":1017,\"sea_level\":1017,\"grnd_level\":1014,\"humidity\":77,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":26},\"wind\":{\"speed\":1.35,\"deg\":165,\"gust\":2.52},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-16 00:00:00\"},{\"dt\":1623812400,\"main\":{\"temp\":288.25,\"feels_like\":287.85,\"temp_min\":288.25,\"temp_max\":288.25,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1013,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":14},\"wind\":{\"speed\":1.12,\"deg\":133,\"gust\":1.31},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-16 03:00:00\"},{\"dt\":1623823200,\"main\":{\"temp\":290.11,\"feels_like\":289.72,\"temp_min\":290.11,\"temp_max\":290.11,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":71,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":9},\"wind\":{\"speed\":1.46,\"deg\":155,\"gust\":2.79},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-16 06:00:00\"},{\"dt\":1623834000,\"main\":{\"temp\":295.94,\"feels_like\":295.71,\"temp_min\":295.94,\"temp_max\":295.94,\"pressure\":1014,\"sea_level\":1014,\"grnd_level\":1011,\"humidity\":55,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.47,\"deg\":190,\"gust\":3.62},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-16 09:00:00\"},{\"dt\":1623844800,\"main\":{\"temp\":300.38,\"feels_like\":300.13,\"temp_min\":300.38,\"temp_max\":300.38,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1010,\"humidity\":39,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.64,\"deg\":207,\"gust\":3.78},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-16 12:00:00\"},{\"dt\":1623855600,\"main\":{\"temp\":301.95,\"feels_like\":301.39,\"temp_min\":301.95,\"temp_max\":301.95,\"pressure\":1011,\"sea_level\":1011,\"grnd_level\":1008,\"humidity\":38,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":9},\"wind\":{\"speed\":3.25,\"deg\":189,\"gust\":3.84},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-16 15:00:00\"},{\"dt\":1623866400,\"main\":{\"temp\":300.21,\"feels_like\":300.38,\"temp_min\":300.21,\"temp_max\":300.21,\"pressure\":1011,\"sea_level\":1011,\"grnd_level\":1008,\"humidity\":46,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"clouds\":{\"all\":14},\"wind\":{\"speed\":2.8,\"deg\":167,\"gust\":2.75},\"visibility\":10000,\"pop\":0.08,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-16 18:00:00\"},{\"dt\":1623877200,\"main\":{\"temp\":295.19,\"feels_like\":295.25,\"temp_min\":295.19,\"temp_max\":295.19,\"pressure\":1011,\"sea_level\":1011,\"grnd_level\":1008,\"humidity\":69,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":39},\"wind\":{\"speed\":2.58,\"deg\":93,\"gust\":5.25},\"visibility\":10000,\"pop\":0.08,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-16 21:00:00\"},{\"dt\":1623888000,\"main\":{\"temp\":292.44,\"feels_like\":292.44,\"temp_min\":292.44,\"temp_max\":292.44,\"pressure\":1009,\"sea_level\":1009,\"grnd_level\":1006,\"humidity\":77,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":69},\"wind\":{\"speed\":2.55,\"deg\":77,\"gust\":8.09},\"visibility\":10000,\"pop\":0.5,\"rain\":{\"3h\":0.47},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-17 00:00:00\"},{\"dt\":1623898800,\"main\":{\"temp\":290.56,\"feels_like\":290.81,\"temp_min\":290.56,\"temp_max\":290.56,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1005,\"humidity\":94,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.99,\"deg\":40,\"gust\":3.37},\"visibility\":10000,\"pop\":0.9,\"rain\":{\"3h\":2.92},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-17 03:00:00\"},{\"dt\":1623909600,\"main\":{\"temp\":289.96,\"feels_like\":290.26,\"temp_min\":289.96,\"temp_max\":289.96,\"pressure\":1007,\"sea_level\":1007,\"grnd_level\":1004,\"humidity\":98,\"temp_kf\":0},\"weather\":[{\"id\":502,\"main\":\"Rain\",\"description\":\"heavy intensity rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.36,\"deg\":62,\"gust\":4.19},\"visibility\":10000,\"pop\":1,\"rain\":{\"3h\":13.19},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-17 06:00:00\"},{\"dt\":1623920400,\"main\":{\"temp\":291.14,\"feels_like\":291.55,\"temp_min\":291.14,\"temp_max\":291.14,\"pressure\":1007,\"sea_level\":1007,\"grnd_level\":1004,\"humidity\":98,\"temp_kf\":0},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.06,\"deg\":134,\"gust\":2.22},\"visibility\":10000,\"pop\":1,\"rain\":{\"3h\":8.35},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-17 09:00:00\"},{\"dt\":1623931200,\"main\":{\"temp\":293.81,\"feels_like\":294.23,\"temp_min\":293.81,\"temp_max\":293.81,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1005,\"humidity\":88,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.06,\"deg\":259,\"gust\":2.76},\"visibility\":10000,\"pop\":1,\"rain\":{\"3h\":0.76},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-17 12:00:00\"},{\"dt\":1623942000,\"main\":{\"temp\":293.58,\"feels_like\":293.85,\"temp_min\":293.58,\"temp_max\":293.58,\"pressure\":1009,\"sea_level\":1009,\"grnd_level\":1006,\"humidity\":83,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":96},\"wind\":{\"speed\":3.09,\"deg\":328,\"gust\":3.02},\"visibility\":10000,\"pop\":0.26,\"rain\":{\"3h\":0.22},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-17 15:00:00\"},{\"dt\":1623952800,\"main\":{\"temp\":290.46,\"feels_like\":290.57,\"temp_min\":290.46,\"temp_max\":290.46,\"pressure\":1010,\"sea_level\":1010,\"grnd_level\":1007,\"humidity\":89,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":98},\"wind\":{\"speed\":3.56,\"deg\":347,\"gust\":7.28},\"visibility\":10000,\"pop\":0.38,\"rain\":{\"3h\":0.16},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-17 18:00:00\"},{\"dt\":1623963600,\"main\":{\"temp\":289.45,\"feels_like\":289.46,\"temp_min\":289.45,\"temp_max\":289.45,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1010,\"humidity\":89,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.16,\"deg\":0,\"gust\":8.9},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-06-17 21:00:00\"},{\"dt\":1623974400,\"main\":{\"temp\":287.47,\"feels_like\":287.31,\"temp_min\":287.47,\"temp_max\":287.47,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1010,\"humidity\":90,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.45,\"deg\":0,\"gust\":10.26},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-18 00:00:00\"},{\"dt\":1623985200,\"main\":{\"temp\":286.98,\"feels_like\":286.77,\"temp_min\":286.98,\"temp_max\":286.98,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1009,\"humidity\":90,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.66,\"deg\":14,\"gust\":11.16},\"visibility\":10000,\"pop\":0.14,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-18 03:00:00\"},{\"dt\":1623996000,\"main\":{\"temp\":286.86,\"feels_like\":286.77,\"temp_min\":286.86,\"temp_max\":286.86,\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1009,\"humidity\":95,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.1,\"deg\":11,\"gust\":10.91},\"visibility\":10000,\"pop\":0.78,\"rain\":{\"3h\":0.6},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-06-18 06:00:00\"}],\"city\":{\"id\":2643743,\"name\":\"London\",\"coord\":{\"lat\":51.5085,\"lon\":-0.1257},\"country\":\"GB\",\"population\":1000000,\"timezone\":3600,\"sunrise\":1623555785,\"sunset\":1623615494}}\n";
        JSONObject jsonObjectWeekLondon = new JSONObject(weekForecastLondon);
        JSONArray londonData = jsonObjectWeekLondon.getJSONArray("list");



    }


}
