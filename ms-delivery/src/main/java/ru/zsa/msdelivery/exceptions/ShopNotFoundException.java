package ru.zsa.msdelivery.exceptions;

public class ShopNotFoundException extends RuntimeException{
    public ShopNotFoundException(String msg) {
        super(msg);
    }
}
