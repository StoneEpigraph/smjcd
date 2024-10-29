package cn.sm.crypto.SM234;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.crypto.signers.StandardDSAEncoding;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

/**
 * 国密sm2非对称加解密算法帮助类
 */
public class SM2Helper {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 公钥加密
     *
     * @param input                 待加密数据
     * @param ecPublicKeyParameters 公钥参数
     * @param mode                  加密方式
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] input, ECPublicKeyParameters ecPublicKeyParameters, SM2Engine.Mode mode) throws Exception {
        SM2Engine engine = new SM2Engine(mode);
        ParametersWithRandom parametersWithRandom = new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom());
        engine.init(true, parametersWithRandom);
        return engine.processBlock(input, 0, input.length);
    }

    public static String encrypt(String publicKey, String Source) throws Exception {
        ECPublicKeyParameters ecPublicKeyParameters = SM2KeyHelper.buildECPublicKeyParameters(publicKey);
        byte[] encryptRet = SM2Helper.encrypt(Source.getBytes(Charset.forName("utf-8")), ecPublicKeyParameters, SM2Engine.Mode.C1C3C2);
        return UtilSM.byteToHex(encryptRet);
    }

    public static String encryptBase64Str(String publicKey, String Source) throws Exception {
        ECPublicKeyParameters ecPublicKeyParameters = SM2KeyHelper.buildECPublicKeyParameters(publicKey);
        byte[] encryptRet = SM2Helper.encrypt(Source.getBytes(Charset.forName("utf-8")), ecPublicKeyParameters, SM2Engine.Mode.C1C3C2);
        return Base64.getEncoder().encodeToString(encryptRet);
    }

    /**
     * 私钥解密
     *
     * @param input                  待解密数据
     * @param ecPrivateKeyParameters 私钥参数
     * @param mode                   加密方式
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] input, ECPrivateKeyParameters ecPrivateKeyParameters, SM2Engine.Mode mode) throws Exception {
        SM2Engine engine = new SM2Engine(mode);
        engine.init(false, ecPrivateKeyParameters);
        return engine.processBlock(input, 0, input.length);
    }

    public static String decrypt(String privateKey, byte[] input) throws Exception {
        ECPrivateKeyParameters ecPrivateKeyParameters = SM2KeyHelper.buildECPrivateKeyParameters(UtilSM.hexToByte(privateKey));
        byte[] decryptRet = SM2Helper.decrypt(input, ecPrivateKeyParameters, SM2Engine.Mode.C1C3C2);
        return new String(decryptRet);
    }

    /**
     * @param privateKey: 私钥
     * @param input:      解密的十六进制串
     * @Description 解密加密字节的十六进制字符串
     * @Author AARON
     * @Date 2021/11/24 11:26
     * @return: java.lang.String
     **/
    public static String decrypt(String privateKey, String input) throws Exception {
        ECPrivateKeyParameters ecPrivateKeyParameters = SM2KeyHelper.buildECPrivateKeyParameters(UtilSM.hexToByte(privateKey));
        if (!input.substring(0, 2).equals("04")) {
            input = "04" + input;
        }
        byte[] decryptRet = SM2Helper.decrypt(UtilSM.hexToByte(input), ecPrivateKeyParameters, SM2Engine.Mode.C1C3C2);
        return new String(decryptRet);
    }

    /**
     * @param privateKey:  私钥
     * @param base64Input: 待解密的base64串
     * @Description 解密base64串
     * @Author AARON
     * @Date 2021/11/24 11:16
     * @return: java.lang.String
     **/
    public static String decryptByBase64Str(String privateKey, String base64Input) throws Exception {
        ECPrivateKeyParameters ecPrivateKeyParameters = SM2KeyHelper.buildECPrivateKeyParameters(UtilSM.hexToByte(privateKey));
        byte[] inputByte = Base64.getDecoder().decode(base64Input);
        if (!UtilSM.byteToHex(inputByte).startsWith("04")) {
            inputByte = UtilSM.hexToByte("04" + UtilSM.byteToHex(inputByte));
        }
        byte[] decryptRet = SM2Helper.decrypt(inputByte, ecPrivateKeyParameters, SM2Engine.Mode.C1C3C2);

        return new String(decryptRet);
    }

    /**
     * 私钥签名
     *
     * @param input                  待签名数据
     * @param ecPrivateKeyParameters 私钥数据
     * @param ID                     用户标识
     * @return
     * @throws Exception
     */
    public static SM2SignResult sign(byte[] input, ECPrivateKeyParameters ecPrivateKeyParameters, byte[] ID) throws Exception {
        SM2Signer signer = new SM2Signer();
        CipherParameters param;
        if (ID != null && ID.length > 0) {
            param = new ParametersWithID(ecPrivateKeyParameters, ID);
        } else {
            param = ecPrivateKeyParameters;
        }
        signer.init(true, param);
        signer.update(input, 0, input.length);
        byte[] sign = signer.generateSignature();
        SM2SignResult SM2SignResult = new SM2SignResult();
        SM2SignResult.decodeStandardDSA(sign);
        return SM2SignResult;
    }

    public static String sign(String privateKey, String input) throws Exception {
        ECPrivateKeyParameters ecPrivateKeyParameters = SM2KeyHelper.buildECPrivateKeyParameters(UtilSM.hexToByte(privateKey));
        SM2SignResult sm2SignResult = sign(input.getBytes(StandardCharsets.UTF_8), ecPrivateKeyParameters, null);
        return UtilSM.byteToHex(sm2SignResult.encodePlainDSA());
    }

    /**
     * 公钥验证签名
     *
     * @param input                 原始数据
     * @param SM2SignResult         签名
     * @param ecPublicKeyParameters 公钥参数
     * @param ID                    用户标识
     * @return
     * @throws Exception
     */
    public static boolean verifySign(byte[] input, SM2SignResult SM2SignResult, ECPublicKeyParameters ecPublicKeyParameters, byte[] ID) throws Exception {
        BigInteger signR = new BigInteger(1, SM2SignResult.getSignR());
        BigInteger signS = new BigInteger(1, SM2SignResult.getSignS());
        byte[] sign = StandardDSAEncoding.INSTANCE.encode(SM2Constants.SM2_ECC_N, signR, signS);

        SM2Signer signer = new SM2Signer();
        CipherParameters param;
        if (ID != null && ID.length > 0) {
            param = new ParametersWithID(ecPublicKeyParameters, ID);
        } else {
            param = ecPublicKeyParameters;
        }
        signer.init(false, param);
        signer.update(input, 0, input.length);
        return signer.verifySignature(sign);
    }

    public static boolean verifySign(String publicKey, String signStr, String input) throws Exception {
        ECPublicKeyParameters ecPublicKeyParameters = SM2KeyHelper.buildECPublicKeyParameters(publicKey);
        SM2SignResult sm2SignResult = new SM2SignResult();
        sm2SignResult.decodePlainDSA(UtilSM.hexToByte(signStr));
        return verifySign(input.getBytes(Charset.forName("utf-8")), sm2SignResult, ecPublicKeyParameters, null);
    }
}
