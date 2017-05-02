package com.example.devikaraathor.weatherfinal;

/**
 * @author draath2
 */

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class ViewOthers extends AppCompatActivity {

    TextView user;
    TextView loc;
    TextView icon;
    TextView temp;
    Typeface weathFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_others);

        weathFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        user = (TextView) findViewById(R.id.userName);
        loc = (TextView) findViewById(R.id.location);
        icon = (TextView) findViewById(R.id.weatherIcon);
        temp = (TextView) findViewById(R.id.temperature);
        icon.setTypeface(weathFont);

        user.setText("User name:");
        loc.setText("Location:");
        temp.setText("Current Temperature:");
        icon.setText(Html.fromHtml("&#xf01e;"));
    }
}