package Practice15_UnionFind;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon1043 {

    static int n;
    static int m;
    static int[] parent;
    static boolean[] truthPeople;
    static int count;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        truthPeople = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i; // union-find 초기화
        }
        st = new StringTokenizer(br.readLine(), " ");
        int people = Integer.parseInt(st.nextToken());
        for(int i = 0; i<people; i++) {
            truthPeople[Integer.parseInt(st.nextToken())] = true; // 이야기의 진실을 아는 사람 = true
        }
        ArrayList<Integer> [] partyUnion = new ArrayList[m];
        for(int i = 0; i<m; i++) {
            partyUnion[i] = new ArrayList<>(); // 각각의 파티마다 참여하는 사람들의 번호를 담을 ArrayList
        }
        int a = 0;
        int b = 0;
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int join = Integer.parseInt(st.nextToken()); // 특정 파티에 참가하는 사람의 수
            if(join > 0) {
                a = Integer.parseInt(st.nextToken());
                partyUnion[i].add(a);
            }
            for(int j = 1; j<join; j++) {
                b = Integer.parseInt(st.nextToken());
                partyUnion[i].add(b);
                union(a, b); // 두 사람씩 union 할 경우 모두가 같은 부모를 가진다
                a = b; // 한 파티에 1, 2, 3, 7번이 참여할 경우 (1, 2) (2, 3) (3, 7)을 union > 모두 같은 부모 1을 가짐
            }
        }

        // ex 진실을 아는 사람이 2번, 8번, 10번이고 한 파티에 참석하는 사람이 6번, 10번인 경우
        // truthPeople[2] = true, truthPeople[8] = true, truthPeople[10] = true 이다
        // parent[6] = 6, parent[10] = 6이며 10번의 부모는 6번이 된다 -> findParent(10) = 6
        // 따라서 6번도 진실을 알 수 있는 사람이 된다 -> truthPeople[6] = true
        for(int i = 1; i<truthPeople.length; i++) {
            if(truthPeople[i] == true) {
                truthPeople[findParent(i)] = true; // 진실을 아는 사람의 부모 또한 진실을 알고 있어야 한다
            }
        }
        for(int i = 0; i<m; i++) {
            if(partyUnion[i].size() > 0) {
               int parent = findParent(partyUnion[i].get(0));
                if(!truthPeople[parent]) count++;
            }
        }
        System.out.println(count);
    }

    public static int findParent(int x) { // 부모 노드를 찾는 메서드
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    public static void union(int x, int y) { // 두 부모 노드를 합치는 메서드
        x = findParent(x);
        y = findParent(y);

        if (x != y) {
            if (x > y) {
                parent[x] = y;
            } else {
                parent[y] = x;
            }
        }
    }
}
