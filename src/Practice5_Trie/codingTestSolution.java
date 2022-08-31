package Practice5_Trie;

// 단어의 검색은 words 배열 내에서 수행
// 검색하고자 하는 단어들은 queries 배열로 주어짐
// 각 검색 단어는 단어의 앞부분 또는 뒷부분의 일부만으로 구성되어 있음
// 앞부분 일부만으로 구성된 단어의 경우 단어 마지막에 *를 붙인다
// 뒷부분 일부만으로 구성된 단어의 경우 단어 시작에 *를 붙인다
// 각 검색 결과에 해당하는 단어의 개수를 배열로 반환한다

import java.util.*;

public class codingTestSolution {
    public static void main(String[] args) {
        String [] words = {"hello","hell","good","goose","children","card","teachable"};
        String [] queries = {"hell*","goo*","*able","qua*"};
        System.out.println(Arrays.toString(solution(words,queries)));

    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie1 trie = new Trie1();
        Trie1 trieReverse = new Trie1();
        for (String str : words) {
            StringBuilder sb = new StringBuilder(str);
            trie.insert(str);
            trieReverse.insert(sb.reverse().toString());
        }
        List<Integer> list = new ArrayList<>();
        for(String str : queries) {
            if(str.charAt(0) != '*') {
                list.add(trie.countGet(str));
            } else {
                StringBuilder sb = new StringBuilder(str);
                list.add(trieReverse.countGet(sb.reverse().toString()));
            }
        }
        for(int i = 0; i< queries.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}


class Node1 {
    Map<Character, Node1> childNode; //  key: 해당 노드에 입력될 문자열, value: 다음 노드
    boolean isTerminal; // 현재 노드가 문자 완성이 되는 종료 노드인지 나타내는 변수
    int count; // 현재 노드까지의 같은 접두사를 가지는 노드의 개수

    Node1() { // 생성자를 통해 기본 초기화
        this.childNode = new HashMap<>();
        this.isTerminal = false;
        this.count = 0;
    }
}

class Trie1 {
    Node1 root; // 루트 노드

    Trie1() {
        this.root = new Node1();
    }

    public void insert(String word) { // 삽입
        Node1 current = this.root; // 루트 노드부터 시작해서 내려감
//        current.count++; // 문자가 * 하나인 경우
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); // 단어를 한 글자씩 쪼갠다
            current.childNode.putIfAbsent(c, new Node1());
            current = current.childNode.get(c); // 현재 노드 위치 초기화
            current.count++;
            if (i == word.length() - 1) { // 마지막 문자라면
                current.isTerminal = true;
                return;
            }
        }
    }

    public int countGet (String query) {
        Node1 current = this.root;
        for(int i = 0; i< query.length(); i++) {
            char c = query.charAt(i);
            if(c == '*') {
                return current.count;
            }
            Node1 node = current.childNode.get(c);
            if(node == null) {
                return 0;
            }
            current = node;
        }
        return current.count;
    }
}