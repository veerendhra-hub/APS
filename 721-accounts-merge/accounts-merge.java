class Solution {
    public java.util.List<java.util.List<String>> accountsMerge(java.util.List<java.util.List<String>> accounts) {
        java.util.Map<String, String> parent = new java.util.HashMap<>();
        java.util.Map<String, String> owner = new java.util.HashMap<>();

        // Initialize
        for (java.util.List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                parent.put(acc.get(i), acc.get(i));
                owner.put(acc.get(i), name);
            }
        }

        // Union
        for (java.util.List<String> acc : accounts) {
            String first = acc.get(1);
            for (int i = 2; i < acc.size(); i++) {
                union(parent, first, acc.get(i));
            }
        }

        // Group emails
        java.util.Map<String, java.util.TreeSet<String>> groups = new java.util.HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            groups.computeIfAbsent(root, k -> new java.util.TreeSet<>()).add(email);
        }

        // Build result
        java.util.List<java.util.List<String>> result = new java.util.ArrayList<>();
        for (String root : groups.keySet()) {
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add(owner.get(root));
            list.addAll(groups.get(root));
            result.add(list);
        }

        return result;
    }

    private String find(java.util.Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x)));
        }
        return parent.get(x);
    }

    private void union(java.util.Map<String, String> parent, String x, String y) {
        String rootX = find(parent, x);
        String rootY = find(parent, y);
        if (!rootX.equals(rootY)) {
            parent.put(rootY, rootX);
        }
    }
}