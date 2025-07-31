package com.webchipherapp.demo.Model;

import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
public class CaesarCipher implements CipherInterface {
    @Override
    public String encryptText(String text, String key) {
        if (key.isEmpty()) return "Key must contain at least one digit!";

        int textLen = text.length();
        int keyLen = key.length();
        char c;
        for (int i = 0; i < keyLen; ++i) {
            c = key.charAt(i);
            if (!Character.isDigit(c)) return "Key must contain only digits!";
        }
        int keyInt = Integer.parseInt(key);

        HashSet<Integer> capitalIndexes = new HashSet<>();
        for (int i = 0; i < textLen; ++i) {
            if (Character.isUpperCase(text.charAt(i))) capitalIndexes.add(i);
        }

        text = text.toLowerCase().replace('ё', 'е');
        StringBuilder encrypted = new StringBuilder();
        char code;
        for (int i = 0; i < textLen; ++i) {
            c = text.charAt(i);
            if (!Character.isLetter(c)) {
                code = c;
            } else {
                if (c >= 'a' && c <= 'z') code = encodeEnglishLetter(c, keyInt);
                else if (c >= 'а' && c <= 'я') code = encodeRussianLetter(c, keyInt);
                else code = c;
                if (capitalIndexes.contains(i)) code = Character.toUpperCase(code);
            }
            encrypted.append(code);
        }
        return encrypted.toString();
    }

    @Override
    public String decryptText(String text, String key) {
        if (key.isEmpty()) return "Key must contain at least one digit!";

        int textLen = text.length();
        int keyLen = key.length();
        char c;
        for (int i = 0; i < keyLen; ++i) {
            c = key.charAt(i);
            if (!Character.isDigit(c)) return "Key must contain only digits!";
        }
        int keyInt = Integer.parseInt(key);

        HashSet<Integer> capitalIndexes = new HashSet<>();
        for (int i = 0; i < textLen; ++i) {
            if (Character.isUpperCase(text.charAt(i))) capitalIndexes.add(i);
        }

        text = text.toLowerCase().replace('ё', 'е');
        StringBuilder decrypted = new StringBuilder();
        char code;
        for (int i = 0; i < textLen; ++i) {
            c = text.charAt(i);
            if (!Character.isLetter(c)) {
                code = c;
            } else {
                if (c >= 'a' && c <= 'z') code = decodeEnglishLetter(c, keyInt);
                else if (c >= 'а' && c <= 'я') code = decodeRussianLetter(c, keyInt);
                else code = c;
                if (capitalIndexes.contains(i)) code = Character.toUpperCase(code);
            }
            decrypted.append(code);
        }
        return decrypted.toString();
    }

    private char encodeEnglishLetter(char letter, int key) {
        return (char) ((letter - 'a' + key) % 26 + 'a');
    }

    private char encodeRussianLetter(char letter, int key) {
        return (char) ((letter - 'а' + key) % 32 + 'а'); // используется А из кириллицы
    }

    private char decodeEnglishLetter(char letter, int key) {
        if (letter - 'a' - key < 0) {
            int shift = Math.abs(letter - 'a' - key) % 26;
            if (shift == 0) return 'a';
            return (char) (1 + 'z' - shift);
        } else
            return (char) ((letter - 'a' - key) % 26 + 'a');
    }

    private char decodeRussianLetter(char letter, int key) {
        if (letter - 'а' - key < 0) { // используется А из кириллицы
            int shift = Math.abs(letter - 'а' - key) % 32;
            if (shift == 0) return 'а';
            return (char) (1 + 'я' - shift);
        } else
            return (char) ((letter - 'а' - key) % 32 + 'а');
    }
}
