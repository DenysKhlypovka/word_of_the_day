package sample;

import org.jsoup.nodes.Element;

public class HtmlElementMethod {

  public interface TreeElementAction {
    String execute(Element data);
  }

  public static class GetHref implements TreeElementAction {
    public String execute(Element data) {
      return data.attr("href");
    }
  }

  public static class GetHtml implements TreeElementAction {
    public String execute(Element data) {
      return data.html();
    }
  }

  public static class GetHtmlExtractDataFromChildren implements TreeElementAction {
    public String execute(Element data) {
      return data.html().replaceAll("<[^>]*>", "");
    }
  }
}
