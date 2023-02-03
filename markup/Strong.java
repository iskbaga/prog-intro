package markup;
import java.util.List;

public class Strong extends AbstractMarkup {
    public Strong(List<Markup> list) {
        super(list, "__", "<strong>", "</strong>");
    }
}