package N5177;

import java.io.*;
import java.util.*;

public class N5177 {
	static int[] arr;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T =  Integer.parseInt(br.readLine());	
		
		for(int t =1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[N+1];
			for(int i =1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());				
				
			}
			
			//구현하기
			binaryMinHeap();
		}
	}
	
	//완전 이진 트리 생성
	static void binaryMinHeap() {
		//몇 제곱인지 구하기
		int n = sqrInt(N);		
		
		for(int i = n; i>=1; i--) {
			int previous = (int)Math.pow(2,i-1);	
			
			for(int j = 0; j < previous; j++) {			
				
				int parent = (previous+j)/2;
				if(previous + j == N) {
					break;
				}
				swap(arr[parent], arr[previous+j]);
			}
			System.out.println();
		}
	
	}
	
	//조상 노드의 저장된 정수 합 알아내는 프로그램
	static void searchNode() {
		int n = sqrInt(N);		
		for(int i = n; i>=1; i--) {
			int previous = (int)Math.pow(2,i-1);
			int present = (int)Math.pow(2,i);
			for(int j = 0; j < previous; j++) {
				System.out.print(" "+ previous);
//				swap(arr[previous], arr[present+j]);
//				swap(arr[previous], arr[present+j+1]);
			}
			System.out.println();
		}
	
	}
	
	static void swap(int front, int rear) {		
		if(front > rear) {
			int temp = front;
			front = rear;
			rear = temp;
		}
			
		
	}
	
	static int sqrInt(int num){
		int cnt = 1;
		
		while(num != 1) {
			num = num/2;
			cnt++;
		}
		
		return cnt;
	}
	
	static boolean isOk(int num) {
		if(num >=0 && num<N) {
			return true;
		}
		else {
			return false;
		}
	}

}

/*
 * 
 * 
 * 
 * 
 * 
 * 1(1) 2(2) 2(3) 3(4) 3(5) 3(6) 3(7) 
 * 1(2^0) 2 (2^1) 4 (2^2) 계층이 만들어지고 계층별로 swap, 비교 계산 수행하면 되겠다
 * 
 * N
 * 6/2 -> 420 -> 3계층이 만들어진다. 
 * 
 * 넣으면 위치에 따라 스왑계산이 이루어지도록 한다.
 * 
 * 함수는 위치값 계산, 
 * 
 * 
 * 
 * */
 