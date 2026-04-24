class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        java.util.List<int[]>[] graph = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new java.util.ArrayList<>();
        }

        // color: 0 = red, 1 = blue
        for (int[] e : redEdges) {
            graph[e[0]].add(new int[]{e[1], 0});
        }
        for (int[] e : blueEdges) {
            graph[e[0]].add(new int[]{e[1], 1});
        }

        int[][] dist = new int[n][2];
        for (int i = 0; i < n; i++) {
            dist[i][0] = dist[i][1] = Integer.MAX_VALUE;
        }

        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.offer(new int[]{0, 0}); // node, color
        queue.offer(new int[]{0, 1});

        dist[0][0] = dist[0][1] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], color = curr[1];

            for (int[] nei : graph[node]) {
                int next = nei[0], nextColor = nei[1];

                if (nextColor != color && dist[next][nextColor] == Integer.MAX_VALUE) {
                    dist[next][nextColor] = dist[node][color] + 1;
                    queue.offer(new int[]{next, nextColor});
                }
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int d = Math.min(dist[i][0], dist[i][1]);
            result[i] = (d == Integer.MAX_VALUE) ? -1 : d;
        }

        return result;
    }
}