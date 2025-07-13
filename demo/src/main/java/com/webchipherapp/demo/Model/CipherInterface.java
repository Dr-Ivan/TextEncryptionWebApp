package com.webchipherapp.demo.Model;

public interface CipherInterface {
    String encryptText(String text, String key);
    String decryptText(String text, String key);
}
