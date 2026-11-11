package N2115;
import java.io.*;
import java.util.*;
public class N2115 {
	static int N, M, C, pointerMax;
	static int[][] pointerBox;
	static int[][] honeyBox;
	static boolean[][] selected;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			//선택 가능한 벌꿀의 개수
			M = Integer.parseInt(st.nextToken());
			//채취 가능한 벌꿀의 양
			C = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			honeyBox = new int[N][N];			
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					honeyBox[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			pointerMax = N-M+1;			
			pointerBox = new int[N][pointerMax];
			selected = new boolean[N][N];
			
			//가로의 값을 저장하기 위한 함수
			checkByPointer();
			
			//같은 줄 선택가능한 경우 같은 줄만 선택한 경우 포함한 메서드
			answer = compareBox();
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n")
			;
			
		}
		System.out.println(sb);
	}	
	
	
	static void checkByPointer() {		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j< pointerMax; j++) {
				combBox(0, i, j, j+M);		
			}
		}
	}
	
	//선택한 벌통에서 수익 뽑아내는 메서드
	static void combBox(int idx, int layer, int start, int end) {
		if(start + idx == end) {
			findMax(layer,start,end);
			return;
		}
		
		selected[layer][start + idx] = true;
		combBox(idx+1, layer, start, end);
		
		selected[layer][start + idx] = false;
		combBox(idx+1, layer, start, end);
		
	}
	
	static void findMax(int layer, int start, int end) {
        int sum = 0;
        int profit = 0;

        for (int i = start; i < end; i++) {
            if (selected[layer][i]) {
                int honey = honeyBox[layer][i];

                sum += honey;
                profit += honey * honey;
            }
        }

        // 채취량 제한 확인
        if (sum <= C) {
            pointerBox[layer][start] =
                Math.max(pointerBox[layer][start], profit);
        }
    }
	
	//뽑아낸 꿀 비교
	static int compareBox() {
		int tempMax = 0;		
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < pointerMax; j++) {

                for (int k = i + 1; k < N; k++) {
                    for (int l = 0; l < pointerMax; l++) {

                        int profit = pointerBox[i][j]
                                   + pointerBox[k][l];

                        tempMax = Math.max(tempMax, profit);
                    }
                }
            }
        }
				
		
		// 같은 줄에 있는 경우 뽑기  
		if (N >= M * 2) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < pointerMax; j++) {

                    // 첫 번째 구간 이후부터 선택
                    for (int k = j + M; k < pointerMax; k++) {

                        int profit = pointerBox[i][j]
                                   + pointerBox[i][k];

                        tempMax = Math.max(tempMax, profit);
                    }
                }
            }
        }
		
		return tempMax;
	}
}
