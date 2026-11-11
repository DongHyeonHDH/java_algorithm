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
		lights = new LinkedList<>(); 
		for(int i = 1; i<= M; i++) {
			lights.add(new Light(Long.parseLong(st.nextToken()),i));
		}
		
		for(int i = 1; i< Q; i++) {
			st = new StringTokenizer(br.readLine());
			order = Integer.parseInt(st.nextToken());
			//가로등 추가
			if(order == 200) {
				max_lights = add_light(max_lights);
				 
				for(int j = 0; j< max_lights; j++) {
					System.out.print(lights.get(j).pos+ " ");
				}
				System.out.println();
				
				System.out.println("--------------");
				
				for(int j = 0; j< max_lights; j++) {
					System.out.print(lights.get(j).code+ " ");
				}
				System.out.println();
			}
			
			//가로등 제거
			else if(order == 300) {
				//D번 가로등 제거
				if(lights.size() <= 2) continue;
				else {
					int D = Integer.parseInt(st.nextToken());
					max_lights = delete_light(D, max_lights);
				}					
				
				for(int j = 0; j< max_lights; j++) {
					System.out.print(lights.get(j).pos+ " ");
				}
				System.out.println();
				
				System.out.println("--------------");
				
				for(int j = 0; j< max_lights; j++) {
					System.out.print(lights.get(j).code+ " ");
				}
				System.out.println();
			}
			//최소 전력 계산, 마을 거리 1~N을 전부 밝히기 위한 최소 소비전력
			else if(order == 400) {
				long r = calc_light();
				sb.append(r).append("\n");								
			}			
			
		}
		System.out.println("-----------");
		System.out.println(sb);
	}
	
	static int add_light(int count){
		long maxNum = 0;
		long lightPos = 0;
		int front = 0;
		int rear = 0;
		for(int i = 0; i<count-1; i++) {
			maxNum = Math.max(Math.abs(lights.get(i+1).pos - lights.get(i).pos), maxNum);
			if(maxNum == Math.abs(lights.get(i+1).pos - lights.get(i).pos)) {
				lightPos = (lights.get(i).pos + lights.get(i+1).pos) / 2;
				front = i;
				rear = i+1;				
			}
		}
		lights.add(rear , new Light(lightPos, count+1));
		
			
		
		return count+1;
	}
	
	static int delete_light(int D, int count) {
		for(int i = 0; i<count; i++) {
			if(lights.get(i).code == D) {
				lights.remove(i);
			}
		}
		
		return count - 1;
	}
	
	static long calc_light() {
		long maxNum = 0;
		
		for(int i = 0; i<lights.size()-1; i++) {
			maxNum = Math.max(lights.get(i+1).pos - lights.get(i).pos, maxNum);			
		}
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
