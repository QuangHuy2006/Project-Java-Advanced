package com.restaurant.utils;
import org.mindrot.jbcrypt.BCrypt;


public class PasswordHasher {
    private PasswordHasher() {
    }

    public static String hash(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }
    public static boolean verify(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
    public static void main(String[] args){
        String pass = hash("Quanghuy@@12345");
        System.out.println(pass);
    }
}