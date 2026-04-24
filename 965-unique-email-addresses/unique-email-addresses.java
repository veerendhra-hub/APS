class Solution {
    public int numUniqueEmails(String[] emails) {
        java.util.Set<String> set = new java.util.HashSet<>();
        
        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];
            
            // remove everything after '+'
            int plusIndex = local.indexOf('+');
            if (plusIndex != -1) {
                local = local.substring(0, plusIndex);
            }
            
            // remove all dots
            local = local.replace(".", "");
            
            set.add(local + "@" + domain);
        }
        
        return set.size();
    }
}