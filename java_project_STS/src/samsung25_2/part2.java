package samsung25_2;
/*
 * 삼성 2025 하반기 오후 2번 문제
 */

import java.io.*;
import java.util.*;

public class part2 {
	static List<Light> lights;
	static long N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int order = Integer.parseInt(st.nextToken());
		N = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int max_lights = M;
		int number_light = M;
		lights = new ArrayList<>(); 
		for(int i = 1; i<= M; i++) {
			lights.add(new Light(Long.parseLong(st.nextToken()),i));
		}
		
		for(int i = 1; i< Q; i++) {
			st = new StringTokenizer(br.readLine());
			order = Integer.parseInt(st.nextToken());
			//가로등 추가
			if(order == 200) {
				max_lights = add_light(max_lights, number_light);
				number_light +=1;				
				
			}
			
			//가로등 제거
			else if(order == 300) {
				//D번 가로등 제거
				if(lights.size() <= 2) continue;
				else {
					int D = Integer.parseInt(st.nextToken());
					max_lights = delete_light(D, lights.size());
				}				
				
				
			}
			//최소 전력 계산, 마을 거리 1~N을 전부 밝히기 위한 최소 소비전력
			else if(order == 400) {
				long r = calc_light();
				sb.append(r).append("\n");								
			}			
			
		}
		
		System.out.println(sb);
	}
	
	static int add_light(int count, int number_light){
		long maxNum = -1;		
		int rear = 1;
		for(int i = 0; i<count-1; i++) {
			long distance = lights.get(i+1).pos - lights.get(i).pos;
			
			if(distance > maxNum) {
				maxNum = distance;
				rear = i+1;
			}
		}
		
		
		long left = lights.get(rear -1).pos;
		long right = lights.get(rear).pos;
		
		long lightPos= left+(right - left +1)/2;
		lights.add(rear, new Light(lightPos, number_light+1) );
			
	
		return count+1;
	}
	
	static int delete_light(int D, int count) {
		for(int i = 0; i<count; i++) {
			if(lights.get(i).code == D) {
				lights.remove(i);
				return count -1;
			}
		}
		
		return count;
	}
	
	static long calc_light() {
		long maxNum = 0;

	    // 시작점부터 첫 가로등까지
	    maxNum = 2 * (lights.get(0).pos - 1);

	    // 인접 가로등 사이
	    for (int i = 1; i < lights.size(); i++) {
	        long distance = lights.get(i).pos
	                      - lights.get(i - 1).pos;

	        maxNum = Math.max(maxNum, distance);
	    }

	    // 마지막 가로등부터 마을 끝까지
	    maxNum = Math.max(
	        maxNum,
	        2 * (N - lights.get(lights.size() - 1).pos)
	    );

	    return maxNum;
	}
	
	static class Light{
		//위치, L
		Long pos;
		//M 고유번호
		int code;
		Light(Long pos, int code){
			this.pos = pos;
			this.code = code;		
		}
	}
}	
