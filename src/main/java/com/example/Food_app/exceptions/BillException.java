package com.example.Food_app.exceptions;

import com.example.Food_app.domain.Bill;

public class BillException extends Exception{

    public BillException(){}

    public BillException(String message)
    {
        super(message);
    }
}
