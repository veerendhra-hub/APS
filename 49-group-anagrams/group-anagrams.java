class Solution {
    public java.util.List<java.util.List<String>> groupAnagrams(String[] strs) {
        java.util.Map<String, java.util.List<String>> map = new java.util.HashMap<>();
        
        for (String s : strs) {
            char[] arr = s.toCharArray();
            java.util.Arrays.sort(arr);
            String key = new String(arr);
            
            map.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(s);
        }
        
        return new java.util.ArrayList<>(map.values());
    }
}