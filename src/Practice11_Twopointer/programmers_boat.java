package Practice11_Twopointer;

// 구명 보트는 최대 2명씩만
// 보트의 무게 제한은 항상 사람들의 몸무게 최댓값보다 크게 주어지므로 최소한 1명은 탈 수 있음
// 구명 보트의 개수를 최소로 하기 위해서는 가능하다면 보트에 2명씩 태워야 함

import java.util.Arrays;

public class programmers_boat {
    public static void main(String[] args) {
        int [] people = {70,50,80,50};
        System.out.println(solution(people, 100));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people); // 오름차순으로 정렬
        int start = 0;
        int end = people.length-1;
        while(start<=end) { // 모든 사람을 다 태울 때까지 (투 포인터가 만나는 경우 배는 혼자 탄다)
            //  left 몸무게와 right 몸무게의 합이 limit 보다 작거나 같은 경우
            if(people[start]+people[end] <= limit) { // 둘이 같이 탄다
                start++;
                end--;
                answer++;
            } else { //  left 몸무게와 right 몸무게의 합이 limit 보다 큰 경우
                end--; // 혼자 탄다
                answer++;
            }
        }
        return answer;
    }
}
