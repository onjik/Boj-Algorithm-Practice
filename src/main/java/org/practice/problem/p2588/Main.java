package org.practice.problem.p2588;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = bufferedReader.readLine();
        String secondLine = bufferedReader.readLine();

        int a = Integer.parseInt(firstLine);
        int b = Integer.parseInt(secondLine);
        String[] split = secondLine.split("");

        for (int i = split.length-1;i>-1;i--) {
            String s = split[i];
            System.out.println(a*Integer.parseInt(s));
        }
        System.out.println(a*b);
    }
}
