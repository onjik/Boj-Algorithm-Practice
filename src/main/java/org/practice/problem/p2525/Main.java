package org.practice.problem.p2525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        int hh = Integer.parseInt(st.nextToken());
        int mm = Integer.parseInt(st.nextToken());
        int dur = Integer.parseInt(bf.readLine());
        //add dur
        mm += dur;
        
        hh += mm/60;
        mm = mm%60;

        hh = hh % 24;
        System.out.printf("%d %d",hh,mm);
    }
}
