import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class P {
	int weight;
	int height;
	int originIdx; // 원래 인덱스
	int bigger; // 자신보다 큰 사람의 수
	int rank; // 자신의 순위
	
	P(int weight, int height, int originIdx) {
		this.weight = weight;
		this.height = height;
		this.originIdx = originIdx;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine()); // 전체 사람의 수
		P[] people = new P[n]; // 사람 클래스 따로 만듦
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 사람 몸무게, 키 정보 입력 받기
			people[i] = new P(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		// 문제 잘 읽기........^^
		
		// 덩치는 자신보다 큰 사람의 수로 정해진다
		// p1, p2, p3가 각각 자신보다 큰 사람의 수가 0명, 0명, 1명이라면
		// 각각 1등,1등,2등이 된다
		// 자신보다 더 큰 덩치의 사람이 k명이라면 그 사람의 덩치 등수는 k+1
		// 1. 배열 완전탐색 N^2. 몸무게, 키 둘다 큰 사람이면 bigger++
		// 2. bigger기준으로 정렬(순서대로 순위매김 앞사람이랑 bigger같으면 같은 등수 & same++)
		// 3. originIdx로 재정렬 후 for문 돌면서 rank 출력
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				if (i==j) continue;
				if (people[i].weight > people[j].weight
						&& people[i].height > people[j].height) { // 배열 완전탐색 N^2. 몸무게, 키 둘다 큰 사람이면 bigger++
					people[j].bigger++;
				} else if (people[i].weight < people[j].weight
						&& people[i].height < people[j].height) {
					people[i].bigger++;
				}
			}
		}
		
		for (P p : people) {
			p.rank = p.bigger + 1; // 자신보다 더 큰 덩치의 사람이 k명이라면 그 사람의 덩치 등수는 k+1
		}
		
		Arrays.sort(people, (P p1, P p2) -> {
			return p1.originIdx - p2.originIdx; // 원래 인덱스(originIdx) 순으로 재정렬
		});
		
		for (P p : people) {
			bw.write(String.valueOf(p.rank) + " "); // rank 출력
		}
		bw.flush();
		bw.close();
	}
}