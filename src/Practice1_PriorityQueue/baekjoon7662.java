package Practice1_PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 우선순위 큐 문제이지만 TreeMap 사용해서 풀어도 된다

public class baekjoon7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < test; i++) {
            int n = Integer.parseInt(br.readLine());
            TreeMap<Integer,Integer> treeMap = new TreeMap<>(); // key 오름차순 정렬
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                int number = Integer.parseInt(st.nextToken());
                if (str.equals("I")) {
                    // getOrDefault(key, 기본 값) : 찾는 키가 존재한다면 찾는 키의 값을 반환하고 없다면 기본 값을 반환
                    treeMap.put(number, treeMap.getOrDefault(number, 0) + 1);
                } else if(str.equals("D")) {
                    if(!treeMap.isEmpty()) {
                        // -1이면 최솟값 (firstKey) 삭제, 1이면 최댓값 (lastKey) 삭제
                        int key = (number == -1) ? treeMap.firstKey() : treeMap.lastKey();
                        if(treeMap.get(key) == 1) {
                            treeMap.remove(key);
                        } else  {
                            treeMap.put(key, treeMap.get(key)-1);
                        }
                    }
                }
            }
            if(treeMap.size() == 0) { // 비어있으면 empty 출력
                sb.append("EMPTY"+"\n");
            } else { // 첫번째 key 값이 제일 작은 값, 마지막 key 값이 제일 큰 값
                sb.append(treeMap.lastKey()+" "+treeMap.firstKey()+"\n");
            }
        }
        System.out.println(sb);
    }
}
