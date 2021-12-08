import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
    private static ArrayList<Integer> firstSolution;
    private static Integer[] middleSolution;
    private static int[] finalSolution;
    private static int rows;
    private static int cols;
    private static boolean[][] visited;
    private final static int[] moves = new int[] {0, 1, 2, 3};


    /**
     * Solves a given labyrinth.
     * @param l the labyrinth to solve
     * @return the solution to the labyrinth in an array of ints (0 = up, 1 = down, 2 = left, 3 = right)
     */
    public static int[] solve(Labyrinth l){
        visited = new boolean[rows][cols]; //creates array that tracks where you have been
        visited[0][0] = true;
        firstSolution = new ArrayList<Integer>();
        findSafeMove(0,0,l);
        return finalSolution;
    }

    /**
     * Finds a safe move to make
     * @param row the row of the current position
     * @param col the column of the current position
     * @param l the labyrinth to solve
     */
    public static boolean findSafeMove(int row, int col, Labyrinth l){
        if(row==rows-1 && col==cols-1){
            saveSolution(firstSolution); //saves the solution to the labyrinth
            return true; //break out of the recursion
        }
        
        for(int i = 0; i < moves.length; i++){ //cycles through the current possible moves
            int x = 0;
            int y = 0;
            //case switch which tries a move depending on the current direction
            switch(moves[i]){
                case 0: x=-1;y=0;break;
                case 1: x=1;y=0;break;
                case 2: x=0;y=-1;break;
                case 3: x=0;y=1;break;
            }
            if(l.isValid(row + x, col + y)&&l.isStone(row + x, col + y)&&!visited[row + x][col + y]){ //checks if the direction is a safe move
                firstSolution.add(moves[i]);
                visited[row+x][col+y] = true; //marks the position as visited
                if(findSafeMove(row + x, col + y, l)){ //recursively calls the method to find the next safe move
                    return true;
                }
                firstSolution.remove(firstSolution.size()-1); //removes the move from the solution if one of the next move is not safe
                visited[row+x][col+y] = false;
            }
        }
        return false;
    }

    //converts the solution from an arraylist to an array
    public static void saveSolution(ArrayList<Integer> firstSolution){
        middleSolution = new Integer[firstSolution.size()];
        firstSolution.toArray(middleSolution);
        finalSolution = new int[middleSolution.length];
        for(int i = 0; i < middleSolution.length; i++){
            finalSolution[i] = middleSolution[i];
        }
        System.out.println(Arrays.toString(finalSolution));
        }


    public static void main(String[] args) {
        rows = Integer.parseInt(args[0]);
        cols = Integer.parseInt(args[1]);
        Labyrinth labyrinthine = new Labyrinth(rows, cols);
        labyrinthine.printGrid();
        System.out.println(labyrinthine.solves(solve(labyrinthine)));
    }
}