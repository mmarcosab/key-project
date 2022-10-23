package br.com.itau.pix.keyprocessor.infra.rest.in;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateKeyRequest extends Request {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("account_type")
    private String accountType;
    @JsonProperty("agency_number")
    private String agencyNumber;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("account_holder_name")
    private String accountHolderName;
    @JsonProperty("account_holder_last_name")
    private String accountHolderLastName;

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

    public void setId(String id) {
        this.id = id;
    }
}
