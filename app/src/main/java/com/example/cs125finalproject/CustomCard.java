package com.example.cs125finalproject;

public class CustomCard {
    private String content;
    private int cardNumber;

    public CustomCard(String setContent,  int setNum) {
        this.cardNumber = setNum;
        this.content = setContent;
    }

    public String getContent() {
        return content;
    }

    public int getCardNumber() {
        return cardNumber;
    }
}
