package Practice5_Trie;

// 일관성 없다 = 한 번호가 다른 번호의 접두어인 경우
// 트라이 사용
// 본인 제외 탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class baekjoon5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<test; i++) {
            int n = Integer.parseInt(br.readLine());
            트라이 trie = new 트라이();
            String [] numbers = new String[n];
            boolean consistent = false;
            for(int j = 0; j<n; j++) {
                numbers[j] = br.readLine();
                trie.insert(numbers[j]);
            }
            for(String str : numbers) {
                if(trie.search(str)) {
                    consistent = false; // 탐색에 성공했다면 일관성이 없는 것
                    break;
                }  else {
                    consistent = true;
                }
            }
            if(consistent) {sb.append("YES"+"\n");}
            else {sb.append("NO"+"\n");}
        }
        System.out.println(sb);
    }
}

class 노드 {

    Map<Character, 노드> childNode; //  key: 해당 노드에 입력될 문자열, value: 다음 노드
    boolean isTerminal;

    노드() { // 생성자를 통해 기본 초기화
        this.childNode = new HashMap<>();
        this.isTerminal = false;
    }

}

class 트라이 {
    노드 root; // 루트 노드

    트라이() {
        this.root = new 노드();
    }

    public void insert(String word) { // 삽입
        노드 current = this.root; // 루트 노드부터 시작해서 내려감

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); // 단어를 한 글자씩 쪼갠다
            current.childNode.putIfAbsent(c, new 노드());
            current = current.childNode.get(c); // 현재 노드 위치

            if (i == word.length() - 1) { // 마지막 문자라면
                current.isTerminal = true;
                return;
            }
        }
    }


    // true = 탐색 성공, false = 탐색 실패
    public boolean search(String word) { // 탐색
        노드 current = this.root; // 루트 노드부터 시작해서 내려감

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            노드 node = current.childNode.get(c);
            if(node == null) {
                return false;
            }
            current = node;
        }

        // 자기 자신 제외하고 해당 단어로 종료하는 문자가 있는 경우 false 반환
        if(current.isTerminal) {
            if(current.childNode.isEmpty()) { // childNode 없으면 해당 종료 노드는 자기 자신임
                return false;
            }
        }
        return true;
    }
}