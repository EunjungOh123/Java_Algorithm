package Practice11_Twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class baekjoon11728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        StringBuilder sb = new StringBuilder();
        String[] str1 = br.readLine().split(" ");
        for (int j = 0; j < str1.length; j++) {
            arr1[j] = Integer.parseInt(str1[j]);
        }
        String[] str2 = br.readLine().split(" ");
        for (int j = 0; j < str2.length; j++) {
            arr2[j] = Integer.parseInt(str2[j]);
        }
        int [] result = new int[arr1.length+arr2.length];
        for(int i = 0; i<arr1.length; i++) {
            result[i] = arr1[i];
        }
        for(int i = 0; i<arr2.length; i++) {
            result[i+arr1.length] = arr2[i];
        }
        Arrays.sort(result);
        for(int i : result) {
            sb.append(i+" ");
        }
        System.out.println(sb);
    }
}
