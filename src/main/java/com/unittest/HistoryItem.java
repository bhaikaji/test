package com.unittest;

public class HistoryItem {
    private int id;
    private int amount;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;
    public HistoryItem(){}
    public HistoryItem(int id, int amount, String name, int total){
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.total = total;
    }
}
