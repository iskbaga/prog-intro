package markup;

import java.util.List;

public class ListItem {
    private List<Item> list;

    public ListItem(List<Item> l) {
        list = l;
    }

    public void toHtml(StringBuilder sb) {
        sb.append("<li>");
        for (Item symbol : list) {
            symbol.toHtml(sb);
        }
        sb.append("</li>");
    }
}
