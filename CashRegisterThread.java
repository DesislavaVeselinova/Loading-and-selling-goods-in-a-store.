package com.company;

public class CashRegisterThread implements Runnable{
    private CashRegister cashRegister;
    private Shop shop;

    public CashRegisterThread(CashRegister cashRegister, Shop shop) {
        this.cashRegister = cashRegister;
        this.shop = shop;
    }

    @Override
    public void run() {
        int clientsMoney = (int)cashRegister.getClient().getMoney();
        for(Goods good: cashRegister.getGoodsOnAile()){
            int goodsPrice = (int)this.shop.onSalePrice(good);
            if(goodsPrice==0.0){
                System.out.println("\nOn cash register " + cashRegister.getCashRegisterNumber() +" is cashier " + cashRegister.getCashier().getCashierName() + ":\nProduct " + good.getGoodsName() + " cannot be sold because it has expired.");
            }else{
                if(clientsMoney>=goodsPrice){
                    this.shop.getSoldGoods().add(good);
                    System.out.println("\nOn cash register " + cashRegister.getCashRegisterNumber() +" is cashier " + cashRegister.getCashier().getCashierName() + ":\nProduct " + good.getGoodsName() + " wad sold.");
                    double updatedClientsMoney = clientsMoney - this.shop.onSalePrice(good);
                    cashRegister.getClient().setMoney(updatedClientsMoney);
                }else System.out.println("The client doesn't have enough money!");
            }
        }
        System.out.println("\nCashier " + cashRegister.getCashier().getCashierName() + " on cash register " + cashRegister.getCashRegisterNumber() + " has finished the service for the client."+ "\nTotal price on receipt: " + cashRegister.getClient().listPrice(this.shop));

    }
}
