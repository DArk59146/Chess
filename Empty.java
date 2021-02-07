public class Empty extends AbstractFigure {

    public Empty(int row, int column) {
        super(Colour.COLORLESS, row, column);
    }

    @Override
    public boolean isValidMove(int endRow, char endColumn) {
        return false;
    }

    @Override
    public String toString() {
        return ". ";
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
