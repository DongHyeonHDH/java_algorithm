package N7206;
import java.io.*;
import java.util.*;

public class N7206 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	
		
		for(int tc =1; tc<=T; tc++){		
			int answer = bfs(br.readLine());
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n")
			;
		}
		System.out.println(sb);
	}
	static int bfs(String number) {

	    int maxNum = 0;

	    Queue<StNode> q = new ArrayDeque<>();
	    Map<String, Integer> visited = new HashMap<>();

	    q.add(new StNode(number, 0));
	    visited.put(number, 0);

	    while (!q.isEmpty()) {

	        StNode cur = q.poll();

	        // 이미 더 깊은 경로로 같은 숫자를 발견했다면
	        // 현재 노드는 탐색할 필요 없음
	        if (visited.get(cur.str) > cur.depth) {
	            continue;
	        }

	        if (cur.str.length() == 1) {
	            maxNum = Math.max(maxNum, cur.depth);
	            continue;
	        }

	        List<StNode> next = splitNumber(cur);

	        for (StNode node : next) {

	            Integer oldDepth = visited.get(node.str);

	            // 처음 발견했거나
	            // 기존보다 더 깊은 경로로 발견했다면
	            if (oldDepth == null || node.depth > oldDepth) {

	                visited.put(node.str, node.depth);
	                q.add(node);
	            }
	        }
	    }

	    return maxNum;
	}
	
	static List<StNode> splitNumber(StNode stnode) {

	    Set<Integer> result = new HashSet<>();

	    boolean[] cut =
	            new boolean[stnode.str.length() - 1];

	    dfs(stnode.str, 0, cut, 0, result);

	    List<StNode> list = new ArrayList<>();

	    for (int num : result) {
	        list.add(
	            new StNode(
	                String.valueOf(num),
	                stnode.depth + 1
	            )
	        );
	    }

	    return list;
	}
	
	static void dfs(String number, int idx, boolean[] cut,
        int cutCount, Set<Integer> result) {

		if (idx == number.length() - 1) {
		
		    if (cutCount == 0) return;
		
		    int product = 1;
		    int start = 0;
		
		    for (int i = 0; i < cut.length; i++) {
		
		        if (cut[i]) {
		            product *= Integer.parseInt(
		                    number.substring(start, i + 1)
		            );
		
		            start = i + 1;
		        }
		    }
		
		    product *= Integer.parseInt(number.substring(start));
		
		    result.add(product);
		    return;
		}
		
		// 안 자름
		cut[idx] = false;
		dfs(number, idx + 1, cut, cutCount, result);
		
		// 자름
		cut[idx] = true;
		dfs(number, idx + 1, cut, cutCount + 1, result);
		
	}	
	
	static class StNode{
		String str;
		int depth;
		
		StNode(String str, int depth){
			this.str = str;
			this.depth = depth;
		}
	}
}
