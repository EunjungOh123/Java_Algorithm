package Practice3_Floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjoon13168 {

    static int[][] trip; // 내일로 티켓을 구매하지 않는 경우의 최단 경로
    static double[][] ticket; // 내일로 티켓을 구매한 경우의 최단 경로
    static int INF = 2000_0001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 한국에 있는 도시의 수
        int cost = Integer.parseInt(st.nextToken()); // 내일로 티켓 가격
        Map<String, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            map.put(st.nextToken(), i + 1);
        }
        int m = Integer.parseInt(br.readLine()); // 여행할 도시의 수
        String [] route = new String[m];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<m; i++) {
            route[i] = st.nextToken(); // 여행할 도시들을 담는 배열
        }
        trip = new int[n + 1][n + 1];
        ticket = new double[n+1][n+1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j) { // 자기 자신으로 가는 최단 경로는 0
                    trip[i][j] = 0;
                    ticket[i][j] = 0;
                } else {
                    trip[i][j] = INF;
                    ticket[i][j] = INF;
                }
            }
        }

        int k = Integer.parseInt(br.readLine()); // 교통수단의 수

        // 중복되는 경로가 존재할 수 있는 경우까지 고려 > 더 적은 비용이 드는 경우 선택
        // 두 도시 사이를 오갈 수 있으므로 양방향
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String type = st.nextToken();
            String start = st.nextToken();
            String end = st.nextToken();
            int money = Integer.parseInt(st.nextToken());
            trip[map.get(start)][map.get(end)] = Math.min(money, trip[map.get(start)][map.get(end)]);
            trip[map.get(end)][map.get(start)] = Math.min(money, trip[map.get(end)][map.get(start)]);
            if (type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun") || type.equals("Mugunghwa")) {
                ticket[map.get(start)][map.get(end)] = 0;
                ticket[map.get(end)][map.get(start)] = 0;
            } else if (type.equals("S-Train") || type.equals("V-Train")) {
                ticket[map.get(start)][map.get(end)] = Math.min(ticket[map.get(start)][map.get(end)], money * 0.5);
                ticket[map.get(end)][map.get(start)] = Math.min(ticket[map.get(end)][map.get(start)], money * 0.5);

            } else {
                ticket[map.get(start)][map.get(end)] = Math.min(money, ticket[map.get(start)][map.get(end)]);
                ticket[map.get(end)][map.get(start)] = Math.min(money, ticket[map.get(end)][map.get(start)]);
            }
        }
        // 플로이드 워셜 알고리즘 사용
        for (int h = 1; h < n + 1; h++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (i == j) continue;
                    trip[i][j] = Math.min(trip[i][h] + trip[h][j], trip[i][j]);
                    ticket[i][j] = Math.min(ticket[i][h] + ticket[h][j], ticket[i][j]);
                }
            }
        }

        int tripSum = 0; // 내일로 티켓을 구매하지 않는 경우
        double ticketSum = 0; // 내일로 티켓을 구매하는 경우
        for(int i = 0; i< route.length-1; i++) { // 각각의 여행할 도시 간 최단 경로의 합
            tripSum += trip[map.get(route[i])][map.get(route[i+1])];
            ticketSum += ticket[map.get(route[i])][map.get(route[i+1])];
        }

        ticketSum = ticketSum+cost; // 내일로 티켓의 가격까지 더해준다

        if(ticketSum < tripSum) { // 내일로 티켓을 사는 경우가 더 저렴한 경우
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}