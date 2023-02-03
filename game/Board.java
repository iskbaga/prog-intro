package game;

public interface Board {
    Position getPosition();
    Cell getTurn();
    Result makeMove(Move move);
    int getM();
    int getN();
}
