import java.util.*;
import java.io.*;
public class Test2_황동현 {
    public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			if(!(N >= 5 && N <= 15)) {
				System.out.println("잘못된 입력 값 부여");
				return;
			}
			
			for(int i = 0; i< N/2; i++) {
				for(int j = 0; j< N; j++) {
					if(j <=i || j >= N-1-i) {
						System.out.print("*");
					}
					else {
						System.out.print(" ");
					}
				}
				System.out.println();
			}	

			for(int j = 0; j< N; j++) {
				System.out.print("*");				
			}
			
			System.out.println();
			for(int i = N/2; i< N; i++) {
				for(int j = 0; j< N; j++) {
					if(j > i || j < N-1-i) {
						System.out.print("*");
					}
					else {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		}
    }
}