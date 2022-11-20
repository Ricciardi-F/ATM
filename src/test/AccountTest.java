package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import code.Businnes_Logic.Euro;
import code.Database.Account;

public class AccountTest {

    private Account accountBase;

    @BeforeEach
    public void setUp(){
        accountBase = new Account(11111,11111,new Euro (0), new Euro(0));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testCredit(int amount) {
        accountBase.credit(new Euro(amount));
        var amountInCent = amount*100;
        assertEquals(amountInCent, accountBase.getTotalBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testDebitAvailableBalance(int amount) {
        accountBase.debit(new Euro(amount));
        var NegativeAmountInCent = -amount*100; // !!! negativo in quanto effettuiamo un debito sul conto che parte con 0 euro !!!
        assertEquals(NegativeAmountInCent, accountBase.getAvailableBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000, -10})
    void testDebitTotalBalance(int amount) {
        accountBase.credit(new Euro(amount));
        accountBase.debit(new Euro(amount));
        assertEquals(0, accountBase.getTotalBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 234234, 13256, 16421, 187879})
    void testGetAccountNumber(int accNumber) {
        Account account = new Account(accNumber,1, new Euro(0), new Euro(0));
        assertEquals(accNumber, account.getAccountNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000})
    void testGetAvailableBalance(int amount) {
        Account account = new Account(1111,1, new Euro(amount), new Euro(0));
        var amountInCent = amount*100;
        assertEquals(amountInCent, account.getAvailableBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100, 1000})
    void testGetTotalBalance(int amount) {
        accountBase.credit(new Euro(amount));
        var amountInCent = amount*100;
        assertEquals(amountInCent, accountBase.getTotalBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(ints = { 123087, 1123550, 189670})
    void testValidatePIN(int pin) {
        Account account = new Account(1, pin, new Euro(0), new Euro(0));
        assertTrue(account.validatePIN(pin));
    }

    @ParameterizedTest
    @ValueSource(ints = { 123087, 1123550, 189670})
    void testFailValidatePIN(int wrongPin) {
        Account account = new Account(1, 00000, new Euro(0), new Euro(0));
        assertFalse(account.validatePIN(wrongPin));
    }
}
