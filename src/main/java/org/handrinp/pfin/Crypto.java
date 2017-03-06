package org.handrinp.pfin;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class Crypto {
    private static final int iterations = 20000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;

    public static String getSaltedHash(String password) {
        String saltedHash;

        try {
            byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
            saltedHash = Base64.encodeBase64String(salt) + "$" + hash(password, salt);
        } catch (Exception e) {
            saltedHash = null;
        }

        return saltedHash;
    }

    public static boolean check(String password, String stored) {
        String[] saltAndPass = stored.split("\\$");
        boolean result;

        if (saltAndPass.length != 2) {
            result = false;
        } else {
            String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
            result = hashOfInput.equals(saltAndPass[1]);
        }

        return result;
    }

    private static String hash(String password, byte[] salt) {
        String hash;

        if (password == null || password.length() == 0) {
            hash = null;
        } else {
            try {
                SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen));
                hash = Base64.encodeBase64String(key.getEncoded());
            } catch (Exception e) {
                hash = null;
            }
        }

        return hash;
    }
}

