package N5644;
import java.io.*;
import java.util.*;
public class N5644 {
	static int M, A;
	static int[] userA, userB;
	static int[] apX, apY, apC, apP;
	//지도의 가로, 세로 크기는 10
	static int[][] gameMap = new int[11][11];
	static int[] aP,bP;
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc<=T; tc++) {
			//입력 값
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			userA = new int[M];
			userB = new int[M];
				
			//a,b 유저의 P값을 저장
			aP = new int[M];
			bP = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<M; i++) {
				userA[i]= Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<M; i++) {
				userB[i]= Integer.parseInt(st.nextToken());
			}
			
			int answer = 0;
			
			apX = new int[A+1];
			apY = new int[A+1];
			apC = new int[A+1];
			apP = new int[A+1];
			
			for(int i =1; i<=A; i++) {
				st = new StringTokenizer(br.readLine());
				apY[i] = Integer.parseInt(st.nextToken());
				apX[i] = Integer.parseInt(st.nextToken());
				apC[i] = Integer.parseInt(st.nextToken());
				apP[i] = Integer.parseInt(st.nextToken());				
			}
			
			//맵초기화	
			for(int i = 1; i<= 10; i++) {
				Arrays.fill(gameMap[i],0);
			}
			//맵 칠하기
			paintMap();
			
			//움직임 수행
			move();			
			
			for(int i = 0; i< M; i++) {
				answer += aP[i]+bP[i];
			}
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n")
			;
		}
		System.out.println(sb);
	}
	
	//움직임 구현
	static void move() {
		int ax = 0;
		int ay = 0;
		
		int bx = 10;
		int by = 10;
		
		//현재 사용자의 위치가 어디인지를 보여주는 변수
		
		//A와 B가 현재 있는 값 분석		
		for(int i = 0; i< M; i++) {
			//사용자 A 접근
			ax = ax+dx[userA[i]];
			ay = ay+dy[userA[i]];
			
			if(isAccept(ax)) continue;
			if(isAccept(ay)) continue;
			//사용자 B 접근
			bx = bx+dx[userB[i]];
			by = by+dy[userB[i]];
			
			if(isAccept(bx)) continue;
			if(isAccept(by)) continue;
			
			//P충전
			//같은 AC의 범위에 있을 때 
			if((gameMap[ax][ay] & gameMap[bx][by]) != 0) {
				whichCharge(ax, ay, bx, by);
				continue;
			}			
			
			if(gameMap[ax][ay] != 0){					
				aP[i] = Integer.numberOfTrailingZeros(gameMap[ax][ay]);
			}												
			
			if(gameMap[bx][by] != 0){					
				bP[i] = Integer.numberOfTrailingZeros(gameMap[bx][by]);
			}
			
			
			
		}
	}
	
	//충전소 범위만큼 영역 칠하기
	static void paintMap() {
		for(int i = 1; i<= A; i++) {			
			for(int x = apX[i] - apC[i]; x <= apX[i] + apC[i]; x++) {
	            if(isAccept(x)) {
	                continue;
	            }

	            int remain = apC[i] - Math.abs(apX[i] - x);

	            for(int y = apY[i] - remain; y <= apY[i] + remain; y++) {
	                if(isAccept(y)) {
	                    continue;
	                }

	                //겹치는 위치에 대한 비트마스킹으로 맵칠하기
	                gameMap[x][y] |= (1 << (i -1));
	            }
	        }
		}
		
	}	

	//겹치는 지점에서 반틈으로 나눌지 아니면 각기 다른 충전소에서 충전할지 분류
	//그냥 겹치는 것을 나눌지 아니면 하나의 값을 나눌지를 판정하는 편이 좋다.
	static void whichCharge(int ax, int ay, int bx, int by) {
		
		//a가 겹치는 AC 위에 있는 경우
		if(Integer.bitCount(gameMap[ax][ay]) > 2) {
			int bit = gameMap[ax][ay] & gameMap[bx][by];
			int index = Integer.numberOfTrailingZeros(bit);
			
			
		}
		//b가 겹치는 AC 위에 있는 경우
		else if(Integer.bitCount(gameMap[bx][by]) > 2) {
			
		}
	}
	
	static boolean isAccept(int n) {
		return n<1 || n>10;			
	}
}
