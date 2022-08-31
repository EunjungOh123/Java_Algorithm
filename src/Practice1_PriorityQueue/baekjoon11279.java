package Practice1_PriorityQueue;

// 우선순위 큐 - 최대 힙 //
// 높은 우선순위의 요소를 먼저 꺼내서 처리하는 구조


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class baekjoon11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위가 높은 숫자 순
        StringBuilder sb=  new StringBuilder();
        for(int i = 0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num != 0) {
                priorityQueueHighest.offer(num);
            } else {
                if(priorityQueueHighest.isEmpty()) {
                    sb.append(0+"\n");
                } else {
                    sb.append(priorityQueueHighest.poll()+"\n");
                }
            }
        }
        System.out.println(sb);
    }
}
