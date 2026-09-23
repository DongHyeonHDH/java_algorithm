package test;

import java.util.*;
import java.io.*;

public class SafePlace {
    static boolean[][] visited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,1,-1};    
    static int[][] grid;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int maxNum = 1;
        grid = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){
                grid[i][j] = sc.nextInt();
                if(grid[i][j] > maxNum) {
                    maxNum = grid[i][j];
                }
            }   
        }
        // Please write your code here.
        // 안전 구역이란 잠기지 않은 집들로 이루어져 있고, 잠기지 않은 집들끼리 상하좌우로 인접해있는 경우
        // 동일 안전영역에 있는 것으로 간주 

        // 안전 영역의 수가 최대가 되는 경우 K가 여러 개라면 그중 가장 작은 K 출력
        
        //k를 index로 가지고  안전가옥 수를 값으로 가진다
        int[] answer = new int[maxNum+1];
        int res = 0;
        int maxN = 0;
        for(int k = 1; k<=maxNum; k++){
            visited = new boolean[n][m];
            res = 0;
            for(int i = 0; i< n; i++){
                for(int j = 0; j< m; j++){                    
                    if(!visited[i][j] && grid[i][j] > k){
                        bfs(i,j,k,n,m);
                        res++;
                    }
                }
            }
            answer[k] = res;
            if(res > maxN) {
            	maxN = res;
            }
        }       
        
        int ans = 0;
        for(int i = 1; i< answer.length; i++){
            if(answer[i] == maxN) {
            	System.out.println(i+" "+answer[i]);
            	break;
            }
        }
        
    }

    static void bfs(int row, int col, int k, int n, int m){
        Queue<Node> q= new ArrayDeque<>();
        q.add(new Node(row, col));
        visited[row][col] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(int i = 0; i<4; i++){
                int r = cur.r +dr[i];
                int c = cur.c +dc[i];

                if(r<0 || r>=n || c<0 || c>=m ) continue;

                if(!visited[r][c] && grid[r][c] > k){
                    q.add(new Node(r,c));
                    visited[r][c] = true;
                }
            }
        }
        
    }

    static class Node{
        int r;
        int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}