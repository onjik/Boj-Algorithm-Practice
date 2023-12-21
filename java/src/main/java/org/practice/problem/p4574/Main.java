package org.practice.problem.p4574;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
기본적인 스도쿠 룰에
인접한 짝 조건 추가

아래 오른쪽을 향해 순차적 전체 탐색 int idx
백트래킹 전략

짝 체크 boolean[][]
스도쿠 룰 체크하는 함수 추가

맵은 항상 9*9
 */
class Main{
    static boolean[][] marked;
    static int[][] map;
    static boolean flag = false;


    static int[] dRow = {1, 0};
    static int[] dCol = {0, 1};

    static int cnt = 1;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = -1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n==0) break;

            map = new int[9][9];
            marked = new boolean[10][10];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                char[] LU = st.nextToken().toCharArray();
                int V = Integer.parseInt(st.nextToken());
                char[] LV = st.nextToken().toCharArray();

                marked[U][V] = true;
                marked[V][U] = true;
                map[LU[0] - 'A'][LU[1] - '1'] = U;
                map[LV[0] - 'A'][LV[1] - '1'] = V;
            }
            String[] s = br.readLine().split(" ");
            for (int i = 0; i < 9; i++) {
                char[] chars = s[i].toCharArray();
                map[chars[0]-'A'][chars[1] - '1'] = i+1;
            }
            flag = false;
            dfs(0);
            cnt++;


        }

    }

    /**
     *
     * @param idx : 0~ 80
     */
    public static void dfs(int idx){
        //마지막 넘었는지 체크
        if (idx > 80){
            if (flag) return;
            flag = true;
            //print
            StringBuffer sb = new StringBuffer(90);
            sb.append(String.format("Puzzle %d\n", cnt));
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    sb.append(map[row][col]);
                }
                sb.append("\n");
            }
            System.out.print(sb);
            return;
        }

        int curRow = idx / 9;
        int curCol = idx % 9;

        //값 있는지 확인
        if (map[curRow][curCol] != 0) {
            dfs(idx+1); //스킵
            return;
        }
        //거기 들어갈 값 찾기
        for (int i = 1; i < 10; i++) {
            if (!isAvailableForSudoku(curRow,curCol,i))continue;
            //스도쿠 룰 통과 했다면,
            //짝 위치 찾기 : 아래랑 오른쪽만 고려하면 됨
            int row2;
            int col2;
            for (int j = 0; j < 2; j++) {
                row2 = curRow + dRow[j];
                col2 = curCol + dCol[j];
                //유효성 검증
                //일단 값 존재하는지, 맵 나갔는지
                try {
                    if (map[row2][col2] != 0) continue;
                } catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
                //값 찾기
                for (int h = 1; h < 10; h++) {
                    //스도쿠 룰 통과하면서, i랑 겹치지 않으면서, 짝도 존재하지 않는 값
                    if (isAvailableForSudoku(row2, col2, h) && i != h && !marked[i][h]) {
                        //백트래킹
                        map[curRow][curCol] = i;
                        map[row2][col2] = h;
                        marked[i][h] = true;
                        marked[h][i] = true;
                        dfs(idx+1);
                        map[curRow][curCol] = 0;
                        map[row2][col2] = 0;
                        marked[i][h] = false;
                        marked[h][i] = false;
                    }
                }

            }

        }
    }

    public static boolean isAvailableForSudoku(int row, int col, int value){
        //가로 체크
        for (int elem : map[row]){
            if (elem == value) return false;
        }
        //세로 체크
        for (int i = 0; i < 9; i++) {
            if (map[i][col] == value) return false;
        }
        //정사각형 체크
        int rowOffset = (row / 3) * 3;
        int colOffset = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[rowOffset + i][colOffset + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }




}