package swea.빌딩건설;

import java.io.*;
import java.util.*;
public class BuildingTest {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		
		int[] dx = {0,0,1,1,1,-1,-1,-1};
		int[] dy = {1,-1,0,-1,1,0,-1,1};
		
		for (int t = 1; t<=T; t++) {
			// 입력받기
			int n = Integer.parseInt(br.readLine());
			String[][] arr = new String[n][n];
			for(int i = 0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<n; j++) {
					arr[i][j] = st.nextToken();
				}
			}
			
			//결과값
			int res = 2;
			int arr_x = 0;
			int arr_y = 0;
			int bad_point = 0;
			
			//탐색 주변에 B로 둘러싸인 값 찾기
			for(int i = 0; i<n; i++) {
				for(int j = 0; j<n; j++) {
					int temp = 0;
					bad_point = 0;
					//주변 탐색
					for(int k = 0; k<8; k++) {
						arr_x=i+dx[k];
						arr_y=j+dy[k];
						
						if(arr_x >=0 && arr_x<=n-1 && arr_y>=0 && arr_y<=n-1) {
							if(arr[arr_x][arr_y].equals("G")) {
								bad_point += 1;
							}
								
						}
					}
					
					//네 갈래 길 더하기
					if(bad_point == 0) {
                        
						//위쪽 길
						for(int l = 0; l<n; l++){
                            if(arr[i][l].equals("B")){
                                temp += 1;
                            }
                            if(arr[l][j].equals("B")){
                                temp += 1;
                            }
                        }
						//최댓값인지 파악하기
						temp -= 1;
                        
						res = Math.max(res, temp);
					}
					
					
					
				}				
			}
            System.out.println("#"+t+" " + res);
		}
		
	}
	
}
