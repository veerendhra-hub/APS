class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        java.util.List<java.util.List<Integer>> graph = new java.util.ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new java.util.ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        
        queue.offer(source);
        visited[source] = true;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            if (node == destination) return true;
            
            for (int nei : graph.get(node)) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    queue.offer(nei);
                }
            }
        }
        
        return false;
    }
}