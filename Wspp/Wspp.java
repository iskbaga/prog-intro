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


public class Wspp {
    public static void main(String[] args) {
        Map<String, IntList> words = new LinkedHashMap<>();
        int pos = 1;
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextWord()) {
                String key = scanner.nextWord().toLowerCase();
                if (!words.containsKey(key)) {
                    IntList a = new IntList();
                    a.add(0);
                    a.add(pos);
                    words.put(key, a);
                } else {
                    words.get(key).add(pos);
                }
                words.get(key).arr[0]++;
                pos++;
            }
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }

        try (BufferedWriter writer = writerFromFile(new File(args[1]))) {
            for (String key : words.keySet()) {
                writer.write(key);
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