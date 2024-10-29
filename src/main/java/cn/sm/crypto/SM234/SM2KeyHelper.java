package cn.sm.crypto.SM234;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 参数构建帮助类
 */
public class SM2KeyHelper {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 生成公私钥
     *
     * @return
     */
    public static SM2KeyPair generateKeyPair() {
        SecureRandom random = new SecureRandom();
        ECKeyGenerationParameters keyGenerationParams = new ECKeyGenerationParameters(SM2Constants.DOMAIN_PARAMS,
                random);
        ECKeyPairGenerator keyGen = new ECKeyPairGenerator();
        keyGen.init(keyGenerationParams);
        AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();
        ECPublicKeyParameters ecPublicKeyParameters = (ECPublicKeyParameters) keyPair.getPublic();
        ECPrivateKeyParameters ecPrivateKeyParameters = (ECPrivateKeyParameters) keyPair.getPrivate();
        return new SM2KeyPair(ecPublicKeyParameters.getQ().getEncoded(false), ecPublicKeyParameters.getQ().getAffineXCoord().getEncoded(), ecPublicKeyParameters.getQ().getAffineYCoord().getEncoded(), ecPrivateKeyParameters.getD().toByteArray());
    }

    /**
     * 构建公钥参数
     *
     * @param sm2KeyPair
     * @return
     */
    public static ECPublicKeyParameters buildECPublicKeyParameters(SM2KeyPair sm2KeyPair) {
        return buildECPublicKeyParameters(sm2KeyPair.getPublicKeyX(), sm2KeyPair.getPublicKeyY());
    }

    /**
     * 构建公钥参数
     *
     * @param publicKey
     * @return
     */
    public static ECPublicKeyParameters buildECPublicKeyParameters(String publicKey) {
        String publicKeyX = publicKey.substring(2, (publicKey.length() / 2) + 1);
        String publicKeyY = publicKey.substring((publicKey.length() / 2) + 1);
        return buildECPublicKeyParameters(hexStringToBytes(publicKeyX), hexStringToBytes(publicKeyY));
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 构建公钥参数
     *
     * @param publicKeyX
     * @param publicKeyY
     * @return
     */
    public static ECPublicKeyParameters buildECPublicKeyParameters(byte[] publicKeyX, byte[] publicKeyY) {
        ECPoint pointQ = SM2Constants.CURVE.createPoint(new BigInteger(1, publicKeyX), new BigInteger(1, publicKeyY));
        return new ECPublicKeyParameters(pointQ, SM2Constants.DOMAIN_PARAMS);
    }

    /**
     * 构建私钥参数
     *
     * @param privateKey
     * @return
     */
    public static ECPrivateKeyParameters buildECPrivateKeyParameters(byte[] privateKey) {
        BigInteger d = new BigInteger(1, privateKey);
        return new ECPrivateKeyParameters(d, SM2Constants.DOMAIN_PARAMS);
    }
}
