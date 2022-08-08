package com.sanchitacodes.InvoiceManagement.service;

import com.sanchitacodes.ClientManagement.entity.ClientDetail;
import com.sanchitacodes.ClientManagement.service.ClientManagementServiceImpl;
import com.sanchitacodes.InvoiceManagement.entity.InvoiceDetail;
import com.sanchitacodes.InvoiceManagement.entity.TransactionDetail;
import com.sanchitacodes.InvoiceManagement.repository.InvoiceManagementRepository;
import com.sanchitacodes.InvoiceManagement.repository.TransactionManagementRepository;
import com.sanchitacodes.InvoiceManagement.utility.InvoiceManagementUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.sanchitacodes.InvoiceManagement.utility.InvoiceManagementConstants.*;
@Slf4j
@Service
@Transactional
public class InvoiceManagementService {
    @Autowired
    private TransactionManagementRepository transactionManagementRepository;
    @Autowired
    private InvoiceManagementRepository invoiceManagementRepository;

    public List<TransactionDetail> saveTransactionDetail(List<TransactionDetail> transactionDetailList) {
        log.info("Inside saveTransactionDetail of InvoiceManagementService, saving records!");
        return transactionManagementRepository.saveAll(transactionDetailList);
    }

    public List<TransactionDetail> fetchTransactionDetail() {
        log.info("Inside fetchTransactionDetail of InvoiceManagementService, fetching records!");
        return transactionManagementRepository.findAll();
    }

    public TransactionDetail updateTransactionDetail(TransactionDetail transactionDetail, int id) {
        log.info("Inside updateTransactionDetail of InvoiceManagementService, updating records!");
        return transactionManagementRepository.save(transactionDetail);
    }

    public void deleteTransactionDetailById(int id) {
        log.info("Inside deleteTransactionDetailById of InvoiceManagementService, deleting records!");
        transactionManagementRepository.deleteById(id);
    }

    public String computeInvoice(int orderId) {
        log.info("Inside invoiceGeneration of InvoiceManagementService, generating invoice!");
        Optional<TransactionDetail> transactionDetail = transactionManagementRepository.findById(orderId);
        ClientDetail clientDetail = getClientDetail(transactionDetail.get().getClient());
        saveInvoiceDetails(orderId, transactionDetail.get().getAmount(), transactionDetail.get().getClient(),
                clientDetail.getFees(), clientDetail.getBilling_interval(), transactionDetail.get().getStatus(),
                transactionDetail.get().getCurrency(), clientDetail.getEmail());
        return "Invoice Computed!!";
    }

    private ClientDetail getClientDetail(String clientName) {
        log.info("Inside getClientDetail of InvoiceManagementService, gettingClientDetails from DB!");
        ClientManagementServiceImpl clientManagementServiceImpl = new ClientManagementServiceImpl();
        return clientManagementServiceImpl.fetchClientDetailByClientName(clientName);
    }

    private void saveInvoiceDetails(int orderId, float amount, String clientName,
                                    float fees, String billing_interval, String tx_status,
                                    String currency, String email) {

        log.info("Inside saveInvoiceDetails of InvoiceManagementService, saving invoice in DB!");

        InvoiceManagementUtil invoiceManagementUtil = new InvoiceManagementUtil();
        InvoiceDetail invoice = new InvoiceDetail();
        if(tx_status.equals(APPROVED)) {
            invoice.setCharges(invoiceManagementUtil.calculateCharges(currency, fees, amount));
        }
        if(tx_status.equals(REFUND)){
            invoice.setCharges(invoiceManagementUtil.calculateCharges(currency, fees, amount));
        }
        if(tx_status.equals(DECLINED)){
            //Refund cannot be done for a declined transaction.
            invoice.setCharges(currency + " " + 0);
        }
        if(billing_interval.equals(DAILY)){
            invoice.setBilling_interval(1);
        }
        if(billing_interval.equals(MONTHLY)){
            invoice.setBilling_interval(2);
        }
        invoice.setClientName(clientName);
        invoice.setOrderId(orderId);
        invoice.setStatus(tx_status);
        invoiceManagementRepository.save(invoice);
    }
}
