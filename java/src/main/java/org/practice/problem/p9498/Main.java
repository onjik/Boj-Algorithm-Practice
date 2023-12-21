package org.practice.problem.p9498;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int score = Integer.parseInt(bufferedReader.readLine());
        if (score > 89){
            System.out.println("A");
        } else if(score > 79) {
            System.out.println("B");
        } else if(score > 69) {
            System.out.println("C");
        } else if(score > 59) {
            System.out.println("D");
        } else{
            System.out.println("F");
        }
    }
}
