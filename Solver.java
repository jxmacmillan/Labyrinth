import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

public class Solver {
    private ArrayList solution;
    private Labyrinth labyrinthine;
    private final int rows;
    private final int cols;
    private final int[] moves = new int[] {0, 1, 2, 3};

    public Solver(int row, int col) {
        this.rows = row;
        this.cols = col;
        labyrinthine = new Labyrinth(row, col);
        labyrinthine.printGrid();
    }

    public int[] solve(Labyrinth l){
        this.findSafeMove(0,0,l);
        int[] finalSolution = new int[solution.size()];
            for(int i = 0; i < solution.size(); i++){
                finalSolution[i] = (int) solution.get(i);
            }
        return finalSolution;
    }

    public void findSafeMove(int row, int col, Labyrinth l){
        ArrayList<Integer> solution = new ArrayList<Integer>();
        if(row==rows-1 && col==cols-1){
            
             //break out of the recursion FIX
        }
        
        for(int i = 0; i < moves.length; i++){
            int x = 0;
            int y = 0;
            //case switch for each direction
            switch(moves[i]){
                case 0: x=0; y=1;
                case 1: x=0;y=-1;
                case 2: x=-1;y=0;
                case 3: x=1;y=0;
            }
            if(l.isStone(row + x, col + y)){
                solution.add(moves[i]);
                findSafeMove(row + x, col + y, l);
                solution.remove(moves[i]);
            }
        }
    }


    public static void main(String[] args) {
        Solver s = new Solver(10, 10);
        s.solve(s.labyrinthine);
    }
}