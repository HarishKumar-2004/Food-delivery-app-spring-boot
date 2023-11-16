package com.example.Food_app.controller;

import com.example.Food_app.domain.Bill;
import com.example.Food_app.dtos.CreateBillRequest;
import com.example.Food_app.exceptions.BillException;
import com.example.Food_app.exceptions.CustomerException;
import com.example.Food_app.exceptions.ItemException;
import com.example.Food_app.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BillController {
    @Autowired
    private BillService billService;


    @PostMapping("/addBill")
    public ResponseEntity<Bill> generateBill(@RequestBody @Valid CreateBillRequest billRequest) throws BillException {
        Bill myBill = billService.addBill(billRequest.toBill());
        return new ResponseEntity<>(myBill, HttpStatus.CREATED);

    }

    @PutMapping("/updateBill")
    public ResponseEntity<Bill> updateBill(@RequestBody @Valid CreateBillRequest billRequest) throws BillException {
        Bill myBill = billService.updateBill(billRequest.toBill());
        return new ResponseEntity<Bill>(myBill, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/removeBill/{billId}")
    public ResponseEntity<Bill> removeBill(@PathVariable("billId") Integer billId ) throws BillException{
        Bill removedBill = billService.removeBill(billId);
        return new ResponseEntity<Bill>(removedBill, HttpStatus.OK);
    }

    @GetMapping("/viewBill/{billId}")
    public ResponseEntity<Bill> viewBill(@PathVariable("billId") Integer billId) throws BillException{
        Bill bill = billService.viewBill(billId);
        return new ResponseEntity<Bill>(bill, HttpStatus.ACCEPTED);
    }


    @GetMapping("/viewTotal/{customerId}")
    public ResponseEntity<String> getTotalByCustomerId(@PathVariable("customerId") Integer customerId) throws ItemException, CustomerException {
        String total = billService.generateTotalBillById(customerId);
        return new ResponseEntity<String>(total, HttpStatus.OK);
    }
}
