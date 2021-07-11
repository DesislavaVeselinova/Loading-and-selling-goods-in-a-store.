package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CashRegister{
    private List<Goods> goodsOnAile;
    private int cashRegisterNumber;
    private Cashier cashier;
    private Client client;

    public CashRegister(List<Goods> goodsOnAile, int cashRegisterNumber, Cashier cashier, Client client) {
        this.goodsOnAile = goodsOnAile;
        this.cashRegisterNumber = cashRegisterNumber;
        this.cashier = cashier;
        this.client = client;
    }

    public List<Goods> getGoodsOnAile() {
        return goodsOnAile;
    }

    public void setGoodsOnAile(ArrayList<Goods> goodsOnAile) {
        this.goodsOnAile = goodsOnAile;
    }

    public int getCashRegisterNumber() {
        return cashRegisterNumber;
    }

    public void setCashRegisterNumber(int cashRegisterNumber) {
        this.cashRegisterNumber = cashRegisterNumber;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }

    public void setGoodsOnAile(List<Goods> goodsOnAile) {
        this.goodsOnAile = goodsOnAile;
    }

    public void writeReceipts(String filename, Receipt receipt, Shop shop){
        if(this.client.enoughMoney(shop)) {
            try (FileWriter fout = new FileWriter(filename)) {
                if (receipt != null) {
                    fout.write("Receipt: " + receipt.getCurrentNumber() + "\nCashier: " + getCashier().getCashierName() + "\nDate and time: " + receipt.FormattedDateAndTime() + "\nGoods: ");
                    for (Goods g : this.client.getClientGoodsList()) {
                        fout.write("\n " + g.getGoodsName() + " | Price: " + shop.onSalePrice(g));
                    }
                    fout.write("\nTotal price: " + this.client.listPrice(shop));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> readReceipt(String filename){
        List<String> Goods = new ArrayList<>();
        try(FileReader fileReader=new FileReader(filename)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null){
                Goods.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Goods;
    }

}
