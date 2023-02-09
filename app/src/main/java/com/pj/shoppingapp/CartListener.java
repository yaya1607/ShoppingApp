package com.pj.shoppingapp;

import java.util.ArrayList;

public interface CartListener {
    void onItemChange(int totalPrice, ArrayList<String> positions );
}
