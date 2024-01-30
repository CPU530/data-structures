public class Knights_tour_prt4 {
    static int N = 8;
    static int[][] sol = new int[N][N];
    static int[] xMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
    static int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[][] prio = {
        {2, 3, 4, 4, 4, 4, 3, 2},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {2, 3, 4, 4, 4, 4, 3, 2}
    };
    static int[][] total_moves = {
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}
    
        };
    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            the_holy_grail_tour(j, i);
            System.out.println();
            }
        }
    }
    static void the_holy_grail_tour(int startX, int startY) {
        int[][] sol = new int[N][N];
        sol[startX][startY] = 1;

        for (int i = 0; i < N * N - 1; i++) {
            int nextX = -1;
            int nextY = -1;
            int minPriority = Integer.MAX_VALUE;
            for (int k = 0; k < 8; k++) {
                int newX = startX + xMove[k];
                int newY = startY + yMove[k];
                if (newX >= 0 && newX < N && newY >= 0 && newY < N && sol[newX][newY] == 0) {
                    int priority = getPriority(newX, newY);
                    if (priority < minPriority) {
                        minPriority = priority;
                        nextX = newX;
                        nextY = newY;
                    }
                }
            }
            if (nextX == -1 || nextY == -1) {
                break;
            }
            sol[nextX][nextY] = i + 2;
            startX = nextX;
            startY = nextY;
        }
        
        printSolution(sol);
    }
    static void printSolution(int[][] sol) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%-3d ", sol[i][j]);
            }
            System.out.println();
        }
    }
    
    static int getPriority(int x, int y) {
        return prio[x][y];
    
    }
}
