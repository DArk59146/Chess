import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MoveReader implements Closeable {
    private FastScanner scanner;

    public MoveReader(InputStream in) {
        try {
            scanner = new FastScanner(in, StandardCharsets.UTF_8, new RequiredSymbols() {
                @Override
                public boolean isRequiredSymbol(int c) {
                    return true;
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String nextMove() {
        try {
            return scanner.nextToSpace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}