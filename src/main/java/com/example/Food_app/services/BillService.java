package com.example.Food_app.services;

import com.example.Food_app.domain.Bill;
import com.example.Food_app.domain.Customer;
import com.example.Food_app.domain.Item;
import com.example.Food_app.exceptions.BillException;
import com.example.Food_app.exceptions.CustomerException;
import com.example.Food_app.exceptions.ItemException;
import com.example.Food_app.repository.BillRepository;
import com.example.Food_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public Bill addBill(Bill bill) throws BillException {
        Optional<Bill> opt = billRepository.findById(bill.getBillId());
        if(opt.isPresent()) {
            throw new BillException("Bill already exists..");
        }
        else {
            return billRepository.save(bill);
        }
    }

    public Bill updateBill(Bill bill) throws BillException {
        Optional<Bill> opt = billRepository.findById(bill.getBillId());
        if(opt.isPresent()) {
            return billRepository.save(bill);
        }
        else {
            throw new BillException("Bill doesn't exists..");
        }
    }

    public Bill removeBill(Integer billId) throws BillException {
        Optional<Bill> opt = billRepository.findById(billId);
        if(opt.isPresent()) {
            Bill bill = opt.get();
            billRepository.delete(bill);
            return bill;
        }
        else {
            throw new BillException("Bill not found with ID: "+billId);
        }

    }

    public Bill viewBill(Integer billId) throws BillException {
        Optional<Bill> opt = billRepository.findById(billId);
        if(opt.isPresent()) {
            return opt.get();
        }
        else{
            throw new BillException("Bill not found with ID: "+billId);
        }
    }

    public String generateTotalBillById(Integer customerId) throws ItemException, CustomerException {
        Optional<Customer> cOpt = customerRepository.findById(customerId);
        if(cOpt.isPresent()) {
            Customer customer = cOpt.get();
            List<Item> items = customer.getCart().getItemList();

            if(items.isEmpty() == false) {

                Double total = 0.0;
                for(Item item : items) {
                    total += (item.getCost()*item.getQuantity());
                }

                return "The total bill for "+customer.getName()+" is "+total;

            }else {
                throw new ItemException("No order items found for "+customer.getName());
            }

        }else {
            throw new CustomerException("No Customer found with ID: "+customerId);
        }
    }
}
