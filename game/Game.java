package game;

public class Game {
    public Player firstPlayer;
    int firstPlayerIndex;
    int secondPlayerIndex;
    public Player secondPlayer;

    public Game(Player firstPlayer, Player secondPlayer,int firstPlayerIndex, int secondPlayerIndex) {
        this.firstPlayer=firstPlayer;
        this.secondPlayer=secondPlayer;
        this.firstPlayerIndex=firstPlayerIndex;
        this.secondPlayerIndex=secondPlayerIndex;
    }

    public int play(Board board) {
        while (true) {
            int result = playerMove(board, firstPlayer, firstPlayerIndex);
            if (result != -2) {
                return result;
            } else{
                result = playerMove(board, secondPlayer, secondPlayerIndex);
                if (result != -2) {
                    return result;
                }
            }

        }
    }

    private int playerMove(final Board board, final Player player, final int playerNumber) {
        final Move move = player.move(board.getPosition(), board.getTurn());
        final Result result = board.makeMove(move);
        System.out.println("Player " + playerNumber + " move:");
        System.out.println("Position:\n" + board);
        if (result == Result.WIN) {
            return playerNumber;
        } else if (result == Result.LOSE) {
            return (playerNumber + 1) % 2;
        } else if (result == Result.DRAW) {
            return -1;
        } else {
            return -2;
        }
    }
}
