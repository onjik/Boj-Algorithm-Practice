package org.practice.problem.p9663;


import java.util.Scanner;

/*
퀸 정보 저장 : 일차원 배열 - 한 행에는 하나의 퀸만 존재가능
대각선의 정의 : 가로와 세로의 간격이 같은 것
dfs를 통해 해결하기
일단 넣고, 조건에 맞으면 다음 인덱스에 채우게 재귀호출
*/
public class Main {
    static int N;
    static int count;
    static int[] colIdxOfQueen;
    public static void main(String[] args) {
        //INPUT
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();
        //INIT
        count = 0;
        colIdxOfQueen = new int[N];
        //INVOKE
        dfs(0);
        //PRINT
        System.out.println(count);
    }

    public static void dfs(int targetIdx){
        //인덱스를 초과하면
        if (targetIdx >= colIdxOfQueen.length){
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            colIdxOfQueen[targetIdx] = i;
            //조건 체크
            if (isPossible(targetIdx)) {
                dfs(targetIdx+1);
            }
        }
    }

    public static boolean isPossible(int targetIdx){
        for (int i = 0; i < targetIdx; i++) {
            //col 체크
            if (colIdxOfQueen[targetIdx] == colIdxOfQueen[i]) return false;
            //대각선 체크
            if ((targetIdx - i) == Math.abs(colIdxOfQueen[targetIdx] - colIdxOfQueen[i])) return false;
        }
        return true;
    }

}
