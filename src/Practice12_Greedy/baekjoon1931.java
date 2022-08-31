package Practice12_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baekjoon1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Conference> list = new ArrayList<>(); // 회의 시작 시간과 끝나는 시간의 정보를 담은 리스트
        StringTokenizer st;
        for(int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Conference(s, e));
        }
        // 두 회의의 종료 시간이 같다면, 시작 시간이 빠른 회의가 앞에 오도록 정렬해야 한다
        Collections.sort(list, new Comparator<Conference>() {
            @Override
            public int compare(Conference o1, Conference o2) {
                if(o1.finish == o2.finish) {
                    return o1.start-o2.start;
                }
                return o1.finish-o2.finish; // 끝나는 시간 오름차순 정렬
            }
        });
        int count = 0;
        int finish = 0;
        for(int i = 0; i<list.size(); i++) {
            if(list.get(i).start >= finish) {
                count++;
                finish = list.get(i).finish;
                continue;
            }
        }
        System.out.println(count);
    }
}

class Conference{
    int start;
    int finish;
    Conference(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}