package com.example.vegprice.pojo;

import java.util.Date;

public class Vegetable  {
    private String id;
    private String name;
    private int price;
    private Date created;
    private Date lastUpdate;

    public Vegetable(String name, int price){
        this.name = name;
        this.price = price;
    }

    public Vegetable(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "Vegetable{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", created=" + created +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
