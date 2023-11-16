package com.example.Food_app.exceptions;

public class CartException extends Exception{
    public CartException() {
    }


    public CartException(String message){
        super(message);
    }
}
