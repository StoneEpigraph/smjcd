package cn.sm.crypto.SM234.sample;


import cn.sm.crypto.SM234.SM4EnDecryption;
import cn.sm.crypto.SM234.Util4Hex;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 国密SM4加解密调用示例代码
 *
 * @author 小帅丶
 */
public class TestSM4 {
    //明文数据
    protected static final byte[] SRC_DATA = "{\"condition\":[{\"dataCode\":\"jsonArray\",\"dataValue\":{\"user\":\"admin\",\"password\":\"admin123\"}}],\"sid\":\"dddddddd\"}".getBytes();

    public static void main(String[] args) throws Exception {
        System.out.println("明文16进制数据:" + Util4Hex.bytesToHexString(SRC_DATA));
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] sm4key = SM4EnDecryption.generateKey();
        System.out.println("SM4密钥:" + Util4Hex.bytesToHexString(sm4key));

        byte[] cipherText = null;
        byte[] decryptedData = null;
        /*********************ECB加解密*************************/
        System.out.println("开始进行ECB加密");
        cipherText = SM4EnDecryption.encrypt_Ecb_Padding(sm4key, SRC_DATA);
        System.out.println("SM4 ECB Padding 加密结果16进制:\n" + Util4Hex.bytesToHexString(cipherText));
        String cipherTextStr = encoder.encode(cipherText);
        System.out.println("SM4 ECB Padding 加密结果:\n" + cipherTextStr);
        decryptedData = SM4EnDecryption.decrypt_Ecb_Padding(sm4key, decoder.decodeBuffer(cipherTextStr));
        System.out.println("SM4 ECB Padding 解密结果:\n" + new String(decryptedData));
        /*********************CBC加解密*************************/
        System.out.println("开始进行CBC加密");
        byte[] iv = SM4EnDecryption.generateKey();
        System.out.println("iv偏移量密钥:" + Util4Hex.bytesToHexString(iv));

        cipherText = SM4EnDecryption.encrypt_Cbc_Padding(sm4key, iv, SRC_DATA);
        System.out.println("SM4 CBC Padding 加密结果16进制:\n" + Util4Hex.bytesToHexString(cipherText));
        System.out.println("SM4 CBC Padding 加密结果:\n" + encoder.encode(cipherText));
        decryptedData = SM4EnDecryption.decrypt_Cbc_Padding(sm4key, iv, cipherText);
        System.out.println("SM4 CBC Padding 解密结果:\n" + new String(decryptedData));
    }
}
