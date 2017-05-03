package com.example.devikaraathor.weatherfinal;

/**
 * @author draath2
 */

import org.junit.Test;

import static com.example.devikaraathor.weatherfinal.Function.setWeatherIcon;
import static org.junit.Assert.*;

/**
 * Created by devikaraathor on 5/2/17.
 */
public class FunctionTest {
    @Test
    public void setWeatherIconTest() throws Exception {
        String icon = "";
        icon = setWeatherIcon(800, 3l, 1493748916500l);
        assertEquals(icon,"&#xf02e;");
    }

    @Test
    public void getWeatherJSON() throws Exception {
        assertEquals(0,0);
    }

}
