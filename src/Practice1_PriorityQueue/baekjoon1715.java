package Practice1_PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 우선순위 큐 - 카드 정렬하기
// 두 묶음씩 골라 서로 합쳐나간다면, 고르는 순서에 따라서 비교 횟수가 달라짐
// 비교 횟수의 최솟값

public class baekjoon1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        for(int i = 0; i<n; i++) {
            long num = Long.parseLong(br.readLine());
            priorityQueue.offer(num);
        }

        // 최소한의 비교를 하기 위해서는 낮은 값부터 먼저 더해야 한다
        // num1과 num2를 더한 값은 뒤에서 사용하므로 더한 값을 우선순위 큐에 계속 넣어준다
        long sum = 0;
        while(priorityQueue.size() >= 2) {
            long num1 = priorityQueue.poll();
            long num2 = priorityQueue.poll();
            sum += num1+num2;
            priorityQueue.add(num1+num2);
        }
        // 만약 n이 1이라면 최소 비교 횟수는 0이 된다 > 합칠 숫자 카드 묶음이 없기 때문에
        System.out.println(sum);
    }
}
