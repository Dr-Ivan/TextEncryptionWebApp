package com.webchipherapp.demo.Model;

import com.webchipherapp.demo.DataTransferObjects.CipherRequestDTO;
import com.webchipherapp.demo.DataTransferObjects.CipherResultDTO;

import org.springframework.stereotype.Service;

@Service
public class CipherManager {
    final VigenerCipher vigenerCipherModel = new VigenerCipher();
    final CaesarCipher caesarCipherModel = new CaesarCipher();

    public CipherResultDTO handleVigenerCipherEncryptionRequest(CipherRequestDTO dto){
        return new CipherResultDTO(vigenerCipherModel.encryptText(dto.getText(), dto.getKey()));
    }

    public CipherResultDTO handleVigenerCipherDecryptionRequest(CipherRequestDTO dto){
        return new CipherResultDTO(vigenerCipherModel.decryptText(dto.getText(), dto.getKey()));
    }

    public CipherResultDTO handleCaesarCipherEncryptionRequest(CipherRequestDTO dto){
        return new CipherResultDTO(caesarCipherModel.encryptText(dto.getText(), dto.getKey()));
    }

    public CipherResultDTO handleCaesarCipherDecryptionRequest(CipherRequestDTO dto){
        return new CipherResultDTO(caesarCipherModel.decryptText(dto.getText(), dto.getKey()));
    }
}
