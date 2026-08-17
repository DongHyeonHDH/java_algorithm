package swea.D3.오목판정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N11315 {
    static char[][] gameMap;
    static int N;
    static final int[] dx = {0, 1, 1, 1};
    static final int[] dy = {1, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc<=T; tc++){
            String answer = "NO";
            N = Integer.parseInt(br.readLine());
            gameMap = new char[N][N];

            for(int i = 0; i<N; i++){
                String temp = br.readLine();
                for(int j =0; j<N; j++){
                    gameMap[i][j] = temp.charAt(j);
                }
            }
            
            for(int i = 0; i<N; i++){                
                for(int j =0; j<N; j++){                   
                    if (gameMap[i][j] != 'o') {
                        continue;
                    }
                    if(checkMap(i,j)){
                        answer = "YES";
                        break;
                    }
                }
            }

            sb.append('#')
            .append(tc)
            .append(' ')
            .append(answer)
            .append('\n')
            ;
        }
        System.out.println(sb);
    }
   

    //움직임 감지
       static boolean checkMap(int row, int col) {

        for (int dir = 0; dir < 4; dir++) {

            // 현재 위치의 돌 포함
            int count = 1;

            // 현재 위치 다음부터 4칸 검사
            for (int i = 1; i < 5; i++) {

                int nextRow = row + dx[dir] * i;
                int nextCol = col + dy[dir] * i;

                if (isOk(nextRow, nextCol)) {
                    count++;
                } else {
                    // 중간에 끊기면 이 방향은 실패
                    break;
                }
            }

            if (count >= 5) {
                return true;
            }
        }

        return false;
    }

  
    
    static public boolean isOk(int row, int col){
        if(row < N && row >= 0 && col < N && col >= 0){
            if(gameMap[row][col] == 'o'){
                return true;
            }
        }
        
        return false;       

    }
}
