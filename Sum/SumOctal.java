public final class SumOctal {
    public static void main(String[] args) {
        int ans = 0;
        for (int i = 0; i < args.length; i++) {
            ans += func(args[i]);
        }
        System.out.println(ans);
    }

    public static int func(String s) {
        int summ = 0;
        char[] ch = s.toCharArray();
        int index = 0;
        while (index < s.length()) {
            if (!Character.isWhitespace(ch[index])) {
                int k = index;
                while (k < s.length() && !Character.isWhitespace(ch[k])) {
                    k++;
                }
                if ((ch[k - 1] == 'o') || (ch[k - 1] == 'O')) {
                    summ += Integer.parseUnsignedInt(s.substring(index, k - 1), 8);
                } else {
                    summ += Integer.parseInt(s.substring(index, k));
                }
                index = k;
            } else {
                index++;
            }
        }
        return summ;
    }
}