package markup;

import java.util.List;

public class OrderedList implements Item {
    List<ListItem> list;

    public OrderedList(List<ListItem> l) {
        list = l;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<ol>");
        for (ListItem symbol : list) {
            symbol.toHtml(sb);
        }
        sb.append("</ol>");
    }

}
