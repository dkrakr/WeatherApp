package com.example.devikaraathor.weatherfinal;

/**
 * @author draath2
 */

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class WeatherLessDetail extends AppCompatActivity {

    private TextView city;
    private TextView weather;
    private Typeface iconFont;
    private TextView icon;
    private Button more;

    private TextView city2;
    private TextView weather2;
    private TextView icon2;
    private Button more2;

    private TextView city3;
    private TextView weather3;
    private TextView icon3;
    private Button more3;

    private TextView city4;
    private TextView weather4;
    private TextView icon4;
    private Button more4;

    private EditText inputSearch;
    private Button go;

    public static final String c2 = "CITY";

    /**
     *
     * @param city list of 1 to 4 cities separated by commas without spaces
     * @return arraylist of Strings of the cities, with each city in its own cell
     */
    public ArrayList<String> makeArray(String city) {
        ArrayList<String> cityList = new ArrayList<String>();
        int countCommas = 0;

        for (int i = 0; i < city.length(); i++) {
            if ((city.charAt(i) == ',')) {
                countCommas++;
            }
        }

        //if there is no city entered
        if ((countCommas == 0) && (city.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Please enter between 1 and 4 cities!", Toast.LENGTH_LONG).show();
        } else if (countCommas == 0) {
            cityList.add(city);
        } else if (countCommas == 1) {
            cityList.add(city.substring(0, city.indexOf(",")));
            String city1 = city.substring(city.indexOf(",") + 1);
            cityList.add(city1);
        } else if (countCommas == 2) {
            cityList.add(city.substring(0, city.indexOf(",")));
            String city1 = city.substring(city.indexOf(",") + 1);
            cityList.add(city1.substring(0, city1.indexOf(",")));
            String city2 = city1.substring(city1.indexOf(",") + 1);
            cityList.add(city2);
        } else if (countCommas == 3) {
            cityList.add(city.substring(0, city.indexOf(",")));
            String city1 = city.substring(city.indexOf(",") + 1);
            cityList.add(city1.substring(0, city1.indexOf(",")));
            String city2 = city1.substring(city1.indexOf(",") + 1);
            cityList.add(city2.substring(0, city2.indexOf(",")));
            String city3 = city2.substring(city2.indexOf(",") + 1);
            cityList.add(city3);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter between 1 and 4 cities!", Toast.LENGTH_LONG).show();
        }
        return cityList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_less_detail);

        iconFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        city = (TextView) findViewById(R.id.cityName);
        weather = (TextView) findViewById(R.id.temp);
        icon = (TextView) findViewById(R.id.weatherIcon);
        icon.setTypeface(iconFont);

        //parsing for the first city
        final Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
                city.setText(weather_city);
                weather.setText(weather_temperature);
                icon.setText(Html.fromHtml(weather_iconText));
            }
        });


        city2 = (TextView) findViewById(R.id.cityName2);
        weather2 = (TextView) findViewById(R.id.temp2);
        icon2 = (TextView) findViewById(R.id.weatherIcon2);
        icon2.setTypeface(iconFont);

        //parsing for the second city
        final Function.placeIdTask asyncTask2 = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
                city2.setText(weather_city);
                weather2.setText(weather_temperature);
                icon2.setText(Html.fromHtml(weather_iconText));
            }
        });


        city3 = (TextView) findViewById(R.id.cityName3);
        weather3 = (TextView) findViewById(R.id.temp3);
        icon3 = (TextView) findViewById(R.id.weatherIcon3);
        icon3.setTypeface(iconFont);

        //parsing for the third city
        final Function.placeIdTask asyncTask3 = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
                city3.setText(weather_city);
                weather3.setText(weather_temperature);
                icon3.setText(Html.fromHtml(weather_iconText));
            }
        });


        city4 = (TextView) findViewById(R.id.cityName4);
        weather4 = (TextView) findViewById(R.id.temp4);
        icon4 = (TextView) findViewById(R.id.weatherIcon4);
        icon4.setTypeface(iconFont);

        //parsing for the fourth city
        final Function.placeIdTask asyncTask4 = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
                city4.setText(weather_city);
                weather4.setText(weather_temperature);
                icon4.setText(Html.fromHtml(weather_iconText));
            }
        });


        inputSearch = (EditText) findViewById(R.id.editText);
        go = (Button) findViewById(R.id.goButton);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = inputSearch.getText().toString();

                //basically same as makeArray(), but this also sets information as visible
                //when that city's information is displayed
                if ((makeArray(city).size()) == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter at least one city!", Toast.LENGTH_LONG).show();
                }
                if ((makeArray(city).size()) == 1) {
                    asyncTask.execute(makeArray(city).get(0));
                    more.setVisibility(View.VISIBLE);

                }
                if ((makeArray(city).size()) == 2) {
                    asyncTask.execute(makeArray(city).get(0));
                    asyncTask2.execute(makeArray(city).get(1));
                    more.setVisibility(View.VISIBLE);
                    city2.setVisibility(View.VISIBLE);
                    weather2.setVisibility(View.VISIBLE);
                    icon2.setVisibility(View.VISIBLE);
                    more2.setVisibility(View.VISIBLE);
                }
                if ((makeArray(city).size()) == 3) {
                    asyncTask.execute(makeArray(city).get(0));
                    asyncTask2.execute(makeArray(city).get(1));
                    asyncTask3.execute(makeArray(city).get(2));
                    more.setVisibility(View.VISIBLE);
                    city2.setVisibility(View.VISIBLE);
                    weather2.setVisibility(View.VISIBLE);
                    icon2.setVisibility(View.VISIBLE);
                    more2.setVisibility(View.VISIBLE);
                    city3.setVisibility(View.VISIBLE);
                    weather3.setVisibility(View.VISIBLE);
                    icon3.setVisibility(View.VISIBLE);
                    more3.setVisibility(View.VISIBLE);
                }
                if ((makeArray(city).size()) == 4) {
                    asyncTask.execute(makeArray(city).get(0));
                    asyncTask2.execute(makeArray(city).get(1));
                    asyncTask3.execute(makeArray(city).get(2));
                    asyncTask4.execute(makeArray(city).get(3));
                    more.setVisibility(View.VISIBLE);
                    city2.setVisibility(View.VISIBLE);
                    weather2.setVisibility(View.VISIBLE);
                    icon2.setVisibility(View.VISIBLE);
                    more2.setVisibility(View.VISIBLE);
                    city3.setVisibility(View.VISIBLE);
                    weather3.setVisibility(View.VISIBLE);
                    icon3.setVisibility(View.VISIBLE);
                    more3.setVisibility(View.VISIBLE);
                    city4.setVisibility(View.VISIBLE);
                    weather4.setVisibility(View.VISIBLE);
                    icon4.setVisibility(View.VISIBLE);
                    more4.setVisibility(View.VISIBLE);
                }
                if ((makeArray(city).size()) > 4){
                    Toast.makeText(getApplicationContext(), "Please enter only up to 4 cities!", Toast.LENGTH_LONG).show();
                }

            }
        });

        //for more, more2, more3, more4
        //switch to activity with more information on this city
        //also allow it to recieve the name of the city so it can parse
        more = (Button) findViewById(R.id.seeMore);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Weather.class);
                String c = inputSearch.getText().toString();

                c = makeArray(c).get(0);
                intent.putExtra(c2, c);
                startActivity(intent);
            }
        });

        more2 = (Button) findViewById(R.id.seeMore2);
        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Weather.class);
                String c = inputSearch.getText().toString();

                c = makeArray(c).get(1);
                intent.putExtra(c2, c);
                startActivity(intent);
            }
        });

        more3 = (Button) findViewById(R.id.seeMore3);
        more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Weather.class);
                String c = inputSearch.getText().toString();

                c = makeArray(c).get(2);
                intent.putExtra(c2, c);
                startActivity(intent);
            }
        });

        more4 = (Button) findViewById(R.id.seeMore4);
        more4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Weather.class);
                String c = inputSearch.getText().toString();

                c = makeArray(c).get(3);
                intent.putExtra(c2, c);
                startActivity(intent);
            }
        });
    }
}
