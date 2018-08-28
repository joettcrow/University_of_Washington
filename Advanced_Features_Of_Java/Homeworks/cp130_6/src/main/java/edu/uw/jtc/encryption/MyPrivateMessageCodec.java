package edu.uw.jtc.encryption;

import edu.uw.ext.framework.crypto.PrivateMessageCodec;
import edu.uw.ext.framework.crypto.PrivateMessageTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * @author jcrowley
 */
public class MyPrivateMessageCodec implements PrivateMessageCodec {

    private static final Logger log = LoggerFactory.getLogger(MyPrivateMessageCodec.class);
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String CERT_ENCODING = "JCEKS";
    private static final String SIGNATURE_ENCODING = "SHA256withRSA";
    private static final String DIRECTORY = "src" +
            File.separator +
            "main" +
            File.separator +
            "resources" +
            File.separator;

    /**
     * The encipher method needs to :
     * Generate a one-time use shared symmetric secret key
     * Encipher the the order data using the one-time use shared symmetric secret key
     * Obtain the bytes representing the one-time use shared symmetric secret key
     * Retrieve the (broker's) public key from the provided truststore
     * Encipher the shared symmetric secret key's bytes using the public key from the truststore
     * Retrieve the (client's) private key from the the provided keystore
     * Sign the plaintext order data using the private key from the the provided keystore
     * Construct and return a PrivateMessageTriple
     * containing the ciphertext, key bytes and signature
     * @param data the data to encrypt
     * @param privateKeyStoreName the name of the private Key store
     * @param privateKeyStorePassword the password for the client store
     * @param privateKeyName the signer's key name
     * @param privateKeyPassword the signer's password
     * @param certificateStoreName the name of the clients trusted store
     * @param certificateStorePassword the password for the clients trusted store
     * @param certificateName the name of the broker certificate
     * @return an encrypted message
     * @throws GeneralSecurityException if there's a security error
     * @throws IOException if the key file cannot be read
     */
    @Override
    public PrivateMessageTriple encipher(
            byte[] data,
            String privateKeyStoreName,
            char[] privateKeyStorePassword,
            String privateKeyName,
            char[] privateKeyPassword,
            String certificateStoreName,
            char[] certificateStorePassword,
            String certificateName
    ) throws GeneralSecurityException, IOException {
        PrivateMessageTriple privateMessageTriple;

        SecretKey secretKey = this.generateSymmetricKey();
        byte[] encryptedData = this.encipherData(secretKey,data);

        byte[] keyBytes = secretKey.getEncoded();
        PublicKey brokerPublicKey = this.getPublicKeyFromKeystore(
                certificateStoreName,
                certificateStorePassword,
                certificateName);
        byte[] encryptedSharedKey = this.encipherData(brokerPublicKey,keyBytes);

        PrivateKey clientPrivateKey = this.getPrivateKeyFromKeystore(
                privateKeyStoreName,
                privateKeyStorePassword,
                privateKeyName,
                privateKeyPassword);
        byte[] signature = this.signOrderData(clientPrivateKey, data);

        privateMessageTriple = new PrivateMessageTriple(encryptedSharedKey,encryptedData,signature);
        return privateMessageTriple;
    }

    /**
     * private method, generates the symmetric key to use
     * @return a secret key
     * @throws GeneralSecurityException if the encryption does not exist
     */
    private SecretKey generateSymmetricKey() throws GeneralSecurityException {
        SecretKey secKey;
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
            generator.init(128);
            secKey = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            log.warn(ENCRYPTION_ALGORITHM + " algorithm doesn't exist.  That's not good...", e);
            throw new GeneralSecurityException(e);
        }
        return secKey;
    }

    /**
     * private method to encipher the data
     * @param secretKey the secret key to use
     * @param data the data to encipher
     * @return encoded data
     * @throws GeneralSecurityException if this doesn't work
     */
    private byte[] encipherData(
            final Key secretKey,
            final byte[] data
    ) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * private method for retreiving the a given key from a store
     * @param storeName the name of the keystore
     * @param storePassword the password for the keystore
     * @param keyName the name of the key
     * @return a secret key
     * @throws IOException if file can't be read
     * @throws GeneralSecurityException if security ai'nt working
     */
    private PublicKey getPublicKeyFromKeystore(
            final String storeName,
            final char[] storePassword,
            final String keyName
            ) throws IOException, GeneralSecurityException {
        PublicKey publicKey = null;

        try{
            KeyStore keystore = loadKeyStore(DIRECTORY + storeName, CERT_ENCODING, storePassword);
            Certificate cert = keystore.getCertificate(keyName);
            publicKey = cert.getPublicKey();
        } catch (KeyStoreException e) {
            log.warn("KeyStoreError", e);
        } catch (FileNotFoundException e) {
            log.warn(e.getMessage() + "error", e);
            throw new IOException(e);
        }
        return publicKey;
    }

    /**
     * Helpter method to load keys
     * @param storeFile the file to load
     * @param storeType the type of store
     * @param storePassword the password for the store
     * @return a keystore
     * @throws KeyStoreException if there's a key exception
     * @throws NoSuchAlgorithmException if there's an algorithm exeption
     * @throws CertificateException if there's a cert exception
     * @throws IOException if there's an error reading the file
     */
    private static KeyStore loadKeyStore(
            String storeFile,
            String storeType,
            char[] storePassword
    ) throws KeyStoreException, NoSuchAlgorithmException,
            CertificateException, IOException
    {
            FileInputStream fIn = new FileInputStream(storeFile);
            KeyStore keystore = KeyStore.getInstance(storeType);
            keystore.load(fIn, storePassword);
            return keystore;
    }

    /**
     * private method for retreiving the a given key from a store
     * @param storeName the name of the keystore
     * @param storePassword the password for the keystore
     * @param keyName the name of the key
     * @return a secret key
     * @throws IOException if file can't be read
            * @throws GeneralSecurityException if security ai'nt working
            */
    private PrivateKey getPrivateKeyFromKeystore(
            final String storeName,
            final char[] storePassword,
            final String keyName,
            final char[] signerPassword
    ) throws IOException, GeneralSecurityException {
        KeyStore keystore;
        Key brokerKey;
        try{
            keystore = loadKeyStore(DIRECTORY + storeName, CERT_ENCODING, storePassword);
            brokerKey = keystore.getKey(keyName, signerPassword);
        } catch (NoSuchAlgorithmException e) {
            log.warn(e.getMessage() + "error", e);
            throw new GeneralSecurityException(e);
        }
        return (PrivateKey) brokerKey;
    }


    /**
     * Private method to sign the data
     * @param signerKey key to sign with
     * @param data data to be signed
     * @return a signed data array
     */
    private byte[] signOrderData(final Key signerKey, final byte[] data){
        byte[] signature = new byte[0];
        try {
            Signature signer = Signature.getInstance(SIGNATURE_ENCODING);
            signer.initSign((PrivateKey) signerKey);
            signer.update(data);
            signature = signer.sign();
        } catch (NoSuchAlgorithmException e) {
            log.warn(SIGNATURE_ENCODING + " Does not exist...", e);
        } catch (SignatureException e) {
            log.warn("Exception ocured during signing", e);
        } catch (InvalidKeyException e) {
            log.warn("Key is invalid", e);
        }
        return signature;
    }


    /**
     * The decipher method needs to (as before this doesn't all need to be done in the single
     * method):
     * Obtain the shared secret key, order data ciphertext and
     * signature from the provided PrivateMessageTriple
     * Retrieve the (brokers's) private key from the the provided keystore
     * Use the private key from the keystore to decipher the shared secret key's bytes
     * Reconstruct the shared secret key from shared secret key's bytes
     * Use the shared secret key to decipher the order data ciphertext
     * Retrieve the (client's) public key from the provided truststore
     * Verify the order data plaintext and signature using the public key from the truststore
     * Return the order data plaintext
     * @param privateMessageTriple The message to decipher
     * @param privateKeyStoreName the name of the private key store
     * @param privateKeyStorePassword the private key store's password
     * @param privateKeyName the name of the private key
     * @param privateKeyPassword the password for the private key
     * @param certificateStoreName the name of the certificate store
     * @param certificateStorePassword the password for the certificate store
     * @param certificateName the name of the certificate
     * @return deciphered data
     * @throws GeneralSecurityException if security is borked
     * @throws IOException if the file open is messed up
     */
    @Override
    public byte[] decipher(
            PrivateMessageTriple privateMessageTriple,
            String privateKeyStoreName,
            char[] privateKeyStorePassword,
            String privateKeyName,
            char[] privateKeyPassword,
            String certificateStoreName,
            char[] certificateStorePassword,
            String certificateName
    ) throws GeneralSecurityException, IOException {

        byte[] ciphertext = privateMessageTriple.getCiphertext();
        byte[] encipheredSharedKey = privateMessageTriple.getEncipheredSharedKey();
        byte[] signature = privateMessageTriple.getSignature();

        PrivateKey privateKey = this.getPrivateKeyFromKeystore(
                privateKeyStoreName,
                privateKeyStorePassword,
                privateKeyName,
                privateKeyPassword);

        byte[] symmetricKeyBytes = decipherData(privateKey,encipheredSharedKey);
        SecretKey originalSymettricKey = new SecretKeySpec(
                symmetricKeyBytes,
                0,
                symmetricKeyBytes.length,
                ENCRYPTION_ALGORITHM);
        byte[] decipheredText = decipherData(originalSymettricKey, ciphertext);

        PublicKey publicKeyFromKeystore = this.getPublicKeyFromKeystore(
                certificateStoreName,
                certificateStorePassword,
                certificateName);

        boolean signatureVerified = verifySignature(publicKeyFromKeystore,decipheredText,signature);
        if (! signatureVerified){
            decipheredText = null;
            log.info("The text was not signed correctly");
        }
        return decipheredText;
    }

    /**
     * private method to encipher the data
     * @param secretKey the secret key to use
     * @param encipheredData the data to encipher
     * @return encoded data
     * @throws GeneralSecurityException if this doesn't work
     */
    private byte[] decipherData(
            final Key secretKey,
            final byte[] encipheredData
    ) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encipheredData);
    }

    /**
     * Private method to sign the data
     * @param signerKey key to sign with
     * @param signature data to be signed
     * @return a signed data array
     */
    private boolean verifySignature(
            final PublicKey signerKey,
            final byte[] data,
            final byte[] signature){
        boolean valid = false;
        try {
            Signature verifier = Signature.getInstance(SIGNATURE_ENCODING);
            verifier.initVerify(signerKey);
            verifier.update(data);
            valid = verifier.verify(signature);
        } catch (NoSuchAlgorithmException e) {
            log.warn(SIGNATURE_ENCODING + " Does not exist...", e);
        } catch (SignatureException e) {
            log.warn("Exception ocured during signing", e);
        } catch (InvalidKeyException e) {
            log.warn("Key is invalid", e);
        }
        return valid;
    }
}
