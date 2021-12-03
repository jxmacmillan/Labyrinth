import java.util.ArrayList;

public class Solver {
    private final static ArrayList<Integer> firstSolution = new ArrayList<Integer>();
    private static Integer[] middleSolution;
    private static int[] finalSolution;
    private static int rows;
    private static int cols;
    private final static int[] moves = new int[] {0, 1, 2, 3};


    /**
     * Solves a given labyrinth.
     * @param l the labyrinth to solve
     * @return the solution to the labyrinth in an array of ints (0 = up, 1 = down, 2 = left, 3 = right)
     */
    public static int[] solve(Labyrinth l){
        findSafeMove(0,0,l);
        middleSolution = new Integer[firstSolution.size()];
        firstSolution.toArray(middleSolution);
            //copies the contents of the middleSolution array into the finalSolution array
            finalSolution = new int[middleSolution.length];
            for(int i = 0; i < middleSolution.length; i++){
                finalSolution[i] = middleSolution[i];
            }
        System.out.println(firstSolution.toString());
        return finalSolution;
    }

    /**
     * Finds a safe move to make
     * @param row the row of the current position
     * @param col the column of the current position
     * @param l the labyrinth to solve
     */
    public static void findSafeMove(int row, int col, Labyrinth l){
        if(row==rows-1 && col==cols-1){
            return;
             //break out of the recursion
        }
        
        for(int i = 0; i < moves.length; i++){
            int x = 0;
            int y = 0;
            //case switch which tries each direction
            switch(moves[i]){
                case 0: x=0; y=1;
                case 1: x=0;y=-1;
                case 2: x=-1;y=0;
                case 3: x=1;y=0;
            }
            if(l.isStone(row + x, col + y)){
                firstSolution.add(moves[i]);
                findSafeMove(row + x, col + y, l);
                firstSolution.remove(moves[i]);
            }
        }
    }


    public static void main(String[] args) {
        rows = 10;//Integer.parseInt(args[0]);
        cols = 10;//Integer.parseInt(args[1]);
        Labyrinth labyrinthine = new Labyrinth(rows, cols);
        labyrinthine.printGrid();
        solve(labyrinthine);
    }
}