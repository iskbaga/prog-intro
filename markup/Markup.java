package markup;

public interface Markup {
    void toMarkdown(StringBuilder sb);

    void toHtml(StringBuilder sb);
}