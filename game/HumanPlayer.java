package game;


import java.util.Scanner;
public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }


    public Move move(Position position, Cell turn) {
        System.out.println();
        System.out.println("Enter your move for " + turn);
        while (true) {
            Scanner line= new Scanner(in.nextLine());
            String row;
            int x;
            int y;
            String col;
            Move move;
            try{
                row = line.next();
                col = line.next();
            } catch(Exception e){
                System.out.println("Your values must be separated by a space on one line, please try again: ");
                continue;
            }
            try {
                x = Integer.parseInt(row);
                y = Integer.parseInt(col);
                move = new Move(
                        x - 1,
                        y - 1,
                        turn
                );
            } catch (Exception e) {
                System.out.println("Your turn should consist of two numbers, please try again: ");
                continue;
            }
            if(position.getCell(x-1,y-1)==Cell.P){
                System.out.println("Its an obstacle, please try again:");
                continue;
            }
            if(!position.isBoardCell(move)) {
                System.out.println("Your turn is not a cell of the field, you lose");
            } else if(!position.isEmptyCell(move)){
                System.out.println("This cell is not empty, you lose");
            }

            return move;
        }
    }
}