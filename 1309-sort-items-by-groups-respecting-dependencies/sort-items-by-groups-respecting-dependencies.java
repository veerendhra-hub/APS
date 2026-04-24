class Solution {
    public int[] sortItems(int n, int m, int[] group, java.util.List<java.util.List<Integer>> beforeItems) {
        // assign unique group to ungrouped items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        java.util.List<java.util.List<Integer>> itemGraph = new java.util.ArrayList<>();
        java.util.List<java.util.List<Integer>> groupGraph = new java.util.ArrayList<>();
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) itemGraph.add(new java.util.ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new java.util.ArrayList<>());

        // build graphs
        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;
                
                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        java.util.List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        java.util.List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        if (itemOrder.size() == 0 || groupOrder.size() == 0) {
            return new int[0];
        }

        java.util.Map<Integer, java.util.List<Integer>> map = new java.util.HashMap<>();
        for (int g : groupOrder) {
            map.put(g, new java.util.ArrayList<>());
        }

        for (int item : itemOrder) {
            map.get(group[item]).add(item);
        }

        int[] result = new int[n];
        int index = 0;

        for (int g : groupOrder) {
            for (int item : map.get(g)) {
                result[index++] = item;
            }
        }

        return result;
    }

    private java.util.List<Integer> topoSort(java.util.List<java.util.List<Integer>> graph, int[] indegree, int size) {
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        java.util.List<Integer> result = new java.util.ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int nei : graph.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }

        if (result.size() == size) return result;
        return new java.util.ArrayList<>();
    }
}