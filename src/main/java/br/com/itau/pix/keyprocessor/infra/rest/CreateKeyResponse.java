package br.com.itau.pix.keyprocessor.infra.rest;


import com.fasterxml.jackson.annotation.JsonProperty;


public class CreateKeyResponse {

    @JsonProperty("id")
    private String id;

    public CreateKeyResponse(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
