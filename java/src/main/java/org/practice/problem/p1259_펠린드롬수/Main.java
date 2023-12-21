package org.practice.problem.p1259_펠린드롬수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private String abc;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //앞뒤가 똑같은지 확인하는 로직
        //1. 문자열을 받는다.
        while (br.readLine().equals("0")) {
            String str = br.readLine();
            int len = str.length();
            boolean isPalindrome = true;
            for (int i = 0; i < len / 2; i++) {
                if (str.charAt(i) != str.charAt(len - i - 1)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

    }

}

