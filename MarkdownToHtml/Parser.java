package md2html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private final String inputFileName;
    private final String outputFileName;

    public Parser(String inputFile, String outputFile) {
        this.inputFileName = inputFile;
        this.outputFileName = outputFile;
    }

    private int checkCounter(String s) {
        int lvl = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                lvl++;
            } else {
                if (!Character.isWhitespace(s.charAt(i))) {
                    lvl = 0;
                }
                break;
            }
        }
        return lvl;
    }

    private String checkSpecialSymbol(char chr) {
        Map<Character, String> specialSymbols = new HashMap<>();
        specialSymbols.put('<', "&lt;");
        specialSymbols.put('>', "&gt;");
        specialSymbols.put('&', "&amp;");
        for (Character key : specialSymbols.keySet()) {
            if (chr == key) {
                return specialSymbols.get(key);
            }
        }
        return Character.toString(chr);
    }

    private String parseHead(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = checkCounter(s);
        if (cnt > 0) {
            sb.append("<h");
            sb.append(cnt);
            sb.append(">");
            sb.append(s.substring(cnt + 1));
            sb.append("</h");
            sb.append(cnt);
            sb.append(">");
        } else {
            sb.append("<p>");
            sb.append(s);
            sb.append("</p>");
        }
        return sb.toString();
    }

    private String parseToHtml(String s) {
        Map<String, String> mdToHtml = new HashMap<>(); // :NOTE: move to constatnt
        mdToHtml.put("__", "strong>");
        mdToHtml.put("**", "strong>");
        mdToHtml.put("--", "s>");
        mdToHtml.put("*", "em>");
        mdToHtml.put("`", "code>");
        mdToHtml.put("``", "code>");
        mdToHtml.put("_", "em>");
        Map<String, Boolean> mdPos = new HashMap<>();
        mdPos.put("__", false);
        mdPos.put("**", false);
        mdPos.put("--", false);
        mdPos.put("*", false);
        mdPos.put("`", false);
        mdPos.put("``", false);
        mdPos.put("_", false);
        Map<String, String> imageToHtml = new HashMap<>();
        imageToHtml.put("![", "<img alt='");
        imageToHtml.put("](", "' src='");
        imageToHtml.put(")", "'>");
        boolean inImage = false;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            for (String key : imageToHtml.keySet()) {
                if (i + 1 <= s.length() && s.startsWith(key, i)) {
                    if (inImage || key.equals("![")) {
                        sb.append(imageToHtml.get(key));
                        i += key.length();
                        inImage = !key.equals(")");
                        break;
                    }
                }
            }
            if (!inImage) {
                if (i + 1 < s.length() && s.charAt(i) == '\\') { // :NOTE: выделдить
                    sb.append(s.charAt(i + 1));
                    i += 2;
                    continue;
                }
                if (i < s.length() &&
                    ((s.charAt(i) == '*' && !mdPos.get("*")) ||
                        (s.charAt(i) == '_' && !mdPos.get("_"))) &&
                    (i + 1 == s.length() || Character.isWhitespace(s.charAt(i + 1)) ||
                        isLineSeparator(s.charAt(i + 1)))) {
                    sb.append(s.charAt(i));
                    i++;
                    continue;
                }
                if (i < s.length() && isMarkdownSymbol(s.charAt(i))) {
                    String key;
                    if (i + 1 < s.length() && s.charAt(i + 1) == s.charAt(i)) { // :NOTE: нечитаемо(
                        key = s.substring(i, i + 2);
                        if (!mdPos.get(key)) {
                            sb.append("<");
                            mdPos.put(key, true);
                        } else {
                            sb.append("</");
                            mdPos.put(key, false);
                        }
                        sb.append(mdToHtml.get(key));
                        i += 2;
                    } else if (mdToHtml.containsKey(s.substring(i, i + 1))) {
                        key = s.substring(i, i + 1);
                        if (!mdPos.get(key)) {
                            sb.append("<");
                            mdPos.put(key, true);
                        } else {
                            sb.append("</");
                            mdPos.put(key, false);
                        }
                        sb.append(mdToHtml.get(key));
                        i++;
                    } else {
                        sb.append(checkSpecialSymbol(s.charAt(i)));
                        i++;
                    }
                    continue;
                }
            }
            if (i < s.length()) {
                sb.append(checkSpecialSymbol(s.charAt(i)));
                i++;
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public void parse() {
        StringBuilder sb = new StringBuilder();
        try {
            File x = new File(inputFileName);
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8));
            Scanner sc = new Scanner(x);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.isEmpty()) {
                    if (sb.length() != 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        String s = sb.toString();
                        str = parseHead(parseToHtml(s)) + "\n";
                        writer.write(str);
                        sb.setLength(0);
                    }

                } else {
                    sb.append(str).append('\n');
                }
            }

            if (!sb.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
                String s = sb.toString();
                String str = (parseHead(parseToHtml(s)));
                writer.write(str);
            }

            writer.close();
            sc.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean isMarkdownSymbol(char s) {
        return s == '*' || s == '_' || s == '`' || s == '-';
    }

    private static boolean isLineSeparator(char s) {
        return s == '\n' || s == '\r';
    }

}