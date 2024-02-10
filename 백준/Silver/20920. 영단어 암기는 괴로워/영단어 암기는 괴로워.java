import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 자주 나오는 단어일수록 앞에 배치한다.
		// 해당 단어의 길이가 길수록 앞에 배치한다.
		// 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치한다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 영어 지문에 나오는 단어의 개수
		int m = Integer.parseInt(st.nextToken()); // 외울 단어의 길이 기준
		
		Map<String, Integer> map = new HashMap<>();

		// 단어 입력 받기
		for (int i=0; i<n; i++) {
			String tmp = br.readLine();
			if (tmp.length() >= m) { // 단어의 길이가 m 이상인 경우만 고려
				
				if (map.containsKey(tmp)) { // map에 있으면 value+1하고 
					map.put(tmp, map.get(tmp) + 1);
					
				} else { // 없으면 새로 만들어서 put
					map.put(tmp, 1);
				}
			}
		}
		
		List<String> keySet = new ArrayList<>(map.keySet());
		
		keySet.sort((String o1, String o2) -> {
			if (map.get(o2) == map.get(o1)) {
				if (o2.length() == o1.length()) {
					return o1.compareTo(o2); // 3) 사전순 정렬(사전 순은 오름차순)
				}
				
				return o2.length() - o1.length(); // 2) 단어 길이 정렬
			}
			
			return map.get(o2) - map.get(o1); // 1) 단어 빈도수 정렬
		});
		
		for (String s : keySet) {
			sb.append(s).append("\n");
		}
		
		System.out.println(sb);
	}
}