package org.practice.problem.p2884;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(inputStr, " ");
        int hour = Integer.parseInt(stringTokenizer.nextToken());
        int minute = Integer.parseInt(stringTokenizer.nextToken());
        if(minute >= 45) {
            System.out.println(hour + " " + (minute-45));
        } else {
            if (hour > 0) {
                System.out.println((hour-1) + " " + (minute + 15) );
            } else {
                System.out.println(23 + " " + (minute + 15));
            }
            
        }
        
    }
}