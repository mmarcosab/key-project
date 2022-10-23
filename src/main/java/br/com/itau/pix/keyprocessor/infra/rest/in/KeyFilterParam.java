package br.com.itau.pix.keyprocessor.infra.rest.in;

public class KeyFilterParam extends Request {
    private String type;
    private String accountHolderName;
    private String accountHolderLastName;
    private String agencyNumber;
    private String accountNumber;

    public KeyFilterParam(
            final String type,
            final String accountHolderName,
            final String accountHolderLastName,
            final String agencyNumber,
            final String accountNumber
    ) {
        this.type = type;
        this.accountHolderName = accountHolderName;
        this.accountHolderLastName = accountHolderLastName;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountHolderLastName() {
        return accountHolderLastName;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}