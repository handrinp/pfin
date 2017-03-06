package org.handrinp.pfin;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CryptoTest {
    @Test
    public void testCrypto() {
        String password = "superSecretPassword";
        String saltedHash = Crypto.getSaltedHash(password);
        assertEquals(true, Crypto.check(password, saltedHash));
    }
}

