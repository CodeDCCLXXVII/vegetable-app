package com.example.vegprice.network;

import com.example.vegprice.pojo.Transaction;
import com.example.vegprice.pojo.Vegetable;

import java.util.List;

public class APIResponseVegTransaction extends APIMessage{

    private Transaction metadata;

    public Transaction getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "APIResponseVegTransaction{" +
                "metadata=" + metadata +
                '}';
    }
}
