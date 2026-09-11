package N2383;
import java.io.*;
import java.util.*;
public class N2383 {
	static int[] stairX, stairY, stairLength;
    static int[] peopleX, peopleY;
    static List<List<Integer>> groupA, groupB;
    static boolean[] selected;
    static boolean[] visited;
    static int peopleCnt;
    static int answer = 0;
    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= T; tc++){
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            stairX= new int[2];
            stairY= new int[2];
            stairLength= new int[2];
 
            peopleX = new int[10];
            peopleY = new int[10];
 
            groupA = new ArrayList<>();
            groupB = new ArrayList<>();
           
            int stairCnt = 0;
            peopleCnt = 0;
            answer = 0;
                        
            for(int i =0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                     
                    if(map[i][j] > 1){
                        stairX[stairCnt] = i;
                        stairY[stairCnt] = j;
                        stairLength[stairCnt++] = map[i][j];
                    }
                    else if(map[i][j] == 1){
                        peopleX[peopleCnt] = i;
                        peopleY[peopleCnt++] = j;                        
                    }
                     
                }                
            }
            //사람수에 따른 수행
            visited = new boolean[peopleCnt];
            selected = new boolean[peopleCnt];
            grouping(0);
 
//            for(int i =0; i< groupA.size(); i++) {
//            	for(int j =0; j< groupA.get(i).size(); j++) {
//            		System.out.print(" "+ groupA.get(i).get(j));
//            	} 
//            	System.out.println();
//            }
            int answer = 0;
            for(int i =0; i< groupA.size(); i++) {
            	int temp = Math.max(simulation(groupA.get(i),  0),
                        simulation(groupB.get(i),  1));
            	System.out.println(temp);
            	answer = Math.min(answer, temp);
            	System.out.println(answer);
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
 
    //두그룹으로 분화
    static void grouping(int idx){
        if(idx == peopleCnt) {
        	List<Integer> As = new ArrayList<>();
        	List<Integer> Bs = new ArrayList<>();
        	for (int i = 0; i < peopleCnt; i++) {
                if (selected[i]) {
                    As.add(i+1);
                }
                else {
                	Bs.add(i+1);
                }
            }           
            
        	groupA.add(As);
        	groupB.add(Bs);
        	
        	return;
        }
        selected[idx] = true;
        grouping(idx+1);
        
        selected[idx] = false;
        grouping(idx+1);
    }
 
    //nC2로 나눈 두그룹에 대해 탐색 수행
    static int simulation(List<Integer> groupA, int stairA){
    	if(groupA.size() == 0) {
    		return 0;
    	}
    	
    	//거리 및 시간을 저장하는 배열
    	int[] arrA = new int[groupA.size()+1];
        
        //계단위 사람수
        int stairing = 0;
 
        //거리 구하기
        int cntA =0;
        for(int a: groupA){
            arrA[cntA++] = Math.abs(stairX[stairA] - peopleX[a]) + Math.abs(stairY[stairA] - peopleY[a]);
//            System.out.println("arrA : "+ arrA[cntA -1]);
        }
 
        System.out.println("거리 계산 끝");
        //A에 관한 수행
        //시간
        int timeA = 1;
        //사람 수
        int countPA = 1;
        //도착자 수
        int dest = 0;
         
        //시작했는지 체크
        boolean visitedA[] = new boolean[arrA.length];
        Queue<Integer> waiters= new ArrayDeque<>();
        while(true){            
            //사람들이 해당계단 앞에 왔는지 체킹
            for(int i = 0; i< arrA.length; i++){
                // 도착한경우
                if(arrA[i] == timeA && !visitedA[i]){
                    //사람이 많은 경우
                    if(countPA > 3){
                        waiters.add(i);                        
                    }
                    else{
                        countPA +=1;  
                        arrA[i] += stairLength[stairA];  
                        visitedA[i] = true;    
                    }                                    
                }
                 
                if(countPA <=3 && !waiters.isEmpty()){
                    int wait= waiters.poll();
                    countPA += 1;
                    arrA[wait] = timeA +stairLength[stairA];
                    visitedA[wait] = true;
                }
 
 
                //계단을 내려온 경우
                if(arrA[i] == timeA){
                    dest++;
                    countPA--;
                }
            }          
             
            
            timeA++;
            if(arrA.length == dest){
                break;   
            }
        }
        System.out.println("시뮬레이션 종료");
        return timeA;
    }
}
