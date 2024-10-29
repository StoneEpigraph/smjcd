package cn.sm.crypto.SM234.sample;


import cn.sm.crypto.SM234.SM2Helper;
import cn.sm.crypto.SM234.SM2KeyHelper;
import cn.sm.crypto.SM234.SM2KeyPair;
import cn.sm.crypto.SM234.UtilSM;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import sun.misc.BASE64Encoder;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * SM2加解密
 *
 * @author AARON
 */
public class TestSM2 {
    public static void main(String[] args) throws Exception {

        // 获取密钥对
        SM2KeyPair sm2KeyPair = SM2KeyHelper.generateKeyPair();
        byte[] publicKey = sm2KeyPair.getPublicKey();
        byte[] privateKey = sm2KeyPair.getPrivateKey();
        //
        System.out.println("========== SM2未压缩公钥 Start==========");
        // 椭圆曲线公钥的点坐标
        System.out.println("X: \n" + Hex.toHexString(sm2KeyPair.getPublicKeyX()));
        System.out.println("Y: \n" + Hex.toHexString(sm2KeyPair.getPublicKeyY()));
        // 将其表示为SM2未压缩的公钥为
        System.out.println("SM2 public key: \n"
                + "04"
                + Hex.toHexString(sm2KeyPair.getPublicKeyX())
                + Hex.toHexString(sm2KeyPair.getPublicKeyY())
        );
        System.out.println("========== SM2未压缩公钥 End==========");

        System.out.println("Public key: \n" + Hex.toHexString(publicKey));
        System.out.println("Private key: \n" + Hex.toHexString(privateKey));

        System.out.println(">> 公钥BASE64: " + org.bouncycastle.util.encoders.Base64.toBase64String(publicKey));
        System.out.println(">> 私钥BASE64: " + org.bouncycastle.util.encoders.Base64.toBase64String(privateKey));

        String privateKeyStr = UtilSM.byteToHex(privateKey); //"4490bb855c8b2617341c0f6aa58fd668d973791ee704551afcc3b05fb1f41857";
        String text = "测试";
        String publicKeyStr = UtilSM.byteToHex(publicKey); //"041b9b43a15dc46a57641a26c21ad88ee3bf29c318433d8fbe8fff36e831b6f4cb6724b7a207f1f6975d2dbd3e2a9a238fe79e0f448fae5a01bd264c69bd475205";

        try {
            System.out.println("========== SM2加解密 Start==========");
            //		String publicKeyStr = "04A98C9347AE0D9079F40439045F43900F69C0DDD63F94B8DC5746258DE4310A2ADC6DEC02541DA61DF6410FF71F9F4FC42DB5B33A5F37A69A39B1A87A14C2D638";
            String result = SM2Helper.encrypt(publicKeyStr, text);
            System.out.println("Public key byteToHex : \n" + publicKeyStr);
            System.out.println(result);
            System.out.println(Base64.getEncoder().encodeToString(result.getBytes()));

//		String privateKeyStr = "00D30A61C784864B27D729274400E57147E6E4CB8088DF4C8541B2082EF44354B6";
            String decryptresult = SM2Helper.decrypt(privateKeyStr, result);
            System.out.println("Private key byteToHex : \n" + privateKeyStr);
            System.out.println(decryptresult);
            System.out.println("========== SM2加解密 End==========");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("========== SM2验证签名 Start==========");
            String signStr = SM2Helper.sign(privateKeyStr, text);
            System.out.println("签名字符串" + signStr);
            if (SM2Helper.verifySign(publicKeyStr, signStr, text)) {
                System.out.println("验签正确");
            } else {
                System.out.println("验签错误");
            }
            System.out.println("========== SM2验证签名 End==========");
        } catch (Exception e) {
            e.printStackTrace();
        }

		System.out.println("----------生成秘钥对start----------------");
		// 引入BC库
		Security.addProvider(new BouncyCastleProvider());
		// 获取SM2椭圆曲线的参数
		final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
		// 获取一个椭圆曲线类型的密钥对生成器
		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
		// 使用SM2参数初始化生成器
		kpg.initialize(sm2Spec);
		// 使用SM2的算法区域初始化密钥生成器
		kpg.initialize(sm2Spec, new SecureRandom());
		// 获取密钥对
		KeyPair keyPair = kpg.generateKeyPair();
		PublicKey pk = keyPair.getPublic();
		PrivateKey privk= keyPair.getPrivate();
		System.out.println("公钥串："+keyPair.getPublic().getEncoded());
		System.out.println("公钥Base64串："+new BASE64Encoder().encode(keyPair.getPublic().getEncoded()));
		System.out.println("私钥串："+keyPair.getPrivate().getEncoded());
		System.out.println("私钥Base64串："+new BASE64Encoder().encode(keyPair.getPrivate().getEncoded()));
		System.out.println("公钥对象："+pk);
		System.out.println("----------生成秘钥对end----------------");

        String inputStr = "admin√√√nJ0wr3ovOSeQ√√√1637894609379";
        try {
            System.out.println("=========SM2加解密，加密结果为Base64串 Start==========");
            String base64Str = SM2Helper.encryptBase64Str(publicKeyStr, inputStr);
            System.out.println("加密base64串：" + base64Str);
            System.out.println(SM2Helper.decryptByBase64Str(privateKeyStr, base64Str));

            System.out.println("base64Str2解密：" + SM2Helper.decryptByBase64Str(privateKeyStr, base64Str));
            System.out.println("=========SM2加解密，加密结果为Base64串 End==========");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("=========SM2加解密，加密结果为十六进制串 Start==========");
            String byteStr = SM2Helper.encrypt(publicKeyStr, inputStr);
            System.out.println("加密串：" + byteStr);

            String deStr = SM2Helper.decrypt(privateKeyStr, byteStr);
            System.out.println("解密串：" + deStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
