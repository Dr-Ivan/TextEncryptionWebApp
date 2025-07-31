package com.webchipherapp.demo.Model;

import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
public class VigenerCipher implements CipherInterface {
    private final int alphabetLength = 26;

    @Override
    public String encryptText(String text, String key){
        if (key.isEmpty()) return "Key must contain at least one english letter!";
        key = key.toLowerCase();
        int textLen = text.length();
        int keyLen = key.length();
        char c;
        for (int i = 0; i < keyLen; ++i){
            c = key.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) return "Key must contain english letters only!";
        }

        for (int i = 0; i < textLen; ++i){
            c = text.charAt(i);
            if (Character.isLetter(c) && !((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')))
                return "Text must not contain non-english letters!";
        }

        HashSet<Integer> capitalIndexes = new HashSet<>();
        for (int i = 0; i < textLen; ++i){
            if (Character.isUpperCase(text.charAt(i))) capitalIndexes.add(i);
        }

        text = text.toUpperCase();
        key = key.toUpperCase();
        StringBuilder encrypted = new StringBuilder();
        char code;
        for (int i = 0; i < textLen; ++i){
            if (!Character.isLetter(text.charAt(i))) {
                code = text.charAt(i);
            } else {
                code = encodeLetter(text.charAt(i), key.charAt(i % keyLen));
                if (capitalIndexes.contains(i)) code = Character.toUpperCase(code);
            }
            encrypted.append(code);
        }

        return encrypted.toString();
    }

    @Override
    public String decryptText(String text, String key){
        if (key.isEmpty()) return "Key must contain at least one english letter!";
        key = key.toLowerCase();
        int textLen = text.length();
        int keyLen = key.length();
        char c;
        for (int i = 0; i < keyLen; ++i){
            c = key.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) return "Key must contain english letters only!";
        }

        for (int i = 0; i < textLen; ++i){
            c = text.charAt(i);
            if (Character.isLetter(c) && (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))))
                return "Text must not contain non-english letters!";
        }

        HashSet<Integer> capitalIndexes = new HashSet<>();
        for (int i = 0; i < textLen; ++i){
            if (Character.isUpperCase(text.charAt(i))) capitalIndexes.add(i);
        }

        text = text.toUpperCase();
        key = key.toUpperCase();
        StringBuilder encrypted = new StringBuilder();
        char code;
        for (int i = 0; i < textLen; ++i){
            if (!Character.isLetter(text.charAt(i))) {
                code = text.charAt(i);
            } else {
                code = decodeLetter(text.charAt(i), key.charAt(i % keyLen));
                if (capitalIndexes.contains(i)) code = Character.toUpperCase(code);
            }
            encrypted.append(code);
        }

        return encrypted.toString();
    }

    private char encodeLetter(char text, char key) {
        return (char) ((text + key) % alphabetLength + 'a');
    }

    private char decodeLetter(char code, char key) {
        char text;
        if (code >= key) {
            text = (char) ((code - key) % alphabetLength + 'a');
        } else {
            text = (char) ('z' + 1 + (code - key) % alphabetLength);
        }
        return text;
    }
}
