import java.util.*;

public class Main {
    static boolean[] visited;
    static int[][] partyArr;
    static List<Integer>[] attendPartyList;

    static void spreadTruth(int know) {
        for (int party : attendPartyList[know]) {
            if (visited[party]) continue;
            visited[party] = true;

            for (int person : partyArr[party]) {
                spreadTruth(person);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        attendPartyList = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            attendPartyList[i] = new ArrayList<>();
        }

        int knowCnt = sc.nextInt();
        List<Integer> knowList = new ArrayList<>();
        for (int i=0; i<knowCnt; i++) {
            knowList.add(sc.nextInt()); // 진실을 아는 사람
        }

        partyArr = new int[m][];
        for (int i=0; i<m; i++) {
            int partyCnt = sc.nextInt();
            partyArr[i] = new int[partyCnt];

            for (int j=0; j<partyCnt; j++) {
                partyArr[i][j] = sc.nextInt();
                attendPartyList[partyArr[i][j]].add(i);
            }
        }

        visited = new boolean[m];
        for (int know : knowList) spreadTruth(know);

        int cnt  = 0;
        for (boolean visit : visited) if (!visit) cnt++;
        System.out.println(cnt);
    }
}