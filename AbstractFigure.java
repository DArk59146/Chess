public abstract class AbstractFigure implements Figure {
    private final Colour colour;
    private int rowPosition;
    private int columnPosition;

    public AbstractFigure(Colour colour, int rowPosition, int columnPosition) {
        this.columnPosition = columnPosition;
        this.rowPosition = rowPosition;
        this.colour = colour;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    protected String toString(String figure) {
        if (getColour() == Colour.WHITE) {
            return figure + "W";
        } else {
            return figure + "B";
        }
    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
