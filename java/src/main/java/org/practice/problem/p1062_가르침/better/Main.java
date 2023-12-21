package org.practice.problem.p1062_가르침.better;

import java.util.Scanner;

public class Main {
    static int n,k,max = 0;
    static int[] arr;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        arr = new int[n];
        //단어 입력 받기
        for (int i = 0; i < n; i++) {
            char[] chars = scanner.next().toCharArray();
            int x = 0;
            for (char c : chars) x |= 1 << (c - 'a'); //1을 알파벳 만큼 shift 해서 or 연산자
            arr[i] = x;
        }
        dfs(0,0,0);
        System.out.println(max);

    }

    public static void dfs(int index, int visited, int count){
        if (count == k){
            //똑같이 가능한 단어 세기
            int availableCount = 0;
            for (int sentence : arr){
                if ((sentence|visited) == visited){
                    availableCount++;
                }
            }
            if (max < availableCount){
                max = availableCount;
            }
        } else if (index < 26) {
            //조합 재귀호출 - 현재 인덱스 뽑고 안뽑고

            //뽑고
            dfs(index+1,(visited|(1<<index)),count+1);
            //안뽑고
            dfs(index+1,visited,count);

        }

    }
}
