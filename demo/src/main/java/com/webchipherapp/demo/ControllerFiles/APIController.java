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
    public static void main(String[] args) {
        APIController a = new APIController(new CipherManager());
        System.out.println(a.processVigenerCipherEncryptionRequest(new CipherRequestDTO("qwery", "key")));
    }

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


    @PostMapping("/test")
    public String testEndpoint(@RequestBody String rawBody) {
        return "Received raw body: " + rawBody;
    }

    @GetMapping("/test")
    public String testGet() {
        return "GET работает!";
    }
}
