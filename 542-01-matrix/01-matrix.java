class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        
        // initialize
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    mat[i][j] = -1; // mark unvisited 1s
                }
            }
        }
        
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        // BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            
            for (int[] d : dirs) {
                int r = curr[0] + d[0];
                int c = curr[1] + d[1];
                
                if (r >= 0 && c >= 0 && r < rows && c < cols && mat[r][c] == -1) {
                    mat[r][c] = mat[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        
        return mat;
    }
}