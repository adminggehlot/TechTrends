package TechTrends;

import lombok.NonNull;
import org.jsoup.nodes.Document;

import javax.inject.Inject;
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

        return topLevelPosts;
    }
}
