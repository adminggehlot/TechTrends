package TechTrends;

public class DocumentParseException extends RuntimeException {
    public DocumentParseException(String message) {
        super(message);
    }

    public DocumentParseException(String message, Exception e) {
        super(message, e);
    }
}
