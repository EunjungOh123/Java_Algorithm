package Practice1_PriorityQueue;

// 우선순위 큐 - 최소힙 //
// 낮은 우선순위의 요소를 먼저 꺼내서 처리하는 구조

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class baekjoon1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue <Integer> priorityQueue = new PriorityQueue<>();
        StringBuilder sb= new StringBuilder();
        for(int i = 0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num != 0) {
                priorityQueue.offer(num);
            } else {
                if(priorityQueue.isEmpty()) {
                    sb.append(0+"\n");
                } else {
                    sb.append(priorityQueue.poll()+"\n");
                }
            }
        }
        System.out.println(sb);
    }
}
