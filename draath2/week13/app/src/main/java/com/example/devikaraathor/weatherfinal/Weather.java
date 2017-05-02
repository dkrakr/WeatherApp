package com.example.devikaraathor.weatherfinal;

/**
 * @author draath2
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

public class Weather extends AppCompatActivity {

    TextView city;
    TextView detail;
    TextView currentTemperature;
    TextView humidity;
    TextView pressure;
    TextView weatherIcon;
    TextView dateTime;

    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_weather);


        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        city = (TextView) findViewById(R.id.city_field);
        dateTime = (TextView) findViewById(R.id.date_time);
        detail = (TextView) findViewById(R.id.details_field);
        currentTemperature = (TextView) findViewById(R.id.current_temperature_field);
        humidity = (TextView) findViewById(R.id.humidity_field);
        pressure = (TextView) findViewById(R.id.pressure_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


        final Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                city.setText(weather_city);
                dateTime.setText(weather_updatedOn);
                detail.setText(weather_description);
                currentTemperature.setText(weather_temperature);
                humidity.setText("Humidity: " + weather_humidity);
                pressure.setText("Pressure: " + weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });

        Intent intent = getIntent();
        asyncTask.execute(intent.getStringExtra(WeatherLessDetail.c2));


    }
}
