package com.huipengpay.sdk.test;

import com.huipengpay.sdk.core.AES;
import org.testng.annotations.Test;

public class AesTest {

    @Test
    public void testEncrypt() throws Exception {
        String key = "1rJTjxpz+L7oWOLYUeDCdg==";
        String content = "中文test";

        String encrypted = AES.encrypt(key, content);
        System.out.println("encrypted after:=>" + encrypted);
        System.out.println("source:=>" + AES.decrypt(key, encrypted));
    }
}
