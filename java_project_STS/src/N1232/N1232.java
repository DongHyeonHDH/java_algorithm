package N1232;
import java.io.*;
import java.util.*;
public class N1232 {
	
	static Node[] tree;	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t =1; t<=10; t++) {			
			int N = Integer.parseInt(br.readLine());
			tree = new Node[N+1];
			for(int i =1; i<=N; i++) {				
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int idx = Integer.parseInt(st.nextToken());
				String op = st.nextToken();
				if(st.countTokens() == 0) {
					int num = Integer.parseInt(op);
					tree[idx] = new Node(idx,num);
				}
				else {
					int left = Integer.parseInt(st.nextToken());
					int right = Integer.parseInt(st.nextToken());					
					tree[idx] = new Node(idx, op.charAt(0), left, right);
				}					
				
			}
			double res = calculate(1);
			sb.append('#')
				.append(t)
				.append(" ")
				.append((int)res)
				.append('\n')
				;			
			
		}
		
		System.out.println(sb);
	}
	
	static double calculate(int idx) {
		Node node  = tree[idx];
		if(node.isNum) {
			return node.num;
		}
		
		double left = calculate(node.left);
		double right = calculate(node.right);
		
		
		switch(node.operator) {
				case '+': return left + right;
				case '*': return left * right;
				case '-': return left - right;
				case '/': return left / right;
			}	
								
			
		return 0;
	}
	
	static class Node{
		boolean isNum;
		int idx;
		int num;
		char operator;
		int left;
		int right;
		
		Node(int idx, int num){
			this.isNum = true;
			this.idx = idx;
			this.num = num;
		}
		
		Node(int idx, char operator, int left, int right){
			this.isNum = false;
			this.idx = idx;
			this.operator = operator;
			this.left = left;
			this.right = right;
		}
	}	
}
