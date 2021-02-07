import java.io.IOException;
import java.io.PrintStream;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final MoveReader in;

    public HumanPlayer(final PrintStream out, final MoveReader in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new MoveReader(System.in));
    }

    @Override
    public Move move(final Position position, final Colour colour) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(colour + "'s move");
            out.println("Enter move");
            String start = in.nextMove();
            String end = in.nextMove();
            int startRow = Integer.parseInt(start.substring(1)) - 1;
            char startColumn = start.charAt(0);
            int endRow = Integer.parseInt(end.substring(1)) - 1;
            char endColumn = end.charAt(0);
            return new Move(startRow, startColumn, endRow, endColumn);
        }
    }

    public void endOfGame() throws IOException {
        in.close();
    }
}
