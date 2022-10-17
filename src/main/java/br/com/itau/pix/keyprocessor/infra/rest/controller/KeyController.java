package br.com.itau.pix.keyprocessor.infra.rest.controller;

import br.com.itau.pix.keyprocessor.domain.usecase.CreateKeyUseCase;
import br.com.itau.pix.keyprocessor.domain.usecase.GetKeyUseCase;
import br.com.itau.pix.keyprocessor.domain.usecase.InactivateKeyUseCase;
import br.com.itau.pix.keyprocessor.domain.usecase.UpdateKeyUseCase;
import br.com.itau.pix.keyprocessor.infra.rest.CreateKeyRequest;
import br.com.itau.pix.keyprocessor.infra.rest.UpdateKeyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/keys")
@RequiredArgsConstructor
public class KeyController {

    private final CreateKeyUseCase createKeyUseCase;
    private final GetKeyUseCase getKeyUseCase;
    private final InactivateKeyUseCase inactivateKeyUseCase;

    private final UpdateKeyUseCase updateKeyUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final CreateKeyRequest createKeyRequest) {
        var response = createKeyUseCase.create(createKeyRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        var response = getKeyUseCase.byId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> findByCombinedFilter(
            @RequestParam (required = false) final String type,
            @RequestParam (required = false) final String accountHolderName,
            @RequestParam (required = false) final String accountHolderLastName,
            @RequestParam (required = false) final String agencyNumber,
            @RequestParam (required = false) final String accountNumber) {
        var response = getKeyUseCase.byCombinedFilter(
                type,
                accountHolderName,
                accountHolderLastName,
                agencyNumber,
                accountNumber
        );
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final String id, @RequestBody UpdateKeyRequest updateKeyRequest) {
        var response = updateKeyUseCase.update(updateKeyRequest, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inactivateById(@PathVariable final String id) {
        var response = inactivateKeyUseCase.byId(id);
        return ResponseEntity.ok(response);
    }

}
