import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.TreeMap;
import java.util.Map;


public class WordStatWordsPrefix {
    public static void main(String[] args) {
        Map<String, Integer> words = new TreeMap<>();
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextWord()) {
                String word = scanner.nextWord();
                String key;
                if (word.length() > 0) {
                    if (word.length() < 3) {
                        key = (word.toLowerCase());
                    } else {
                        key = (word.substring(0, 3)).toLowerCase();
                    }
                } else {
                    continue;
                }
                if (!words.containsKey(key)) {
                    words.put(key, 1);
                } else {
                    words.put(key, words.get(key) + 1);
                }
            }

        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }
        try (BufferedWriter writer = writerFromFile(new File(args[1]))) {
            for (Map.Entry<String, Integer> entry : words.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
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