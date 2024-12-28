import java.util.*;

class Solution {
    static class Car {
        int weight, time;
        Car(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 1;
        int currW = truck_weights[0];
        int truckIdx = 1;
        Queue<Car> q = new LinkedList<>();
        
        q.add(new Car(truck_weights[0], 1));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                Car curr = q.poll();
                if (curr.time == bridge_length) {
                    currW -= curr.weight;
                    continue;
                }
                curr.time++;
                q.add(curr);
            }
            
            time++;
            if (q.size() == bridge_length) continue;
            if (truckIdx == truck_weights.length) continue;
            
            int currTruckW = truck_weights[truckIdx];
            if (currW + currTruckW > weight) continue;
            q.add(new Car(currTruckW, 1));
            currW += currTruckW;
            truckIdx++;
        }
        
        return time;
    }
}