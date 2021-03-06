/**
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:

Input: m = 7, n = 3
Output: 28

**/

public class uniqure_paths {
    public static int uniquePaths(int m, int n) {
        int[][] map = new int[m][n];
        
        // 边界处都只有一种路线，所有都初始化为1
        for (int i = 0; i < m; i++) {
            map[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            map[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
    }
}
