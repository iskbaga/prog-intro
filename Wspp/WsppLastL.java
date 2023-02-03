import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;


public class WsppLastL {
    public static void main(String[] args) {
        Map<String, IntList> words = new LinkedHashMap<>();
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        Set<String> tempWords = new HashSet<>();
        int pos = 1;
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextWord()) {
                if (scanner.isLineEnd()) {
                    tempWords = new HashSet<>();
                    pos = 1;
                }
                String key = scanner.nextWord().toLowerCase();
                if (!(words.containsKey(key))) {
                    IntList a = new IntList();
                    a.add(pos);
                    words.put(key, a);
                    wordCount.put(key, 0);
                    tempWords.add(key);
                } else if (!tempWords.contains(key)) {
                    words.get(key).add(pos);
                    tempWords.add(key);
                } else {
                    words.get(key).arr[words.get(key).size - 1] = pos;
                }
                wordCount.put(key, wordCount.get(key) + 1);
                pos++;
            }
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        try (BufferedWriter writer = writerFromFile(new File(args[1]))) {
            for (String key : words.keySet()) {
                writer.write(key);
                writer.write(" " + wordCount.get(key));
                int i = 0;
                while (i < words.get(key).arr.length && words.get(key).arr[i] != 0) {
                    writer.write(" " + words.get(key).arr[i]);
                    i++;
                }
                writer.newLine();
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("File writing error: " + e.getMessage());
        }
    }

    private static BufferedWriter writerFromFile(File filePath)
            throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        return new BufferedWriter(outputStreamWriter);
    }
}