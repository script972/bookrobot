package utils;

public class InvalidData extends Exception {

    public InvalidData() {
        super();
    }

    public InvalidData(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidData(String message) {
        super(message);
    }
}
