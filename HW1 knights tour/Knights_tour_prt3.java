/**
 *
 * @author Robert McVey
 */

// class declaring and board size X,Y move arrays
public class Knights_tour_prt3 {
    static int N = 8;
    static int[][] sol = new int[N][N];
    static int[] xMove = { 2, 1, -1, -2, -2, -1, 1, 2 };
    static int[] yMove = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[][] prio_matrix = {
        {2, 3, 4, 4, 4, 4, 3, 2},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {4, 6, 8, 8, 8, 8, 6, 4},
        {3, 4, 6, 6, 6, 6, 4, 3},
        {2, 3, 4, 4, 4, 4, 3, 2}
    };
    

    // initialize coordinate variables, and set position to (sol = solution)
    public static void main(String[] args) {
        int startX = 0; // Specify the starting X coordinate here
        int startY = 7; // Specify the starting Y coordinate here
        // actually sets the starting position equal to the first move
        sol[startX][startY] = 1;
        // create a loop that will iterate through max number of movements 64 spaces 63
        // moves
        // initialize transformative variables
        for (int i = 0; i < N * N - 1; i++) {
            int nextX = -1;
            int nextY = -1;
            int minPriority = Integer.MAX_VALUE;
            // creates a loop that iterate through all moves in the array
            // initializes the minprio var
            for (int k = 0; k < 8; k++) {
                int newX = startX + xMove[k];
                int newY = startY + yMove[k];
                // checks if the new position (newX, newY) has been visited or not, and also if
                // it's on the board/valid.
                // Because the array "sol" is created through ((static int[][] sol = new
                // int[N][N];)) all values in the 2-dimensional array are initialized at 0, thus
                // does sol[newX][newY] == 0 check if a position has been visited
                // if statement ofr priority checks against the given "prio array" and choose the location
                if (newX >= 0 && newX < N && newY >= 0 && newY < N && sol[newX][newY] == 0) {
                    int priority = getPriority(newX, newY);
                    if (priority < minPriority) {
                        minPriority = priority;
                        nextX = newX;
                        nextY = newY;
                    }
                }
            }
            // check if a move is possible or has been determined to be possible
            if (nextX == -1 || nextY == -1) {
                break;
            }
            // this sets the determined move to the now current position, it makes the move
            // sets the "tile" that was moved to, to the correct number ie the first tile
            // will be 1 and i starts @ 0 therefore i+2 creates 2
            sol[nextX][nextY] = i + 2;
            startX = nextX;
            startY = nextY;
        }
        printSolution();
    }
    // shows output of one tour
    static void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%-3d ", sol[i][j]);
            }
            System.out.println();
        }
    }
    
    // simply calls the prio_matrix with a given coordinate pair and returns the value
    static int getPriority(int x, int y) {
        return prio_matrix[x][y];
    
    }
}
