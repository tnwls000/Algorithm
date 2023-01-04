import java.util.*;


class Main {
	static int n;
	static String paper[][], result = "";
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		paper = new String[n][n];
		String[] input = new String[n];
		for (int i=0; i<n; i++) {
			input[i] = sc.nextLine();
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				paper[i][j] = input[i].substring(j, j+1);
			}
		}
		
		countPaper(0, 0, n);
		System.out.println(result);
	}

	private static void countPaper(int r, int c, int size) {
		if (check(r, c, size)) {
			String str = paper[r][c];
			if (str.equals("0")) result += "0";
			else if (str.equals("1")) result += "1";
			return;
		}
		
		int newSize = size/2;
		result += "(";
		for (int i=r; i<r+size; i+=newSize) {
			for (int j=c; j<c+size; j+=newSize) {
				countPaper(i, j, newSize);
			}
		}
		result += ")";
	}
	
	private static boolean check(int r, int c, int size) {
		for (int i=r; i<r+size; i++) {
			for (int j=c; j<c+size; j++) {
				if (!paper[r][c].equals(paper[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
}