package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Extractor {
  private final String ADDRESS = "https://www.dictionary.com/e/word-of-the-day/";
  private Document htmlDom;

  public Map<TagEntity, String> extract(List<TagEntity> tagEntities) {
    try {
      htmlDom = Jsoup.connect(ADDRESS).get();
      return tagEntities.stream().collect(Collectors.toMap(Function.identity(), this::processElement));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Collections.emptyMap();
    }
  }

  private String processElement(TagEntity tagEntity) {
    Element elementToProcess = htmlDom.select(tagEntity.getSelector()).iterator().next();
    return tagEntity.getTreeElementAction().execute(elementToProcess);
  }
}
