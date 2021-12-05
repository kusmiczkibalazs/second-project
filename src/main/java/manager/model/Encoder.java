package manager.model;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class Encoder {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    private PublicKey publicKeyFromFile;
    private PrivateKey privateKeyFromFile;

    public Encoder() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
        createPublicFile();
        createPrivateFile();

        this.publicKeyFromFile = getPublicKeyFromFile();
        this.privateKeyFromFile = getPrivateKeyFromFile();
    }

    public String encrypt(String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKeyFromFile);
        byte[] secretMessageBytes = secret.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        String encoded = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        return encoded;
    }

    public String decode(String encoded) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKeyFromFile);
        byte[] encryptedMessageBytes = Base64.getDecoder().decode(encoded);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        return decryptedMessage;
    }

    private void createPublicFile() {
        String file = System.getProperty("user.home") + File.separator + ".public.key";
        if (Files.notExists(Path.of(file))) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(publicKey.getEncoded());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createPrivateFile() {
        String file = System.getProperty("user.home") + File.separator + ".private.key";
        if (Files.notExists(Path.of(file))) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(privateKey.getEncoded());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PublicKey getPublicKeyFromFile() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        File publicKeyFile = new File(System.getProperty("user.home") + File.separator + ".public.key");
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return publicKey;
    }

    private PrivateKey getPrivateKeyFromFile() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        File privateKeyFile = new File(System.getProperty("user.home") + File.separator + ".private.key");
        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }

}
