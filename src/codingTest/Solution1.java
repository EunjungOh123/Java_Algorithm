package codingTest;

public class Solution1 {
    static int [][] grid;
    static StringBuilder sb = new StringBuilder();

    public static String solution(int[][] img) {
        grid = img;
        quadTree(0,0, img.length);
        System.out.println(sb);
        String answer = sb.toString();
        return answer;
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

    public static void main(String[] args) {
        int [][] img = {{0, 0, 0, 0, 1, 1, 1, 1},{0, 0, 0, 0, 1, 1, 0, 0},{0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1}};
        System.out.println(solution(img));
    }
}
