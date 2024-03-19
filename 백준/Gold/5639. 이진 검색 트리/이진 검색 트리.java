import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
	int parent;
	int left;
	int right;
	Node(int parent) {
		this.parent = parent;
	}
}

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static Node[] tree;
	
	static void postOrder(int num) {
		if (tree[num] == null) return;
		
		postOrder(tree[num].left);
		postOrder(tree[num].right);
		sb.append(num).append("\n");
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tree = new Node[1000001];
		
		int root = Integer.parseInt(br.readLine()); // 루트 노드 하나만 먼저 받기
		tree[root] = new Node(-1); // 해당 값 아닌 parent 넣음
		int now = root;
		try {
			while (true) {
				int num = Integer.parseInt(br.readLine());
				// 현재 노드(now)보다 입력 받은 값(num)이 작으면 -> 왼쪽 노드
				// 현재 노드보다 입력 받은 값이 크면 -> 오른쪽 노드
				if (now > num) {
					
					while (tree[now].right != 0 && tree[now].right > num) {
						now = tree[now].right;
					}
					tree[now].left = num;
					
				} else {
					
					while (tree[now].parent != -1 && tree[now].parent < num) {
						now = tree[now].parent;
					}
					
					while (tree[now].right != 0) now = tree[now].right;
					
					tree[now].right = num;
					
				}
				
				
				tree[num] = new Node(now);
				
				now = num;
				if (now == root) now = tree[now].right; // root에서 오른쪽으로 분기
				
			}
			
			
		} catch (Exception e) {
			postOrder(root);
			System.out.println(sb);
			return;
		}
	}
}