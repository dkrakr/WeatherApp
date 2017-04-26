package com.example.devikaraathor.weatherfinal;

/**
 * @author draath2
 */

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/*
public class WeatherLessDetail extends RecyclerView.Adapter<WeatherLessDetail.ViewHolder> {
    public static final String CITY = "CITY";
    public static final String WEATHER = "WEATHER";
    public static final String ICON = "ICON";

    ArrayList<Weather> weathers;

    public WeatherLessDetail(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View weatherItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_weather_less_detail, parent, false);
        return new ViewHolder(weatherItem);

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
   //     holder.city.setText(CITY, );
   //     holder.weather.setText(WEATHER);
        holder.icon.setText(ICON);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Weather.class);

    //            intent.putExtra(CITY, );
    //            intent.putExtra(WEATHER, );
    //            intent.putExtra(ICON, );

                v.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        TextView city;
        TextView weather;
        TextView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            city = (TextView) itemView.findViewById(R.id.cityName);
            weather = (TextView) itemView.findViewById(R.id.temp);
            icon = (TextView) itemView.findViewById(R.id.weatherIcon);

        }

    }
}
*/


public class WeatherLessDetail extends AppCompatActivity {

    TextView city;
    TextView weather;
    Typeface iconFont;
    TextView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_less_detail);

        iconFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        city = (TextView) findViewById(R.id.cityName);
        weather = (TextView) findViewById(R.id.temp);
        icon = (TextView) findViewById(R.id.weatherIcon);
        icon.setTypeface(iconFont);

        Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
                city.setText(weather_city);
                weather.setText(weather_temperature);
                icon.setText(Html.fromHtml(weather_iconText));
            }
        });

        asyncTask.execute("40.1164", "-88.2434");
    }
}
