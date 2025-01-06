package org.sid.bank_account_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bank_account_service.enums.AccountType;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BankAccount {
    @Id
    private UUID id;
    private Date createdAt;
    @NotNull(message = "Account balance cannot be null")
    private Double balance;
    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}