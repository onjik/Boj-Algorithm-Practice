package org.practice.problem.p1062_가르침;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

    static boolean[] isSelected = new boolean[26];
    static int n, k, max = 0;
    static String[] strings;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        scanner.nextLine();

        strings = new String[n];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            strings[i] = line.substring(4,line.length()-4);
        }

        //조건 제외
        if (k < 5) {
            System.out.println(0);
            return;
        } else if (k > 26){
            System.out.println(k);
            return;
        }

        isSelected['a' - 'a'] = true;
        isSelected['c' - 'a'] = true;
        isSelected['i' - 'a'] = true;
        isSelected['n' - 'a'] = true;
        isSelected['t' - 'a'] = true;

        //backtracking
        backTracking(0,5);
        System.out.println(max);



    }

    public static void backTracking(int index, int count){
        if (count == k){
            //단어 갯수 세기
            Long collect = Arrays.stream(strings)
                    .filter(s -> {
                        for (char c : s.toCharArray()) {
                            if (!isSelected[c - 'a']) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .count();
            max = (int)Math.max(max,collect);
            return;
        }
        if (index == 26) return;

        //선택안하고
        backTracking(index+1,count);
        //선택하고
        if (!isSelected[index]){
            isSelected[index] = true;
            backTracking(index+1,count+1);
            isSelected[index] = false;
        }
    }
}
