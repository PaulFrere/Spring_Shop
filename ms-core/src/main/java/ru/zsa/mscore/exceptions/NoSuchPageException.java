package ru.zsa.mscore.exceptions;

public class NoSuchPageException extends RuntimeException {
    public NoSuchPageException(String message) {
        super(message);
    }
}
