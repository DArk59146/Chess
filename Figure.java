public interface Figure {
    boolean isValidMove(int endRow, char endColumn);
    boolean canJump();
    Colour getColour();
}
