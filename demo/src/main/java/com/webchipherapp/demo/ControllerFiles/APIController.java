package com.webchipherapp.demo.ControllerFiles;

import com.webchipherapp.demo.DataTransferObjects.CipherRequestDTO;

import com.webchipherapp.demo.DataTransferObjects.CipherResultDTO;
import com.webchipherapp.demo.Model.CipherManager;

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
    public CipherResultDTO processVigenerCipherEncryptionRequest(@RequestBody CipherRequestDTO request) {
        return model.handleVigenerCipherEncryptionRequest(request);
    }

    @PostMapping("/processVigenerCipherDecryptionRequest")
    public CipherResultDTO processVigenerCipherDecryptionRequest(@RequestBody CipherRequestDTO request) {
        return model.handleVigenerCipherDecryptionRequest(request);
    }

    @PostMapping("/processCaesarCipherEncryptionRequest")
    public CipherResultDTO processCaesarCipherEncryptionRequest(@RequestBody CipherRequestDTO request){
        return model.handleCaesarCipherEncryptionRequest(request);
    }

    @PostMapping("/processCaesarCipherDecryptionRequest")
    public CipherResultDTO processCaesarCipherDecryptionRequest(@RequestBody CipherRequestDTO request){
        return model.handleCaesarCipherDecryptionRequest(request);
    }

    @PostMapping("/test")
    public String testEndpoint(@RequestBody String rawBody) {
        return "Received raw body: " + rawBody;
    }

    @GetMapping("/test")
    public String testGet() {
        return "GET работает!";
    }
}
