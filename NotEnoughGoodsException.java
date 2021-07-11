package com.company;

public class NotEnoughGoodsException extends Exception {

    public NotEnoughGoodsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "NotEnoughGoodsException{}";
    }
}
