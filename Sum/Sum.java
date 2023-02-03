public final class Sum {
    public static void main(String[] args) {
        int ans = 0;
        for (int i = 0; i < args.length; i++) {
            ans += func(args[i]);
        }
        System.out.println(ans);
    }

    public static int func(String s) {
        int summ = 0;
        String number = "";
        char[] ch = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(ch[i])) {
                number += ch[i];
                if (i == s.length() - 1 && number != "") {
                    summ += Integer.parseInt(number);
                }
            } else {
                if (number != "") {
                    summ += Integer.parseInt(number);
                    number = "";
                }
            }
        }
        return summ;
    }
}