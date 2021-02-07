public interface Board {
    Position getPosition();
    Result makeMove(Move move);
    Colour getTurn();
    void nextTurn();
}
