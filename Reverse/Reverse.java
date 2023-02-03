import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class Reverse {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> allStrings = new ArrayList<ArrayList<Integer>>();
        int a;
        while (sc.hasNextLine()) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            Scanner ls = new Scanner(sc.nextLine());
            while (ls.hasNextInt()) {
                a = ls.nextInt();
                row.add(a);
            }
            allStrings.add(row);
            //System.err.println(row);
        }
        for (int i = allStrings.size() - 1; i >= 0; i--) {
            for (int j = (allStrings.get(i)).size() - 1; j >= 0; j--) {
                System.out.print((allStrings.get(i)).get(j));
                System.out.print(" ");
                //System.err.print((allStrings.get(i)).get(j));
                //System.err.print(" ");
            }
            System.out.println();
            //System.err.println();
        }
    }
}