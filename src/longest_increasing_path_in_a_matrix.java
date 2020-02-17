public class longest_increasing_path_in_a_matrix {
    // dfs + memorization, 避免重复搜索
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public int longestIncreasingPath(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];

        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int max = dfs(matrix, i, j, m, n, memo);
                res = Math.max(max, res);
            }
        }
        return res;
    }

    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] memo) {
        // 已经被访问过了
        if (memo[i][j] != 0) return memo[i][j];

        int max = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k], y = j +dy[k];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(matrix, x, y, m, n, memo);
            max = Math.max(max, len);
        }
        // i, j存的是能取到的最长的递增路径长度
        memo[i][j] = max;
        return max;
    }
}