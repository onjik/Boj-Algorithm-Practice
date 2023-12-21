package org.practice.problem.p13460_구슬탈출2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/*
1. 10번 제한
2. 파란공 나오면 실패
3. 빨간공이 나오면 성공
4. 경우의 수는 대략 4^10 = 백만 정도?
5. 완전 탐색 실시
6.빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
 */
public class Main {

    static char[][] map;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static List<String> combiCase;

    public static void main(String[] args) throws IOException {
        Marble blue = null;
        Marble red = null;
        combiCase = new ArrayList<>();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken()); //세로 사이즈
        int M = Integer.parseInt(st.nextToken()); //가로 사이즈
        map = new char[N][M];
        for (int row = 0; row < N; row++) {
            char[] rowLine = br.readLine().toCharArray();
            for (int col = 0; col < M; col++) {
                map[row][col] = rowLine[col];
                if (rowLine[col] == 'R') {
                    red = new Marble(col,row);
                } else if (rowLine[col] == 'B'){
                    blue = new Marble(col,row);
                }
            }
        }
        br.close();
        assert blue != null;
        assert red != null;

        int result = bfs(new Pair(blue, red));
        System.out.println(result);


    }


    public static int bfs(Pair initPair){
        Queue<Pair> queue = new LinkedList<>();
        int distance = 0;
        queue.offer(initPair);

        while (!queue.isEmpty() && distance <= 10){
            int roundSize = queue.size();
//            System.out.println(distance);
            for (int i = 0; i < roundSize; i++) {
                Pair pair = queue.poll();
                combiCase.add(pair.toString());
                Marble red = pair.red;
                Marble blue = pair.blue;
//                System.out.println(pair.toString());
                //먼저 조건 체크
                //1. 파랑이 나갔냐?
                if (blue.readCurrentTile() == 'O') {
                    continue;
                }
                //2. 빨강이 나갔냐?
                if (red.readCurrentTile() == 'O') {
                    return distance;
                }

                //이동 로직
                for (int j = 0; j < 4; j++) {
                    Pair newPair = pair.move(dx[j], dy[j]);
                    if (newPair == null) continue;
                    queue.offer(newPair);
//                    System.out.println(">> "+newPair.toString());
                }
            }
            distance++;
        }
        //결과가 안나왔는데 큐가 비었거나 거리 10을 넘으면, 1,5
        return -1;
    }

    static class Pair{
        Marble blue;
        Marble red;

        public Pair(Marble blue, Marble red) {
            this.blue = blue;
            this.red = red;
        }

        public Pair move(int dx, int dy){
            Marble newBlue = blue.move(dx, dy);
            Marble newRed = red.move(dx, dy);
            //둘이 같이 빠지면, 실패처리해야한다.
            if (newBlue.readCurrentTile() == 'O' && newRed.readCurrentTile() == 'O'){
                return new Pair(newBlue, newRed);
            }
            //만약 둘이 겹치면 이전것을 비교해서 한놈을 이동시킨다
            if (newBlue.equals(newRed)){
                if (dx < 0){
                    if (blue.x > red.x){
                        newBlue.x -= dx;
                    } else {
                        newRed.x -= dx;
                    }
                } else if (dx > 0) {
                    if (blue.x > red.x){
                        newRed.x -= dx;
                    } else {
                        newBlue.x -= dx;
                    }
                } else if (dy < 0){
                    if (blue.y > red.y){
                        newBlue.y -= dy;
                    } else {
                        newRed.y -= dy;
                    }
                } else if (dy > 0) {
                    if (blue.y > red.y){
                        newRed.y -= dy;
                    } else {
                        newBlue.y -= dy;
                    }
                }
            } else if (newBlue.equals(blue) && newRed.equals(red)){ //안움직여도, 경우의수에 넣을 필요가 없다.
                return null;
            }
            Pair newPair = new Pair(newBlue, newRed);
            if (combiCase.contains(newPair.toString())){ //기존에 존재하던 경우라면 경우의 수에 넣을 필요가 없다.
                return null;
            }
            return newPair;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "blue=" + blue +
                    ", red=" + red +
                    '}';
        }
    }


    static class Marble{
        private int x;
        private int y;

        public Marble(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public char readCurrentTile(){
            return map[y][x];
        }

        public Marble move(int dx, int dy){
            int newX = x;
            int newY = y;
            if (dx != 0){
                while (map[newY][newX + dx] != '#') {
                    if (map[newY][newX] == 'O') break;
                    newX+=dx;
                }
            } else {
                while (map[newY + dy][newX] != '#') {
                    if (map[newY][newX] == 'O') break;
                    newY += dy;
                }
            }
            return new Marble(newX, newY);

        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Marble){
                Marble marble = (Marble) obj;
                return marble.x == this.x && marble.y == this.y;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Marble{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    //10 이하, 0문자 틀림

}
