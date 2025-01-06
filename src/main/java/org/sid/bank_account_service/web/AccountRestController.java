package org.sid.bank_account_service.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sid.bank_account_service.dto.BankAccountDTO;
import org.sid.bank_account_service.dto.CreateBankAccountDTO;
import org.sid.bank_account_service.dto.UpdateBankAccountDTO;
import org.sid.bank_account_service.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankAccounts")
@Tag(name = "Bank Account API", description = "API pour la gestion des comptes bancaires")
public class AccountRestController {

    @Autowired
    private BankAccountService bankAccountService;

    @Operation(summary = "Récupérer tous les comptes", description = "Retourne une liste de tous les comptes bancaires")
    @ApiResponse(responseCode = "200", description = "Liste des comptes récupérée avec succès")
    @GetMapping
    public ResponseEntity<List<BankAccountDTO>> getAllAccounts() {
        List<BankAccountDTO> accounts = bankAccountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Récupérer un compte par ID", description = "Retourne un compte bancaire spécifique")
    @ApiResponse(responseCode = "200", description = "Compte trouvé")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDTO> getAccount(@PathVariable String id) {
        BankAccountDTO accountDTO = bankAccountService.getAccountById(id);
        if (accountDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // return 404 if account not found
        }
        return ResponseEntity.ok(accountDTO);
    }

    @Operation(summary = "Créer un nouveau compte", description = "Crée un nouveau compte bancaire")
    @ApiResponse(responseCode = "200", description = "Compte créé avec succès")
    @PostMapping
    public ResponseEntity<BankAccountDTO> createAccount(@RequestBody CreateBankAccountDTO createBankAccountDTO) {
        BankAccountDTO savedAccountDTO = bankAccountService.createAccount(createBankAccountDTO);
        return ResponseEntity.ok(savedAccountDTO);
    }

    @Operation(summary = "Mettre à jour un compte", description = "Met à jour un compte bancaire existant")
    @ApiResponse(responseCode = "200", description = "Compte mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDTO> updateAccount(@PathVariable String id, @RequestBody UpdateBankAccountDTO updateBankAccountDTO) {
        BankAccountDTO updatedAccountDTO = bankAccountService.updateAccount(id, updateBankAccountDTO);
        return ResponseEntity.ok(updatedAccountDTO);
    }

    @Operation(summary = "Supprimer un compte", description = "Supprime un compte bancaire par son ID")
    @ApiResponse(responseCode = "204", description = "Compte supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        bankAccountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}