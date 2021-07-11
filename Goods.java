package com.company;

import java.time.LocalDate;

public class Goods implements Comparable<Goods>{
    private int goodsID;
    private String GoodsName;
    private double deliveryPrice;
    private GoodsType goodsType;
    private LocalDate expirationDate;
    private int countGoods;

    public Goods(int goodsID, String goodsName, double deliveryPrice, GoodsType goodsType, LocalDate expirationDate, int countGoods) {
        this.goodsID = goodsID;
        this.GoodsName = goodsName;
        this.deliveryPrice = deliveryPrice;
        this.goodsType = goodsType;
        this.expirationDate = expirationDate;
        this.countGoods = countGoods;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(int goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() { return GoodsName; }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCountGoods() { return countGoods; }

    public void setCountGoods(int countGoods) { this.countGoods = countGoods; }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsID=" + goodsID +
                ", GoodsName='" + GoodsName + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", goodsType=" + goodsType +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public void enoughGoods(int goodsToBuy) throws NotEnoughGoodsException{
        if(this.countGoods<goodsToBuy){
            throw new NotEnoughGoodsException("There are not enough goods of this kind.");
        }
    }

    @Override
    public int compareTo(Goods o) {

        if(this.getGoodsID()<(o.getGoodsID())){
            return 1;
        }else if(this.getGoodsID()>(o.getGoodsID())){
            return -1;
        }else return 0;
    }
}
