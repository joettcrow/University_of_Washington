package cp120.assignments.assignment005;

import org.junit.Test;

import static org.junit.Assert.*;

/** This is the test class for the Crypto methods
 * @author jcrowley
 */
public class CryptoTest {

    @Test
    public void encryptTestOne() {
        String encodedWord = "em\u0002Vuzoyz";
        String word = "Squirrel";
        assertEquals(encodedWord, Crypto.encrypt(word));
    }

    @Test
    public void decryptTestOne() {
        String encodedWord = "em\u0002Vuzoyz";
        String word = "Squirrel";
        assertEquals(Crypto.decrypt(encodedWord), word);
    }

    @Test
    public void encryptTestTwo() {
        String encodedWord = "A\u0001\u0002";
        String word = "A";
        assertEquals(encodedWord, Crypto.encrypt(word));
    }

    @Test
    public void decryptTestTwo() {
        String encodedWord = "A\u0001\u0002";
        String word = "A";
        assertEquals(Crypto.decrypt(encodedWord), word);
    }

    @Test
    public void encryptTestThree() {
        String encodedWord = "\u0000\u0001\u000276;";
        String word = "426";
        assertEquals(encodedWord, Crypto.encrypt(word));
    }

    @Test
    public void decryptTestThree() {
        String encodedWord = "\u0000\u0001\u000276;";
        String word = "426";
        assertEquals(Crypto.decrypt(encodedWord), word);
    }
}