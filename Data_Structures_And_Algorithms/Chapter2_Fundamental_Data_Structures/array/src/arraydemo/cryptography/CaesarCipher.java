package arraydemo.cryptography;

/**
 * Array data structure can be used for cryptographic operations.
 * Following is the very basic example of using array as a cryptographic operation
 *
 */
/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class CaesarCipher {
    protected char[] encoder = new char[26];
    protected char[] decoder = new char[26];

    public CaesarCipher(int rotation) {
        for (int k = 0; k < 26 ; k++){
            encoder[k] = (char) ('A' + (rotation + k) % 26);
            decoder[k] = (char) ('A' + (k - rotation + 26) % 26);
        }
    }

    public String encrypt(String message){
        return transform(message,encoder);
    }

    public String decrypt(String secret){
        return transform(secret,decoder);
    }

    private String transform(String original, char[] key){
        char[] msg = original.toCharArray();
        for (int k = 0; k < msg.length ; k++) {
            if (Character.isUpperCase(msg[k])) {
                int j = msg[k] - 'A';
                msg[k] = key[j];
            }
        }
        return new String(msg);
    }

    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher(3);
        System.out.println("Encryption Key: " + new String(cipher.encoder));
        System.out.println("Decryption Key: " + new String(cipher.decoder));

        String plainText = "HIKMAT SINGH DHAMEE";
        System.out.println("Plain Text: " + plainText + " Cipher Text: " + cipher.encrypt(plainText));
        System.out.println("Cipher Text: " + cipher.encrypt(plainText) + " Plain Text: " + cipher.decrypt(cipher.encrypt(plainText)));
    }
}
