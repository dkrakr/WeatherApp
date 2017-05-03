package com.example.devikaraathor.weatherfinal;

/**
 * @author draath2
 */

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by devikaraathor on 5/2/17.
 */
public class FunctionTest {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%s";
    private static final String API = "030dbebaadd41cb5b7fc0d21c37a713e";
    Function func = new Function();

    @Test
    public void setWeatherIconTest() throws Exception {
        assertEquals("&#xf02e;", func.setWeatherIcon(800, 3l, 1493748916500l));
    }

    @Test
    public void getWeatherJSON() throws Exception {
        assertEquals(null, func.getWeatherJSON("alsjfdlajdf"));
    }

}
