package Practice10_BinarySearch;

// 랜선의 최대 길이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int k = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선
        int n = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
        int [] stick = new int[k];
        for(int i = 0; i<k; i++) {
            stick[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(stick); // 오름차순 정렬
        long min = 1; // 랜선의 길이는 자연수이므로 자를 수 있는 랜선의 길이 최솟값은 1
        long max = stick[stick.length-1]; // 자를 수 있는 랜선의 길이 최댓값은 주어진 랜선들의 길이의 최댓값
        long mid = 0;
        while(min<=max) {
            mid = (min + max) / 2;
            long count = 0;
            for(int i = 0; i< stick.length; i++) {
                count += stick[i]/mid; // 자른 랜선의 개수
            }
            if(count < n) { // 자른 랜선의 갯수가 n보다 작을 경우 갯수를 늘려야 한다 (잘리는 랜선의 길이를 줄여야 함)
                max = mid - 1;
            } else { // 자른 랜선의 개수가 n보다 크거나 같을 경우 갯수를 줄여야 한다 (잘리는 랜선의 길이를 늘려야 함)
                min = mid + 1;
            }
        }
        System.out.println(max);
    }
}
