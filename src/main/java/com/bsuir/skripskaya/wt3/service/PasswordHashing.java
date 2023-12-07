package com.bsuir.skripskaya.wt3.service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHashing {
    public static String generatePasswordHash(String password) throws NoSuchAlgorithmException {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            StringBuilder hashFormer = new StringBuilder();
            byte[] hash = digest.digest(String.valueOf(password).getBytes());
            for (byte b : hash) {
               hashFormer.append(String.format("%02x", b));
            }
            String result = hashFormer.toString();
            return result;
    }
}
