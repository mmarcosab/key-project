package br.com.itau.pix.keyprocessor.infra.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UpdateKeyRequest {

    @JsonProperty("name")
    private String name;                            //Nome Tipo Dado Obrigatório
    @JsonProperty("account_type")
    private String accountType;                     //TIPO CONTA (corrente|poupança) Texto (10) SIM
    @JsonProperty("agency_number")
    private String agencyNumber;                    //NUMERO AGENCIA Numérico (4) SIM
    @JsonProperty("account_number")
    private String accountNumber;                   //NUMERO CONTA Numérico (8) SIM
    @JsonProperty("account_holder_name")
    private String accountHolderName;                //NOME CORRENTISTA
    @JsonProperty("account_holder_last_name")
    private String accountHolderLastName;            //SOBRENOME CORRENTISTA Texto (45) NÃO

    private UpdateKeyRequest() {

    }

    public UpdateKeyRequest(String name, String accountType, String agencyNumber, String accountNumber, String accountHolderName, String accountHolderLastName) {
        this.name = name;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderLastName = accountHolderLastName;
    }

    public String getName() {
        return name;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountHolderLastName() {
        return accountHolderLastName;
    }
}
