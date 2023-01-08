import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //피연산자의 개수(1≤N≤26)
		String str = sc.next(); //후위표기식
		double[] arr = new double[n]; //각 피연산자의 대응값(1≤arr[i]≤100)
		for (int i=0; i<n; i++) {
			arr[i] = sc.nextDouble();
		}
		Stack<Double> st = new Stack<>();
		
		//1.피연산자->대응값 stack에 push
		//2.사칙연산자-> 
		//stack에 값2개 pop해서 사칙연산자로 계산 후, 그 값을 다시 stack에 push
		//3.마지막에 남아 있는 stack의 값이 결과값
		for (int i=0; i<str.length(); i++) {
			char value = str.charAt(i);
			double second = 0;
			double first = 0;
			switch(value) {
				case '*':
					second = st.pop();
					first = st.pop();
					st.push(first * second);
					break;
				case '/':
					second = st.pop();
					first = st.pop();
					st.push(first / second);
					break;
				case '+':
					second = st.pop();
					first = st.pop();
					st.push(first + second);
					break;
				case '-':
					second = st.pop();
					first = st.pop();
					st.push(first - second);
					break;
				default :
					double count = arr[value-'A'];
					st.push(count);
					break;
			}
		}
		System.out.printf("%.2f", st.pop());
    }
}