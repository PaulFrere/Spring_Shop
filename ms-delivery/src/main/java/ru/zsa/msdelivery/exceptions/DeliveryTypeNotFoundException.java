package ru.zsa.msdelivery.exceptions;

public class DeliveryTypeNotFoundException extends RuntimeException{
    public DeliveryTypeNotFoundException(String msg) {
        super(msg);
    }
}
