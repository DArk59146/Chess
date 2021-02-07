import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Main {
    private static BufferedReader reader;

    public static void main(String[] args) {
        final List<Player> players = new LinkedList<>();
        int result;
        players.add(new HumanPlayer());
        players.add(new HumanPlayer());
        final Game game = new Game(false, players);
        Figure[][] board = new Figure[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Empty(i, j);
            }
        }
        board[Integer.parseInt(args[0].substring(1)) - 1][args[0].charAt(0) - 'a'] =
                new King(Colour.BLACK, Integer.parseInt(args[0].substring(1)), args[0].charAt(0) - 'a');
        board[Integer.parseInt(args[1].substring(1)) - 1][args[1].charAt(0) - 'a'] =
                new King(Colour.WHITE, Integer.parseInt(args[1].substring(1)), args[1].charAt(0) - 'a');
        board[Integer.parseInt(args[2].substring(1)) - 1][args[2].charAt(0) - 'a'] =
                new Rook(Colour.BLACK, Integer.parseInt(args[2].substring(1)), args[2].charAt(0) - 'a');
        result = game.play(new ChessBoard(8, 'h', board, Colour.WHITE));
        System.out.println("Game result: " + result);
        for (Player player : players) {
            if (player instanceof HumanPlayer) {
                try {
                    ((HumanPlayer) player).endOfGame();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
