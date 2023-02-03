package game;

public class PlayerPosition implements Position {
    private Position position;

    PlayerPosition(Position board) {
        this.position = board;
    }


    @Override
    public boolean isBoardCell(Move move) {
        return position.isBoardCell(move);
    }

    @Override
    public boolean isEmptyCell(Move move) {
        return position.isEmptyCell(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }

    @Override
    public int getM() {
        return position.getM();
    }

    @Override
    public int getN() {
        return position.getN();
    }

}
