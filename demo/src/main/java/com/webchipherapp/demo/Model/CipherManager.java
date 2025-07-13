package com.webchipherapp.demo.Model;

import com.webchipherapp.demo.DataTransferObjects.CipherRequestDTO;
import com.webchipherapp.demo.DataTransferObjects.CipherResultDTO;

import org.springframework.stereotype.Service;

@Service
public class CipherManager {
    final VigenerCipher vigenerCipherModel = new VigenerCipher();
    final CaesarCipher caesarCipherModel = new CaesarCipher();

    public static void main(String[] args) {
        CipherManager testObj = new CipherManager();
        CipherRequestDTO testDTO = new CipherRequestDTO("QWerTY TXT 1234 .m, n!", "key");
        CipherResultDTO res = testObj.handleVigenerCipherEncryptionRequest(testDTO);
        System.out.println(res);

        CipherRequestDTO decryptDTO = new CipherRequestDTO(res.getText(), "key");
        System.out.println(testObj.handleVigenerCipherDecryptionRequest(decryptDTO));
    }

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
