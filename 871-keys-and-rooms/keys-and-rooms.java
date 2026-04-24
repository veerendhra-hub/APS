class Solution {
    public boolean canVisitAllRooms(java.util.List<java.util.List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        stack.push(0);
        visited[0] = true;
        
        while (!stack.isEmpty()) {
            int room = stack.pop();
            
            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    stack.push(key);
                }
            }
        }
        
        for (boolean v : visited) {
            if (!v) return false;
        }
        
        return true;
    }
}