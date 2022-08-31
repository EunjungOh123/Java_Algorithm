package Practice4_Partition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1992 {
    static int n;
    static int [][] grid;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        String [] str;
        for(int i = 0; i<n; i++) {
            str = br.readLine().split("");
            for(int j = 0; j<n; j++) {
                grid[i][j] = Integer.parseInt(str[j]);
            }
        }
        quadTree(0,0,n);
        System.out.println(sb);
    }
    public static boolean check (int x, int y, int n) {
        for(int i = x; i<x+n; i++) {
            for(int j = y; j<y+n; j++) {
                int start = grid[x][y];
                if(start != grid[i][j]) return false;
            }
        }
        return true;
    }
    public static void quadTree (int x, int y, int n) {
        if(check(x, y, n)) {
            sb.append(grid[x][y]);
        } else {
            int size = n / 2;
            sb.append("(");
            for(int i = x; i<x+n; i+=size) {
                for(int j = y; j<y+n; j+= size) {
                    quadTree(i, j, size);
                }
            }
            sb.append(")");
        }
    }
}
