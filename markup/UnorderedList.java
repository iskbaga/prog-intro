package markup;

import java.util.List;

public class UnorderedList implements Item {
    List<ListItem> list;

    public UnorderedList(List<ListItem> l) {
        list = l;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<ul>");
        for (ListItem symbol : list) {
            symbol.toHtml(sb);
        }
        sb.append("</ul>");
    }

}
