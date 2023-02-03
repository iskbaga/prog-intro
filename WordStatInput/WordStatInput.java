import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;


public class WordStatInput {
    public static void main(String[] args) {
        Map<String, Integer> words = new LinkedHashMap<>();
        try (BufferedReader reader = readerFromFile(args[0])) {
            String line = reader.readLine();
            while (line != null) {
                char[] ch = line.toCharArray();
                int index = 0;
                while (index < line.length()) {
                    int k = 1;
                    if (correctSymbol(ch[index])) {
                        k = 0;
                        while (correctSymbol(ch[index + k])) {
                            k++;
                            if (index + k == line.length()) {
                                break;
                            }
                        }
                        String key = (line.substring(index, index + k)).toLowerCase();
                        if (!words.containsKey(key)) {
                            words.put(key, 1);
                        } else {
                            words.put(key, words.get(key) + 1);
                        }
                    }
                    index += k;
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
        }
        try (BufferedWriter writer = writerFromFile(args[1])) {
            for (Map.Entry<String, Integer> entry : words.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("File writing error: " + e.getMessage());
        }
    }


    private static BufferedReader readerFromFile(String filePath)
            throws FileNotFoundException, UnsupportedEncodingException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        return new BufferedReader(inputStreamReader);

    }


    private static BufferedWriter writerFromFile(String filePath)
            throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        return new BufferedWriter(outputStreamWriter);
    }


    public static boolean correctSymbol(char x) {
        boolean flag = Character.DASH_PUNCTUATION == Character.getType(x) || Character.isLetter(x) || x == '\'';
        return flag;
    }

}