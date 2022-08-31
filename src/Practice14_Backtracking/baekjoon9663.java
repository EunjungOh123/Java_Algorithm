package Practice14_Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon9663 {
    static int n;
    static int [] chess;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        chess = new int[n];
        System.out.println(nQueen(0));
    }

    public static int nQueen (int row) {
        if(row == n) { // 종료 조건 (퀸을 다 놓은 경우)
            count++;
            return count;
        }
        for(int i = 0; i<n; i++) {
            chess[row] = i;
            if(isPromising(row)) {
                nQueen(row+1);
            }
        }
        return count;
    }

    public static boolean isPromising (int row) {
        for(int i = 0; i<row; i++) {
            if(chess[i] == chess[row] || Math.abs(chess[row]-chess[i]) == row-i) { // 퀸이 같은 열에 존재하는 경우 or 퀸이 대각선 상에 존재하는 경우
                return false;
            }
        }
        return true;
    }
}
