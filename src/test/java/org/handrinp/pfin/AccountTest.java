package org.handrinp.pfin;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AccountTest {
    @Test
    public void testBoilerplate() {
        Account a = new Account("Test account");
        assertEquals("Test account", a.getName());
        assertEquals(0.0, a.getBalance(), Constants.DELTA);
    }
}

