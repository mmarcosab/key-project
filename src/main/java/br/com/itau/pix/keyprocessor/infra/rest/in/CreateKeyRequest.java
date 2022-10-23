package br.com.itau.pix.keyprocessor.infra.rest.in;

import br.com.itau.pix.keyprocessor.infra.rest.in.Request;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CreateKeyRequest extends Request {

    @NotNull(message = "field name can not be null")
    @JsonProperty("name")
    private String name;                            //Nome Tipo Dado Obrigatório
    @NotNull(message = "field name can not be null")
    @JsonProperty("type")
    private String type;                            //TIPO CHAVE (celular|email|cpf|cnpj|aleatorio) Texto (9) SIM
    @NotNull(message = "field value can not be null")
    @JsonProperty("value")
    private String value;                           //VALOR CHAVE Texto (77) SIM
    @NotNull(message = "field account_type can not be null")
    @JsonProperty("account_type")
    private String accountType;                     //TIPO CONTA (corrente|poupança) Texto (10) SIM
    @NotNull(message = "field name can not be null")
    @JsonProperty("agency_number")
    private String agencyNumber;                    //NUMERO AGENCIA Numérico (4) SIM
    @NotNull(message = "field account_number can not be null")
    @JsonProperty("account_number")
    private String accountNumber;                   //NUMERO CONTA Numérico (8) SIM
    @NotNull(message = "field account_holder_name can not be null")
    @JsonProperty("account_holder_name")
    private String accountHolderName;                //NOME CORRENTISTA
    @JsonProperty("account_holder_last_name")
    private String accountHolderLastName;            //SOBRENOME CORRENTISTA Texto (45) NÃO

    private CreateKeyRequest() {

    }

    public CreateKeyRequest(String name, String type, String value, String accountType, String agencyNumber, String accountNumber, String accountHolderName, String accountHolderLastName) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderLastName = accountHolderLastName;
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
}
