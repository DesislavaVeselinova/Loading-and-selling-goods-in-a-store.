package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        LocalDate dateNow = LocalDate.now();
        Goods goods1 = new Goods(1589345, "Orange juice", 2.50, GoodsType.EATABLE, LocalDate.of(2021, 8, 12), 5);
        Goods goods2 = new Goods(245581345, "Scissors", 2.0, GoodsType.UNEATABLE, dateNow.plusDays(3), 2);
        Goods goods3 = new Goods(96234715, "Strawberries", 3.0, GoodsType.EATABLE, dateNow.plusDays(1), 11);
        Goods goods4 = new Goods(702351683, "Hammer", 3.0, GoodsType.UNEATABLE, dateNow.plusDays(2), 14);
        Goods goods5 = new Goods(5030348, "Cup", 4.25, GoodsType.UNEATABLE, dateNow.plusDays(2), 5);

        List<Goods> soldGoodsList1 = new ArrayList<>();
        soldGoodsList1.add(goods1);
        soldGoodsList1.add(goods2);
        soldGoodsList1.add(goods2);
        soldGoodsList1.add(goods1);
        List<Goods> deliveredGoodsList1 = new ArrayList<>();
        deliveredGoodsList1.add(goods3);
        deliveredGoodsList1.add(goods4);
        deliveredGoodsList1.add(goods5);
        deliveredGoodsList1.add(goods5);
        deliveredGoodsList1.add(goods5);
        deliveredGoodsList1.add(goods4);
        deliveredGoodsList1.add(goods1);
        deliveredGoodsList1.add(goods1);

        List<Goods> soldGoodsList2 = new ArrayList<>();
        soldGoodsList2.add(goods3);
        soldGoodsList2.add(goods3);
        soldGoodsList2.add(goods3);
        soldGoodsList2.add(goods5);
        soldGoodsList2.add(goods4);
        soldGoodsList2.add(goods5);
        soldGoodsList2.add(goods5);
        List<Goods> deliveredGoodsList2 = new ArrayList<>();
        deliveredGoodsList2.add(goods2);
        deliveredGoodsList2.add(goods2);
        deliveredGoodsList2.add(goods2);
        deliveredGoodsList2.add(goods1);
        deliveredGoodsList2.add(goods1);

        //CLIENTS
        Client client1 = new Client(soldGoodsList1, 200.01);
        Client client2 = new Client(soldGoodsList2, 150.01);
        Client client3 = new Client(soldGoodsList1, 111.01);
        Client client4 = new Client(soldGoodsList2, 127.01);

        //CASHIERS AND LISTS OF CASHIERS
        Cashier cashier1 = new Cashier("Linda", 18932454, 2500.01);
        Cashier cashier2 = new Cashier("Nikol", 52035518, 1500.01);
        Cashier cashier3 = new Cashier("George", 70223018, 2000.0);
        Cashier cashier4 = new Cashier("Max", 1023468, 2100.50);
        Cashier cashier5 = new Cashier("Elena", 8610356, 1700.60);
        List<Cashier> cashierList1 = new ArrayList<>();
        cashierList1.add(cashier1);
        cashierList1.add(cashier2);
        List<Cashier> cashierList2 = new ArrayList<>();
        cashierList2.add(cashier3);
        cashierList2.add(cashier4);
        cashierList2.add(cashier5);

        //RECEIPTS AND LISTS OF RECEIPTS
        Receipt receipt1 = new Receipt(cashier1, LocalDateTime.now(), client1.getClientGoodsList());
        Receipt receipt2 = new Receipt(cashier2, LocalDateTime.now(), client2.getClientGoodsList());
        Receipt receipt3 = new Receipt(cashier3, LocalDateTime.now(), client3.getClientGoodsList());
        Receipt receipt4 = new Receipt(cashier4, LocalDateTime.now(), client4.getClientGoodsList());
        List<Receipt> receiptList1 = new ArrayList<>();
        receiptList1.add(receipt1);
        receiptList1.add(receipt2);
        List<Receipt> receiptList2 = new ArrayList<>();
        receiptList2.add(receipt3);
        receiptList2.add(receipt4);

        //CASH REGISTERS
        CashRegister cashRegister1 = new CashRegister(client1.getClientGoodsList(), 2, cashier1, client1);
        CashRegister cashRegister2 = new CashRegister(client2.getClientGoodsList(), 5, cashier2, client2);
        CashRegister cashRegister3 = new CashRegister(client3.getClientGoodsList(), 3, cashier3, client3);
        CashRegister cashRegister4 = new CashRegister(client4.getClientGoodsList(), 7, cashier4, client4);
        List<CashRegister> cashRegisterList1 = new ArrayList<>();
        cashRegisterList1.add(cashRegister1);
        cashRegisterList1.add(cashRegister2);
        cashRegisterList1.add(cashRegister3);
        cashRegisterList1.add(cashRegister4);
        //SHOPS
        Shop shop1 = new Shop("Billa", soldGoodsList1, deliveredGoodsList1, cashierList1, receiptList1, cashRegisterList1, 15, 20, 10, 5, 2);
        Shop shop2 = new Shop("Fantastico", soldGoodsList2, deliveredGoodsList2, cashierList2, receiptList2, cashRegisterList1, 30, 25, 30, 5, 3);

        System.out.println("\nThe price of " + goods1.getGoodsName() + " is: " + shop1.onSalePrice(goods1) + "\n");

        //WRITING RECEIPT 1 IN FILE
        String filename1 = "files/Receipt1.txt";
        cashRegister1.writeReceipts(filename1, receipt1, shop1);
        //WRITING RECEIPT 2 IN FILE
        String filename2 = "files/Receipt2.txt";
        cashRegister2.writeReceipts(filename2, receipt2, shop1);
        //WRITING RECEIPT 3 IN FILE
        String filename3 = "files/Receipt3.txt";
        cashRegister3.writeReceipts(filename3, receipt3, shop2);
        //WRITING RECEIPT 4 IN FILE
        String filename4 = "files/Receipt4.txt";
        cashRegister4.writeReceipts(filename4, receipt4, shop2);

        //READING FROM FILE
        List<String> receipt1DataFile = new ArrayList<>(CashRegister.readReceipt(filename1));
        System.out.println("Reading information from file for receipt 1: " + receipt1DataFile);
        List<String> receipt2DataFile = new ArrayList<>(CashRegister.readReceipt(filename2));
        System.out.println("Reading information from file for receipt 2: " + receipt2DataFile);
        List<String> receipt3DataFile = new ArrayList<>(CashRegister.readReceipt(filename3));
        System.out.println("Reading information from file for receipt 3: " + receipt3DataFile);
        List<String> receipt4DataFile = new ArrayList<>(CashRegister.readReceipt(filename4));
        System.out.println("Reading information from file for receipt 4: " + receipt4DataFile);

        //RECEIPTS COUNT AND COUNT
        int countOfReceipts = Receipt.getCurrentNumber();
        double totalPriceOfReceipts = shop2.ReceiptListPrice();
        System.out.println("\nThe count of the receipts is: " + countOfReceipts +"\nThe total price of the receipts is: " + totalPriceOfReceipts);

        //COSTS, TURNOVER AND INCOME OF THE TWO SHOPS
        System.out.println("\n" + shop1.getShopName() + ":");
        System.out.println("The costs " + shop1.getShopName() + " has are: " + shop1.Costs());
        System.out.println("The turnover " + shop1.getShopName() + " has is: " + shop1.TurnOver());
        System.out.println("The income " + shop1.getShopName() + " has is: " + shop1.Income());
        System.out.println("\n" + shop2.getShopName() + ":");
        System.out.println("The costs " + shop2.getShopName() + " has are: " + shop2.Costs());
        System.out.println("The turnover " + shop2.getShopName() + " has is: " + shop2.TurnOver());
        System.out.println("The income " + shop2.getShopName() + " has is: " + shop2.Income() + "\n");

        //WORKFLOW THREADS ON CASH REGISTERS
        System.out.println("\nWorkflow of cash registers:");
        shop1.startWorking();

    }
}
