package Practice6_BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon1327 {

    static int n; // 순열의 개수
    static int k; // 뒤집어야할 문자 개수
    static char [] arr; // 순열에 들어가는 수 배열
    static char [] copy; // 순열에 들어가는 수 배열을 복사한 배열
    static String arr_str; // 순열에 들어가는 수 배열을 오름차순 정렬 후 문자열로 만든 것
    static String copy_str; // 순열에 들어가는 수 배열을 문자열로 만든 것



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new char[n];
        arr = br.readLine().replace(" ","").toCharArray();
        copy = Arrays.copyOf(arr, n);
        Arrays.sort(arr); // 오름차순 정렬
        arr_str = new String(arr); // 12345
        copy_str = new String(copy); // 54321
        System.out.println(bfs());
    }

    public static int bfs () {
        Set <String> search = new HashSet<>(); // 방문 여부 체크 > Set 중복 허용 안하기 때문에 똑같은 문자열이 들어올 수 없음
        Queue<Str> queue = new LinkedList<>();
        queue.offer(new Str(copy_str, 0)); // count 0부터 시작
        while(!queue.isEmpty()) {
            Str string = queue.poll();
            if(arr_str.equals(string.str)) return string.count; // 12345와 같다면 오름차순을 만드는데 걸린 횟수를 반환
            // poll()한 문자열이 set 안에 포함되어 있지 않을 경우 set 안에 추가
            if(!search.contains(string.str)) {
                search.add(string.str); // set 안에 54321 추가
                for(int i = 0; i<=n-k; i++) {
                    // k = 3일 때 54321 > 543, 432, 321 : 뒤집을 수 있는 경우는 3가지
                    // 34521, 52341, 54123이 큐로 들어감 > 이 떼 횟수 1
                    // 34521를 poll > set 안에 34512를 추가 > 54321, 32541, 34125가 큐로 들어감 > 이 때 횟수는 2
                    // 52341을 poll > set 안에 52341를 추가 > 32541, 54321, 52143가 큐로 들어감 > 이 떼 횟수는 3
                    // 54123을 poll > set 안에 54123를 추가 > 14523, 52143, 54321가 큐로 들어감 > 이 떼 횟수는 4 ... 반복
                    queue.offer(new Str(reverse(string.str,i, i+k), string.count+1));
                }
            }
        }
        return -1;
    }
    // substring(x, y)만 뒤집히면 된다. 나머지는 그대로 문자열 유지 > substring(x, y)에서 y는 포함 X
    public static String reverse (String str, int x, int y) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0,x));
        String reverse = str.substring(x, y); // k = 3일 때 substring(0,3) substring(1,4), substring(2,5)를 뒤집는다
        for(int i = k-1; i>=0; i--) { // 뒤집기
            sb.append(reverse.charAt(i));
        }
        sb.append(str.substring(y,n));
        return sb.toString();
    }
}

class Str {
    String str;
    int count;
    Str(String str, int count) {
        this.str = str;
        this.count = count;
    }
}