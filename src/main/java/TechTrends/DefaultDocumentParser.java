package TechTrends;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DefaultDocumentParser implements DocumentParser {
    private static final Logger logger = LoggerFactory.getLogger(DefaultDocumentParser.class);

    private final String url;

    public DefaultDocumentParser(String url) {
        this.url = url;
    }

    @Override
    public ParsedDocument parse(String url) {
        logger.info("parsing document for url: {}", url);
        checkUrl(url);

        try {
            Document document = Jsoup.connect(url).get();
            return new ParsedDocument(document);
        } catch (IOException ioe) {
            logger.error("Error while trying to read the URL", ioe);
        }

        return null;
    }

    private void checkUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
