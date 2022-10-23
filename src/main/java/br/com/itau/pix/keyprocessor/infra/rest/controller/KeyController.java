package br.com.itau.pix.keyprocessor.infra.rest.controller;

import br.com.itau.pix.keyprocessor.domain.usecase.KeyPixService;
import br.com.itau.pix.keyprocessor.infra.rest.in.CreateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.in.KeyFilterParam;
import br.com.itau.pix.keyprocessor.infra.rest.in.UpdateKeyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/keys")
@RequiredArgsConstructor
public class KeyController {

    private final KeyPixService keyUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final CreateKeyRequest createKeyRequest) {
        var response = keyUseCase.create(createKeyRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        var response = keyUseCase.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> findByCombinedFilter(
            @RequestParam (required = false) final String type,
            @RequestParam (required = false) final String accountHolderName,
            @RequestParam (required = false) final String accountHolderLastName,
            @RequestParam (required = false) final String agencyNumber,
            @RequestParam (required = false) final String accountNumber) {

        var keyFilter = new KeyFilterParam(
                type,
                accountHolderName,
                accountHolderLastName,
                agencyNumber,
                accountNumber
        );

        var response = keyUseCase.getByFilter(keyFilter);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final String id, @RequestBody UpdateKeyRequest updateKeyRequest) {
        updateKeyRequest.setId(id);
        var response = keyUseCase.update(updateKeyRequest, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inactivateById(@PathVariable final String id) {
        var response = keyUseCase.inactivateById(id);
        return ResponseEntity.ok(response);
    }

}
