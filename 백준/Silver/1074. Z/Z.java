import java.util.Scanner;

class Main {
	static int result = 0;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int pow = (int) Math.pow(2, n);
		sequence(r, c, pow);
        System.out.println(result);
	}

	private static void sequence(int r, int c, int size) {
		if (size == 1) return;
		
        //1사분면
		if (r < size/2 && c < size/2) {
            sequence(r, c, size/2);
        //2사분면
        } else if (r < size/2 && c >= size/2) {
            result += size * size / 4;
            sequence(r, c - size/2, size/2);
        //3사분면
        } else if (r >= size/2 && c < size/2) {
            result += (size * size / 4) * 2;
            sequence(r - size/2, c, size/2);
        //4사분면
        } else {
            result += (size * size / 4) * 3;
            sequence(r - size/2, c - size/2, size/2);
        }
	}
}	