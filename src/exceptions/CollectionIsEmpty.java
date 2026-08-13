package exceptions;

public class CollectionIsEmpty extends RuntimeException {
    public CollectionIsEmpty(String message) {
        super(message);
    }
}
