package sample;

public enum TagEntity {
  Word(".wotd-item-headword__word h1", new HtmlElementMethod.GetHtml()),
  Description(".wotd-item-headword__pos p:last-child", new HtmlElementMethod.GetHtmlExtractDataFromChildren()),
  More_Info(".wotd-item-headword__anchors-link[href]", new HtmlElementMethod.GetHref());

  private String selector;
  private HtmlElementMethod.TreeElementAction treeElementAction;

  TagEntity(String selector, HtmlElementMethod.TreeElementAction treeElementAction) {
    this.selector = selector;
    this.treeElementAction = treeElementAction;
  }

  public String getSelector() {
    return selector;
  }

  public HtmlElementMethod.TreeElementAction getTreeElementAction() {
    return treeElementAction;
  }
}
