package TechTrends;

import lombok.NonNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ParsedDocument {
    private final Document document;
    private List<Post> topLevelPosts;

    @Inject
    public ParsedDocument(@NonNull Document document) {
        this.document = document;
    }

    public String getTitle() {
        return document.title();
    }

    public List<Post> fetchTopLevelPosts() {
        List<String> linksToAllComments = findAllComments();
        List<String> linksToTopLevelComments = filterTopLevelComments(linksToAllComments);
        return topLevelPosts;
    }

    private List<String> filterTopLevelComments(@NonNull List<String> linksToAllComments) {
        List<String> clonedLinkCollection = new ArrayList<>(linksToAllComments);
        for(String linkToComment: linksToAllComments) {

        }
        return clonedLinkCollection;
    }

    private List<String> findAllComments() {
        List<String> result = new ArrayList<>();
        Elements allComments = document.select("span.comment");
        for (Element element: allComments) {
            Elements linkElements = element.select("a[href]:contains(link)");
            if (linkElements.size() > 0) {
                String linkText = linkElements.get(0).attr("href");
                result.add(linkText);
            }
        }
        return result;
    }
}
