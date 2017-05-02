package com.example.devikaraathor.weatherfinal;

/**
 * @author draath2
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Function {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%s";
    private static final int TIME = 1000;
    //  private static final double KELVIN_CONSTANT = 273.15;
    private static final double KELVIN_MULTIPLIER = 1.8;
    private static final double KELVIN_SUBTRACTER = 459.67;

    private static final String API = "030dbebaadd41cb5b7fc0d21c37a713e";

    //uses http://erikflowers.github.io/weather-icons/ to get weather icons
    public static String setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";
        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = "&#xf00d;"; //day sunny
            } else {
                icon = "&#xf02e;"; //night clear
            }
        } else {
            switch (id) {
                case 2:
                    icon = "&#xf01e;"; //thunderstorm
                    break;
                case 3:
                    icon = "&#xf01c;"; //sprinkles
                    break;
                case 4:
                    icon = "&#xf014;"; //fog
                    break;
                case 5:
                    icon = "&#xf013;"; //cloudy
                    break;
                case 6:
                    icon = "&#xf01b;"; //snow
                    break;
                case 7:
                    icon = "&#xf019;"; //rain
                    break;
            }
        }
        return icon;
    }

    //TODO: change the naming of the outputs
    public interface AsyncResponse {

        void processFinish(String output1, String output2, String output3, String output4, String output5, String output6, String output7, String output8);
    }


    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {

        public AsyncResponse delegate = null;//Call back interface

        public placeIdTask(AsyncResponse asyncResponse) {
            delegate = asyncResponse;//Assigning call back interface through constructor
        }

        @Override
        /**
         * make sure JSON is valid
         */
        protected JSONObject doInBackground(String... params) {

            JSONObject jsonWeather = null;
            try {
                jsonWeather = getWeatherJSON(params[0]);
            } catch (Exception e) {
                Log.d("Error", "Cannot process JSON results", e);
            }


            return jsonWeather;
        }

        @Override
        /**
         * as long as JSON is valid, get weather information on city
         */
        protected void onPostExecute(JSONObject json) {
            try {
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();


                    String city = json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country");
                    String description = details.getString("description").toUpperCase(Locale.US);
                    // String temperature = String.format("%.2f", (main.getDouble("temp")) - KELVIN_CONSTANT) + "°C";
                    String temperature = String.format("%.2f", ((main.getDouble("temp")) * KELVIN_MULTIPLIER) - KELVIN_SUBTRACTER) + "°F";
                    // String temperature = String.format("%.2f", (main.getDouble("temp")))  + "°K";
                    String humidity = main.getString("humidity") + "%";
                    String pressure = main.getString("pressure") + " hPa";
                    String updatedOn = df.format(new Date(json.getLong("dt") * TIME));
                    String iconText = setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * TIME,
                            json.getJSONObject("sys").getLong("sunset") * TIME);

                    delegate.processFinish(city, description, temperature, humidity, pressure, updatedOn, iconText, "" + (json.getJSONObject("sys").getLong("sunrise") * 1000));

                }
            } catch (JSONException e) {
                //Log.e(LOG_TAG, "Cannot process JSON results", e);
            }


        }
    }

    /*
     * use HttpURLConnection to make request
     * OpenWeatherMAP API needs the key in x-api-key
     * convert response of BufferedReader to a StringBuffer to a JSONObject
     * @param cityName name of city
     * @return JSON object of weather
     */
    public static JSONObject getWeatherJSON(String cityName) {
        try {
            URL url = new URL(String.format(URL, cityName));
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.addRequestProperty("x-api-key", API);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // 404 if the request isn't successful
            if (data.getInt("cod") != 200) {
                return null;
            }

            return data;
        } catch (Exception e) {
            return null;
        }
    }


}