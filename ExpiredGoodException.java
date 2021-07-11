package com.company;

public class ExpiredGoodException extends Exception{

    public ExpiredGoodException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ExpiredGoodException{}";
    }
}
