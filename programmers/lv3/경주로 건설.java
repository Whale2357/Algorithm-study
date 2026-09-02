import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    static class Node implements Comparable<Node> {
        int r, c, dir, cost;

        Node(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost); 
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        int[][][] dist = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        if (board[0][1] == 0) {
            dist[0][1][3] = 100;
            pq.offer(new Node(0, 1, 3, 100));
        }
        if (board[1][0] == 0) {
            dist[1][0][1] = 100;
            pq.offer(new Node(1, 0, 1, 100));
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.r == n - 1 && curr.c == n - 1) {
                return curr.cost;
            }

            if (curr.cost > dist[curr.r][curr.c][curr.dir]) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + DR[d];
                int nc = curr.c + DC[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] == 1) {
                    continue;
                }

                int nextCost = curr.cost + (curr.dir == d ? 100 : 600);

                if (nextCost < dist[nr][nc][d]) {
                    dist[nr][nc][d] = nextCost;
                    pq.offer(new Node(nr, nc, d, nextCost));
                }
            }
        }

        return -1;
    }
}