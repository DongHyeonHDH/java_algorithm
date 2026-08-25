package J1053;
import java.io.*;
import java.util.*;

public class J1053 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long[][] A = {
				{1,1},
				{1,0}
		};
		
		
		while(true) {
			int input = Integer.parseInt(br.readLine());
			if(input == -1) {
				break;
			}		
			if (input == 0) {
                sb.append(0).append('\n');
                continue;
            }
			long[][] result = power(A,input);
			long res = result[0][1];
			sb.append(res)
			.append('\n');
		}		
		System.out.println(sb);
		
	}	
	
	static long[][] matrixMul(long[][] arr1, long[][] arr2) {
		long[][] res = new long[2][2];
		
		for(int i =0; i<2; i++) {
			for(int j = 0; j<2; j++) {
				for(int k =0; k<2; k++) {
					
					res[i][j] += arr1[i][k] * arr2[k][j];
					res[i][j] %= 10000;
				}
			}
		}
		return res;
	}
	
	static long[][] power(long[][] A, long n) {
		
	    if (n == 1) {
	        return A;
	    }

	    long[][] half = power(A, n / 2);
	    long[][] result = matrixMul(half, half);

	    if (n % 2 == 1) {
	        result = matrixMul(result, A);
	    }

	    return result;
	}
	
	
}
