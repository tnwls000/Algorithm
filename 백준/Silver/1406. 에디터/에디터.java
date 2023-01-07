import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        String str = br.readLine(); //편집기에 입력되어 있는 문자열
		int m = Integer.parseInt(br.readLine());; //입력할 명령어의 개수
		
		LinkedList<Character> list = new LinkedList<>();
		for (int i=0; i<str.length(); i++) {
			list.add(str.charAt(i));
		}
		
		ListIterator<Character> iter = list.listIterator();
		while(iter.hasNext()) iter.next();
		
		for (int i=0; i<m; i++) {
			String input = br.readLine();
			char c = input.charAt(0);
			switch(c) {
				case 'L':
					//커서를 왼쪽으로 한 칸 옮김
					if (iter.hasPrevious()) iter.previous();
					break;
				case 'D':
					//커서를 오른쪽으로 한 칸 옮김
					if (iter.hasNext()) iter.next();
					break;
				case 'B':
					//커서 왼쪽에 있는 문장 삭제
					if (iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
					break;
				default :
					//문자를 커서 왼쪽에 추가
					char t = input.charAt(2);
					iter.add(t);
					break;
			}
		}
		for (Character chr : list) {
			bw.write(chr);
		}
		bw.flush();
		bw.close();
    }
}