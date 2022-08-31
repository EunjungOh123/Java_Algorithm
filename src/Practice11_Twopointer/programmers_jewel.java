package Practice11_Twopointer;

import java.util.*;

public class programmers_jewel {
    public static void main(String[] args) {
        String [] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(solution(gems));
        gems = new String[]{"AA", "AB", "AC", "AA", "AC"};
        System.out.println(solution(gems));
    }
    public static int[] solution(String[] gems) {
        Set<String> set = new HashSet<>(Arrays.asList(gems)); // 보석의 종류
        Map<String, Integer> map = new HashMap<>(); // 보석의 종류와 각각의 개수를 담음
        int[] answer = new int[2]; // 시작 진열대 번호와 끝 진열대 번호를 담을 배열
        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;
        int length = 0;
        while(true) {
            if(set.size() == map.size()) {  // 모든 보석 종류가 map 안에 다 들어온 경우
                map.put(gems[start],map.get(gems[start])-1);
                if(map.get(gems[start]) == 0) { // 보석이 하나일 떼 제거된 경우 보석을 map 안에서 삭제
                    map.remove(gems[start]);
                }
                start++; // 범위 줄이기
            } else if (end == gems.length) { // 끝까지 탐색한 경우 종료
                break;
            } else { // 보석 종류가 모두 담기지 않은 경우
                map.put(gems[end], map.getOrDefault(gems[end], 0)+1);
                end++;
            }
            if(set.size() == map.size()) {
                length = end - start;
                if(length<min) {
                    min = length;
                    answer[0] = start+1; // 진열대는 1번부터 시작 (인덱스 조심)
                    answer[1] = end;
                    System.out.println(answer[0] + " "+ answer[1]);
                }
            }
        }
        return answer;
    }
}
