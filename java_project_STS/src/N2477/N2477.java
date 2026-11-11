package N2477;
import java.io.*;
import java.util.*;

public class N2477 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int[] reception = new int[N+1];
			int[] repair = new int[M+1];
			Customer[] customer = new Customer[K+1];
			
			//접수
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=N; i++) {
				reception[i] = Integer.parseInt(st.nextToken());
			}
			
			//수리
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=M; i++) {
				repair[i] = Integer.parseInt(st.nextToken());
			}
			
			//고객			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=K; i++) {
				int temp = Integer.parseInt(st.nextToken());
				customer[i] = new Customer(temp,i);				
			}
			
			//출력할 것은 지갑을 두고간 고객과 같은 접수창구 A와 같은 장비 청구 B를 이용한 고객들의 고객번호의 합
			
			//종료조건 : t가 흐르고 있고 남아있는 손님이 다 완료하는 경우
			//완료된 고객들
			Queue<Customer> CompleteCustomer = new ArrayDeque<>();
			
			//1. 접수 창구			
			Queue<Customer> receptWaiting = new ArrayDeque<>();
			int receptMax = (1<< (N+1))-2;
			int receptCnt = 0;
			Desk[] receptDesk = new Desk[N+1];
			
			//2. 정비 창구
			Queue<Customer> repairWaiting = new ArrayDeque<>();
			int repairMax = (1<< (M+1))-2;
			int repairCnt = 0;
			Desk[] repairDesk = new Desk[M+1];
			
			int time = 0;
			int customerIdx = 1;
			int completeCnt = 0;
			int answer = 0; 
			
			while(true) {			    
				//정비 완료된 고객 처리
				for(int i = 1; i<= M; i++) {
					if(repairDesk[i] != null && repairDesk[i].time == time) {
						Customer temp = repairDesk[i].cus;
				        if (temp.receptNum == A && temp.repairNum == B) {
				            answer += temp.no;
				        }
				        completeCnt++;
				        repairCnt &= ~(1 << i);
				        repairDesk[i] = null;
					}
				}
				
				//접수 완료 고객 처리
				for(int i = 1; i<= N; i++) {
					if(receptDesk[i] != null && receptDesk[i].time == time ) {
						repairWaiting.add(receptDesk[i].cus);
						receptCnt &= ~(1<<i);
						receptDesk[i] = null;
					}
				}
				
				// 현재 시간까지 도착한 고객 추가
			    while (customerIdx <= K &&
			           customer[customerIdx].arrive <= time) {

			        receptWaiting.add(customer[customerIdx]);
			        customerIdx++;
			    }
						
				//접수 창구 배치가 비었다면  receptWaiting에서 receptDesk로 전달 
				if(receptCnt != receptMax) {
					for(int i = 1; i<= N; i++) {
						if (receptWaiting.isEmpty()) break;
						
						if((receptCnt & (1<<i)) == 0) {
							Customer temp = receptWaiting.poll();
							
							temp.receptNum = i;
							receptCnt |= (1<<i);
							
							receptDesk[i] = new Desk(time + reception[i],temp);
						}
					}
				}				
								
				//receptDesk에서 나온 것들은 repairWaiting에 넣는다.
				if(repairCnt != repairMax) {
					for(int i = 1; i<= M; i++) {
						if (repairWaiting.isEmpty()) break;
						if((repairCnt & (1<<i)) == 0) {
							Customer temp = repairWaiting.poll();
							temp.repairNum = i;
							repairCnt |= (1<<i);
							
							repairDesk[i] = new Desk(time + repair[i],temp);
						}
					}
				}
											
				
				//종료 조건
				if(completeCnt == K) {
					break;
				}
				
				time++;
			}		

			
			
			//고객이 없는 경우
			if(answer == 0) {
				answer = -1;
			}
			
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n");
		}		
		System.out.println(sb);
		
	}
	
	static class Customer{
		int arrive;
		int no;
		int receptNum;
		int repairNum;
		
		Customer(int arrive, int no){
			this.arrive= arrive;
			this.no= no;
			this.receptNum = 0;
			this.repairNum = 0;
		}		
		
	}
	
	static class Desk{
		int time;
		Customer cus;
		
		Desk(int time, Customer cus){
			this.time = time;
			this.cus = cus;
		}
	}
}
