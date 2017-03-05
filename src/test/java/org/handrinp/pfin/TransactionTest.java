package org.handrinp.pfin;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TransactionTest {
    @Test
    public void testBoilerplate() {
        Transaction t = new Transaction(100.00, "Test transaction 1", "2017-03-05 12:30:00.000");
        assertEquals(100.00, t.getAmount(), Constants.DELTA);
        assertEquals("Test transaction 1", t.getMessage());
        assertEquals("2017-03-05 12:30:00.000", t.getTime());

        Transaction t2 = new Transaction(100.00, "Test transaction 1", "2017-03-05 12:30:00.000");
        assertEquals(t2, t);
    }
}

