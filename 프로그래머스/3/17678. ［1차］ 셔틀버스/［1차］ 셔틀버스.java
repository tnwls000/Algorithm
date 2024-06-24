import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] newTimetable = new int[timetable.length];
        for (int i=0; i<timetable.length; i++) {
            StringTokenizer st = new StringTokenizer(timetable[i], ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            newTimetable[i] = hour*60+min;
        }
        Arrays.sort(newTimetable);
        
        List<Integer>[] waiting = new ArrayList[n];
        for (int i=0; i<n; i++) {
            waiting[i] = new ArrayList<>();
        }
        
        int suttle = 540;
        int idx = 0;
        for (int i=0; i<n; i++) {
            if (idx == newTimetable.length) break;
            while (idx < newTimetable.length && suttle >= newTimetable[idx]) {
                waiting[i].add(newTimetable[idx]);
                idx++;
                if (waiting[i].size() == m) break;
            }
            
            suttle += t;
        }
        
        int answer = 0;
        if (waiting[n-1].size() == m) answer = waiting[n-1].get(waiting[n-1].size() - 1) - 1;
        else answer = 540+(n-1)*t;
        
        return ((answer/60 < 10) ? "0" : "") + answer/60 + ":" + ((answer%60 < 10) ? "0" : "") + answer%60;
    }
}