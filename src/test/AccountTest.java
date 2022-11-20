package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import code.Businnes_Logic.Account;
import code.Businnes_Logic.Euro;

public class AccountTest {

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testCredit(int amount) {
        Account accountBase = new Account(1,1, new Euro(0), new Euro(0));
        accountBase.credit(new Euro(amount));
        var amountInCent = amount*100;
        assertEquals(amountInCent, accountBase.getTotalBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testDebitAvailableBalance(int amount) {
        Account accountBase = new Account(1,1, new Euro(amount), new Euro(0));
        accountBase.debit(new Euro(amount));
        assertEquals(0, accountBase.getAvailableBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testDebitTotalBalance(int amount) {
        Account accountBase = new Account(1,1, new Euro(0), new Euro(amount));
        accountBase.debit(new Euro(amount));
        assertEquals(0, accountBase.getTotalBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000})
    void testGetAccountNumber(int accNumber) {
        Account accountBase = new Account(accNumber,1, new Euro(0), new Euro(0));
        assertEquals(accNumber, accountBase.getAccountNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000})
    void testGetAvailableBalance(int amount) {
        Account accountBase = new Account(1,1, new Euro(amount), new Euro(0));
        var amountInCent = amount*100;
        assertEquals(amountInCent, accountBase.getAvailableBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000})
    void testGetTotalBalance(int amount) {
        Account accountBase = new Account(1,1, new Euro(0), new Euro(amount));
        var amountInCent = amount*100;
        assertEquals(amountInCent, accountBase.getTotalBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 123087, 1123550, 189670})
    void testValidatePIN(int pin) {
        Account accountBase = new Account(1, pin, new Euro(0), new Euro(0));
        assertTrue(accountBase.validatePIN(pin));
    }

    @ParameterizedTest
    @ValueSource(ints = { 123087, 1123550, 189670})
    void testFailValidatePIN(int wrongPin) {
        Account accountBase = new Account(1, 00000, new Euro(0), new Euro(0));
        assertFalse(accountBase.validatePIN(wrongPin));
    }
}
