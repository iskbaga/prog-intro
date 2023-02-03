import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;



public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] up = new int[m];
        int[] down = new int[m];
        char[][] a = new char[n][m];
        int aN = 0;
        int aM = 0;
        int maxS;
        int x1;
        int x2;
        int y1;
        int y2;
        int minUp;
        int minDown;
        for (int i = 0; i < n; i++) {
            String str = scan.nextLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = str.charAt(j);
                if (a[i][j] == 'A') {
                    aN = i;
                    aM = j;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            while (aN > up[j] && a[aN - up[j] - 1][j] == '.') {
                up[j]++;
            }
            while (aN < n - down[j] - 1 && a[aN + down[j] + 1][j] == '.') {
                down[j]++;
            }
        }
        maxS = 1;
        x1 = aM;
        x2 = aM;
        y1 = aN;
        y2 = aN;
        minUp = n + 1;
        minDown = n + 1;
        for (int left = aM; left >= 0; left--) {
            if (a[aN][left] != 'A' && a[aN][left] != '.') {
                break;
            }
            minUp = Math.min(minUp, up[left]);
            minDown = Math.min(minDown, down[left]);
            for (int right = aM; right < m; right++) {
                if (a[aN][right] != 'A' && a[aN][right] != '.') {
                    break;
                }
                int s = (right - left + 1) * (minUp + minDown + 1);
                if (maxS < s) {
                    maxS = s;
                    x1 = left;
                    x2 = right;
                    y1 = aN - minUp;
                    y2 = aN + minDown;
                }
            }
        }
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (a[i][j] != 'A') {
                    a[i][j] = 'a';
                }
            }
        }
        a = stretching(a, 0, y1, 0, m, m - 1, -1, false);
        a = stretching(a, 0, m, 0, y1, y1 - 1, -1, true);
        a = stretching(a, y2 + 1, n, 0, m, m - 1, -1, false);
        a = stretching(a, 0, m, y2 + 1, n, n - 1, y2, true);
        a = stretching(a, y1, y2 + 1, 0, x1, x1 - 1, -1, false);
        a = stretching(a, 0, x1, y1, y2 + 1, y2, y1 - 1, true);
        a = stretching(a, y1, y2 + 1, x2 + 1, m, m - 1, x2, false);
        a = stretching(a, x2 + 1, m, y1, y2 + 1, y2, y1 - 1, true);

        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < m; j++) {
                s.append(a[i][j]);
            }
            System.out.println(s);
        }
    }

    public static char[][] stretching(char[][] a, int f1, int f2, int f3, int f4, int f5, int f6, boolean sidePick) {
        if (sidePick) {
            for (int j = f1; j < f2; j++) {
                char c = ' ';
                for (int i = f3; i < f4; i++) {
                    if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                        c = Character.toLowerCase(a[i][j]);
                    }
                    if (a[i][j] == 'A' || a[i][j] == 'a') {
                        c = ' ';
                    }
                    if (c != ' ' && a[i][j] == '.') {
                        a[i][j] = c;
                    }
                }
                for (int i = f5; i > f6; i--) {
                    if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                        c = Character.toLowerCase(a[i][j]);
                    }
                    if (a[i][j] == 'A' || a[i][j] == 'a') {
                        c = ' ';
                    }
                    if (c != ' ' && a[i][j] == '.') {
                        a[i][j] = c;
                    }
                }
            }
        } else {
            for (int i = f1; i < f2; i++) {
                char c = ' ';
                for (int j = f3; j < f4; j++) {
                    if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                        c = Character.toLowerCase(a[i][j]);
                    }
                    if (a[i][j] == 'A' || a[i][j] == 'a') {
                        c = ' ';
                    }
                    if (c != ' ' && a[i][j] == '.') {
                        a[i][j] = c;
                    }
                }
                for (int j = f5; j > f6; j--) {
                    if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                        c = Character.toLowerCase(a[i][j]);
                    }
                    if (a[i][j] == 'A' || a[i][j] == 'a') {
                        c = ' ';
                    }
                    if (c != ' ' && a[i][j] == '.') {
                        a[i][j] = c;
                    }
                }
            }
        }
        return a;
    }
}
class Scanner implements AutoCloseable {
    private final Reader reader;
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
        return Character.isDigit(s);
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
        return Integer.parseInt(token(Type.INT));
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