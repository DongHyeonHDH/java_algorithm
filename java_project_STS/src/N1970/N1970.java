package N1970;
import java.io.*;
import java.util.*;
public class N1970 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int n1=N%50000;
			int n2=n1%10000;
			int n3=n2%5000;
			int n4=n3%1000;
			int n5=n4%500;
			int n6=n5%100;
			int n7=n6%50;
			int n8=n7%10;
			
			int an1=N/50000;
			int an2=n1/10000;
			int an3=n2/5000;
			int an4=n3/1000;
			int an5=n4/500;
			int an6=n5/100;
			int an7=n6/50;
			int an8=n7/10;
			
			sb.append("#")
			.append(tc)
			.append(" ")
			.append("\n")
			.append(an1)
			.append(" ")
			.append(an2)
			.append(" ")
			.append(an3)
			.append(" ")
			.append(an4)
			.append(" ")
			.append(an5)
			.append(" ")
			.append(an6)
			.append(" ")
			.append(an7)
			.append(" ")
			.append(an8)
			.append(" ")		
			.append("\n")
			;
		}
		System.out.println(sb);
	}
}
