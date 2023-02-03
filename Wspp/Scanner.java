import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;


public class Scanner implements AutoCloseable {
    private Reader reader;
    private final int BUFFER_SIZE = 128;
    private int size = 0;
    private int pos = 0;
    private boolean lineEnd = false;
    private char[] buffer = new char[BUFFER_SIZE];

    public Scanner(File file) throws IOException {
        this.reader = new FileReader(file, StandardCharsets.UTF_8);

    }

    public Scanner(InputStream inputStream) {
        this.reader = new InputStreamReader(inputStream);
    }

    public Scanner(String s) {
        this.reader = new StringReader(s);
    }

    private static boolean wordChar(char s) {
        return ((Character.DASH_PUNCTUATION == Character.getType(s)) || Character.isLetter(s) || s == '\'');
    }

    private static boolean intChar(char s) {
        return Character.isDigit(s) || s == '-' || s == '+' || s == 'o' || s == 'O';
    }

    private static boolean lineChar(char current) {
        return !(current == '\n' || current == '\r');
    }

    public static boolean correctSymbol(char current, Type type, boolean startSymbol) {
        switch (type) {
            case WORD -> {
                return wordChar(current);
            }
            case INT -> {
                return intChar(current);
            }
            case LINE -> {
                if (startSymbol) {
                    return true;
                } else {
                    return lineChar(current);
                }
            }
            default -> {
                return false;
            }
        }
    }

    public String token(Type type) throws IOException {
        StringBuilder tempStr = new StringBuilder();
        if (hasNextToken(type)) {
            while (pos < size && correctSymbol(buffer[pos], type, false) && lineChar(buffer[pos])) {
                tempStr.append(buffer[pos]);
                pos++;
                if (pos == size) {
                    read();
                }
            }
            if (pos >= size) {
                return tempStr.toString();
            } else {
                if (!correctSymbol(buffer[pos], type, false) && lineChar(buffer[pos])) {
                    return tempStr.toString();
                }
                if (!lineChar(buffer[pos])) {
                    lineEnd = true;
                    if (buffer[pos] == '\r') {
                        pos++;
                        if (pos == buffer.length) {
                            read();
                        }
                        if (buffer[pos] == '\n') {
                            pos++;
                        }
                    } else {
                        pos++;
                    }
                    return tempStr.toString();
                }
            }

        }
        throw new NoSuchElementException();
    }

    private boolean hasNextToken(Type type) throws IOException {
        if (pos == size) {
            read();
            if (size == -1) {
                return false;
            }
        }
        while (pos < size) {
            if (correctSymbol(buffer[pos], type, true)) {
                return true;
            } else {
                if (!lineChar(buffer[pos])) {
                    lineEnd = true;
                }
                pos++;
                if (pos == size) {
                    if (size == buffer.length) {
                        read();
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public int nextInt() throws IOException {
        String token = token(Type.INT);
        if (token.charAt(token.length() - 1) == 'o' || token.charAt(token.length() - 1) == 'O') {
            return Integer.parseUnsignedInt(token.substring(0, token.length() - 1), 8);
        } else {
            return Integer.parseInt(token);
        }
    }

    public String nextWord() throws IOException {
        return token(Type.WORD);
    }

    public String nextLine() throws IOException {
        return token(Type.LINE);
    }

    public boolean hasNext() throws IOException {
        return (hasNextToken(Type.INT) || hasNextToken(Type.WORD) || hasNextToken(Type.LINE));
    }

    public boolean hasNextLine() throws IOException {
        return hasNextToken(Type.LINE);
    }

    public boolean hasNextWord() throws IOException {
        return hasNextToken(Type.WORD);
    }

    public boolean hasNextInt() throws IOException {
        return hasNextToken(Type.INT);
    }

    public boolean isLineEnd() {
        if (lineEnd) {
            lineEnd = false;
            return true;
        }
        return false;
    }

    private void read() throws IOException {
        buffer = new char[BUFFER_SIZE];
        size = reader.read(buffer);
        pos = 0;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException ignored) {
        }
    }

    public enum Type {
        WORD, INT, LINE
    }
}