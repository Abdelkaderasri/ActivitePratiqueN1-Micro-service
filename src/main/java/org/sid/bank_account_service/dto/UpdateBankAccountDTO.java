package org.sid.bank_account_service.dto;

import lombok.Data;

@Data
public class UpdateBankAccountDTO {
    private double balance;
    private String currency;
    private String type;
}