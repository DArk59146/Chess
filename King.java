public class King extends AbstractFigure {

    public King(Colour colour, int row, int column) {
        super(colour, row, column);
    }

    @Override
    public boolean isValidMove(int endRow, char endColumn) {
        if (getRowPosition() - 1 == endRow) {
            return Math.abs(endColumn - getColumnPosition() - 'a') == 1;
        } else if (getColumnPosition() == endColumn - 'a') {
            return Math.abs(endRow - getRowPosition() + 1) == 1;
        } else {
            return Math.abs(endColumn - getColumnPosition()) == 1 && Math.abs(endRow - getRowPosition() + 1) == 1;
        }
    }

    @Override
    public String toString() {
        return toString("K");
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
