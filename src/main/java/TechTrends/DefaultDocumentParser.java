package TechTrends;

import lombok.NonNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DefaultDocumentParser implements DocumentParser {
    private static final Logger logger = LoggerFactory.getLogger(DefaultDocumentParser.class);

    private final String url;

    public DefaultDocumentParser(@NonNull String url) {
        this.url = url;
    }

    @Override
    public ParsedDocument parse() {
        logger.info("parsing document for url: {}", url);
        checkUrl(url);

        try {
            Document document = Jsoup.connect(url).get();
            return new ParsedDocument(document);
        } catch (IOException ioe) {
            logger.error("Error while trying to read the URL", ioe);
            throw new DocumentParseException("Errpr while trying to read url", ioe);
        }
    }

    private void checkUrl(String url) {
        if (url.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
