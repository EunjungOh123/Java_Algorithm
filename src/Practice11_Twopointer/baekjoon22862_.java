package Practice11_Twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon22862_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int [] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0; // start 포인터는 0에 위치

        int count = arr[start] % 2 == 0 ? 0 : 1; // start 포인터가 가리키는 값이 홀수면 삭제해야 하므로 count 는 1개부터 시작
        int length = arr[start] % 2 == 0 ? 1 : 0; // start 포인터가 가리키는 값이 짝수면 길이는 1부터 시작

        for(int end = 1; end<n; end++) { // 현재 end 포인터는 1에 위치
            if(arr[end] % 2 != 0) { //  end 포인터가 가리키는 값이 홀수라면
                count++; // count(삭제 횟수) 하나 늘려줌

                while(count > k) { // count 가 삭제할 수 있는 최대 횟수인 k보다 커진다면
                    if(arr[start] % 2 != 0) {
                        count--; // start 포인터가 가리키는 값이 홀수인 경우 count(삭제 횟수) 하나 줄여주고
                    }
                    start++; // start 포인터를 오른쪽으로 한 칸 이동
                }
            }
            length = Math.max(length, end-start+1-count); // 길이에 삭제된 경우는 포함되면 안됨
        }
        System.out.println(length);
    }
}
