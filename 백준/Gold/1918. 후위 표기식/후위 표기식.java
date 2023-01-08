import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Stack<Character> st = new Stack<>();
		
		//1.피연산자는 바로 출력
		//2.사칙연산자일 경우
		//- stack이 비어있으면 push
		//- tail의 사칙연산자보다 우선순위가 낮을 경우 tail을 pop하고 해당값을 push
		//- tail의 사칙연산자보다 우선순위가 높을 경우 stack에 push
		//- tail이 열린 괄호일 경우 stack에 바로 push
		//3. 열린 괄호일 경우 stack에 push
		//4. 닫힌 괄호일 경우 stack에 열린 괄호가 나올 때까지 pop하고 열린 괄호는 버리기
		for (int i=0; i<str.length(); i++) {
			char value = str.charAt(i);
			switch(value) {
				case '(':
					st.push(value);
					break;
				case ')':
					while (st.peek() != '(') {
						System.out.print(st.pop());
					}
					st.pop();
					break;
				case '+': case '-':
					if (st.empty()) st.push(value);
					else if (st.peek() == '*' || st.peek() == '/') {
						while (!st.empty()) {
							if (st.peek() == '(') break;
							System.out.print(st.pop());
						}
						st.push(value);
					} else if (st.peek() == '+' || st.peek() == '-') {
						System.out.print(st.pop());
						st.push(value);
					} else st.push(value);
					break;
				case '*': case '/':
					if (st.empty()) st.push(value);
					else if (st.peek() == '*' || st.peek() == '/') {
						System.out.print(st.pop());
						st.push(value);
					} else st.push(value);
					break;
				default :
					System.out.print(value);
					break;
			}
		}
		while (!st.empty()) {
			System.out.print(st.pop());
		}
    }
}