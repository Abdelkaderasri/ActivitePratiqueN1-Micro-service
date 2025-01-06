package org.sid.bank_account_service.mappers;

import org.mapstruct.Mapping;
import org.sid.bank_account_service.dto.BankAccountDTO;
import org.sid.bank_account_service.dto.CreateBankAccountDTO;
import org.sid.bank_account_service.dto.UpdateBankAccountDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    @Mapping(target = "id", source = "id")
    BankAccountDTO toDTO(BankAccount bankAccount);

    BankAccount toEntity(BankAccountDTO bankAccountDTO);

    BankAccount toEntity(CreateBankAccountDTO createBankAccountDTO);

    void toEntity(UpdateBankAccountDTO updateBankAccountDTO, @MappingTarget BankAccount bankAccount);

    // Custom methods to convert UUID to String
    default String map(UUID value) {
        return value != null ? value.toString() : null;
    }

    default UUID map(String value) {
        return value != null ? UUID.fromString(value) : null;
    }
}
