class Solution {
    public int solution(int[] stones, int k) {
        int answer = findSpot(stones, 0, 200000001, k);
        return (canCross(stones, answer, k)) ? answer : answer - 1;
    }
    
    int findSpot(int[] arr, int l, int r, int k) {
        if (l >= r) return l;
        
        int mid = (l+r)/2;
        if (canCross(arr, mid, k)) {
            return findSpot(arr, mid+1, r, k);
        } else return findSpot(arr, l, mid-1, k);
    }
    
    boolean canCross(int[] arr, int mid, int k) {
        int zeroCnt = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] - (mid-1) <= 0) {
                zeroCnt++;
                if (zeroCnt >= k) return false;
            } else zeroCnt = 0;
        }
        return true;
    }
}