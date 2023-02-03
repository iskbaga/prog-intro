package game;

public interface Position {
    boolean isBoardCell(Move move);
    String toString();
    boolean isEmptyCell(Move move);
    Cell getCell(int r, int c);
    int getN();
    int getM();
}
