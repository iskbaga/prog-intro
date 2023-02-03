package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    public RandomPlayer(){};

    @Override
    public Move move(final Position position, final Cell cell) {
        int row;
        int column;
        Move move;
        while (true) {
            row = random.nextInt(position.getM());
            column = random.nextInt(position.getN());
            move = new Move(row, column, cell);
            if (position.isBoardCell(move) && position.isEmptyCell(move)) {
                return move;
            }
        }
    }
}
