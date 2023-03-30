package org.practice.problem.p4574;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
# 게임의 룰
1. 가로줄 중복 안됨
2. 세로줄 중복 안됨
3. 정사각형 중복 안됨

4. 도미노 타일은 (1~9)X(1~9)의 모든 가능한 쌍이 포함되어야한다. 순서 상관없다
5. 겹쳐지면 안된다.

# 입력
1. 채워져 있는 도미노 수는 10~ 35
2. 위치는 A1과 같은 형식 : 알파벳 row, 숫자 col
3. 그리고 쭉 초기 박힌 값들 불러줌
4. 0이면 끝남

# 전략
일단 비어있는 칸 = 0
dfs 백트래킹 전략

visited_domino 단순 2차원 boolean 배열
스도쿠 룰을 검사하는 함수
dfs에는 idx를 매개변수로 넘겨줌 - > 0~ 81

 */
class Main{

    static boolean[][] isMarkedDom;
    static int[][] map;
    static int cnt = 1;

    static int[] dRow = new int[]{1, 0}; //여기 오답
    static int[] dCol = new int[]{0, 1};

    static boolean flag; //여기 오답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeat;
        while (true){
            //init
            isMarkedDom = new boolean[10][10];
            map = new int[9][9];

            //input
            repeat = Integer.parseInt(br.readLine());
            if (repeat == 0 ){ //종료 사인
                break;
            }

            //초기 도미노 배정
            for (int i = 0; i < repeat; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int U  = Integer.parseInt(st.nextToken());
                char[] UL = st.nextToken().toCharArray();
                int V = Integer.parseInt(st.nextToken());
                char[] VL = st.nextToken().toCharArray();

                //register
                map[UL[0] - 'A'][UL[1] - '1'] = U;
                map[VL[0] - 'A'][VL[1] - '1'] = V;

                isMarkedDom[U][V] =true;
                isMarkedDom[V][U] =true;

            }
            //초기 숫자 배정
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 10; i++) {
                char[] chars = st.nextToken().toCharArray();
                map[chars[0]-'A'][chars[1]-'1'] = i;
            }


            flag = false;
            //dfs call
            dfs(0);
            cnt++;


        }

    }

    /**
     *
     * @param idx : 0~80 까지 인덱스를 의미함
     */
    public static void dfs(int idx){
        if (idx == 81){ //마지막까지 다 둘러봄
            if (flag==true) return; //여기 오답
            flag =true;
            StringBuffer sb = new StringBuffer(90);
            //printing map
            sb.append("Puzzle ");
            sb.append(cnt);
            sb.append("\n");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb);
            return; //여기 오답
        }

        //마지막이 아니면
        int row = idx/9;
        int col = idx%9;

        //만약 해당 위치에 값이 있으면 스킵
        if (map[row][col] != 0){
            dfs(idx+1);
            return;
        }

        for (int i = 1; i < 10; i++) {
            //스도쿠 룰을 따르는 유효성 검증
            if (!isAvailable(row,col,i)) continue;
            //유효성 통과했다면, 도미노 체크
            //왼쪽 위부터 시작하므로 오른쪽과 아래만 체
            for (int j = 0; j < 2; j++) {
                int newRow = row+dRow[j];
                int newCol = col+dCol[j];
                //맵 밖인지 체크, 이미 점유된 도미노인지 체크
                try {
                    if (map[newRow][newCol] != 0){
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
                //맵 안이고 점유되지 않은 위치임
                for (int k = 1; k < 10; k++) {
                    //이미 사용했던 도미노면 건너뛰기
                    if (isMarkedDom[i][k] || !isAvailable(newRow,newCol,k) || k==i) continue;
                    //사용한적없고 스도쿠에도 맞으면 맵에 배정
                    map[row][col] = i;
                    map[newRow][newCol] = k;
                    isMarkedDom[k][i] = true;
                    isMarkedDom[i][k] = true;
                    dfs(idx+1);
                    map[row][col] = 0;
                    map[newRow][newCol] = 0;
                    isMarkedDom[k][i] = false;
                    isMarkedDom[i][k] = false;
                }
            }


        }
    }

    public static boolean isAvailable(int row, int col, int value){
        //가로줄, 세로줄 체크
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == value) return false;
            if (map[i][col] == value) return false;
        }
        //정사각형 공간 체크
        int rowOffset = (row/3)*3;
        int colOffset = (col/3)*3;
        for (int i = rowOffset; i < rowOffset + 3; i++) {
            for (int j = colOffset; j < colOffset + 3; j++) {
                if (map[i][j] == value) return false;
            }
        }
        return true;
    }



}