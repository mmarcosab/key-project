package br.com.itau.pix.keyprocessor.infra.rest.out;


import br.com.itau.pix.keyprocessor.infra.rest.out.Response;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CreateKeyResponse extends Response {

    @JsonProperty("id")
    private String id;

    public CreateKeyResponse(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
