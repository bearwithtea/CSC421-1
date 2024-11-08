import java.util.Scanner;

/**
 * Simple driver for solving a Sudoku puzzle.
 *   @author Dave Reed, Owen McGrath
 *   @version 10/20/24
 */
public class 
PuzzleDriver
{
    /**
    * Driver method that repeatedly prompts the user for the text file and the puzzle solver they want.
    */
    public static void
    main(String[] args) 
    throws java.io.FileNotFoundException
    {

        Scanner input = new Scanner (System.in);
        System.out.println("Enter the puzzle file name: ");
        String filename = input.next();                    
    
        while (true)
        {
            System.out.println("What puzzle would you like solved? (S, 3, R, H) or Q to quit: ");
            String desiredPuzzle = input.next();

            if (desiredPuzzle.equals("Q"))
            {
                System.out.println("Bye!");
                break;
            }

            if (desiredPuzzle.equals("S"))
            {
                System.out.println("You have chosen sudoku.");
                Puzzle sud = new Sudoku(filename);
                System.out.println(sud);z

                if (sud.solve())
                {
                    System.out.println ("Solution found:\n" + sud);
                }
                else
                {
                    System.out.println ("No solution possible");
                }
                break;
            }
            else if (desiredPuzzle.equals ("3"))
            {
                System.out.println ("You have chosen three-in-a-row.");
                Puzzle three = new ThreeInARow (filename);
                System.out.println (three);

                if (three.solve())
                {
                    System.out.println ("Solution found:\n" + three);
                }
                else
                {
                    System.out.println ("No solution possible");
                }
                break;
            }
            else if (desiredPuzzle.equals ("R"))
            {
                System.out.println ("You have chosen range.");
                Puzzle range = new Range (filename);
                System.out.println (range);

                if (range.solve())
                {
                    System.out.println ("Solution found: \n" + range);
                }
                else
                {
                    System.out.println ("No solution possible");
                }
                break;
            }
            else if (desiredPuzzle.equals ("H"))
            {
                System.out.println ("You have chosen hitori. ");
                Puzzle hitori = new Hitori (filename);
                System.out.println (hitori);

                if (hitori.solve())
                {
                    System.out.println ("Solution found: \n" + hitori);
                }
                else
                {
                    System.out.println ("No solution is possible");
                }
                break;
            }
            else
            {
                System.out.println ("Your selection was unrecognized. Please try again.");
            } 
        }
        input.close();
    }
}
