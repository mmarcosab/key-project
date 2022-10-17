package br.com.itau.pix.keyprocessor.domain;

import java.time.LocalDateTime;

public class KeyPix {

    private String id;
    private String name;                            //Nome Tipo Dado Obrigatório
    private String type;                            //TIPO CHAVE (celular|email|cpf|cnpj|aleatorio) Texto (9) SI
    private String value;                           //VALOR CHAVE Texto (77) SIM
    private String accountType;                     //TIPO CONTA (corrente|poupança) Texto (10) SIM
    private String agencyNumber;                    //NUMERO AGENCIA Numérico (4) SIM
    private String accountNumber;                   //NUMERO CONTA Numérico (8) SIM
    private String accountHolderName;                //NOME CORRENTISTA
    private String accountHolderLastName;            //SOBRENOME CORRENTISTA Texto (45) NÃO
    private LocalDateTime dateTimeInclusion;
    private LocalDateTime dateTimeUpdate;
    private Integer active;

    private KeyPix() {

    }

    public KeyPix(final String name, final String type, final String value, final String accountType, final String agencyNumber,
                  final String accountNumber, final String accountHolderName, final String accountHolderLastName,
                  final  LocalDateTime dateTimeInclusion, final  LocalDateTime dateTimeUUpdate, final Integer active) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderLastName = accountHolderLastName;
        this.dateTimeInclusion = dateTimeInclusion;
        this.dateTimeUpdate = dateTimeUUpdate;
        this.active = active;
    }

    public KeyPix(final String id, final String name, final String type, final String value, final String accountType, final String agencyNumber,
                  final String accountNumber, final String accountHolderName, final String accountHolderLastName,
                  final  LocalDateTime dateTimeInclusion, final  LocalDateTime dateTimeUUpdate, final Integer active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderLastName = accountHolderLastName;
        this.dateTimeInclusion = dateTimeInclusion;
        this.dateTimeUpdate = dateTimeUUpdate;
        this.active = active;
    }

    public void inactivate() {
        this.active = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
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

    public LocalDateTime getDateTimeInclusion() {
        return dateTimeInclusion;
    }

    public LocalDateTime getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public boolean isActive() {
        return active.equals(1)? true : false;
    }
}
