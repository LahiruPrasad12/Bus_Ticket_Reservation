package com.example.busticketreservation;

import com.example.busticketreservation.Admin.AddRoutes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FullRoutePriceTest {
    private AddRoutes addRoute;

    @Before
    public void setup() {
       addRoute = new AddRoutes();
    }

    @Test
    public void priceCalculation() {
        int result = addRoute.calFullRoutePrice(41, 30, 10);
        assertEquals(430, result);
    }
}