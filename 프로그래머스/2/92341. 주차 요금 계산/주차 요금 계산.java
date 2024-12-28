import java.util.*;

class Solution {
    static int basicMin;
    static int basicFee;
    static double unitMin;
    static int unitFee;
    public int[] solution(int[] fees, String[] records) {
        basicMin = fees[0];
        basicFee = fees[1];
        unitMin = Double.valueOf(fees[2]);
        unitFee = fees[3];
        
        Map<Integer, Integer> timeMap = new HashMap<>();
        Map<Integer, Integer> feeMap = new HashMap<>();
        
        for (int i=0; i<records.length; i++) {
            String[] record = records[i].split(" ");
            
            String[] time = record[0].split(":");
            int min = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            int number = Integer.parseInt(record[1]);
            String InOrOut = record[2];
            
            // 입차 시 넣기
            if (InOrOut.equals("IN")) {
                timeMap.put(number, min);
            // 출차 시 빼고 계산
            } else {
                int omin = min - timeMap.get(number);
                feeMap.put(number, feeMap.getOrDefault(number,0) + omin);
                timeMap.remove(number);
            }
        }
        
        if (!timeMap.isEmpty()) {
            for (Integer number : timeMap.keySet()) {
                int out = 24 * 60 - 1;
                int min = out - timeMap.get(number);
                feeMap.put(number, feeMap.getOrDefault(number, 0) + min);
            }
        }
        
        List<Integer> carList = new ArrayList<>(feeMap.keySet());
        Collections.sort(carList);
        
        List<Integer> answer = new ArrayList<>();
        for (Integer car : carList) {
            int min = feeMap.get(car);
            int fee = 0;
            if (basicMin >= min) fee = basicFee;
            else fee = basicFee + (int) Math.ceil((min - basicMin)/unitMin) * unitFee;
            answer.add(fee);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}