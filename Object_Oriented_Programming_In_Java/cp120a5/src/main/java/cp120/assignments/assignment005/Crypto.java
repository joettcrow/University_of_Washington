package cp120.assignments.assignment005;

/**
 * Cryptography class for encoding and decoding strings
 * @author jcrowley
 */
public class Crypto {
    /**
     * This is the encryption class, when called it encrypts the string
     * @param str String value to encrypt
     * @return The encrypted string value
     */
    public static String encrypt( String str ) {
        String paddedStr = pad(str);
        String swappedString = swap(paddedStr);
        return increment(swappedString);
    }

    /**
     * This is a helper method class for the encryption class
     * It pads the provided string with between 1 and 3 null values
     * Always pads to make the new string a multiple of 3
     * @param str The string to be padded
     * @return The padded string
     */
    private static String pad(String str){
        String paddedStr;
        if (str.length() % 3 == 0){
            paddedStr = str + '\u0000' + '\u0000' + '\u0000';
        }
        else if (str.length() % 3 == 1){
            paddedStr = str + '\u0000' + '\u0000';
        }
        else {
            paddedStr = str + '\u0000';
        }
        return paddedStr;
    }

    /**
     * This is a helper method class for the encryption class
     * It swaps the string, moving each chunk of 3 forward one
     * and places the first chunk at the end
     * @param str The string to be swapped
     * @return The swapped string
     */
    private static String swap(String str){
        StringBuilder swappedString = new StringBuilder();
        swappedString.append(str.substring(str.length()-3));

        for (int i = 0; i < str.length()-3; i+= 3) {
            swappedString.append(str.substring(i, i+3));

        }
        return swappedString.toString();
    }

    /**
     * This is a helper method class for the encryption class
     * It increments the string, increasing each character by the location value
     * of the position.
     * @param str The string to be incremented
     * @return The incremented string
     */
    private static String increment(String str){
        StringBuilder incrementedStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            x+=i;
            incrementedStr.append(String.valueOf(x));
        }

        return incrementedStr.toString();
    }

    /**
     * This is the decryption class, when called it decrypts the string
     * @param str String value to decrypt
     * @return The decrypted string value
     */
    public static String decrypt(String str){
        String decreasedString = decrease(str);
        String swapedBackString = swapBack(decreasedString);
        return trim(swapedBackString);
    }

    /**
     * This is a helper method for decryption, it undoes what the increase method did.
     * @param str the string to be decreased
     * @return the decreased string
     */
    private static String decrease(String str){
        StringBuilder decrementedStr = new StringBuilder();
        for (int i = 0; i < str.length() ; i++) {
            char x = str.charAt(i);
            x-=i;
            decrementedStr.append(String.valueOf(x));
        }
        return decrementedStr.toString();
    }

    /**
     * This is a helper method for decryption, it undoes what the swap method did.
     * @param str the string to be swapped back
     * @return the swapped back string
     */
    private static String swapBack(String str){
        String heldString = str.substring(0,3);
        StringBuilder swapper = new StringBuilder();

        for (int i = 3; i < str.length(); i+= 3) {
            swapper.append(str.substring(i,i+3));
        }
        swapper.append(heldString);
        return swapper.toString();

    }

    /**
     * This is a helper method for decryption, it undoes what the pad method did.
     * @param str the string to be trimmed
     * @return the trimmed string
     */
    private static String trim(String str){
        String trimmedString = str;
        do {
            trimmedString=trimmedString.substring(0,trimmedString.length()-1);
        } while (trimmedString.endsWith("\u0000"));
        return trimmedString;
    }

}

