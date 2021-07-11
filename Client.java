package com.company;

import java.util.List;

public class Client{
    private List<Goods> clientGoodsList;
    private double money;

    public Client(List<Goods> clientGoodsList, double money) {
        this.clientGoodsList = clientGoodsList;
        this.money = money;
    }

    public List<Goods> getClientGoodsList() {
        return clientGoodsList;
    }

    public void setClientGoodsList(List<Goods> clientGoodsList) {
        this.clientGoodsList = clientGoodsList;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double listPrice(Shop shop) {
        double result = 0.0;
        for(Goods goods: clientGoodsList){
            result += shop.onSalePrice(goods);
        }
        return result;
    }

    public boolean enoughMoney(Shop shop){
        if(money>listPrice(shop)){
            return true;
        }else return false;
    }
}
