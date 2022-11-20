package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import code.Businnes_Logic.Euro;
import code.Database.BankDatabase;

public class BankDatabaseTest {
    
    private BankDatabase bankDatabase;


    @BeforeEach
    public void setUp(){
        bankDatabase = new BankDatabase();
    }

    @Test
    void testAuthenticateUser() {
        assertTrue(bankDatabase.authenticateUser(12345, 54321));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testFailAuthenticateUser(int pin) {
        assertFalse(bankDatabase.authenticateUser(12345, pin));
    }


    @Test
    void testGetAvailableBalance() {
        var amountInCent = 1000*100;
        assertEquals(amountInCent, bankDatabase.getAvailableBalance(12345).getValore());
    }

    @Test
    void testGetTotalBalance() {
        var amountInCent = 1200*100;
        assertEquals(amountInCent, bankDatabase.getTotalBalance(12345).getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testCredit(int amount) {
        var amountInCent = amount*100;
        var total = bankDatabase.getTotalBalance(12345).getValore() + amountInCent;
        bankDatabase.credit(12345, new Euro(amount));
        assertEquals(total, bankDatabase.getTotalBalance(12345).getValore());
    }


    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testDebit(int amount) {
        var amountInCent = amount*100;
        var total = bankDatabase.getTotalBalance(12345).getValore() - amountInCent;
        bankDatabase.debit(12345, new Euro(amount));
        assertEquals(total, bankDatabase.getTotalBalance(12345).getValore());
    }


}
