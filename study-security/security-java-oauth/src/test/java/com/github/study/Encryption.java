package com.github.study;

import junit.framework.Assert;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.security.Key;

/**
 *
 **/
public class Encryption {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    public static void test4() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128); //设置key长度
        //生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        //加密
        String encrptText =
                aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 =
                new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(text, text2);
        System.out.println(encrptText);
    }

    public static void test3() {
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str).toString();
        String md5Salt = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
        System.out.println(md5);
        System.out.println(md5Salt);

        String sha1 = new Sha256Hash(str, salt).toString();
        System.out.println(sha1);
    }

    public static void test1() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String str2 = Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str, str2);
        System.out.println(base64Encoded);
    }

    public static void test2() {
        String str = "hello";
        String base64Encoded = Hex.encodeToString(str.getBytes());
        String str2 = new String(Hex.decode(base64Encoded.getBytes()));
        Assert.assertEquals(str, str2);
        System.out.println(base64Encoded);
    }
}
