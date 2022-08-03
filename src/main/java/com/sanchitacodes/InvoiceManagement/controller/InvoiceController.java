package com.sanchitacodes.InvoiceManagement.controller;

import com.sanchitacodes.InvoiceManagement.entity.TransactionDetail;
import com.sanchitacodes.InvoiceManagement.service.InvoiceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceManagementService invoiceManagementService;
    @PostMapping("/transactionDetail")
    public TransactionDetail addPaymentTransaction(@Valid @RequestBody TransactionDetail transactionDetail){
        return invoiceManagementService.addTransactionDetail(transactionDetail);
    }

    @GetMapping("/transactionDetail")
    public List<TransactionDetail> fetchPaymentTransaction(){
        return invoiceManagementService.fetchTransactionDetail();
    }

    @PutMapping("/transactionDetail/{id}")
    public TransactionDetail updatePaymentTransaction(@RequestBody TransactionDetail transactionDetail,
                                                      @PathVariable("id") Integer id){
        return null;
    }

    @DeleteMapping("/transactionDetail/{id}")
    public String deletePaymentTransaction(@PathVariable("id") Integer id){
        invoiceManagementService.deleteTransactionDetailById(id);
        return "Deleted Successfully";
    }
}
