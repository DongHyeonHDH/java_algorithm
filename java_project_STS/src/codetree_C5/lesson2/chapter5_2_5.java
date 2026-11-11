package codetree_C5.lesson2;
import java.io.*;
import java.util.*;

public class chapter5_2_5 {
	static int[][] grid;
	static Point[][] memo;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		grid = new int[N][N];
		memo = new Point[N][N];	

		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j< N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i = 0; i< N; i++) {		
			for(int j = 0; j< N; j++) {
				memo[i][j] = new Point(0, 0);
			}
		}
		
		int max = 0;
		int min = 100;
		int temp_min = 100;
		int temp_max = 0;
		//젤 왼쪽
		for(int i = 0; i< N; i++) {	
			max = Math.max(max, grid[i][0]);
			memo[i][0].maxPoint = max;
			min = Math.min(min, grid[i][0]);
			memo[i][0].minPoint = min;
		}
		
		max = 0;
		min = 100;
		//젤 윗줄
		for(int i = 0; i< N; i++) {			
			max = Math.max(max, grid[0][i]);
			memo[0][i].maxPoint = max;
			min = Math.min(min, grid[0][i]);
			memo[0][i].minPoint = min;
		}
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j< N; j++) {
				System.out.println(memo[i][j].maxPoint);
			}
			System.out.println();
		}
		
		//1,1에서 출발해서 N,N까지 도착했을 때 최댓값과 최솟값의 차이?
		//최소가 가장 크고 최대가 가장 작은 경우 
		max = 0;
		min = 100;
		temp_min = 100;
		temp_max = 0;
		for(int i = 1; i< N-1; i++) {
			for(int j = 1; j< N-1; j++) {
				temp_max = Math.max(max, j);
				max = Math.max(max, grid[i][j]);
				memo[i][j].maxPoint = max;
				min = Math.min(min, grid[i][j]);
				memo[i][j].minPoint = min;
			}
		}

	}
	
	public class Point{
		int maxPoint;
		int minPoint;
		
		Point(int maxPoint, int minPoint){
			this.maxPoint = maxPoint;
			this.minPoint = minPoint;
		}
	}
}
