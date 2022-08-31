package Practice10_BinarySearch;

// 정해진 총액 이하에서 가능한 한 최대의 총 예산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 지방의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] request_budget = new int[n]; // 예산 요청액 배열
        for(int i = 0; i<n; i++){
            request_budget[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(request_budget);
        long total = Integer.parseInt(br.readLine()); // 총 예산
        // 상한액 이진 탐색
        long min = 0;
        long max = request_budget[n-1];
        while(min<=max) {
            long mid = min + max / 2;
            long sum = 0;
            for(int i = 0; i<request_budget.length; i++) {
                if(request_budget[i] > mid) { // 예산 요청액이 상한액보다 큰 경우
                    sum += mid; // 상한액까지만 받을 수 있다
                } else { // 예산 요청액이 상한액보다 작은 경우
                    sum += request_budget[i]; // 예산 요청액 전부 받을 수 있다
                }
            }
            if(sum<=total) { // 합이 국가예산의 총합보다 작은 경우 > 상한액을 올려야 한다
                min = mid+1;
            } else { // 합이 국가예산의 총합보다 큰 경우 > 상한액을 줄여야 한다
                max = mid-1;
            }
        }
        System.out.println(max);
    }
}