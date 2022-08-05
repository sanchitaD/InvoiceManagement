package com.sanchitacodes.InvoiceManagement.repository;

import com.sanchitacodes.InvoiceManagement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceManagementRepository extends JpaRepository<Invoice,Integer> {
}
