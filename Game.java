import java.util.List;
public class Game {
    private final boolean log;
    private final List<Player> players;
    private final int numberOfPlayers;

    public Game(final boolean log, final List<Player> players) {
        this.log = log;
        this.players = players;
        numberOfPlayers = players.size();
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < numberOfPlayers; i++) {
                final int result = move(board, players.get(i), i + 1);
                if (result != -1) {
                    System.out.println(board);
                    return result;
                }
            }
        }

    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getTurn());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            return -1;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
