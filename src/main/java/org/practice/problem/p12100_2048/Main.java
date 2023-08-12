package org.practice.problem.p12100_2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    /*
    기본 전략 : 가는쪽 부터 밀면서 합칠수 있으면 합치기
     */

    static int[][] map;
    static int answer;
    static int N;


    public static void main(String[] args) {

    }

    public static void game(int round){
        if (round == 5) {
            findMax();
            return;
        }
        //make back up
        int[][] copy = Arrays.copyOf(map, map.length);

        //move and recursive call
        for (Direction direction: Direction.values()){
            move(direction);
            game(round+1);
            //reset
            map = copy;
        }
    }

    private static void move(Direction direction) {
        int idx, block;
        switch (direction) {
            case UP:
                for (int col = 0; col < N; col++) {
                    idx = 0; // 이제 채워질 인덱스
                    block = 0;
                    for (int row = 0; row < N; row++) {
                        if (map[row][col] == 0) continue;
                        //이동하고
                        if (map[row][col] == block){
                            map[idx-1][col] = block * 2;
                            block = 0;
                        } else {
                            block = map[row][col];
                            map[idx][col] = map[row][col];
                            idx++;
                        }
                        //있던 자리는 0으로
                        map[row][col] = 0;
                    }
                }
                break;
            case LEFT:


        }
    }

    private static void findMax() {
        for (int i = 0; i < N; i++) {
            for (int tmp :map[i]){
                if (tmp > answer){
                    answer = tmp;
                }
            }
        }
    }



    enum Direction{
        UP,DOWN,LEFT,RIGHT;
    }

}
