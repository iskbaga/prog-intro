package markup;
import java.util.List;

public class Paragraph implements Item {
    private List<Markup> list;

    public Paragraph(List<Markup> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder sb) {
        for (var item : list) {
            item.toMarkdown(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        for (var item : list) {
            item.toHtml(sb);
        }
    }
}