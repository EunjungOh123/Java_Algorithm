package Practice4_Partition;

// 전위 순회 : root > left > right
// 중위 순회 : left > root > right
// 후위 순회 : left > right > root

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon4256 {
    static int [] preorder; // 전위 순회
    static int [] inorder; // 중위 순회
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i<test; i++) {
            int n = Integer.parseInt(br.readLine());
            preorder = new int[n];
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j<n; j++) {
                preorder[j] = Integer.parseInt(st.nextToken());
            }
            inorder = new int[n];
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j<n; j++) {
                inorder[j] = Integer.parseInt(st.nextToken());
            }
            postOrder(0,n,0);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void postOrder (int start, int end, int cur) {
        for(int i = start; i<end; i++) {
            // preorder[0]의 값(루트 노드)과 중위 순회 배열 내 값이 같은 경우
            if(inorder[i] == preorder[cur]) { // 루트 노드 값을 기준으로 중위 순회 배열이 왼쪽 서브 트리와 오른쪽 서브 트리로 나뉨
                postOrder(start, i, cur+1); // 왼쪽 서브 트리
                postOrder(i+1, end, cur+i-start+1); // 오른쪽 서브 트리
                sb.append(preorder[cur]+" ");
            }
        }
    }
}
