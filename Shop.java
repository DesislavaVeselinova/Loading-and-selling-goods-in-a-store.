package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Shop{
    private String shopName;
    private List<Goods> deliveredGoods;
    private List<Goods> soldGoods;
    private List<Cashier> cashierList;
    private List<Receipt> receiptList;
    private List<CashRegister> cashRegisterList;
    private double percentMarkupEatable;
    private double percentMarkupUneatable;
    private double percentReduction;
    private int daysUntilExpiration;

    public Shop(String shopName, List<Goods> deliveredGoods, List<Goods> soldGoods, List<Cashier> cashierList, List<Receipt> receiptList, List<CashRegister> cashRegisterList,double percentMarkup, double percentMarkupEatable, double percentMarkupUneatable, double percentReduction, int daysUntilExpiration) {
        this.shopName = shopName;
        this.deliveredGoods = deliveredGoods;
        this.soldGoods = soldGoods;
        this.cashierList = cashierList;
        this.receiptList = receiptList;
        this.cashRegisterList = cashRegisterList;
        this.percentMarkupEatable = percentMarkupEatable;
        this.percentMarkupUneatable = percentMarkupUneatable;
        this.percentReduction = percentReduction;
        this.daysUntilExpiration = daysUntilExpiration;
    }

    //SETTERS AND GETTERS
    public String getShopName() {
        return shopName;
    }
    public List<Cashier> getCashierList() { return cashierList; }
    public void setCashierList(List<Cashier> cashierList) { this.cashierList = cashierList; }
    public List<Receipt> getReceiptList() { return receiptList; }
    public void setReceiptList(List<Receipt> receiptList) { this.receiptList = receiptList; }
    public double getPercentReduction() { return percentReduction; }
    public void setPercentReduction(double percentReduction) { this.percentReduction = percentReduction; }
    public double getPercentMarkupEatable() {
        return percentMarkupEatable;
    }
    public void setPercentMarkupEatable(double percentMarkupEatable) { this.percentMarkupEatable = percentMarkupEatable; }
    public double getPercentMarkupUneatable() {
        return percentMarkupUneatable;
    }
    public void setPercentMarkupUneatable(double percentMarkupUneatable) { this.percentMarkupUneatable = percentMarkupUneatable; }
    public int getDaysUntilExpiration() { return daysUntilExpiration; }
    public void setDaysUntilExpiration(int daysUntilExpiration) { this.daysUntilExpiration = daysUntilExpiration; }
    public void setShopName(String shopName) { this.shopName = shopName; }
    public List<Goods> getDeliveredGoods() { return deliveredGoods; }
    public void setDeliveredGoods(List<Goods> deliveredGoods) { this.deliveredGoods = deliveredGoods; }
    public List<Goods> getSoldGoods() { return soldGoods; }
    public void setSoldGoods(List<Goods> soldGoods) { this.soldGoods = soldGoods; }
    public List<CashRegister> getCashRegisterList() { return cashRegisterList; }
    public void setCashRegisterList(List<CashRegister> cashRegisterList) { this.cashRegisterList = cashRegisterList; }

    //FINAL SALE PRICE
    public double onSalePrice(Goods goods){
        LocalDate currentDate = LocalDate.now();
        long currentDaysLeft = ChronoUnit.DAYS.between(currentDate, goods.getExpirationDate());
        if(goods.getExpirationDate().isBefore(currentDate)){
            try{
                throw new ExpiredGoodException(goods.getGoodsName());
            }catch(ExpiredGoodException e){
                e.printStackTrace();
            }
            return 0.0;
        }
        if(this.daysUntilExpiration>currentDaysLeft){
            return sellingPrice(goods) - sellingPrice(goods)*getPercentReduction()/100;
        } else return sellingPrice(goods);
    }

    //PRICE BY GOODS TYPE
    public double sellingPrice(Goods goods){
        if(goods.getGoodsType().equals(GoodsType.EATABLE)){
            return ((getPercentMarkupEatable()*goods.getDeliveryPrice())/100 + goods.getDeliveryPrice());
        } else return ((getPercentMarkupUneatable()*goods.getDeliveryPrice())/100 + goods.getDeliveryPrice());
    }

    public double ReceiptListPrice() {
        double result = 0;
        for(Receipt r: receiptList){
            for(Goods g: r.getGoodsOnReceipt()){
                result = result + onSalePrice(g);
            }
        }
        return result;
    }

    public double Costs(){
        double costsResult = 0;
        for(Cashier c: cashierList){
            costsResult += c.getSalary();
        }
        for(Goods dg: deliveredGoods){
            costsResult += dg.getDeliveryPrice();
        }
        for(Goods sg: soldGoods){
            costsResult += sg.getDeliveryPrice();
        }
        return costsResult;
    }

    public double TurnOver(){
        double temporaryTurnOver = 0;
        for(Goods sg: soldGoods){
            temporaryTurnOver += onSalePrice(sg);
        }
        return temporaryTurnOver;
    }

    public double Income(){
        return TurnOver() - Costs();
    }

    public synchronized void startWorking(){
        for(CashRegister cashRegister: this.cashRegisterList){
            Thread thread = new Thread(new CashRegisterThread(cashRegister, this));
            thread.start();
        }
    }

   public int enoughProducts(Goods good){

        for(Goods good2: this.getDeliveredGoods()){
            if(good.compareTo(good2)==0){
                good.setCountGoods(good.getCountGoods()+1);
            }
        }
        return good.getCountGoods();
    }

}
