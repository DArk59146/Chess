public final class Move {
    private final int startRow;
    private final char startColumn;
    private final int endRow;
    private final char endColumn;

    public Move(int startRow, char startColumn, int endRow, char endColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    public char getEndColumn() {
        return endColumn;
    }

    public char getStartColumn() {
        return startColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getStartRow() {
        return startRow;
    }

    @Override
    public String toString() {
        return startColumn + startRow + " to " + endColumn + endRow;
    }
}
