package markup;

public class Text extends AbstractMarkup {
    public Text(String str) {
        super(str);
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(str);
    }

    public void toHtml(StringBuilder sb) {
        sb.append(str);
    }

}