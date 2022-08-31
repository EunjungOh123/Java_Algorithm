package Practice10_BinarySearch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [] times = new int[n];
        for(int i = 0; i<n; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution(m, times));
    }


    public static long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long min = 1; // 최선의 경우
        long max = (long) times[times.length-1] * n; // 최악의 경우
        long mid = 0;
        while(min<=max) {
            long sum = 0;
            mid = (min + max) / 2;
            for(int i = 0; i<times.length; i++) {
                sum += mid / times[i]; // 심사받을 수 있는 사람 수의 합
            }
            if(sum<n) { // 심사 받는 사람의 수가 n보다 적으면 시간을 늘려야 함
                min = mid+1;
            } else { // 심사 받는 사람의 수가 n보다 많거나 같은 경우 시간을 줄여야 한다
                max = mid-1;
            }
        }
        answer = min;
        return answer;
    }
}
