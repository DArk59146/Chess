import java.util.LinkedList;
import java.util.List;

public class ChessBoard implements Board {
    private Position position;
    private final int rowSize;
    private final char columnSize;
    private final Figure[][] board;
    private Colour turn;
    private List<Figure> playingBlackFigure;
    private List<Figure> playingWhiteFigure;

    public ChessBoard(int rowSize, char columnSize, Figure[][] startPosition, Colour startTurn) {
        this.position = new ChessPosition();
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        playingBlackFigure = new LinkedList<>();
        playingWhiteFigure = new LinkedList<>();
        board = startPosition;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < columnSize - 'a'; c++) {
                Figure test1 = board[r][c];
                if (!(test1 instanceof Empty)) {
                    if (test1.getColour() == Colour.WHITE) {
                        playingWhiteFigure.add(test1);
                    } else {
                        System.out.println(r + " " + c + test1.getColour());
                        playingBlackFigure.add(test1);
                    }
                }
            }
        }
        turn = startTurn;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Result makeMove(Move move) {
        if (!position.isValid(move)) {
            return Result.LOSE;
        } else {
            Figure figure = board[move.getStartRow()][move.getStartColumn() - 'a'];
            board[move.getStartRow()][move.getStartColumn() - 'a'] = new Empty(move.getStartRow(), move.getStartColumn());
            board[move.getEndRow()][move.getEndColumn() - 'a'] = figure;
            for (int i = 0; i < rowSize; i++) {
                for (int k = 0; k < columnSize - 'a' + 1; k++) {
                    Figure test = board[i][k];
                    if (test instanceof King && test.getColour() != turn) {
                        boolean isCheckMate = true;
                        if (turn == Colour.BLACK) {
                            for (int r = -1; r < 2; r++) {
                                for (int c = -1; c < 2; c++) {
                                    if (!canEat(i + r, k + c, playingWhiteFigure)) {
                                        isCheckMate = false;
                                    }
                                }
                            }
                        } else {
                            for (int r = -1; r < 2; r++) {
                                for (int c = -1; c < 2; c++) {
                                    if (!canEat(i + r, k + c, playingBlackFigure)) {
                                        isCheckMate = false;
                                    }
                                }
                            }
                        }
                        if (isCheckMate) return Result.WIN;
                        break;
                    }
                }
            }
            nextTurn();
            return Result.UNKNOWN;
        }
    }

    @Override
    public Colour getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    private boolean canEat(int r, int c, List<Figure> playingWhiteFigure) {
        if (r < 0 || c < 0 || r > rowSize || c > columnSize) {
            return false;
        } else {
            for (Figure figure : playingWhiteFigure) {
                if (figure.isValidMove(r, Character.toChars(c + 'a')[0])) {
                    return true;
                };
            }
        }
        return false;
    }

    @Override
    public void nextTurn() {
        if (turn == Colour.WHITE) {
            turn = Colour.BLACK;
        } else {
            turn = Colour.WHITE;
        }
    }

    private class ChessPosition implements Position {

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("  ");
            for (int i = 0; i <= columnSize - 'a'; i++) {
                sb.append(Character.toChars(i + 'a')[0]).append(" ");
            }
            sb.append('\n');
            int i = 1;
            for (Figure[] figures : board) {
                sb.append(i++).append(" ");
                for (Figure figure : figures) {
                    sb.append(figure.toString());
                }
                sb.append('\n');
            }
            return sb.toString();
        }

        @Override
        public boolean isValid(Move move) {
            Figure figure = board[move.getStartRow()][move.getStartColumn() - 'a'];
            if (!figure.canJump()) {
                if (move.getStartRow() == move.getEndRow()) {
                    int row = move.getStartRow();
                    int start = Math.min(move.getStartColumn(), move.getEndColumn()) - 'a';
                    int end = Math.max(move.getStartColumn(), move.getEndColumn()) - 'a';
                    for (int i = start + 1; i < end; i++) {
                        if (!(board[row][i] instanceof Empty)) {
                            return false;
                        }
                    }
                } else {
                    int column = move.getStartColumn();
                    int start = Math.min(move.getStartRow(), move.getEndRow());
                    int end = Math.max(move.getStartRow(), move.getEndRow());
                    for (int i = start + 1; i < end; i++) {
                        if (!(board[i][column - 'a'] instanceof Empty)) return false;
                    }
                }
            }
            System.out.println(move.getEndRow() + " " +  move.getEndColumn());
            return board[move.getEndRow()][move.getEndColumn() - 'a'].getColour() != figure.getColour() &&
                    figure.isValidMove(move.getEndRow(), move.getEndColumn());
        }

        @Override
        public int getRow() {
            return rowSize;
        }

        @Override
        public int getColumn() {
            return columnSize - 'a' + 1;
        }
    }
}
