package org.practice.problem.p3003.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final int[] NORMAL_ARRANGEMENT = {1, 1, 2, 2, 2, 8};
        int[] answer = new int[6];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        for (int i=0; i < 6;i++){
            int j = Integer.parseInt(stringTokenizer.nextToken());
            System.out.print(NORMAL_ARRANGEMENT[i] - j);
            if(i!=5){
                System.out.print(" ");
            }
        }
    }
}
