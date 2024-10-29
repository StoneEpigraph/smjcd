package cn.sm.crypto.SM234.sample;


import cn.sm.crypto.SM234.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

/**
 * SM2加解密
 *
 * @author AARON
 */
public class TestSM3 {
    public static void main(String[] args) throws Exception {

        byte[] md = new byte[32];
        byte[] msg1 = "张三12312312312".getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        System.out.println(s.toUpperCase());
        // C2C898F5FEFE794528E45CC946E383190C9C4E9C686CE41BA1056E05B246261A
    }
}
