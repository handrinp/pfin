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

    @Test
    public void testTransactions() {
        Account a = new Account("Test account");
        a.addTransaction(100.00, "test transaction 1", "2017-03-05 12:00:00.000");
        a.addTransaction(-50.00, "test transaction 2", "2017-03-05 12:10:00.000");
        a.addTransaction(-50.00, "test transaction 3", "2017-03-05 12:20:00.000");

        assertEquals(3, a.getTransactionCount());
        assertEquals(100.00, a.getTransaction(0).getAmount(), Constants.DELTA);
        assertEquals("test transaction 2", a.getTransaction(1).getMessage());
        assertEquals("2017-03-05 12:20:00.000", a.getTransaction(2).getTime());
    }
}

