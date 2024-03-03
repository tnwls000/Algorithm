import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Stock> stack = new Stack<>();
        stack.push(new Stock(prices[0], 0));
        for (int i=1; i<prices.length; i++) {
            int nowprice = prices[i];
            if (nowprice < stack.peek().price) {
                while (!stack.isEmpty() && stack.peek().price > nowprice) {
                    Stock stock = stack.pop();
                    answer[stock.id] = i - stock.id;
                }
            }
            stack.push(new Stock(nowprice, i));
        }
        
        while (!stack.isEmpty()) {
            Stock stock = stack.pop();
            answer[stock.id] = prices.length - 1 - stock.id;
        }
        
        return answer;
    }
}

class Stock {
    int price;
    int id;
    Stock(int price, int id) {
        this.price = price;
        this.id = id;
    }
}