package Practice5_Trie;

// 루트 노드는 아무런 문자열도 포함하지 않고 모든 문자열의 접두사들을 자식 배열로 갖고 있어야 한다

import java.util.HashMap;
import java.util.Map;

class Node {

    Map<Character, Node> childNode; //  key: 해당 노드에 입력될 문자열, value: 다음 노드
    boolean isTerminal; // 현재 노드가 문자 완성이 되는 종료 노드인지 나타내는 변수
    int count; // 현재 노드까지의 같은 접두사를 가지는 노드의 개수

    Node() { // 생성자를 통해 기본 초기화
        this.childNode = new HashMap<>();
        this.isTerminal = false;
        this.count = 0;
    }

}

class Trie {
    Node root; // 루트 노드

    Trie() {
        this.root = new Node();
    }

    public void insert(String word) { // 삽입
        Node current = this.root; // 루트 노드부터 시작해서 내려감

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); // 단어를 한 글자씩 쪼갠다
            current.count++;
            current.childNode.putIfAbsent(c, new Node());
            current = current.childNode.get(c); // 현재 노드 위치 초기화

            if (i == word.length() - 1) { // 마지막 문자라면
                current.isTerminal = true;
                return;
            }
        }
    }
    // query 존재한다면, 그 개수를 리턴
    public int countGet (String query) {
        Node current = this.root;
        for(int i = 0; i< query.length(); i++) {
            char c = query.charAt(i);
            Node node = current.childNode.get(c);
            if(node == null) {
                return 0;
            }
            current = node;
        }
        return current.count;
    }

    // 루트 노드부터 순서대로 문자가 일치하는 자식노드들이 존재해야 함
    // 해당 단어의 마지막 문자에 해당하는 노드만 isTerminal == true

    public boolean search(String word) { // 탐색
        Node current = this.root; // 루트 노드부터 시작해서 내려감

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node = current.childNode.get(c);
            if(node == null) { // 모든 문자열을 탐색하기 전에 노드가 끊어지면 해당 문자열은 존재하지 않으므로 false
                return false;
            }
            current = node; // 현재 노드 위치 초기화
        }
        return current.isTerminal;
    }

}

public class Practice {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("123440");
        trie.insert("12345");
        trie.insert("12340");
        trie.insert("113");
        trie.insert("98346");
        System.out.println(trie.search("113"));
        System.out.println(trie.search("12340"));
        System.out.println(trie.search("12345"));
        trie.insert("97625999");
        trie.insert("91125426");
        System.out.println(trie.search("911"));
        System.out.println(trie.countGet("123"));
        trie.insert("hello");
        trie.insert("hell");
        System.out.println(trie.countGet("hell"));
    }
}

