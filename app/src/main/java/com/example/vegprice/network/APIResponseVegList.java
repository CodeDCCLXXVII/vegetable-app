package com.example.vegprice.network;

import com.example.vegprice.pojo.Vegetable;

import java.util.List;

public class APIResponseVegList extends APIMessage{

    private List<Vegetable> metadata;

    public List<Vegetable> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "APIResponseVegList{" +
                "metadata=" + metadata +
                '}';
    }
}
