import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int aN=0;
        int aM=0;
        char[][] x = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = scan.nextLine();
            for (int j = 0; j < m; j++) {
                x[i][j] = str.charAt(j);
                if(x[i][j]=='A'){
                    aN=i;
                    aM=j;
                }
            }
        }
        int right=aM;
        int left=aM;
        while(right<m-1 && !Character.isUpperCase(x[aN][right+1])){
            right++;
        }
        while(left>0 && !Character.isUpperCase(x[aN][left-1])){
            left--;
        }
        int[] up= new int[right-left+1];
        int[] down= new int[right-left+1];
        for(int i=left;i<=right;i++){
            while(up[i-left]<aN && x[(aN-up[i-left])-1][i]=='.'){
                up[i-left]++;
            }
            while(down[i-left]<n-aN-1 && x[(aN+down[i-left])+1][i]=='.'){
                down[i-left]++;
            }
        }
        int maxS = 1;
        int x1 = aM;
        int x2 = aM;
        int y1 = aN;
        int y2 = aN;
        int minUp = n+1;
        int minDown = n+1;
        for (int l = aM; l >= 0; l--) {
            if (x[aN][l] != 'A' && x[aN][l] != '.') {
                break;
            }
            minUp = Math.min(minUp, up[l-left]);
            minDown = Math.min(minDown, down[l-left]);
            for (int r = aM; r < m; r++) {
                if (x[aN][r] != 'A' && x[aN][r] != '.') {
                    break;
                }
                minUp = Math.min(minUp, up[r-left]);
                minDown = Math.min(minDown, down[r-left]);
                int s=(r - l + 1) * (minUp + minDown + 1);
                if (maxS<s) {
                    maxS = s;
                    x1 = l;
                    x2 = r;
                    y1 = aN - minUp;
                    y2 = aN + minDown;
                }
            }
        }
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (x[i][j] != 'A') {
                    x[i][j] = 'a';
                }
            }
        }

        for (int i = 0; i < y1; i++) {
            char c=' ';
            for (int j = 0; j < m; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int j = 0; j <= m-1; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            char c=' ';
            for (int i = 0; i < y1; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int i = 0; i <= y1 - 1; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }

        for (int i = y2 + 1; i < n; i++) {
            char c=' ';
            for (int j = 0; j < m; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int j = 0; j <= m - 1; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            char c=' ';
            for (int i = y2 + 1; i < n; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int i = y2 + 1; i <= n-1; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }

        for (int i = y1; i <= y2; i++) {
            char c=' ';
            for (int j = 0; j < x1; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int j = 0; j <= x1 - 1; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }
        for (int j = 0; j < x1; j++) {
            char c=' ';
            for (int i = y1; i <= y2; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int i = y1; i <= y2; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }

        for (int i = y1; i <= y2; i++) {
            char c=' ';
            for (int j = x2 + 1; j < m; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int j = x2 + 1; j <=m - 1; j++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }
        for (int j = x2 + 1; j < m; j++) {
            char c=' ';
            for (int i = y1; i <= y2; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
            for (int i = y1; i <=y2; i++) {
                if (x[i][j] != '.' && x[i][j] != 'A' && x[i][j] != 'a') {
                    c = Character.toLowerCase(x[i][j]);
                }
                if (c != ' ' && x[i][j] == '.') {
                    x[i][j] = c;
                }
            }
        }




        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < m; j++) {
                s.append(x[i][j]);
            }
            System.out.println(s);
        }
    }
}


class Scanner implements AutoCloseable {
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