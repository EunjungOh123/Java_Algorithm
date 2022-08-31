package Practice1_PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// 우선순위 큐 - 절대값 힙
// 절댓값이 가장 작은 값을 출력

public class baekjoon11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1)==Math.abs(o2)) {
                    return o1-o2; // 절댓값이 가장 작은 값이 여러개일 때 가장 작은 수를 출력 (오름차순 정렬)
                }
                return Math.abs(o1)-Math.abs(o2);
            }
        });
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

