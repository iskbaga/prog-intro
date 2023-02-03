package markup;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMarkup implements Markup {
    private String markdownSign = "";
    private String htmlStart = "";
    private String htmlEnd = "";
    protected String str;
    protected List<Markup> list;

    public AbstractMarkup(String str) {
        this.str = str;
    }


    protected AbstractMarkup(List<Markup> list, String markdownSign, String htmlStart, String htmlEnd) {
        this.list = list;
        this.markdownSign = markdownSign;
        this.htmlStart = htmlStart;
        this.htmlEnd = htmlEnd;
    }

    public void toHtml(StringBuilder sb) {
        sb.append(htmlStart);
        for (Markup symbol : list) {
            symbol.toHtml(sb);
        }
        sb.append(htmlEnd);
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownSign);
        for (Markup symbol : list) {
            symbol.toMarkdown(sb);
        }
        sb.append(markdownSign);
    }
}