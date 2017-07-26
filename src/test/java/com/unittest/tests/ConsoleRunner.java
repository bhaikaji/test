package com.unittest.tests;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class ConsoleRunner {

    public static void main(String arg[]){
        JUnitCore junits = new JUnitCore();
        junits.addListener(new TextListener(System.out));
        junits.run(TrackingServiceTests.class);
    }

}
