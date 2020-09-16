package com.example.vegprice.pojo;


import java.util.Date;
import java.util.List;


public class Transaction {

    private String id;
    private String cashierId;
    private float total;
    List<VegetableTrans> vegtableTransList;
    private Date created;
    private Date lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<VegetableTrans> getVegtableTransList() {
        return vegtableTransList;
    }

    public void setVegtableTransList(List<VegetableTrans> vegtableTransList) {
        this.vegtableTransList = vegtableTransList;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", cashierId='" + cashierId + '\'' +
                ", total=" + total +
                ", vegtableTransList=" + vegtableTransList +
                ", created=" + created +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
