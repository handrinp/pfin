package org.handrinp.pfin;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.File;

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

    @Test
    public void testEquals() {
        Account a = new Account("Test account");
        a.addTransaction(100.00, "test transaction 1", "2017-03-05 12:00:00.000");
        a.addTransaction(-50.00, "test transaction 2", "2017-03-05 12:10:00.000");

        Account b = new Account("Test account");
        b.addTransaction(100.00, "test transaction 1", "2017-03-05 12:00:00.000");
        b.addTransaction(-50.00, "test transaction 2", "2017-03-05 12:10:00.000");

        assertEquals(a, b);
    }

    @Test
    public void testJson() {
        Account a = new Account("Test account");
        a.addTransaction(100.00, "test transaction 1", "2017-03-05 12:00:00.000");
        a.addTransaction(-50.00, "test transaction 2", "2017-03-05 12:10:00.000");

        String json = a.toJson();
        String expected = "{\"name\":\"Test account\",\"balance\":50.0,\"transactions\"" +
                ":[{\"amount\":100.0,\"message\":\"test transaction 1\",\"time\":\"201" +
                "7-03-05 12:00:00.000\"},{\"amount\":-50.0,\"message\":\"test transact" +
                "ion 2\",\"time\":\"2017-03-05 12:10:00.000\"}]}";
        assertEquals(expected, json);

        Account b = Account.fromJson(json);
        assertEquals(b, a);
    }

    @Test
    public void testFileUtilities() {
        Account a = new Account("Test account");
        a.addTransaction(100.00, "test transaction 1", "2017-03-05 12:00:00.000");
        a.addTransaction(-50.00, "test transaction 2", "2017-03-05 12:10:00.000");

        assertEquals("test-account.json", a.getFileName());
        assertEquals(true, a.save());
        assertEquals(a, Account.load("Test account"));
        assertEquals(true, new File(a.getFileName()).delete());
    }
}

