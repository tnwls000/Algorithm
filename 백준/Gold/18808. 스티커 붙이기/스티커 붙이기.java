import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n,m,k,cnt;
	static int[][] laptop;
	static int[][][] stickers;
	
	static boolean checkSticker(int lapR, int lapC, int[][] sticker) {
		for (int r=0; r<sticker.length; r++) {
			for (int c=0; c<sticker[0].length; c++) {
				if (sticker[r][c] == 1 && laptop[lapR+r][lapC+c] == 1) return false;
			}
		}
		return true;
	}
	
	static void addSticker(int lapR, int lapC, int[][] sticker) {
		for (int r=0; r<sticker.length; r++) {
			for (int c=0; c<sticker[0].length; c++) {
				if (sticker[r][c] == 1) {
					laptop[lapR+r][lapC+c] = 1;
					cnt++; // 붙인 칸 개수 세기
				}
			}
		}
	}
	
	static void turnSticker(int k, int[][] sticker) {
		stickers[k] = new int[sticker[0].length][sticker.length];
		for (int r=0; r<sticker.length; r++) {
			for (int c=0; c<sticker[0].length; c++) {
				stickers[k][c][r] = sticker[sticker.length-1-r][c];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		laptop = new int[n][m];
		stickers = new int[k][][];
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			stickers[i] = new int[r][c];
			for (int dr=0; dr<r; dr++) {
				st = new StringTokenizer(br.readLine());
				for (int dc=0; dc<c; dc++) {
					stickers[i][dr][dc] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// 스티커 붙이기
		for (int i=0; i<k; i++) {
			if (cnt == n*m) break; // 노트북에 스티커 붙일 자리 없으면 미리 멈추기
			loop: for (int j=0; j<4; j++) {
				// 이미 스티커 길이가 노트북 길이 넘기면 못붙임
				if (stickers[i].length <= n && stickers[i][0].length <= m) {
					// 붙일 수 있는 지 확인
					for (int r=0; r<=n-stickers[i].length; r++) {
						for (int c=0; c<=m-stickers[i][0].length; c++) {
							if (checkSticker(r,c,stickers[i])) {
								addSticker(r,c,stickers[i]); // 붙일 수 있으면 붙이기
								break loop;
							}
						}
					}
				}
				
				// 못 붙이면 90도 회전
				turnSticker(i, stickers[i]);
			}
		}
		System.out.println(cnt);
	}
}