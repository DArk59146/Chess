public interface Position {
    boolean isValid(Move move);
    int getRow();
    int getColumn();
}