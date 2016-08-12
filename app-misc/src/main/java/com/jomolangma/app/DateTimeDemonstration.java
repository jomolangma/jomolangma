package com.jomolangma.app;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateTimeDemonstration {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("The local date is :: " + localDate);
        //Find the length of the month. That is, how many days are there for this month.
        System.out.println("The number of days available for this month:: " + localDate.lengthOfMonth());
        //Know the month name
        System.out.println("What is the month name? :: " + localDate.getMonth().name());
        //add 2 days to the today's date.
        System.out.println(localDate.plus(2, ChronoUnit.DAYS));
        //substract 2 days from today
        System.out.println(localDate.minus(2, ChronoUnit.DAYS));
        //Convert the string to date
        System.out.println(localDate.parse("2017-04-07"));
    }
}