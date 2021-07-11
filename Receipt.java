package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Receipt {
    public static int currentNumber = 0;
    private Cashier cashier;
    private LocalDateTime issueDateAndTime;
    private List<Goods> goodsOnReceipt;


    public Receipt( Cashier cashier, LocalDateTime issueDateAndTime, List<Goods> goodsOnReceipt) {
        Receipt.currentNumber++;
        this.cashier = cashier;
        this.issueDateAndTime = issueDateAndTime;
        this.goodsOnReceipt = goodsOnReceipt;
    }

    public Receipt(LocalDateTime issueDateAndTime, List<Goods> goodsOnReceipt) {
        Receipt.currentNumber++;
        this.issueDateAndTime = issueDateAndTime;
        this.goodsOnReceipt = goodsOnReceipt;
    }

    public static int getCurrentNumber() { return currentNumber; }

    public Cashier getCashier() { return cashier; }

    public void setCashier(Cashier cashier) { this.cashier = cashier; }

    public LocalDateTime getIssueDateAndTime() { return issueDateAndTime; }

    public void setIssueDateAndTime(LocalDateTime issueDateAndTime) { this.issueDateAndTime = issueDateAndTime; }

    public List<Goods> getGoodsOnReceipt() { return goodsOnReceipt; }

    public void setGoodsOnReceipt(List<Goods> goodsOnReceipt) { this.goodsOnReceipt = goodsOnReceipt; }

    public String FormattedDateAndTime(){
        DateTimeFormatter formattedDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedData = formattedDateTime.format(issueDateAndTime);
        return formattedData;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "currentNumber=" + currentNumber +
                ", cashier=" + cashier +
                ", issueDateAndTime=" + issueDateAndTime +
                ", goodsOnReceipt=" + goodsOnReceipt +
                '}';
    }

}
