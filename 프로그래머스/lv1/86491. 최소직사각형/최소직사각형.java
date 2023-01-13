class Solution {
    public int solution(int[][] sizes) {
        int max = 0;
        boolean left = false;
        boolean right = false;
        for (int i=0; i<sizes.length; i++) {
            if (max <= sizes[i][0]) {
                max = sizes[i][0];
                left = true;
                right = false;
            } 
            if (max <= sizes[i][1]) {
                max = sizes[i][1];
                left = false;
                right = true;
            }
        }
        
        for (int i=0; i<sizes.length; i++) {
            if (left) {
                int s = sizes[i][0];
                if (sizes[i][0] < sizes[i][1]) {
                    sizes[i][1] = sizes[i][0];
                    sizes[i][0] = s;
                }
            }
            else if (right) {
                int s = sizes[i][1];
                if (sizes[i][1] < sizes[i][0]) {
                    sizes[i][0] = sizes[i][1];
                    sizes[i][1] = s;
                }
            }
        }
        
        int secondMax = 0;
        for (int i=0; i<sizes.length; i++) {
            if (left) {
                secondMax = Math.max(secondMax, sizes[i][1]);
            }
            else if (right) {
                secondMax = Math.max(secondMax, sizes[i][0]);
            }
        }
        
        return max * secondMax;
    }
}