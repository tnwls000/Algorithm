import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int r,c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int n,m,min;
    static int[][] map;
    static Point[] choicedHospitals;
    static List<Point> hospitals, people;

    static void choiceHospitals(int idx, int cnt) {
        if (cnt == m) {
            min = Math.min(min, calcDistance());
            return;
        }

        for (int i=idx; i<hospitals.size(); i++) {
            choicedHospitals[cnt] = hospitals.get(i);
            choiceHospitals(i+1,cnt+1);
        }
    }

    static int calcDistance() {
        int result = 0;
        for (int i=0; i<people.size(); i++) {
            Point person = people.get(i);
            int minDis = Integer.MAX_VALUE;
            for (int j=0; j<choicedHospitals.length; j++) {
                Point hospital = choicedHospitals[j];
                int calcDis = Math.abs(person.r - hospital.r) + Math.abs(person.c - hospital.c);
                minDis = Math.min(minDis, calcDis);
            }
            result += minDis;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        hospitals = new ArrayList<>();
        people = new ArrayList<>();
        choicedHospitals = new Point[m];
        for (int r=0; r<n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<n; c++) {
                int num = Integer.parseInt(st.nextToken());
                map[r][c] = num;
                if (num == 1) people.add(new Point(r,c));
                if (num == 2) hospitals.add(new Point(r,c));
            }
        }

        min = Integer.MAX_VALUE;
        choiceHospitals(0,0);
        System.out.println(min);
    }
}