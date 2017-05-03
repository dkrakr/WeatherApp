package com.example.devikaraathor.weatherfinal;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author draath2
 */
public class WeatherLessDetailTest {
    @Test
    public void makeArrayTest() throws Exception {
        WeatherLessDetail less = new WeatherLessDetail();

        String citiesTest1 = "Amsterdam";
        assertEquals("Amsterdam", less.makeArray(citiesTest1).get(0));

        String citiesTest2 = "Prague,Madrid";
        assertEquals("Prague", less.makeArray(citiesTest2).get(0));
        assertEquals("Madrid", less.makeArray(citiesTest2).get(1));

        String citiesTest3 = "Chicago,London,Paris";
        assertEquals("Chicago", less.makeArray(citiesTest3).get(0));
        assertEquals("London", less.makeArray(citiesTest3).get(1));
        assertEquals("Paris", less.makeArray(citiesTest3).get(2));

        String citiesTest4 = "Moscow,Paris,Belgium,Hyderabad";
        assertEquals("Moscow", less.makeArray(citiesTest4).get(0));
        assertEquals("Paris", less.makeArray(citiesTest4).get(1));
        assertEquals("Belgium", less.makeArray(citiesTest4).get(2));
        assertEquals("Hyderabad", less.makeArray(citiesTest4).get(3));
    }

}