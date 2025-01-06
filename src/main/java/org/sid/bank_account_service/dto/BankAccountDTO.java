package org.sid.bank_account_service.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class BankAccountDTO {
    private UUID id;
    private String accountNumber;
    private double balance;
    private String currency;
    private String type;
    private Date createdAt;
}