package swea.D3.상호의배틀필드;
import java.util.*;
import java.io.*;

public class Solution {
    static int[][] gameMap;
    static String cons= ".-*#";
    static String portris = "^v<>";
    static String dir = "UDLR";
    static int H;
    static int W;

    //전차의 머리방향에 관한 화살표
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};

    //전차의 위치를 저장
    static int portRow = 0;
    static int portCol = 0;
    static int portStartDir = 0;
    
    //전차의 머리 방향을 저장
    static int headerRow = 0;
    static int headerCol = 0;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        String line;        

        for(int t = 1; t<= T; t++){
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            gameMap = new int[H][W];           
            
            // 맵 구현
            for(int i = 0; i< H; i++){
                line = br.readLine();
                for(int j =0; j<W; j++){
                    //강철 벽은 3
                    if(line.charAt(j) == '#' ){
                        gameMap[i][j] = 3;
                    }
                    //벽돌 벽은 2
                    else if(line.charAt(j) == '*'){
                        gameMap[i][j] = 2;
                    }
                    //물 웅덩이는 1
                    else if(line.charAt(j) == '-'){
                        gameMap[i][j] = 1;
                    }
                    //전차 위치
                    else if(portris.contains(String.valueOf(line.charAt(j)))){                        
                        portStartDir = findPortDir(line.charAt(j));
                        headerRow = dx[portStartDir];
                        headerCol = dy[portStartDir];
                        portRow = i;
                        portCol = j;

                    }
                    // 평지
                    else{
                        gameMap[i][j] = 0;
                    }                    
                    
                }
            }            
            

            int N = Integer.parseInt(br.readLine());
            String havior = br.readLine();
            for(int l=0; l< N; l++){
                battle(havior.charAt(l));

            }
            
            gameMap[portRow][portCol] = 4;
            sb.append("#"+t+" ");
            for(int i=0; i<H; i++){
                for(int j=0; j<W; j++){
                    if(gameMap[i][j] <= 3){
                        sb.append(cons.charAt(gameMap[i][j]));
                    } 
                    else if(gameMap[i][j] == 4){
                        sb.append(returnPort());
                    }
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
    
    //방향표를 받아서 전차의 방향을 return 해주는 메서드 
    public static int findPortDir(char dir){
        if(dir == '^'){
            return 0;
        }
        else if(dir == 'v'){
            return 1;
        }
        else if(dir == '<'){
            return 2;
        }
        else if(dir == '>'){
            return 3;
        }
        else{
            System.out.println("잘못된 방향 입력");
            return -1;
        }
    }

    //동작을 수행하는 메서드
    public static void battle(char havior){        
        if(havior == 'S'){
            shoot();
        }
        else if((havior == 'U')){
            headerRow = dx[0];
            headerCol = dy[0];
            if(isPossible(portRow+dx[0], portCol+dy[0])){
                portRow = portRow+dx[0];
                portCol = portCol+dy[0];
            }
        }
        else if((havior == 'D')){
            headerRow = dx[1];
            headerCol = dy[1];
            if(isPossible(portRow+dx[1], portCol+dy[1])){
                portRow = portRow+dx[1];
                portCol = portCol+dy[1];
            }
        }
        else if((havior == 'L')){
            headerRow = dx[2];
            headerCol = dy[2];
            if(isPossible(portRow+dx[2], portCol+dy[2])){
                portRow = portRow+dx[2];
                portCol = portCol+dy[2];
            }
        }
        
        else if((havior == 'R')){
            headerRow = dx[3];
            headerCol = dy[3];
            if(isPossible(portRow+dx[3], portCol+dy[3])){
                portRow = portRow+dx[3];
                portCol = portCol+dy[3];
            }
        }
        else{
            System.out.println("잘못된 방향 입력");
        }

    }

    public static boolean isPossible(int row, int col){
        if(row >=0 && row < H && col >=0 && col < W && gameMap[row][col] == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean shootPossible(int row, int col){
        if(row >=0 && row < H && col >=0 && col < W){
            return true;
        }
        else{
            return false;
        }
    }

    public static void shoot(){
        int row = portRow + headerRow;
        int col = portCol + headerCol;
        while(shootPossible(row, col)){
            if(gameMap[row][col] == 3){
                break;
            }
            else if(gameMap[row][col] == 2){
                gameMap[row][col] = 0;
                break;
            }
            else{
                row = row + headerRow;
                col = col + headerCol;
            }
        }
    }

    public static char returnPort(){
        if(headerRow == dx[0] && headerCol == dy[0]){
            return '^';
        }
        else if(headerRow == dx[1] && headerCol == dy[1]){
            return 'v';
        }
        else if(headerRow == dx[2] && headerCol == dy[2]){
            return '<';
        }
        else if(headerRow == dx[3] && headerCol == dy[3]){
            return '>';
        }
        else{            
            System.out.println("잘못된 returnPort");
            return ' ';
        }
    }
}
