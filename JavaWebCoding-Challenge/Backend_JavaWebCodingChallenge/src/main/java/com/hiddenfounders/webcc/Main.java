package com.hiddenfounders.webcc;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.util.regex.Pattern;


/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 12:36 AM
 * @package com.hiddenfounders.webcc
 */


public class Main {





    public static void main( String args[] ) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
/*

        List<Status> shopListLike = new ArrayList<>();
        Status status = new Status.StatusBuilder()
                .setStatus(Constants.STATUS.LIKE)
                .setPassedTime(new Date())
                .setIdShop("5a0c6711fb3aac66aafe26c6")
                .build();
        shopListLike.add(status);

        status = new Status.StatusBuilder()
                .setStatus(Constants.STATUS.LIKE)
                .setPassedTime(new Date())
                .setIdShop("5a0c6711fb3aac66aafe26cc")
                .build();

        shopListLike.add(status);

        User persist = new User.UserBuilder()
                .setEmail("a@a.com")
                .setPassword("123456")
                .setShopdisliked(new ArrayList<>())
                .setShopLiked(new ArrayList<>() )
                .build();

        System.out.println(persist.toString());
        //System.out.println(Arrays.toString(shopListLike.toArray()));


        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        MongoCredential credential;

        credential = MongoCredential.createCredential("mohamed", "shops", "".toCharArray());
        //credential = MongoCredential.createCredential("sampleUser", "myDb",
         //       "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("shops");
        System.out.println("Credentials ::"+ credential);

        MongoCollection<Document> collection = database.getCollection("shops");
        System.out.println("Collection myCollection selected successfully ");

*/

        SecretKeySpec key;
        Cipher cipher;
        int size = 128;
        Charset CHARSET = Charset.forName("UTF-8");

        String secret = "this-is-my-secret-key-02811da22377d62fcfdb02f29aad77d9e";
        String ok = "U2FsdGVkX1/S2zetsz5b1W0kuQFQDuDEkTABecv7UkbBekaazQ4mKymGdCR0ckgA\n";


        SecretKey secKey = null;
        try {
            secKey = getSecretEncryptionKey();
            String decrypted = decryptText(ok.getBytes(), secKey);

            System.out.println(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public static SecretKey getSecretEncryptionKey() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return secKey;
    }


    /**
     * Decrypts encrypted byte array using the key used for encryption.
     * @param byteCipherText
     * @param secKey
     * @return
     * @throws Exception
     */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }

    /**
     * Convert a binary byte array into readable hex form
     * @param hash
     * @return
     */
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}
