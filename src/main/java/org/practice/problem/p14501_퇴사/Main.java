package org.practice.problem.p14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /*
    문제 : 최대 수익을 구하는 문제
    조건 : 상담에 걸리는 시간과, 제한 된 날짜, 주어지는 수익이 주어진다.
    구현 방법 : 완전탐색을 해야한다. 중간에 조건에 맞지 않는 경우 가지를 폐기한다
    시간 복잡도 : O(N^2), 1<N<15 이므로, 최대 165 번의 연산 -> 시간 합격
     */

    static int max;
    static int n;
    static int[] benefits;
    static int[] timeCosts;

    public static void main(String[] args) {

        parseInput();
        dfs(0,0);
        System.out.println(max);

    }

    public static void parseInput(){
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            n = Integer.parseInt(bufferedReader.readLine());
            benefits = new int[n];
            timeCosts = new int[n];

            //파싱
            for (int i = 0; i < n; i++) {
                int[] s = Arrays.stream(bufferedReader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                timeCosts[i] = s[0];
                benefits[i] = s[1];
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    //앞에서 부터 선택 하고 안하고 두가지 경우만,
    //nextIdx : 다음 스케쥴을 결정할 수 있는 free day
    public static void dfs(int benefit, int nextIdx){

        if (nextIdx > n){
            //불가능 한 경우
            return;
        } else if (nextIdx == n){ //어차피 선택 안하면 1씩 증가하기 때문에 무조건 여기 옴
            //딱 끝난 경우
            max = Math.max(max,benefit);
            return;
        } else {
            //그 외의 경우 -> 선택 하거나 안하거나


            //선택 하면
            dfs(benefit+benefits[nextIdx],nextIdx+timeCosts[nextIdx]);
            //선택 안하면
            dfs(benefit,nextIdx+1);


        }





    }
}
