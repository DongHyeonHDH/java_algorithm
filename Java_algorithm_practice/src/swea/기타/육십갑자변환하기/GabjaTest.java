package swea.육십갑자변환하기;

import java.io.*;
import java.util.*;

/**
 * 60 갑자를 서양력으로 변환하는 프로그램
 */

public class GabjaTest {

   	static int gcd(int a, int b) {
	    while (b != 0) {
	        int temp = a % b;
	        a = b;
	        b = temp;
	    }
	    return a;
	}

	static int lcm(int a, int b) {
	    return a / gcd(a, b) * b;
	}
	
	public static void main(String[] args) throws IOException{
		String[] tenGan = {
			"갑","을","병", "정","무","기","경","신","임","계"
		};
		
		String[] tenEz = {
			"자","축","인","묘","진","사","오","미","신","유","술","해"
		};		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String InputString ="";
		int year= 0;
		
		while(true){
			System.out.print("60갑자를 입력하세요: ");			
			InputString = br.readLine();	
			
            if ("종료".equals(InputString)) {
                break;
            }

			//입력값 분석
			int left_idx = -1;
			String target = InputString.substring(0, 1);            
			for (int i = 0; i< tenGan.length; i++){
				if(tenGan[i].equals(target)) {
					left_idx = i;
					break;
				}
			}
			
			
			int right_idx = -1;
            
			target = InputString.substring(1, 2);            
			for (int i = 0; i< tenEz.length; i++){
				if(tenEz[i].equals(target)) {
					right_idx = i;
					break;
				}
			}
			
			if (left_idx == -1 || right_idx == -1 ) {
				System.out.println("잘못된 입력");
				continue;
			}
            else{
                int offset = -1;
			    for (int i = 0; i < 60; i++) {

                    int g = i % 10;
                    int j = i % 12;

                    if(g == left_idx && j == right_idx){
                        offset = i;
                        break;
                    }
                }
                //년도에 적용하기
                year = offset + 1444;
                
                //출력문 
                while(year<=2100){				
                    if(year >= 1800) {
                        System.out.print(year + " ");
                    }				
                    
                    if(year > 2100) {
                        year = year - 60;
                        break;
                    }

                    year += 60;
                }  
                System.out.println();
            }			            
			
			
		}		
		
		
	}
}

