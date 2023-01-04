import java.util.*;

class Main {
	static int n;
	static char star[][];
	static int count = 0;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		star = new char[n][n];
		
		order(0, 0, n);
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (star[i][j] == 0) {
					star[i][j] = ' ';
				}
			}
		}
		
        for (int i=0; i<n; i++) {
			System.out.println(star[i]);
		}
	}

	private static void order(int r, int c, int size) {
		if (size == 1) {
			star[r][c] = '*';
			return;
		}
		
		
        int newSize = size/3;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (!(i==1 && j==1)) {
					order(r + i*newSize, c + j*newSize, newSize);
				}
			}
		}
	}
}