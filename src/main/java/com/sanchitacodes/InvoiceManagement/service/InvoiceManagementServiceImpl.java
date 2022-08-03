package com.sanchitacodes.InvoiceManagement.service;

import com.sanchitacodes.InvoiceManagement.entity.TransactionDetail;
import com.sanchitacodes.InvoiceManagement.repository.InvoiceManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceManagementServiceImpl implements InvoiceManagementService{

    @Autowired
    private InvoiceManagementRepository invoiceManagementRepository;

    @Override
    public TransactionDetail addTransactionDetail(TransactionDetail transactionDetail) {
        return invoiceManagementRepository.save(transactionDetail);
    }

    @Override
    public List<TransactionDetail> fetchTransactionDetail() {
        return invoiceManagementRepository.findAll();
    }

    @Override
    public TransactionDetail updateTransactionDetail(TransactionDetail transactionDetail, Integer id) {
        return null;
    }

    @Override
    public void deleteTransactionDetailById(Integer id) {
        invoiceManagementRepository.deleteById(id);
    }
}
