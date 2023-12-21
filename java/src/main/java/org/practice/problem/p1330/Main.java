package org.practice.problem.p1330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s," ");
        int a = Integer.parseInt(stringTokenizer.nextToken());
        int b = Integer.parseInt(stringTokenizer.nextToken());

        if (a > b){
            System.out.println(">");
        } else if(a < b){
            System.out.println("<");
        } else {
            System.out.println("==");
        }
    
    }
}
