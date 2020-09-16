package com.example.vegprice.network;
public class TaskRequest {

    private String id;
    private String vegName;
    private int vegPrice;
    private float vegQuantity;
    private String transactionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVegName() {
        return vegName;
    }

    public void setVegName(String vegName) {
        this.vegName = vegName;
    }

    public int getVegPrice() {
        return vegPrice;
    }

    public void setVegPrice(int vegPrice) {
        this.vegPrice = vegPrice;
    }

    public float getVegQuantity() {
        return vegQuantity;
    }

    public void setVegQuantity(float vegQuantity) {
        this.vegQuantity = vegQuantity;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "TaskRequest{" +
                "id='" + id + '\'' +
                ", vegName='" + vegName + '\'' +
                ", vegPrice=" + vegPrice +
                ", vegQuantity=" + vegQuantity +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
