package game;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the dimensions of the field and the win line condition:");
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        System.out.println("Enter the number of players:");
        int number = in.nextInt();
        Player[] players = new Player[number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                players[i] = new RandomPlayer();
            }
        }
        int[][] table = new int[number][number];
        System.out.println("Note: if the cell coordinates are not contained in the field " +
                "or the cell is already occupied you will lose");
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                if (i != j) {
                    final Game game = new Game(players[i], players[j], i, j);
                    int result = game.play(new MNKBoard(n, m, k));
                    if (result >= 0) {
                        System.out.println("Player: " + (result + 1) + " won");
                        table[result][i + j - result] += 3;
                    } else {
                        System.out.println("Draw");
                        table[i][j]++;
                        table[j][i]++;
                    }
                }
            }
        }
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

    }
}
