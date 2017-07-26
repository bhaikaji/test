package com.unittest.tests;

import com.unittest.NotifierStub;
import com.unittest.TrackingService;
import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TrackingServiceTheories {
    @DataPoints
    public static int[] data(){
        return new int[]{
                1, 5, 10, 15, 20, 50 , -4
        };
    }
    @Theory
    public void positiveValuesShouldAlwaysHavePositiveTotals(int value){
        TrackingService service = new TrackingService(new NotifierStub());
        service.addProtein(value);

        Assume.assumeTrue(value > 0);

        assertTrue(service.getTotal() > 0);
    }
}
