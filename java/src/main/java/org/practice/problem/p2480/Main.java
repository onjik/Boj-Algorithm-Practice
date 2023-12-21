package org.practice.problem.p2480;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        if ((a==b) && (b==c)){
            System.out.println(10000+1000*a);
        } else if(a==b || b==c) {
            System.out.println(1000 + 100*b);
        } else if(a == c){
            System.out.println(1000 + 100*c);
        } else {
            System.out.println(Math.max(a,Math.max(b, c))*100);
        }
    }
}
