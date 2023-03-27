package org.practice.problem.p2580;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point{
        int col;
        int row;

        public Point(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
    static int[][] map = new int[9][9];
    static List<Point> zeroQueue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        br.close();

        //indexing 0 point
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] == 0 ){
                    zeroQueue.add(new Point(col,row));
                }
            }
        }

        //invoke
        dfs(0);

    }

    public static void dfs(int depth){
        if (depth >= zeroQueue.size()){
            //모든 작업 끝남
            printMap();
            System.exit(0);
            return;
        }
        //해결해야할 녀석
        Point targetPoint = zeroQueue.get(depth);
        //후보자 set 생성
        Set<Integer> candiSet = new HashSet<>();
        for (int i = 1;i <10; i++ ) candiSet.add(i);
        //가로축 후보자 추리기
        updateCandidate(candiSet,map[targetPoint.row]);
        //세로축 후보자 추리기
        for (int i = 0; i < 9; i++) {
            updateCandidate(candiSet,map[i][targetPoint.col]);
        }
        //정사각형 후보자 추리기
        int rowStart = (targetPoint.row/3)*3;
        int colStart = (targetPoint.col/3)*3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                updateCandidate(candiSet,map[rowStart+i][colStart+j]);
            }
        }
        //색출된 후보자로 가지치기
        for (int candi : candiSet){
            map[targetPoint.row][targetPoint.col] = candi;
            dfs(depth+1);
            map[targetPoint.row][targetPoint.col] = 0;
        }
    }

    public static void updateCandidate(Set<Integer> candiSet,int elem){
        candiSet.remove(elem);
    }
    public static void updateCandidate(Set<Integer> candiSet,int[] arr){
        for (int elem : arr){
            candiSet.remove(elem);
        }
    }

    public static void printMap(){
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                sb.append(map[row][col]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}
