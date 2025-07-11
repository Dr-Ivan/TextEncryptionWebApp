package ControllerFiles;

import DataTransferObjects.CipherRequestDTO;
import DataTransferObjects.CipherResultDTO;

import Model.CipherManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class APIController {
    private final CipherManager model;

    @Autowired
    public APIController(CipherManager model) {
        this.model = model;
    }

    @PostMapping("/processVigenerCipherEncryptionRequest")
    public String processVigenerCipherEncryptionRequest(@RequestBody CipherRequestDTO request) {
        return model.handleVigenerCipherEncryptionRequest(request).toString();
    }

    @PostMapping("/processVigenerCipherDecryptionRequest")
    public String processVigenerCipherDecryptionRequest(@RequestBody CipherRequestDTO request) {
        return model.handleVigenerCipherDecryptionRequest(request).toString();
    }

}
