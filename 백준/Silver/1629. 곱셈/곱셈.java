import java.util.*;


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();
		long c = sc.nextLong();
		
		System.out.println(power(a,b,c));
	}
	
	private static long power(long a, long b, long c) {
		if (b==0) {
			return 1;
		}
		long temp = power(a, b/2, c);
		if (b%2 == 0) {
			return temp * temp % c;
		} else {
			return (temp * temp %c) * a %c;
		}
	}
}	