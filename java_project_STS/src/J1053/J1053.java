package J1053;
import java.io.*;
import java.util.*;

public class J1053 {
	static long[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int arrSize = Integer.MAX_VALUE;
		arrSize = arrSize/10;
		arr = new long[arrSize]; 
		arr[0] =0;
		arr[1] =1;
		int input = 0;
		long res = 0;
		
		for(int i = 0; i< arrSize/10; i++) {
			arr[i] = pibo(i);
			System.out.print(" "+ arr[i]);
		}
		System.out.println();
		
		while(true) {
			input = Integer.parseInt(br.readLine());
			if(input == -1) {
				break;
			}
			res = arr[input];
			if(res > 10000) {
				res = res % 10000;
			}
			sb.append(res)
			.append('\n');
		}
		
		System.out.println(sb);
		
	}
	
	static long pibo(int idx) {		
		if(idx == 0) {
			return 0;
		}
		
		if(idx == 1) {
			return 1;
		}
		
		return pibo(idx-1) + pibo(idx-2);
	}
	
	
}
