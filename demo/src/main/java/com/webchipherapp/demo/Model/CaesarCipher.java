package com.webchipherapp.demo.Model;

import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
public class CaesarCipher implements CipherInterface{
    @Override
    public String encryptText(String text, String key) {

        return "";
    }

    @Override
    public String decryptText(String text, String key) {

        return "";
    }

    private char encodeEnglishLetter(char letter, int key){
        return (char) ((letter - 'a' + key) % 26 + 'a');
    }

    private char encodeRussianLetter(char letter, int key){
        return (char) ((letter - 'а' + key) % 33 + 'а'); // используется А из кириллицы
    }

    private char decodeEnglishLetter(char letter, int key){
        return (char) ((letter - 'a' - key) % 26 + 'a');
    }

    private char decodeRussianLetter(char letter, int key){
        return (char) ((letter - 'а' - key) % 33 + 'а'); // используется А из кириллицы
    }
}
