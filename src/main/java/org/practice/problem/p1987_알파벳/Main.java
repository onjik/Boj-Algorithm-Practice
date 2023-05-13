package org.practice.problem.p1987_알파벳;

/*
가장 깊이 들어가는 길이를 구하는 문제이므로 dfs 로 풀어야 한다.
백트래킹을 사용한다.

조건
1. 같은 알파뱃은 밟지 못한다.
2. max distance 를 구하라
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int max;
    static char[][] map;
    //어차피 방문한 곳은 다시 못방문함
    static boolean[] isDupAlpha = new boolean[26];

    static int[] dx = new int[] {1,-1,0,0};
    static int[] dy = new int[] {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new char[ints[0]][ints[1]];
        for (int i = 0; i < map.length; i++){
            map[i] = br.readLine().toCharArray();
        }

        isDupAlpha[map[0][0] - 'A'] = true;
        dfs(1, 0,0);

        System.out.println(max);

    }

    public static void dfs(int depth,int x, int y){
        if (depth > max) max = depth;
        //한바퀴 돌면서 체크
        int nX;
        int nY;
        char c;
        for (int i = 0; i < 4; i++){
            nX = x + dx[i];
            nY = y + dy[i];
            try {
                c = map[nY][nX];
            } catch (ArrayIndexOutOfBoundsException e) {continue;}
            if (isDupAlpha[c - 'A']) continue;

            //유효하므로 진행
            isDupAlpha[c - 'A'] = true;
            dfs(depth + 1,nX,nY);
            isDupAlpha[c - 'A'] = false;
        }

    }

}
