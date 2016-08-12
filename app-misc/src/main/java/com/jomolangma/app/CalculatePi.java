package com.jomolangma.app;

import java.util.Random;

/**
 * Created by ZhangLijun on 4/21/16.
 */
public class CalculatePi {
    public static void main(String[] args){
        long n = 1000000000l;
        long m = 0l;
        double x,y;
        Random random = new Random();
        for (long i = 0; i < n; i++){
            x = random.nextDouble();
            y = random.nextDouble();

            if ((x*x + y *y) <1 ){
                m++;
            }
        }

        double pi = 4.0 * m/n;

        System.out.println(pi);
    }
}
