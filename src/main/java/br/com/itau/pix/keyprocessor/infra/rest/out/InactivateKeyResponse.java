package br.com.itau.pix.keyprocessor.infra.rest.out;

import br.com.itau.pix.keyprocessor.infra.rest.out.Response;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class InactivateKeyResponse extends Response {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;                            //Nome Tipo Dado Obrigatório
    @JsonProperty("type")
    private String type;                            //TIPO CHAVE (celular|email|cpf|cnpj|aleatorio) Texto (9) SIM
    @JsonProperty("value")
    private String value;                           //VALOR CHAVE Texto (77) SIM
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date_time_inclusion")
    private LocalDateTime dateTimeInclusion;        //NUMERO CONTA Numérico (8) SIM
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date_time_inactivation")
    private LocalDateTime dateTimeUpdate;           //NOME CORRENTISTA
    @JsonProperty("active")
    private boolean active;

    public InactivateKeyResponse(String id, String name, String type, String value, String accountType,
                                 String agencyNumber, String accountNumber, String accountHolderName, String accountHolderLastName,
                                 LocalDateTime dateTimeInclusion, LocalDateTime dateTimeUpdate, boolean active) {
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
        this.dateTimeUpdate = dateTimeUpdate;
        this.active = active;
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
        return active;
    }
}
